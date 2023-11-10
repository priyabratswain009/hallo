package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.TaskReasonMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.TaskReasonMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.TaskReasonMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaskReasonMasterAuditLogMapper;
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
 * Integration tests for the {@link TaskReasonMasterAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaskReasonMasterAuditLogResourceIT {

    private static final Long DEFAULT_TSK_RASON_ID = 1L;
    private static final Long UPDATED_TSK_RASON_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/task-reason-master-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TaskReasonMasterAuditLogRepository taskReasonMasterAuditLogRepository;

    @Autowired
    private TaskReasonMasterAuditLogMapper taskReasonMasterAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaskReasonMasterAuditLogMockMvc;

    private TaskReasonMasterAuditLog taskReasonMasterAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskReasonMasterAuditLog createEntity(EntityManager em) {
        TaskReasonMasterAuditLog taskReasonMasterAuditLog = new TaskReasonMasterAuditLog()
            .tskRasonId(DEFAULT_TSK_RASON_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return taskReasonMasterAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskReasonMasterAuditLog createUpdatedEntity(EntityManager em) {
        TaskReasonMasterAuditLog taskReasonMasterAuditLog = new TaskReasonMasterAuditLog()
            .tskRasonId(UPDATED_TSK_RASON_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return taskReasonMasterAuditLog;
    }

    @BeforeEach
    public void initTest() {
        taskReasonMasterAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createTaskReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeCreate = taskReasonMasterAuditLogRepository.findAll().size();
        // Create the TaskReasonMasterAuditLog
        TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO = taskReasonMasterAuditLogMapper.toDto(taskReasonMasterAuditLog);
        restTaskReasonMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskReasonMasterAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TaskReasonMasterAuditLog in the database
        List<TaskReasonMasterAuditLog> taskReasonMasterAuditLogList = taskReasonMasterAuditLogRepository.findAll();
        assertThat(taskReasonMasterAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        TaskReasonMasterAuditLog testTaskReasonMasterAuditLog = taskReasonMasterAuditLogList.get(taskReasonMasterAuditLogList.size() - 1);
        assertThat(testTaskReasonMasterAuditLog.getTskRasonId()).isEqualTo(DEFAULT_TSK_RASON_ID);
        assertThat(testTaskReasonMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testTaskReasonMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testTaskReasonMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testTaskReasonMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testTaskReasonMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createTaskReasonMasterAuditLogWithExistingId() throws Exception {
        // Create the TaskReasonMasterAuditLog with an existing ID
        taskReasonMasterAuditLog.setId(1L);
        TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO = taskReasonMasterAuditLogMapper.toDto(taskReasonMasterAuditLog);

        int databaseSizeBeforeCreate = taskReasonMasterAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaskReasonMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskReasonMasterAuditLog in the database
        List<TaskReasonMasterAuditLog> taskReasonMasterAuditLogList = taskReasonMasterAuditLogRepository.findAll();
        assertThat(taskReasonMasterAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTaskReasonMasterAuditLogs() throws Exception {
        // Initialize the database
        taskReasonMasterAuditLogRepository.saveAndFlush(taskReasonMasterAuditLog);

        // Get all the taskReasonMasterAuditLogList
        restTaskReasonMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taskReasonMasterAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].tskRasonId").value(hasItem(DEFAULT_TSK_RASON_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getTaskReasonMasterAuditLog() throws Exception {
        // Initialize the database
        taskReasonMasterAuditLogRepository.saveAndFlush(taskReasonMasterAuditLog);

        // Get the taskReasonMasterAuditLog
        restTaskReasonMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, taskReasonMasterAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taskReasonMasterAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.tskRasonId").value(DEFAULT_TSK_RASON_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingTaskReasonMasterAuditLog() throws Exception {
        // Get the taskReasonMasterAuditLog
        restTaskReasonMasterAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTaskReasonMasterAuditLog() throws Exception {
        // Initialize the database
        taskReasonMasterAuditLogRepository.saveAndFlush(taskReasonMasterAuditLog);

        int databaseSizeBeforeUpdate = taskReasonMasterAuditLogRepository.findAll().size();

        // Update the taskReasonMasterAuditLog
        TaskReasonMasterAuditLog updatedTaskReasonMasterAuditLog = taskReasonMasterAuditLogRepository
            .findById(taskReasonMasterAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedTaskReasonMasterAuditLog are not directly saved in db
        em.detach(updatedTaskReasonMasterAuditLog);
        updatedTaskReasonMasterAuditLog
            .tskRasonId(UPDATED_TSK_RASON_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO = taskReasonMasterAuditLogMapper.toDto(updatedTaskReasonMasterAuditLog);

        restTaskReasonMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taskReasonMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskReasonMasterAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the TaskReasonMasterAuditLog in the database
        List<TaskReasonMasterAuditLog> taskReasonMasterAuditLogList = taskReasonMasterAuditLogRepository.findAll();
        assertThat(taskReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        TaskReasonMasterAuditLog testTaskReasonMasterAuditLog = taskReasonMasterAuditLogList.get(taskReasonMasterAuditLogList.size() - 1);
        assertThat(testTaskReasonMasterAuditLog.getTskRasonId()).isEqualTo(UPDATED_TSK_RASON_ID);
        assertThat(testTaskReasonMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testTaskReasonMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testTaskReasonMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testTaskReasonMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testTaskReasonMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingTaskReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = taskReasonMasterAuditLogRepository.findAll().size();
        taskReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the TaskReasonMasterAuditLog
        TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO = taskReasonMasterAuditLogMapper.toDto(taskReasonMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskReasonMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taskReasonMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskReasonMasterAuditLog in the database
        List<TaskReasonMasterAuditLog> taskReasonMasterAuditLogList = taskReasonMasterAuditLogRepository.findAll();
        assertThat(taskReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaskReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = taskReasonMasterAuditLogRepository.findAll().size();
        taskReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the TaskReasonMasterAuditLog
        TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO = taskReasonMasterAuditLogMapper.toDto(taskReasonMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskReasonMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskReasonMasterAuditLog in the database
        List<TaskReasonMasterAuditLog> taskReasonMasterAuditLogList = taskReasonMasterAuditLogRepository.findAll();
        assertThat(taskReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaskReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = taskReasonMasterAuditLogRepository.findAll().size();
        taskReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the TaskReasonMasterAuditLog
        TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO = taskReasonMasterAuditLogMapper.toDto(taskReasonMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskReasonMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taskReasonMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaskReasonMasterAuditLog in the database
        List<TaskReasonMasterAuditLog> taskReasonMasterAuditLogList = taskReasonMasterAuditLogRepository.findAll();
        assertThat(taskReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaskReasonMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        taskReasonMasterAuditLogRepository.saveAndFlush(taskReasonMasterAuditLog);

        int databaseSizeBeforeUpdate = taskReasonMasterAuditLogRepository.findAll().size();

        // Update the taskReasonMasterAuditLog using partial update
        TaskReasonMasterAuditLog partialUpdatedTaskReasonMasterAuditLog = new TaskReasonMasterAuditLog();
        partialUpdatedTaskReasonMasterAuditLog.setId(taskReasonMasterAuditLog.getId());

        partialUpdatedTaskReasonMasterAuditLog.oldRowData(UPDATED_OLD_ROW_DATA);

        restTaskReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaskReasonMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaskReasonMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the TaskReasonMasterAuditLog in the database
        List<TaskReasonMasterAuditLog> taskReasonMasterAuditLogList = taskReasonMasterAuditLogRepository.findAll();
        assertThat(taskReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        TaskReasonMasterAuditLog testTaskReasonMasterAuditLog = taskReasonMasterAuditLogList.get(taskReasonMasterAuditLogList.size() - 1);
        assertThat(testTaskReasonMasterAuditLog.getTskRasonId()).isEqualTo(DEFAULT_TSK_RASON_ID);
        assertThat(testTaskReasonMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testTaskReasonMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testTaskReasonMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testTaskReasonMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testTaskReasonMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateTaskReasonMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        taskReasonMasterAuditLogRepository.saveAndFlush(taskReasonMasterAuditLog);

        int databaseSizeBeforeUpdate = taskReasonMasterAuditLogRepository.findAll().size();

        // Update the taskReasonMasterAuditLog using partial update
        TaskReasonMasterAuditLog partialUpdatedTaskReasonMasterAuditLog = new TaskReasonMasterAuditLog();
        partialUpdatedTaskReasonMasterAuditLog.setId(taskReasonMasterAuditLog.getId());

        partialUpdatedTaskReasonMasterAuditLog
            .tskRasonId(UPDATED_TSK_RASON_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restTaskReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaskReasonMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaskReasonMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the TaskReasonMasterAuditLog in the database
        List<TaskReasonMasterAuditLog> taskReasonMasterAuditLogList = taskReasonMasterAuditLogRepository.findAll();
        assertThat(taskReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        TaskReasonMasterAuditLog testTaskReasonMasterAuditLog = taskReasonMasterAuditLogList.get(taskReasonMasterAuditLogList.size() - 1);
        assertThat(testTaskReasonMasterAuditLog.getTskRasonId()).isEqualTo(UPDATED_TSK_RASON_ID);
        assertThat(testTaskReasonMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testTaskReasonMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testTaskReasonMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testTaskReasonMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testTaskReasonMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingTaskReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = taskReasonMasterAuditLogRepository.findAll().size();
        taskReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the TaskReasonMasterAuditLog
        TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO = taskReasonMasterAuditLogMapper.toDto(taskReasonMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taskReasonMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskReasonMasterAuditLog in the database
        List<TaskReasonMasterAuditLog> taskReasonMasterAuditLogList = taskReasonMasterAuditLogRepository.findAll();
        assertThat(taskReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaskReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = taskReasonMasterAuditLogRepository.findAll().size();
        taskReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the TaskReasonMasterAuditLog
        TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO = taskReasonMasterAuditLogMapper.toDto(taskReasonMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaskReasonMasterAuditLog in the database
        List<TaskReasonMasterAuditLog> taskReasonMasterAuditLogList = taskReasonMasterAuditLogRepository.findAll();
        assertThat(taskReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaskReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = taskReasonMasterAuditLogRepository.findAll().size();
        taskReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the TaskReasonMasterAuditLog
        TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO = taskReasonMasterAuditLogMapper.toDto(taskReasonMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaskReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taskReasonMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaskReasonMasterAuditLog in the database
        List<TaskReasonMasterAuditLog> taskReasonMasterAuditLogList = taskReasonMasterAuditLogRepository.findAll();
        assertThat(taskReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaskReasonMasterAuditLog() throws Exception {
        // Initialize the database
        taskReasonMasterAuditLogRepository.saveAndFlush(taskReasonMasterAuditLog);

        int databaseSizeBeforeDelete = taskReasonMasterAuditLogRepository.findAll().size();

        // Delete the taskReasonMasterAuditLog
        restTaskReasonMasterAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, taskReasonMasterAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TaskReasonMasterAuditLog> taskReasonMasterAuditLogList = taskReasonMasterAuditLogRepository.findAll();
        assertThat(taskReasonMasterAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
