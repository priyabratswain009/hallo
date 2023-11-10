package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.NoteReasonMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.NoteReasonMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.NoteReasonMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.NoteReasonMasterAuditLogMapper;
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
 * Integration tests for the {@link NoteReasonMasterAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NoteReasonMasterAuditLogResourceIT {

    private static final Long DEFAULT_NOT_RASON_ID = 1L;
    private static final Long UPDATED_NOT_RASON_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/note-reason-master-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NoteReasonMasterAuditLogRepository noteReasonMasterAuditLogRepository;

    @Autowired
    private NoteReasonMasterAuditLogMapper noteReasonMasterAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNoteReasonMasterAuditLogMockMvc;

    private NoteReasonMasterAuditLog noteReasonMasterAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoteReasonMasterAuditLog createEntity(EntityManager em) {
        NoteReasonMasterAuditLog noteReasonMasterAuditLog = new NoteReasonMasterAuditLog()
            .notRasonId(DEFAULT_NOT_RASON_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return noteReasonMasterAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoteReasonMasterAuditLog createUpdatedEntity(EntityManager em) {
        NoteReasonMasterAuditLog noteReasonMasterAuditLog = new NoteReasonMasterAuditLog()
            .notRasonId(UPDATED_NOT_RASON_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return noteReasonMasterAuditLog;
    }

    @BeforeEach
    public void initTest() {
        noteReasonMasterAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createNoteReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeCreate = noteReasonMasterAuditLogRepository.findAll().size();
        // Create the NoteReasonMasterAuditLog
        NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO = noteReasonMasterAuditLogMapper.toDto(noteReasonMasterAuditLog);
        restNoteReasonMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteReasonMasterAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the NoteReasonMasterAuditLog in the database
        List<NoteReasonMasterAuditLog> noteReasonMasterAuditLogList = noteReasonMasterAuditLogRepository.findAll();
        assertThat(noteReasonMasterAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        NoteReasonMasterAuditLog testNoteReasonMasterAuditLog = noteReasonMasterAuditLogList.get(noteReasonMasterAuditLogList.size() - 1);
        assertThat(testNoteReasonMasterAuditLog.getNotRasonId()).isEqualTo(DEFAULT_NOT_RASON_ID);
        assertThat(testNoteReasonMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testNoteReasonMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testNoteReasonMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testNoteReasonMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testNoteReasonMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createNoteReasonMasterAuditLogWithExistingId() throws Exception {
        // Create the NoteReasonMasterAuditLog with an existing ID
        noteReasonMasterAuditLog.setId(1L);
        NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO = noteReasonMasterAuditLogMapper.toDto(noteReasonMasterAuditLog);

        int databaseSizeBeforeCreate = noteReasonMasterAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNoteReasonMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteReasonMasterAuditLog in the database
        List<NoteReasonMasterAuditLog> noteReasonMasterAuditLogList = noteReasonMasterAuditLogRepository.findAll();
        assertThat(noteReasonMasterAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllNoteReasonMasterAuditLogs() throws Exception {
        // Initialize the database
        noteReasonMasterAuditLogRepository.saveAndFlush(noteReasonMasterAuditLog);

        // Get all the noteReasonMasterAuditLogList
        restNoteReasonMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(noteReasonMasterAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].notRasonId").value(hasItem(DEFAULT_NOT_RASON_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getNoteReasonMasterAuditLog() throws Exception {
        // Initialize the database
        noteReasonMasterAuditLogRepository.saveAndFlush(noteReasonMasterAuditLog);

        // Get the noteReasonMasterAuditLog
        restNoteReasonMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, noteReasonMasterAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(noteReasonMasterAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.notRasonId").value(DEFAULT_NOT_RASON_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingNoteReasonMasterAuditLog() throws Exception {
        // Get the noteReasonMasterAuditLog
        restNoteReasonMasterAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewNoteReasonMasterAuditLog() throws Exception {
        // Initialize the database
        noteReasonMasterAuditLogRepository.saveAndFlush(noteReasonMasterAuditLog);

        int databaseSizeBeforeUpdate = noteReasonMasterAuditLogRepository.findAll().size();

        // Update the noteReasonMasterAuditLog
        NoteReasonMasterAuditLog updatedNoteReasonMasterAuditLog = noteReasonMasterAuditLogRepository
            .findById(noteReasonMasterAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedNoteReasonMasterAuditLog are not directly saved in db
        em.detach(updatedNoteReasonMasterAuditLog);
        updatedNoteReasonMasterAuditLog
            .notRasonId(UPDATED_NOT_RASON_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO = noteReasonMasterAuditLogMapper.toDto(updatedNoteReasonMasterAuditLog);

        restNoteReasonMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, noteReasonMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteReasonMasterAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the NoteReasonMasterAuditLog in the database
        List<NoteReasonMasterAuditLog> noteReasonMasterAuditLogList = noteReasonMasterAuditLogRepository.findAll();
        assertThat(noteReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        NoteReasonMasterAuditLog testNoteReasonMasterAuditLog = noteReasonMasterAuditLogList.get(noteReasonMasterAuditLogList.size() - 1);
        assertThat(testNoteReasonMasterAuditLog.getNotRasonId()).isEqualTo(UPDATED_NOT_RASON_ID);
        assertThat(testNoteReasonMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testNoteReasonMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testNoteReasonMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testNoteReasonMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testNoteReasonMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingNoteReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = noteReasonMasterAuditLogRepository.findAll().size();
        noteReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the NoteReasonMasterAuditLog
        NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO = noteReasonMasterAuditLogMapper.toDto(noteReasonMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoteReasonMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, noteReasonMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteReasonMasterAuditLog in the database
        List<NoteReasonMasterAuditLog> noteReasonMasterAuditLogList = noteReasonMasterAuditLogRepository.findAll();
        assertThat(noteReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNoteReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = noteReasonMasterAuditLogRepository.findAll().size();
        noteReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the NoteReasonMasterAuditLog
        NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO = noteReasonMasterAuditLogMapper.toDto(noteReasonMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoteReasonMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteReasonMasterAuditLog in the database
        List<NoteReasonMasterAuditLog> noteReasonMasterAuditLogList = noteReasonMasterAuditLogRepository.findAll();
        assertThat(noteReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNoteReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = noteReasonMasterAuditLogRepository.findAll().size();
        noteReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the NoteReasonMasterAuditLog
        NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO = noteReasonMasterAuditLogMapper.toDto(noteReasonMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoteReasonMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteReasonMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NoteReasonMasterAuditLog in the database
        List<NoteReasonMasterAuditLog> noteReasonMasterAuditLogList = noteReasonMasterAuditLogRepository.findAll();
        assertThat(noteReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNoteReasonMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        noteReasonMasterAuditLogRepository.saveAndFlush(noteReasonMasterAuditLog);

        int databaseSizeBeforeUpdate = noteReasonMasterAuditLogRepository.findAll().size();

        // Update the noteReasonMasterAuditLog using partial update
        NoteReasonMasterAuditLog partialUpdatedNoteReasonMasterAuditLog = new NoteReasonMasterAuditLog();
        partialUpdatedNoteReasonMasterAuditLog.setId(noteReasonMasterAuditLog.getId());

        partialUpdatedNoteReasonMasterAuditLog.dmlTimestamp(UPDATED_DML_TIMESTAMP);

        restNoteReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNoteReasonMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNoteReasonMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the NoteReasonMasterAuditLog in the database
        List<NoteReasonMasterAuditLog> noteReasonMasterAuditLogList = noteReasonMasterAuditLogRepository.findAll();
        assertThat(noteReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        NoteReasonMasterAuditLog testNoteReasonMasterAuditLog = noteReasonMasterAuditLogList.get(noteReasonMasterAuditLogList.size() - 1);
        assertThat(testNoteReasonMasterAuditLog.getNotRasonId()).isEqualTo(DEFAULT_NOT_RASON_ID);
        assertThat(testNoteReasonMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testNoteReasonMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testNoteReasonMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testNoteReasonMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testNoteReasonMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateNoteReasonMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        noteReasonMasterAuditLogRepository.saveAndFlush(noteReasonMasterAuditLog);

        int databaseSizeBeforeUpdate = noteReasonMasterAuditLogRepository.findAll().size();

        // Update the noteReasonMasterAuditLog using partial update
        NoteReasonMasterAuditLog partialUpdatedNoteReasonMasterAuditLog = new NoteReasonMasterAuditLog();
        partialUpdatedNoteReasonMasterAuditLog.setId(noteReasonMasterAuditLog.getId());

        partialUpdatedNoteReasonMasterAuditLog
            .notRasonId(UPDATED_NOT_RASON_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restNoteReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNoteReasonMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNoteReasonMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the NoteReasonMasterAuditLog in the database
        List<NoteReasonMasterAuditLog> noteReasonMasterAuditLogList = noteReasonMasterAuditLogRepository.findAll();
        assertThat(noteReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        NoteReasonMasterAuditLog testNoteReasonMasterAuditLog = noteReasonMasterAuditLogList.get(noteReasonMasterAuditLogList.size() - 1);
        assertThat(testNoteReasonMasterAuditLog.getNotRasonId()).isEqualTo(UPDATED_NOT_RASON_ID);
        assertThat(testNoteReasonMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testNoteReasonMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testNoteReasonMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testNoteReasonMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testNoteReasonMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingNoteReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = noteReasonMasterAuditLogRepository.findAll().size();
        noteReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the NoteReasonMasterAuditLog
        NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO = noteReasonMasterAuditLogMapper.toDto(noteReasonMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoteReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, noteReasonMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noteReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteReasonMasterAuditLog in the database
        List<NoteReasonMasterAuditLog> noteReasonMasterAuditLogList = noteReasonMasterAuditLogRepository.findAll();
        assertThat(noteReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNoteReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = noteReasonMasterAuditLogRepository.findAll().size();
        noteReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the NoteReasonMasterAuditLog
        NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO = noteReasonMasterAuditLogMapper.toDto(noteReasonMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoteReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noteReasonMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteReasonMasterAuditLog in the database
        List<NoteReasonMasterAuditLog> noteReasonMasterAuditLogList = noteReasonMasterAuditLogRepository.findAll();
        assertThat(noteReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNoteReasonMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = noteReasonMasterAuditLogRepository.findAll().size();
        noteReasonMasterAuditLog.setId(count.incrementAndGet());

        // Create the NoteReasonMasterAuditLog
        NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO = noteReasonMasterAuditLogMapper.toDto(noteReasonMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoteReasonMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noteReasonMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NoteReasonMasterAuditLog in the database
        List<NoteReasonMasterAuditLog> noteReasonMasterAuditLogList = noteReasonMasterAuditLogRepository.findAll();
        assertThat(noteReasonMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNoteReasonMasterAuditLog() throws Exception {
        // Initialize the database
        noteReasonMasterAuditLogRepository.saveAndFlush(noteReasonMasterAuditLog);

        int databaseSizeBeforeDelete = noteReasonMasterAuditLogRepository.findAll().size();

        // Delete the noteReasonMasterAuditLog
        restNoteReasonMasterAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, noteReasonMasterAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NoteReasonMasterAuditLog> noteReasonMasterAuditLogList = noteReasonMasterAuditLogRepository.findAll();
        assertThat(noteReasonMasterAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
