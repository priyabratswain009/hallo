package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.BranchItemLocationMapAuditLog;
import com.sunknowledge.dme.rcm.repository.BranchItemLocationMapAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.BranchItemLocationMapAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchItemLocationMapAuditLogMapper;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link BranchItemLocationMapAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BranchItemLocationMapAuditLogResourceIT {

    private static final Long DEFAULT_BRNCH_ITM_LOCTIN_MAP_ID = 1L;
    private static final Long UPDATED_BRNCH_ITM_LOCTIN_MAP_ID = 2L;

    private static final String DEFAULT_OLD_ROW_DATA = "AAAAAAAAAA";
    private static final String UPDATED_OLD_ROW_DATA = "BBBBBBBBBB";

    private static final String DEFAULT_NEW_ROW_DATA = "AAAAAAAAAA";
    private static final String UPDATED_NEW_ROW_DATA = "BBBBBBBBBB";

    private static final String DEFAULT_DML_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DML_TYPE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DML_TIMESTAMP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DML_TIMESTAMP = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DML_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_DML_CREATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/branch-item-location-map-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BranchItemLocationMapAuditLogRepository branchItemLocationMapAuditLogRepository;

    @Autowired
    private BranchItemLocationMapAuditLogMapper branchItemLocationMapAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBranchItemLocationMapAuditLogMockMvc;

    private BranchItemLocationMapAuditLog branchItemLocationMapAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchItemLocationMapAuditLog createEntity(EntityManager em) {
        BranchItemLocationMapAuditLog branchItemLocationMapAuditLog = new BranchItemLocationMapAuditLog()
            .brnchItmLoctinMapId(DEFAULT_BRNCH_ITM_LOCTIN_MAP_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return branchItemLocationMapAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchItemLocationMapAuditLog createUpdatedEntity(EntityManager em) {
        BranchItemLocationMapAuditLog branchItemLocationMapAuditLog = new BranchItemLocationMapAuditLog()
            .brnchItmLoctinMapId(UPDATED_BRNCH_ITM_LOCTIN_MAP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return branchItemLocationMapAuditLog;
    }

    @BeforeEach
    public void initTest() {
        branchItemLocationMapAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createBranchItemLocationMapAuditLog() throws Exception {
        int databaseSizeBeforeCreate = branchItemLocationMapAuditLogRepository.findAll().size();
        // Create the BranchItemLocationMapAuditLog
        BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO = branchItemLocationMapAuditLogMapper.toDto(
            branchItemLocationMapAuditLog
        );
        restBranchItemLocationMapAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchItemLocationMapAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BranchItemLocationMapAuditLog in the database
        List<BranchItemLocationMapAuditLog> branchItemLocationMapAuditLogList = branchItemLocationMapAuditLogRepository.findAll();
        assertThat(branchItemLocationMapAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        BranchItemLocationMapAuditLog testBranchItemLocationMapAuditLog = branchItemLocationMapAuditLogList.get(
            branchItemLocationMapAuditLogList.size() - 1
        );
        assertThat(testBranchItemLocationMapAuditLog.getBrnchItmLoctinMapId()).isEqualTo(DEFAULT_BRNCH_ITM_LOCTIN_MAP_ID);
        assertThat(testBranchItemLocationMapAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testBranchItemLocationMapAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testBranchItemLocationMapAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testBranchItemLocationMapAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testBranchItemLocationMapAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createBranchItemLocationMapAuditLogWithExistingId() throws Exception {
        // Create the BranchItemLocationMapAuditLog with an existing ID
        branchItemLocationMapAuditLog.setId(1L);
        BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO = branchItemLocationMapAuditLogMapper.toDto(
            branchItemLocationMapAuditLog
        );

        int databaseSizeBeforeCreate = branchItemLocationMapAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBranchItemLocationMapAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchItemLocationMapAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchItemLocationMapAuditLog in the database
        List<BranchItemLocationMapAuditLog> branchItemLocationMapAuditLogList = branchItemLocationMapAuditLogRepository.findAll();
        assertThat(branchItemLocationMapAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBranchItemLocationMapAuditLogs() throws Exception {
        // Initialize the database
        branchItemLocationMapAuditLogRepository.saveAndFlush(branchItemLocationMapAuditLog);

        // Get all the branchItemLocationMapAuditLogList
        restBranchItemLocationMapAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(branchItemLocationMapAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].brnchItmLoctinMapId").value(hasItem(DEFAULT_BRNCH_ITM_LOCTIN_MAP_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getBranchItemLocationMapAuditLog() throws Exception {
        // Initialize the database
        branchItemLocationMapAuditLogRepository.saveAndFlush(branchItemLocationMapAuditLog);

        // Get the branchItemLocationMapAuditLog
        restBranchItemLocationMapAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, branchItemLocationMapAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(branchItemLocationMapAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.brnchItmLoctinMapId").value(DEFAULT_BRNCH_ITM_LOCTIN_MAP_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingBranchItemLocationMapAuditLog() throws Exception {
        // Get the branchItemLocationMapAuditLog
        restBranchItemLocationMapAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBranchItemLocationMapAuditLog() throws Exception {
        // Initialize the database
        branchItemLocationMapAuditLogRepository.saveAndFlush(branchItemLocationMapAuditLog);

        int databaseSizeBeforeUpdate = branchItemLocationMapAuditLogRepository.findAll().size();

        // Update the branchItemLocationMapAuditLog
        BranchItemLocationMapAuditLog updatedBranchItemLocationMapAuditLog = branchItemLocationMapAuditLogRepository
            .findById(branchItemLocationMapAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedBranchItemLocationMapAuditLog are not directly saved in db
        em.detach(updatedBranchItemLocationMapAuditLog);
        updatedBranchItemLocationMapAuditLog
            .brnchItmLoctinMapId(UPDATED_BRNCH_ITM_LOCTIN_MAP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO = branchItemLocationMapAuditLogMapper.toDto(
            updatedBranchItemLocationMapAuditLog
        );

        restBranchItemLocationMapAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchItemLocationMapAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchItemLocationMapAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the BranchItemLocationMapAuditLog in the database
        List<BranchItemLocationMapAuditLog> branchItemLocationMapAuditLogList = branchItemLocationMapAuditLogRepository.findAll();
        assertThat(branchItemLocationMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
        BranchItemLocationMapAuditLog testBranchItemLocationMapAuditLog = branchItemLocationMapAuditLogList.get(
            branchItemLocationMapAuditLogList.size() - 1
        );
        assertThat(testBranchItemLocationMapAuditLog.getBrnchItmLoctinMapId()).isEqualTo(UPDATED_BRNCH_ITM_LOCTIN_MAP_ID);
        assertThat(testBranchItemLocationMapAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testBranchItemLocationMapAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testBranchItemLocationMapAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testBranchItemLocationMapAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testBranchItemLocationMapAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingBranchItemLocationMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchItemLocationMapAuditLogRepository.findAll().size();
        branchItemLocationMapAuditLog.setId(count.incrementAndGet());

        // Create the BranchItemLocationMapAuditLog
        BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO = branchItemLocationMapAuditLogMapper.toDto(
            branchItemLocationMapAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchItemLocationMapAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchItemLocationMapAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchItemLocationMapAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchItemLocationMapAuditLog in the database
        List<BranchItemLocationMapAuditLog> branchItemLocationMapAuditLogList = branchItemLocationMapAuditLogRepository.findAll();
        assertThat(branchItemLocationMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBranchItemLocationMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchItemLocationMapAuditLogRepository.findAll().size();
        branchItemLocationMapAuditLog.setId(count.incrementAndGet());

        // Create the BranchItemLocationMapAuditLog
        BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO = branchItemLocationMapAuditLogMapper.toDto(
            branchItemLocationMapAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchItemLocationMapAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchItemLocationMapAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchItemLocationMapAuditLog in the database
        List<BranchItemLocationMapAuditLog> branchItemLocationMapAuditLogList = branchItemLocationMapAuditLogRepository.findAll();
        assertThat(branchItemLocationMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBranchItemLocationMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchItemLocationMapAuditLogRepository.findAll().size();
        branchItemLocationMapAuditLog.setId(count.incrementAndGet());

        // Create the BranchItemLocationMapAuditLog
        BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO = branchItemLocationMapAuditLogMapper.toDto(
            branchItemLocationMapAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchItemLocationMapAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchItemLocationMapAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchItemLocationMapAuditLog in the database
        List<BranchItemLocationMapAuditLog> branchItemLocationMapAuditLogList = branchItemLocationMapAuditLogRepository.findAll();
        assertThat(branchItemLocationMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBranchItemLocationMapAuditLogWithPatch() throws Exception {
        // Initialize the database
        branchItemLocationMapAuditLogRepository.saveAndFlush(branchItemLocationMapAuditLog);

        int databaseSizeBeforeUpdate = branchItemLocationMapAuditLogRepository.findAll().size();

        // Update the branchItemLocationMapAuditLog using partial update
        BranchItemLocationMapAuditLog partialUpdatedBranchItemLocationMapAuditLog = new BranchItemLocationMapAuditLog();
        partialUpdatedBranchItemLocationMapAuditLog.setId(branchItemLocationMapAuditLog.getId());

        partialUpdatedBranchItemLocationMapAuditLog
            .brnchItmLoctinMapId(UPDATED_BRNCH_ITM_LOCTIN_MAP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE);

        restBranchItemLocationMapAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchItemLocationMapAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchItemLocationMapAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the BranchItemLocationMapAuditLog in the database
        List<BranchItemLocationMapAuditLog> branchItemLocationMapAuditLogList = branchItemLocationMapAuditLogRepository.findAll();
        assertThat(branchItemLocationMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
        BranchItemLocationMapAuditLog testBranchItemLocationMapAuditLog = branchItemLocationMapAuditLogList.get(
            branchItemLocationMapAuditLogList.size() - 1
        );
        assertThat(testBranchItemLocationMapAuditLog.getBrnchItmLoctinMapId()).isEqualTo(UPDATED_BRNCH_ITM_LOCTIN_MAP_ID);
        assertThat(testBranchItemLocationMapAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testBranchItemLocationMapAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testBranchItemLocationMapAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testBranchItemLocationMapAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testBranchItemLocationMapAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateBranchItemLocationMapAuditLogWithPatch() throws Exception {
        // Initialize the database
        branchItemLocationMapAuditLogRepository.saveAndFlush(branchItemLocationMapAuditLog);

        int databaseSizeBeforeUpdate = branchItemLocationMapAuditLogRepository.findAll().size();

        // Update the branchItemLocationMapAuditLog using partial update
        BranchItemLocationMapAuditLog partialUpdatedBranchItemLocationMapAuditLog = new BranchItemLocationMapAuditLog();
        partialUpdatedBranchItemLocationMapAuditLog.setId(branchItemLocationMapAuditLog.getId());

        partialUpdatedBranchItemLocationMapAuditLog
            .brnchItmLoctinMapId(UPDATED_BRNCH_ITM_LOCTIN_MAP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restBranchItemLocationMapAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchItemLocationMapAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchItemLocationMapAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the BranchItemLocationMapAuditLog in the database
        List<BranchItemLocationMapAuditLog> branchItemLocationMapAuditLogList = branchItemLocationMapAuditLogRepository.findAll();
        assertThat(branchItemLocationMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
        BranchItemLocationMapAuditLog testBranchItemLocationMapAuditLog = branchItemLocationMapAuditLogList.get(
            branchItemLocationMapAuditLogList.size() - 1
        );
        assertThat(testBranchItemLocationMapAuditLog.getBrnchItmLoctinMapId()).isEqualTo(UPDATED_BRNCH_ITM_LOCTIN_MAP_ID);
        assertThat(testBranchItemLocationMapAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testBranchItemLocationMapAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testBranchItemLocationMapAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testBranchItemLocationMapAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testBranchItemLocationMapAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingBranchItemLocationMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchItemLocationMapAuditLogRepository.findAll().size();
        branchItemLocationMapAuditLog.setId(count.incrementAndGet());

        // Create the BranchItemLocationMapAuditLog
        BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO = branchItemLocationMapAuditLogMapper.toDto(
            branchItemLocationMapAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchItemLocationMapAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, branchItemLocationMapAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchItemLocationMapAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchItemLocationMapAuditLog in the database
        List<BranchItemLocationMapAuditLog> branchItemLocationMapAuditLogList = branchItemLocationMapAuditLogRepository.findAll();
        assertThat(branchItemLocationMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBranchItemLocationMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchItemLocationMapAuditLogRepository.findAll().size();
        branchItemLocationMapAuditLog.setId(count.incrementAndGet());

        // Create the BranchItemLocationMapAuditLog
        BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO = branchItemLocationMapAuditLogMapper.toDto(
            branchItemLocationMapAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchItemLocationMapAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchItemLocationMapAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchItemLocationMapAuditLog in the database
        List<BranchItemLocationMapAuditLog> branchItemLocationMapAuditLogList = branchItemLocationMapAuditLogRepository.findAll();
        assertThat(branchItemLocationMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBranchItemLocationMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchItemLocationMapAuditLogRepository.findAll().size();
        branchItemLocationMapAuditLog.setId(count.incrementAndGet());

        // Create the BranchItemLocationMapAuditLog
        BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO = branchItemLocationMapAuditLogMapper.toDto(
            branchItemLocationMapAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchItemLocationMapAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchItemLocationMapAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchItemLocationMapAuditLog in the database
        List<BranchItemLocationMapAuditLog> branchItemLocationMapAuditLogList = branchItemLocationMapAuditLogRepository.findAll();
        assertThat(branchItemLocationMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBranchItemLocationMapAuditLog() throws Exception {
        // Initialize the database
        branchItemLocationMapAuditLogRepository.saveAndFlush(branchItemLocationMapAuditLog);

        int databaseSizeBeforeDelete = branchItemLocationMapAuditLogRepository.findAll().size();

        // Delete the branchItemLocationMapAuditLog
        restBranchItemLocationMapAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, branchItemLocationMapAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BranchItemLocationMapAuditLog> branchItemLocationMapAuditLogList = branchItemLocationMapAuditLogRepository.findAll();
        assertThat(branchItemLocationMapAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
