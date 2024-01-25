package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.StopReasonMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.StopReasonMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.StopReasonMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.StopReasonMasterAuditLogMapper;
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
 * Integration tests for the {@link StopReasonMasterAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StopReasonMasterAuditLogResourceIT {

    private static final Long DEFAULT_STP_RASON_ID = 1L;
    private static final Long UPDATED_STP_RASON_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/stop-reason-master-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StopReasonMasterAuditLogRepository stopReasonMasterAuditLogRepository;

    @Autowired
    private StopReasonMasterAuditLogMapper stopReasonMasterAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStopReasonMasterAuditLogMockMvc;

    private StopReasonMasterAuditLog stopReasonMasterAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StopReasonMasterAuditLog createEntity(EntityManager em) {
        StopReasonMasterAuditLog stopReasonMasterAuditLog = new StopReasonMasterAuditLog()
            .stpRasonId(DEFAULT_STP_RASON_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return stopReasonMasterAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StopReasonMasterAuditLog createUpdatedEntity(EntityManager em) {
        StopReasonMasterAuditLog stopReasonMasterAuditLog = new StopReasonMasterAuditLog()
            .stpRasonId(UPDATED_STP_RASON_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return stopReasonMasterAuditLog;
    }

    @BeforeEach
    public void initTest() {
        stopReasonMasterAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createStopReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeCreate = stopReasonMasterAuditLogRepository.findAll().size();
        // Create the StopReasonMasterAuditLog
        StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO = stopReasonMasterAuditLogMapper.toDto(stopReasonMasterAuditLog);
        restStopReasonMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stopReasonMasterAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the StopReasonMasterAuditLog in the database
        List<StopReasonMasterAuditLog> stopReasonMasterAuditLogList = stopReasonMasterAuditLogRepository.findAll();
        assertThat(stopReasonMasterAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        StopReasonMasterAuditLog testStopReasonMasterAuditLog = stopReasonMasterAuditLogList.get(stopReasonMasterAuditLogList.size() - 1);
        assertThat(testStopReasonMasterAuditLog.getStpRasonId()).isEqualTo(DEFAULT_STP_RASON_ID);
        assertThat(testStopReasonMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testStopReasonMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testStopReasonMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testStopReasonMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testStopReasonMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createStopReasonMasterAuditLogWithExistingId() throws Exception {
        // Create the StopReasonMasterAuditLog with an existing ID
        stopReasonMasterAuditLog.setId(1L);
        StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO = stopReasonMasterAuditLogMapper.toDto(stopReasonMasterAuditLog);

        int databaseSizeBeforeCreate = stopReasonMasterAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStopReasonMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stopReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StopReasonMasterAuditLog in the database
        List<StopReasonMasterAuditLog> stopReasonMasterAuditLogList = stopReasonMasterAuditLogRepository.findAll();
        assertThat(stopReasonMasterAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllStopReasonMasterAuditLogs() throws Exception {
        // Initialize the database
        stopReasonMasterAuditLogRepository.saveAndFlush(stopReasonMasterAuditLog);

        // Get all the stopReasonMasterAuditLogList
        restStopReasonMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stopReasonMasterAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].stpRasonId").value(hasItem(DEFAULT_STP_RASON_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getStopReasonMasterAuditLog() throws Exception {
        // Initialize the database
        stopReasonMasterAuditLogRepository.saveAndFlush(stopReasonMasterAuditLog);

        // Get the stopReasonMasterAuditLog
        restStopReasonMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, stopReasonMasterAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(stopReasonMasterAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.stpRasonId").value(DEFAULT_STP_RASON_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingStopReasonMasterAuditLog() throws Exception {
        // Get the stopReasonMasterAuditLog
        restStopReasonMasterAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingStopReasonMasterAuditLog() throws Exception {
        // Initialize the database
        stopReasonMasterAuditLogRepository.saveAndFlush(stopReasonMasterAuditLog);

        int databaseSizeBeforeUpdate = stopReasonMasterAuditLogRepository.findAll().size();

        // Update the stopReasonMasterAuditLog
        StopReasonMasterAuditLog updatedStopReasonMasterAuditLog = stopReasonMasterAuditLogRepository
            .findById(stopReasonMasterAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedStopReasonMasterAuditLog are not directly saved in db
        em.detach(updatedStopReasonMasterAuditLog);
        updatedStopReasonMasterAuditLog
            .stpRasonId(UPDATED_STP_RASON_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO = stopReasonMasterAuditLogMapper.toDto(updatedStopReasonMasterAuditLog);

        restStopReasonMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, stopReasonMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stopReasonMasterAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the StopReasonMasterAuditLog in the database
        List<StopReasonMasterAuditLog> stopReasonMasterAuditLogList = stopReasonMasterAuditLogRepository.findAll();
        assertThat(stopReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        StopReasonMasterAuditLog testStopReasonMasterAuditLog = stopReasonMasterAuditLogList.get(stopReasonMasterAuditLogList.size() - 1);
        assertThat(testStopReasonMasterAuditLog.getStpRasonId()).isEqualTo(UPDATED_STP_RASON_ID);
        assertThat(testStopReasonMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testStopReasonMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testStopReasonMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testStopReasonMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testStopReasonMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingStopReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = stopReasonMasterAuditLogRepository.findAll().size();
        stopReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the StopReasonMasterAuditLog
        StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO = stopReasonMasterAuditLogMapper.toDto(stopReasonMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStopReasonMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, stopReasonMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stopReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StopReasonMasterAuditLog in the database
        List<StopReasonMasterAuditLog> stopReasonMasterAuditLogList = stopReasonMasterAuditLogRepository.findAll();
        assertThat(stopReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchStopReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = stopReasonMasterAuditLogRepository.findAll().size();
        stopReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the StopReasonMasterAuditLog
        StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO = stopReasonMasterAuditLogMapper.toDto(stopReasonMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStopReasonMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stopReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StopReasonMasterAuditLog in the database
        List<StopReasonMasterAuditLog> stopReasonMasterAuditLogList = stopReasonMasterAuditLogRepository.findAll();
        assertThat(stopReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamStopReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = stopReasonMasterAuditLogRepository.findAll().size();
        stopReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the StopReasonMasterAuditLog
        StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO = stopReasonMasterAuditLogMapper.toDto(stopReasonMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStopReasonMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stopReasonMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the StopReasonMasterAuditLog in the database
        List<StopReasonMasterAuditLog> stopReasonMasterAuditLogList = stopReasonMasterAuditLogRepository.findAll();
        assertThat(stopReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateStopReasonMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        stopReasonMasterAuditLogRepository.saveAndFlush(stopReasonMasterAuditLog);

        int databaseSizeBeforeUpdate = stopReasonMasterAuditLogRepository.findAll().size();

        // Update the stopReasonMasterAuditLog using partial update
        StopReasonMasterAuditLog partialUpdatedStopReasonMasterAuditLog = new StopReasonMasterAuditLog();
        partialUpdatedStopReasonMasterAuditLog.setId(stopReasonMasterAuditLog.getId());

        partialUpdatedStopReasonMasterAuditLog
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP);

        restStopReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStopReasonMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStopReasonMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the StopReasonMasterAuditLog in the database
        List<StopReasonMasterAuditLog> stopReasonMasterAuditLogList = stopReasonMasterAuditLogRepository.findAll();
        assertThat(stopReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        StopReasonMasterAuditLog testStopReasonMasterAuditLog = stopReasonMasterAuditLogList.get(stopReasonMasterAuditLogList.size() - 1);
        assertThat(testStopReasonMasterAuditLog.getStpRasonId()).isEqualTo(DEFAULT_STP_RASON_ID);
        assertThat(testStopReasonMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testStopReasonMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testStopReasonMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testStopReasonMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testStopReasonMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateStopReasonMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        stopReasonMasterAuditLogRepository.saveAndFlush(stopReasonMasterAuditLog);

        int databaseSizeBeforeUpdate = stopReasonMasterAuditLogRepository.findAll().size();

        // Update the stopReasonMasterAuditLog using partial update
        StopReasonMasterAuditLog partialUpdatedStopReasonMasterAuditLog = new StopReasonMasterAuditLog();
        partialUpdatedStopReasonMasterAuditLog.setId(stopReasonMasterAuditLog.getId());

        partialUpdatedStopReasonMasterAuditLog
            .stpRasonId(UPDATED_STP_RASON_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restStopReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStopReasonMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStopReasonMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the StopReasonMasterAuditLog in the database
        List<StopReasonMasterAuditLog> stopReasonMasterAuditLogList = stopReasonMasterAuditLogRepository.findAll();
        assertThat(stopReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        StopReasonMasterAuditLog testStopReasonMasterAuditLog = stopReasonMasterAuditLogList.get(stopReasonMasterAuditLogList.size() - 1);
        assertThat(testStopReasonMasterAuditLog.getStpRasonId()).isEqualTo(UPDATED_STP_RASON_ID);
        assertThat(testStopReasonMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testStopReasonMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testStopReasonMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testStopReasonMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testStopReasonMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingStopReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = stopReasonMasterAuditLogRepository.findAll().size();
        stopReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the StopReasonMasterAuditLog
        StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO = stopReasonMasterAuditLogMapper.toDto(stopReasonMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStopReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, stopReasonMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stopReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StopReasonMasterAuditLog in the database
        List<StopReasonMasterAuditLog> stopReasonMasterAuditLogList = stopReasonMasterAuditLogRepository.findAll();
        assertThat(stopReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchStopReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = stopReasonMasterAuditLogRepository.findAll().size();
        stopReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the StopReasonMasterAuditLog
        StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO = stopReasonMasterAuditLogMapper.toDto(stopReasonMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStopReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stopReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StopReasonMasterAuditLog in the database
        List<StopReasonMasterAuditLog> stopReasonMasterAuditLogList = stopReasonMasterAuditLogRepository.findAll();
        assertThat(stopReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamStopReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = stopReasonMasterAuditLogRepository.findAll().size();
        stopReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the StopReasonMasterAuditLog
        StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO = stopReasonMasterAuditLogMapper.toDto(stopReasonMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStopReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stopReasonMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the StopReasonMasterAuditLog in the database
        List<StopReasonMasterAuditLog> stopReasonMasterAuditLogList = stopReasonMasterAuditLogRepository.findAll();
        assertThat(stopReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteStopReasonMasterAuditLog() throws Exception {
        // Initialize the database
        stopReasonMasterAuditLogRepository.saveAndFlush(stopReasonMasterAuditLog);

        int databaseSizeBeforeDelete = stopReasonMasterAuditLogRepository.findAll().size();

        // Delete the stopReasonMasterAuditLog
        restStopReasonMasterAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, stopReasonMasterAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StopReasonMasterAuditLog> stopReasonMasterAuditLogList = stopReasonMasterAuditLogRepository.findAll();
        assertThat(stopReasonMasterAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
