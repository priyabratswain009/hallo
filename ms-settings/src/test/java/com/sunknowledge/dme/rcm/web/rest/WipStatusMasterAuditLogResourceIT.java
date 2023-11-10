package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.WipStatusMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.WipStatusMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.WipStatusMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.WipStatusMasterAuditLogMapper;
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
 * Integration tests for the {@link WipStatusMasterAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WipStatusMasterAuditLogResourceIT {

    private static final Long DEFAULT_WP_STTUS_ID = 1L;
    private static final Long UPDATED_WP_STTUS_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/wip-status-master-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private WipStatusMasterAuditLogRepository wipStatusMasterAuditLogRepository;

    @Autowired
    private WipStatusMasterAuditLogMapper wipStatusMasterAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWipStatusMasterAuditLogMockMvc;

    private WipStatusMasterAuditLog wipStatusMasterAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WipStatusMasterAuditLog createEntity(EntityManager em) {
        WipStatusMasterAuditLog wipStatusMasterAuditLog = new WipStatusMasterAuditLog()
            .wpSttusId(DEFAULT_WP_STTUS_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return wipStatusMasterAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WipStatusMasterAuditLog createUpdatedEntity(EntityManager em) {
        WipStatusMasterAuditLog wipStatusMasterAuditLog = new WipStatusMasterAuditLog()
            .wpSttusId(UPDATED_WP_STTUS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return wipStatusMasterAuditLog;
    }

    @BeforeEach
    public void initTest() {
        wipStatusMasterAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createWipStatusMasterAuditLog() throws Exception {
        int databaseSizeBeforeCreate = wipStatusMasterAuditLogRepository.findAll().size();
        // Create the WipStatusMasterAuditLog
        WipStatusMasterAuditLogDTO wipStatusMasterAuditLogDTO = wipStatusMasterAuditLogMapper.toDto(wipStatusMasterAuditLog);
        restWipStatusMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipStatusMasterAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the WipStatusMasterAuditLog in the database
        List<WipStatusMasterAuditLog> wipStatusMasterAuditLogList = wipStatusMasterAuditLogRepository.findAll();
        assertThat(wipStatusMasterAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        WipStatusMasterAuditLog testWipStatusMasterAuditLog = wipStatusMasterAuditLogList.get(wipStatusMasterAuditLogList.size() - 1);
        assertThat(testWipStatusMasterAuditLog.getWpSttusId()).isEqualTo(DEFAULT_WP_STTUS_ID);
        assertThat(testWipStatusMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testWipStatusMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testWipStatusMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testWipStatusMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testWipStatusMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createWipStatusMasterAuditLogWithExistingId() throws Exception {
        // Create the WipStatusMasterAuditLog with an existing ID
        wipStatusMasterAuditLog.setId(1L);
        WipStatusMasterAuditLogDTO wipStatusMasterAuditLogDTO = wipStatusMasterAuditLogMapper.toDto(wipStatusMasterAuditLog);

        int databaseSizeBeforeCreate = wipStatusMasterAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWipStatusMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipStatusMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipStatusMasterAuditLog in the database
        List<WipStatusMasterAuditLog> wipStatusMasterAuditLogList = wipStatusMasterAuditLogRepository.findAll();
        assertThat(wipStatusMasterAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllWipStatusMasterAuditLogs() throws Exception {
        // Initialize the database
        wipStatusMasterAuditLogRepository.saveAndFlush(wipStatusMasterAuditLog);

        // Get all the wipStatusMasterAuditLogList
        restWipStatusMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wipStatusMasterAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].wpSttusId").value(hasItem(DEFAULT_WP_STTUS_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getWipStatusMasterAuditLog() throws Exception {
        // Initialize the database
        wipStatusMasterAuditLogRepository.saveAndFlush(wipStatusMasterAuditLog);

        // Get the wipStatusMasterAuditLog
        restWipStatusMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, wipStatusMasterAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(wipStatusMasterAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.wpSttusId").value(DEFAULT_WP_STTUS_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingWipStatusMasterAuditLog() throws Exception {
        // Get the wipStatusMasterAuditLog
        restWipStatusMasterAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewWipStatusMasterAuditLog() throws Exception {
        // Initialize the database
        wipStatusMasterAuditLogRepository.saveAndFlush(wipStatusMasterAuditLog);

        int databaseSizeBeforeUpdate = wipStatusMasterAuditLogRepository.findAll().size();

        // Update the wipStatusMasterAuditLog
        WipStatusMasterAuditLog updatedWipStatusMasterAuditLog = wipStatusMasterAuditLogRepository
            .findById(wipStatusMasterAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedWipStatusMasterAuditLog are not directly saved in db
        em.detach(updatedWipStatusMasterAuditLog);
        updatedWipStatusMasterAuditLog
            .wpSttusId(UPDATED_WP_STTUS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        WipStatusMasterAuditLogDTO wipStatusMasterAuditLogDTO = wipStatusMasterAuditLogMapper.toDto(updatedWipStatusMasterAuditLog);

        restWipStatusMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, wipStatusMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipStatusMasterAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the WipStatusMasterAuditLog in the database
        List<WipStatusMasterAuditLog> wipStatusMasterAuditLogList = wipStatusMasterAuditLogRepository.findAll();
        assertThat(wipStatusMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        WipStatusMasterAuditLog testWipStatusMasterAuditLog = wipStatusMasterAuditLogList.get(wipStatusMasterAuditLogList.size() - 1);
        assertThat(testWipStatusMasterAuditLog.getWpSttusId()).isEqualTo(UPDATED_WP_STTUS_ID);
        assertThat(testWipStatusMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testWipStatusMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testWipStatusMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testWipStatusMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testWipStatusMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingWipStatusMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = wipStatusMasterAuditLogRepository.findAll().size();
        wipStatusMasterAuditLog.setId(count.incrementAndGet());

        // Create the WipStatusMasterAuditLog
        WipStatusMasterAuditLogDTO wipStatusMasterAuditLogDTO = wipStatusMasterAuditLogMapper.toDto(wipStatusMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWipStatusMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, wipStatusMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipStatusMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipStatusMasterAuditLog in the database
        List<WipStatusMasterAuditLog> wipStatusMasterAuditLogList = wipStatusMasterAuditLogRepository.findAll();
        assertThat(wipStatusMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWipStatusMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = wipStatusMasterAuditLogRepository.findAll().size();
        wipStatusMasterAuditLog.setId(count.incrementAndGet());

        // Create the WipStatusMasterAuditLog
        WipStatusMasterAuditLogDTO wipStatusMasterAuditLogDTO = wipStatusMasterAuditLogMapper.toDto(wipStatusMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWipStatusMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipStatusMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipStatusMasterAuditLog in the database
        List<WipStatusMasterAuditLog> wipStatusMasterAuditLogList = wipStatusMasterAuditLogRepository.findAll();
        assertThat(wipStatusMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWipStatusMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = wipStatusMasterAuditLogRepository.findAll().size();
        wipStatusMasterAuditLog.setId(count.incrementAndGet());

        // Create the WipStatusMasterAuditLog
        WipStatusMasterAuditLogDTO wipStatusMasterAuditLogDTO = wipStatusMasterAuditLogMapper.toDto(wipStatusMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWipStatusMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(wipStatusMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WipStatusMasterAuditLog in the database
        List<WipStatusMasterAuditLog> wipStatusMasterAuditLogList = wipStatusMasterAuditLogRepository.findAll();
        assertThat(wipStatusMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWipStatusMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        wipStatusMasterAuditLogRepository.saveAndFlush(wipStatusMasterAuditLog);

        int databaseSizeBeforeUpdate = wipStatusMasterAuditLogRepository.findAll().size();

        // Update the wipStatusMasterAuditLog using partial update
        WipStatusMasterAuditLog partialUpdatedWipStatusMasterAuditLog = new WipStatusMasterAuditLog();
        partialUpdatedWipStatusMasterAuditLog.setId(wipStatusMasterAuditLog.getId());

        partialUpdatedWipStatusMasterAuditLog.newRowData(UPDATED_NEW_ROW_DATA).dmlType(UPDATED_DML_TYPE);

        restWipStatusMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWipStatusMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWipStatusMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the WipStatusMasterAuditLog in the database
        List<WipStatusMasterAuditLog> wipStatusMasterAuditLogList = wipStatusMasterAuditLogRepository.findAll();
        assertThat(wipStatusMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        WipStatusMasterAuditLog testWipStatusMasterAuditLog = wipStatusMasterAuditLogList.get(wipStatusMasterAuditLogList.size() - 1);
        assertThat(testWipStatusMasterAuditLog.getWpSttusId()).isEqualTo(DEFAULT_WP_STTUS_ID);
        assertThat(testWipStatusMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testWipStatusMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testWipStatusMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testWipStatusMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testWipStatusMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateWipStatusMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        wipStatusMasterAuditLogRepository.saveAndFlush(wipStatusMasterAuditLog);

        int databaseSizeBeforeUpdate = wipStatusMasterAuditLogRepository.findAll().size();

        // Update the wipStatusMasterAuditLog using partial update
        WipStatusMasterAuditLog partialUpdatedWipStatusMasterAuditLog = new WipStatusMasterAuditLog();
        partialUpdatedWipStatusMasterAuditLog.setId(wipStatusMasterAuditLog.getId());

        partialUpdatedWipStatusMasterAuditLog
            .wpSttusId(UPDATED_WP_STTUS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restWipStatusMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWipStatusMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWipStatusMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the WipStatusMasterAuditLog in the database
        List<WipStatusMasterAuditLog> wipStatusMasterAuditLogList = wipStatusMasterAuditLogRepository.findAll();
        assertThat(wipStatusMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        WipStatusMasterAuditLog testWipStatusMasterAuditLog = wipStatusMasterAuditLogList.get(wipStatusMasterAuditLogList.size() - 1);
        assertThat(testWipStatusMasterAuditLog.getWpSttusId()).isEqualTo(UPDATED_WP_STTUS_ID);
        assertThat(testWipStatusMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testWipStatusMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testWipStatusMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testWipStatusMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testWipStatusMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingWipStatusMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = wipStatusMasterAuditLogRepository.findAll().size();
        wipStatusMasterAuditLog.setId(count.incrementAndGet());

        // Create the WipStatusMasterAuditLog
        WipStatusMasterAuditLogDTO wipStatusMasterAuditLogDTO = wipStatusMasterAuditLogMapper.toDto(wipStatusMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWipStatusMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, wipStatusMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(wipStatusMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipStatusMasterAuditLog in the database
        List<WipStatusMasterAuditLog> wipStatusMasterAuditLogList = wipStatusMasterAuditLogRepository.findAll();
        assertThat(wipStatusMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWipStatusMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = wipStatusMasterAuditLogRepository.findAll().size();
        wipStatusMasterAuditLog.setId(count.incrementAndGet());

        // Create the WipStatusMasterAuditLog
        WipStatusMasterAuditLogDTO wipStatusMasterAuditLogDTO = wipStatusMasterAuditLogMapper.toDto(wipStatusMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWipStatusMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(wipStatusMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WipStatusMasterAuditLog in the database
        List<WipStatusMasterAuditLog> wipStatusMasterAuditLogList = wipStatusMasterAuditLogRepository.findAll();
        assertThat(wipStatusMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWipStatusMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = wipStatusMasterAuditLogRepository.findAll().size();
        wipStatusMasterAuditLog.setId(count.incrementAndGet());

        // Create the WipStatusMasterAuditLog
        WipStatusMasterAuditLogDTO wipStatusMasterAuditLogDTO = wipStatusMasterAuditLogMapper.toDto(wipStatusMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWipStatusMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(wipStatusMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WipStatusMasterAuditLog in the database
        List<WipStatusMasterAuditLog> wipStatusMasterAuditLogList = wipStatusMasterAuditLogRepository.findAll();
        assertThat(wipStatusMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWipStatusMasterAuditLog() throws Exception {
        // Initialize the database
        wipStatusMasterAuditLogRepository.saveAndFlush(wipStatusMasterAuditLog);

        int databaseSizeBeforeDelete = wipStatusMasterAuditLogRepository.findAll().size();

        // Delete the wipStatusMasterAuditLog
        restWipStatusMasterAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, wipStatusMasterAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WipStatusMasterAuditLog> wipStatusMasterAuditLogList = wipStatusMasterAuditLogRepository.findAll();
        assertThat(wipStatusMasterAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
