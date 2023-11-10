package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.HoldReasonMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.HoldReasonMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.HoldReasonMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.HoldReasonMasterAuditLogMapper;
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
 * Integration tests for the {@link HoldReasonMasterAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HoldReasonMasterAuditLogResourceIT {

    private static final Long DEFAULT_HLD_RASON_ID = 1L;
    private static final Long UPDATED_HLD_RASON_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/hold-reason-master-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private HoldReasonMasterAuditLogRepository holdReasonMasterAuditLogRepository;

    @Autowired
    private HoldReasonMasterAuditLogMapper holdReasonMasterAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHoldReasonMasterAuditLogMockMvc;

    private HoldReasonMasterAuditLog holdReasonMasterAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HoldReasonMasterAuditLog createEntity(EntityManager em) {
        HoldReasonMasterAuditLog holdReasonMasterAuditLog = new HoldReasonMasterAuditLog()
            .hldRasonId(DEFAULT_HLD_RASON_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return holdReasonMasterAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HoldReasonMasterAuditLog createUpdatedEntity(EntityManager em) {
        HoldReasonMasterAuditLog holdReasonMasterAuditLog = new HoldReasonMasterAuditLog()
            .hldRasonId(UPDATED_HLD_RASON_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return holdReasonMasterAuditLog;
    }

    @BeforeEach
    public void initTest() {
        holdReasonMasterAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createHoldReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeCreate = holdReasonMasterAuditLogRepository.findAll().size();
        // Create the HoldReasonMasterAuditLog
        HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO = holdReasonMasterAuditLogMapper.toDto(holdReasonMasterAuditLog);
        restHoldReasonMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(holdReasonMasterAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the HoldReasonMasterAuditLog in the database
        List<HoldReasonMasterAuditLog> holdReasonMasterAuditLogList = holdReasonMasterAuditLogRepository.findAll();
        assertThat(holdReasonMasterAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        HoldReasonMasterAuditLog testHoldReasonMasterAuditLog = holdReasonMasterAuditLogList.get(holdReasonMasterAuditLogList.size() - 1);
        assertThat(testHoldReasonMasterAuditLog.getHldRasonId()).isEqualTo(DEFAULT_HLD_RASON_ID);
        assertThat(testHoldReasonMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testHoldReasonMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testHoldReasonMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testHoldReasonMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testHoldReasonMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createHoldReasonMasterAuditLogWithExistingId() throws Exception {
        // Create the HoldReasonMasterAuditLog with an existing ID
        holdReasonMasterAuditLog.setId(1L);
        HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO = holdReasonMasterAuditLogMapper.toDto(holdReasonMasterAuditLog);

        int databaseSizeBeforeCreate = holdReasonMasterAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHoldReasonMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(holdReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoldReasonMasterAuditLog in the database
        List<HoldReasonMasterAuditLog> holdReasonMasterAuditLogList = holdReasonMasterAuditLogRepository.findAll();
        assertThat(holdReasonMasterAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllHoldReasonMasterAuditLogs() throws Exception {
        // Initialize the database
        holdReasonMasterAuditLogRepository.saveAndFlush(holdReasonMasterAuditLog);

        // Get all the holdReasonMasterAuditLogList
        restHoldReasonMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(holdReasonMasterAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].hldRasonId").value(hasItem(DEFAULT_HLD_RASON_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getHoldReasonMasterAuditLog() throws Exception {
        // Initialize the database
        holdReasonMasterAuditLogRepository.saveAndFlush(holdReasonMasterAuditLog);

        // Get the holdReasonMasterAuditLog
        restHoldReasonMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, holdReasonMasterAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(holdReasonMasterAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.hldRasonId").value(DEFAULT_HLD_RASON_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingHoldReasonMasterAuditLog() throws Exception {
        // Get the holdReasonMasterAuditLog
        restHoldReasonMasterAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewHoldReasonMasterAuditLog() throws Exception {
        // Initialize the database
        holdReasonMasterAuditLogRepository.saveAndFlush(holdReasonMasterAuditLog);

        int databaseSizeBeforeUpdate = holdReasonMasterAuditLogRepository.findAll().size();

        // Update the holdReasonMasterAuditLog
        HoldReasonMasterAuditLog updatedHoldReasonMasterAuditLog = holdReasonMasterAuditLogRepository
            .findById(holdReasonMasterAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedHoldReasonMasterAuditLog are not directly saved in db
        em.detach(updatedHoldReasonMasterAuditLog);
        updatedHoldReasonMasterAuditLog
            .hldRasonId(UPDATED_HLD_RASON_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO = holdReasonMasterAuditLogMapper.toDto(updatedHoldReasonMasterAuditLog);

        restHoldReasonMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, holdReasonMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(holdReasonMasterAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the HoldReasonMasterAuditLog in the database
        List<HoldReasonMasterAuditLog> holdReasonMasterAuditLogList = holdReasonMasterAuditLogRepository.findAll();
        assertThat(holdReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        HoldReasonMasterAuditLog testHoldReasonMasterAuditLog = holdReasonMasterAuditLogList.get(holdReasonMasterAuditLogList.size() - 1);
        assertThat(testHoldReasonMasterAuditLog.getHldRasonId()).isEqualTo(UPDATED_HLD_RASON_ID);
        assertThat(testHoldReasonMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testHoldReasonMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testHoldReasonMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testHoldReasonMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testHoldReasonMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingHoldReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = holdReasonMasterAuditLogRepository.findAll().size();
        holdReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the HoldReasonMasterAuditLog
        HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO = holdReasonMasterAuditLogMapper.toDto(holdReasonMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHoldReasonMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, holdReasonMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(holdReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoldReasonMasterAuditLog in the database
        List<HoldReasonMasterAuditLog> holdReasonMasterAuditLogList = holdReasonMasterAuditLogRepository.findAll();
        assertThat(holdReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHoldReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = holdReasonMasterAuditLogRepository.findAll().size();
        holdReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the HoldReasonMasterAuditLog
        HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO = holdReasonMasterAuditLogMapper.toDto(holdReasonMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoldReasonMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(holdReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoldReasonMasterAuditLog in the database
        List<HoldReasonMasterAuditLog> holdReasonMasterAuditLogList = holdReasonMasterAuditLogRepository.findAll();
        assertThat(holdReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHoldReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = holdReasonMasterAuditLogRepository.findAll().size();
        holdReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the HoldReasonMasterAuditLog
        HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO = holdReasonMasterAuditLogMapper.toDto(holdReasonMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoldReasonMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(holdReasonMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HoldReasonMasterAuditLog in the database
        List<HoldReasonMasterAuditLog> holdReasonMasterAuditLogList = holdReasonMasterAuditLogRepository.findAll();
        assertThat(holdReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHoldReasonMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        holdReasonMasterAuditLogRepository.saveAndFlush(holdReasonMasterAuditLog);

        int databaseSizeBeforeUpdate = holdReasonMasterAuditLogRepository.findAll().size();

        // Update the holdReasonMasterAuditLog using partial update
        HoldReasonMasterAuditLog partialUpdatedHoldReasonMasterAuditLog = new HoldReasonMasterAuditLog();
        partialUpdatedHoldReasonMasterAuditLog.setId(holdReasonMasterAuditLog.getId());

        partialUpdatedHoldReasonMasterAuditLog
            .hldRasonId(UPDATED_HLD_RASON_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP);

        restHoldReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHoldReasonMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHoldReasonMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the HoldReasonMasterAuditLog in the database
        List<HoldReasonMasterAuditLog> holdReasonMasterAuditLogList = holdReasonMasterAuditLogRepository.findAll();
        assertThat(holdReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        HoldReasonMasterAuditLog testHoldReasonMasterAuditLog = holdReasonMasterAuditLogList.get(holdReasonMasterAuditLogList.size() - 1);
        assertThat(testHoldReasonMasterAuditLog.getHldRasonId()).isEqualTo(UPDATED_HLD_RASON_ID);
        assertThat(testHoldReasonMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testHoldReasonMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testHoldReasonMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testHoldReasonMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testHoldReasonMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateHoldReasonMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        holdReasonMasterAuditLogRepository.saveAndFlush(holdReasonMasterAuditLog);

        int databaseSizeBeforeUpdate = holdReasonMasterAuditLogRepository.findAll().size();

        // Update the holdReasonMasterAuditLog using partial update
        HoldReasonMasterAuditLog partialUpdatedHoldReasonMasterAuditLog = new HoldReasonMasterAuditLog();
        partialUpdatedHoldReasonMasterAuditLog.setId(holdReasonMasterAuditLog.getId());

        partialUpdatedHoldReasonMasterAuditLog
            .hldRasonId(UPDATED_HLD_RASON_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restHoldReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHoldReasonMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHoldReasonMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the HoldReasonMasterAuditLog in the database
        List<HoldReasonMasterAuditLog> holdReasonMasterAuditLogList = holdReasonMasterAuditLogRepository.findAll();
        assertThat(holdReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        HoldReasonMasterAuditLog testHoldReasonMasterAuditLog = holdReasonMasterAuditLogList.get(holdReasonMasterAuditLogList.size() - 1);
        assertThat(testHoldReasonMasterAuditLog.getHldRasonId()).isEqualTo(UPDATED_HLD_RASON_ID);
        assertThat(testHoldReasonMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testHoldReasonMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testHoldReasonMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testHoldReasonMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testHoldReasonMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingHoldReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = holdReasonMasterAuditLogRepository.findAll().size();
        holdReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the HoldReasonMasterAuditLog
        HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO = holdReasonMasterAuditLogMapper.toDto(holdReasonMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHoldReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, holdReasonMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(holdReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoldReasonMasterAuditLog in the database
        List<HoldReasonMasterAuditLog> holdReasonMasterAuditLogList = holdReasonMasterAuditLogRepository.findAll();
        assertThat(holdReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHoldReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = holdReasonMasterAuditLogRepository.findAll().size();
        holdReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the HoldReasonMasterAuditLog
        HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO = holdReasonMasterAuditLogMapper.toDto(holdReasonMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoldReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(holdReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoldReasonMasterAuditLog in the database
        List<HoldReasonMasterAuditLog> holdReasonMasterAuditLogList = holdReasonMasterAuditLogRepository.findAll();
        assertThat(holdReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHoldReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = holdReasonMasterAuditLogRepository.findAll().size();
        holdReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the HoldReasonMasterAuditLog
        HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO = holdReasonMasterAuditLogMapper.toDto(holdReasonMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoldReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(holdReasonMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HoldReasonMasterAuditLog in the database
        List<HoldReasonMasterAuditLog> holdReasonMasterAuditLogList = holdReasonMasterAuditLogRepository.findAll();
        assertThat(holdReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHoldReasonMasterAuditLog() throws Exception {
        // Initialize the database
        holdReasonMasterAuditLogRepository.saveAndFlush(holdReasonMasterAuditLog);

        int databaseSizeBeforeDelete = holdReasonMasterAuditLogRepository.findAll().size();

        // Delete the holdReasonMasterAuditLog
        restHoldReasonMasterAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, holdReasonMasterAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HoldReasonMasterAuditLog> holdReasonMasterAuditLogList = holdReasonMasterAuditLogRepository.findAll();
        assertThat(holdReasonMasterAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
