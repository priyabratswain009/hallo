package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.FunctionalityMaster;
import com.sunknowledge.dme.rcm.repository.FunctionalityMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.FunctionalityMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.FunctionalityMasterMapper;
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
 * Integration tests for the {@link FunctionalityMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FunctionalityMasterResourceIT {

    private static final String DEFAULT_FUNCTIONALITY_NO = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTIONALITY_NO = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCTIONALITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTIONALITY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCTIONALITY_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTIONALITY_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_FUNCTIONALITY_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_FUNCTIONALITY_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/functionality-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{functionalityId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FunctionalityMasterRepository functionalityMasterRepository;

    @Autowired
    private FunctionalityMasterMapper functionalityMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFunctionalityMasterMockMvc;

    private FunctionalityMaster functionalityMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FunctionalityMaster createEntity(EntityManager em) {
        FunctionalityMaster functionalityMaster = new FunctionalityMaster()
            .functionalityNo(DEFAULT_FUNCTIONALITY_NO)
            .functionalityName(DEFAULT_FUNCTIONALITY_NAME)
            .functionalityDescription(DEFAULT_FUNCTIONALITY_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .functionalityMasterUuid(DEFAULT_FUNCTIONALITY_MASTER_UUID);
        return functionalityMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FunctionalityMaster createUpdatedEntity(EntityManager em) {
        FunctionalityMaster functionalityMaster = new FunctionalityMaster()
            .functionalityNo(UPDATED_FUNCTIONALITY_NO)
            .functionalityName(UPDATED_FUNCTIONALITY_NAME)
            .functionalityDescription(UPDATED_FUNCTIONALITY_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .functionalityMasterUuid(UPDATED_FUNCTIONALITY_MASTER_UUID);
        return functionalityMaster;
    }

    @BeforeEach
    public void initTest() {
        functionalityMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createFunctionalityMaster() throws Exception {
        int databaseSizeBeforeCreate = functionalityMasterRepository.findAll().size();
        // Create the FunctionalityMaster
        FunctionalityMasterDTO functionalityMasterDTO = functionalityMasterMapper.toDto(functionalityMaster);
        restFunctionalityMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(functionalityMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the FunctionalityMaster in the database
        List<FunctionalityMaster> functionalityMasterList = functionalityMasterRepository.findAll();
        assertThat(functionalityMasterList).hasSize(databaseSizeBeforeCreate + 1);
        FunctionalityMaster testFunctionalityMaster = functionalityMasterList.get(functionalityMasterList.size() - 1);
        assertThat(testFunctionalityMaster.getFunctionalityNo()).isEqualTo(DEFAULT_FUNCTIONALITY_NO);
        assertThat(testFunctionalityMaster.getFunctionalityName()).isEqualTo(DEFAULT_FUNCTIONALITY_NAME);
        assertThat(testFunctionalityMaster.getFunctionalityDescription()).isEqualTo(DEFAULT_FUNCTIONALITY_DESCRIPTION);
        assertThat(testFunctionalityMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFunctionalityMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testFunctionalityMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testFunctionalityMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFunctionalityMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testFunctionalityMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testFunctionalityMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testFunctionalityMaster.getFunctionalityMasterUuid()).isEqualTo(DEFAULT_FUNCTIONALITY_MASTER_UUID);
    }

    @Test
    @Transactional
    void createFunctionalityMasterWithExistingId() throws Exception {
        // Create the FunctionalityMaster with an existing ID
        functionalityMaster.setFunctionalityId(1L);
        FunctionalityMasterDTO functionalityMasterDTO = functionalityMasterMapper.toDto(functionalityMaster);

        int databaseSizeBeforeCreate = functionalityMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFunctionalityMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(functionalityMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FunctionalityMaster in the database
        List<FunctionalityMaster> functionalityMasterList = functionalityMasterRepository.findAll();
        assertThat(functionalityMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFunctionalityMasters() throws Exception {
        // Initialize the database
        functionalityMasterRepository.saveAndFlush(functionalityMaster);

        // Get all the functionalityMasterList
        restFunctionalityMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=functionalityId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].functionalityId").value(hasItem(functionalityMaster.getFunctionalityId().intValue())))
            .andExpect(jsonPath("$.[*].functionalityNo").value(hasItem(DEFAULT_FUNCTIONALITY_NO)))
            .andExpect(jsonPath("$.[*].functionalityName").value(hasItem(DEFAULT_FUNCTIONALITY_NAME)))
            .andExpect(jsonPath("$.[*].functionalityDescription").value(hasItem(DEFAULT_FUNCTIONALITY_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].functionalityMasterUuid").value(hasItem(DEFAULT_FUNCTIONALITY_MASTER_UUID.toString())));
    }

    @Test
    @Transactional
    void getFunctionalityMaster() throws Exception {
        // Initialize the database
        functionalityMasterRepository.saveAndFlush(functionalityMaster);

        // Get the functionalityMaster
        restFunctionalityMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, functionalityMaster.getFunctionalityId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.functionalityId").value(functionalityMaster.getFunctionalityId().intValue()))
            .andExpect(jsonPath("$.functionalityNo").value(DEFAULT_FUNCTIONALITY_NO))
            .andExpect(jsonPath("$.functionalityName").value(DEFAULT_FUNCTIONALITY_NAME))
            .andExpect(jsonPath("$.functionalityDescription").value(DEFAULT_FUNCTIONALITY_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.functionalityMasterUuid").value(DEFAULT_FUNCTIONALITY_MASTER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingFunctionalityMaster() throws Exception {
        // Get the functionalityMaster
        restFunctionalityMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFunctionalityMaster() throws Exception {
        // Initialize the database
        functionalityMasterRepository.saveAndFlush(functionalityMaster);

        int databaseSizeBeforeUpdate = functionalityMasterRepository.findAll().size();

        // Update the functionalityMaster
        FunctionalityMaster updatedFunctionalityMaster = functionalityMasterRepository
            .findById(functionalityMaster.getFunctionalityId())
            .get();
        // Disconnect from session so that the updates on updatedFunctionalityMaster are not directly saved in db
        em.detach(updatedFunctionalityMaster);
        updatedFunctionalityMaster
            .functionalityNo(UPDATED_FUNCTIONALITY_NO)
            .functionalityName(UPDATED_FUNCTIONALITY_NAME)
            .functionalityDescription(UPDATED_FUNCTIONALITY_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .functionalityMasterUuid(UPDATED_FUNCTIONALITY_MASTER_UUID);
        FunctionalityMasterDTO functionalityMasterDTO = functionalityMasterMapper.toDto(updatedFunctionalityMaster);

        restFunctionalityMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, functionalityMasterDTO.getFunctionalityId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(functionalityMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the FunctionalityMaster in the database
        List<FunctionalityMaster> functionalityMasterList = functionalityMasterRepository.findAll();
        assertThat(functionalityMasterList).hasSize(databaseSizeBeforeUpdate);
        FunctionalityMaster testFunctionalityMaster = functionalityMasterList.get(functionalityMasterList.size() - 1);
        assertThat(testFunctionalityMaster.getFunctionalityNo()).isEqualTo(UPDATED_FUNCTIONALITY_NO);
        assertThat(testFunctionalityMaster.getFunctionalityName()).isEqualTo(UPDATED_FUNCTIONALITY_NAME);
        assertThat(testFunctionalityMaster.getFunctionalityDescription()).isEqualTo(UPDATED_FUNCTIONALITY_DESCRIPTION);
        assertThat(testFunctionalityMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFunctionalityMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testFunctionalityMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testFunctionalityMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFunctionalityMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testFunctionalityMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testFunctionalityMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testFunctionalityMaster.getFunctionalityMasterUuid()).isEqualTo(UPDATED_FUNCTIONALITY_MASTER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingFunctionalityMaster() throws Exception {
        int databaseSizeBeforeUpdate = functionalityMasterRepository.findAll().size();
        functionalityMaster.setFunctionalityId(count.incrementAndGet());

        // Create the FunctionalityMaster
        FunctionalityMasterDTO functionalityMasterDTO = functionalityMasterMapper.toDto(functionalityMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFunctionalityMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, functionalityMasterDTO.getFunctionalityId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(functionalityMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FunctionalityMaster in the database
        List<FunctionalityMaster> functionalityMasterList = functionalityMasterRepository.findAll();
        assertThat(functionalityMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFunctionalityMaster() throws Exception {
        int databaseSizeBeforeUpdate = functionalityMasterRepository.findAll().size();
        functionalityMaster.setFunctionalityId(count.incrementAndGet());

        // Create the FunctionalityMaster
        FunctionalityMasterDTO functionalityMasterDTO = functionalityMasterMapper.toDto(functionalityMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFunctionalityMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(functionalityMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FunctionalityMaster in the database
        List<FunctionalityMaster> functionalityMasterList = functionalityMasterRepository.findAll();
        assertThat(functionalityMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFunctionalityMaster() throws Exception {
        int databaseSizeBeforeUpdate = functionalityMasterRepository.findAll().size();
        functionalityMaster.setFunctionalityId(count.incrementAndGet());

        // Create the FunctionalityMaster
        FunctionalityMasterDTO functionalityMasterDTO = functionalityMasterMapper.toDto(functionalityMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFunctionalityMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(functionalityMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FunctionalityMaster in the database
        List<FunctionalityMaster> functionalityMasterList = functionalityMasterRepository.findAll();
        assertThat(functionalityMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFunctionalityMasterWithPatch() throws Exception {
        // Initialize the database
        functionalityMasterRepository.saveAndFlush(functionalityMaster);

        int databaseSizeBeforeUpdate = functionalityMasterRepository.findAll().size();

        // Update the functionalityMaster using partial update
        FunctionalityMaster partialUpdatedFunctionalityMaster = new FunctionalityMaster();
        partialUpdatedFunctionalityMaster.setFunctionalityId(functionalityMaster.getFunctionalityId());

        partialUpdatedFunctionalityMaster
            .functionalityNo(UPDATED_FUNCTIONALITY_NO)
            .functionalityDescription(UPDATED_FUNCTIONALITY_DESCRIPTION)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE);

        restFunctionalityMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFunctionalityMaster.getFunctionalityId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFunctionalityMaster))
            )
            .andExpect(status().isOk());

        // Validate the FunctionalityMaster in the database
        List<FunctionalityMaster> functionalityMasterList = functionalityMasterRepository.findAll();
        assertThat(functionalityMasterList).hasSize(databaseSizeBeforeUpdate);
        FunctionalityMaster testFunctionalityMaster = functionalityMasterList.get(functionalityMasterList.size() - 1);
        assertThat(testFunctionalityMaster.getFunctionalityNo()).isEqualTo(UPDATED_FUNCTIONALITY_NO);
        assertThat(testFunctionalityMaster.getFunctionalityName()).isEqualTo(DEFAULT_FUNCTIONALITY_NAME);
        assertThat(testFunctionalityMaster.getFunctionalityDescription()).isEqualTo(UPDATED_FUNCTIONALITY_DESCRIPTION);
        assertThat(testFunctionalityMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFunctionalityMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testFunctionalityMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testFunctionalityMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFunctionalityMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testFunctionalityMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testFunctionalityMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testFunctionalityMaster.getFunctionalityMasterUuid()).isEqualTo(DEFAULT_FUNCTIONALITY_MASTER_UUID);
    }

    @Test
    @Transactional
    void fullUpdateFunctionalityMasterWithPatch() throws Exception {
        // Initialize the database
        functionalityMasterRepository.saveAndFlush(functionalityMaster);

        int databaseSizeBeforeUpdate = functionalityMasterRepository.findAll().size();

        // Update the functionalityMaster using partial update
        FunctionalityMaster partialUpdatedFunctionalityMaster = new FunctionalityMaster();
        partialUpdatedFunctionalityMaster.setFunctionalityId(functionalityMaster.getFunctionalityId());

        partialUpdatedFunctionalityMaster
            .functionalityNo(UPDATED_FUNCTIONALITY_NO)
            .functionalityName(UPDATED_FUNCTIONALITY_NAME)
            .functionalityDescription(UPDATED_FUNCTIONALITY_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .functionalityMasterUuid(UPDATED_FUNCTIONALITY_MASTER_UUID);

        restFunctionalityMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFunctionalityMaster.getFunctionalityId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFunctionalityMaster))
            )
            .andExpect(status().isOk());

        // Validate the FunctionalityMaster in the database
        List<FunctionalityMaster> functionalityMasterList = functionalityMasterRepository.findAll();
        assertThat(functionalityMasterList).hasSize(databaseSizeBeforeUpdate);
        FunctionalityMaster testFunctionalityMaster = functionalityMasterList.get(functionalityMasterList.size() - 1);
        assertThat(testFunctionalityMaster.getFunctionalityNo()).isEqualTo(UPDATED_FUNCTIONALITY_NO);
        assertThat(testFunctionalityMaster.getFunctionalityName()).isEqualTo(UPDATED_FUNCTIONALITY_NAME);
        assertThat(testFunctionalityMaster.getFunctionalityDescription()).isEqualTo(UPDATED_FUNCTIONALITY_DESCRIPTION);
        assertThat(testFunctionalityMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFunctionalityMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testFunctionalityMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testFunctionalityMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFunctionalityMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testFunctionalityMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testFunctionalityMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testFunctionalityMaster.getFunctionalityMasterUuid()).isEqualTo(UPDATED_FUNCTIONALITY_MASTER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingFunctionalityMaster() throws Exception {
        int databaseSizeBeforeUpdate = functionalityMasterRepository.findAll().size();
        functionalityMaster.setFunctionalityId(count.incrementAndGet());

        // Create the FunctionalityMaster
        FunctionalityMasterDTO functionalityMasterDTO = functionalityMasterMapper.toDto(functionalityMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFunctionalityMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, functionalityMasterDTO.getFunctionalityId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(functionalityMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FunctionalityMaster in the database
        List<FunctionalityMaster> functionalityMasterList = functionalityMasterRepository.findAll();
        assertThat(functionalityMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFunctionalityMaster() throws Exception {
        int databaseSizeBeforeUpdate = functionalityMasterRepository.findAll().size();
        functionalityMaster.setFunctionalityId(count.incrementAndGet());

        // Create the FunctionalityMaster
        FunctionalityMasterDTO functionalityMasterDTO = functionalityMasterMapper.toDto(functionalityMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFunctionalityMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(functionalityMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FunctionalityMaster in the database
        List<FunctionalityMaster> functionalityMasterList = functionalityMasterRepository.findAll();
        assertThat(functionalityMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFunctionalityMaster() throws Exception {
        int databaseSizeBeforeUpdate = functionalityMasterRepository.findAll().size();
        functionalityMaster.setFunctionalityId(count.incrementAndGet());

        // Create the FunctionalityMaster
        FunctionalityMasterDTO functionalityMasterDTO = functionalityMasterMapper.toDto(functionalityMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFunctionalityMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(functionalityMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FunctionalityMaster in the database
        List<FunctionalityMaster> functionalityMasterList = functionalityMasterRepository.findAll();
        assertThat(functionalityMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFunctionalityMaster() throws Exception {
        // Initialize the database
        functionalityMasterRepository.saveAndFlush(functionalityMaster);

        int databaseSizeBeforeDelete = functionalityMasterRepository.findAll().size();

        // Delete the functionalityMaster
        restFunctionalityMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, functionalityMaster.getFunctionalityId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FunctionalityMaster> functionalityMasterList = functionalityMasterRepository.findAll();
        assertThat(functionalityMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
