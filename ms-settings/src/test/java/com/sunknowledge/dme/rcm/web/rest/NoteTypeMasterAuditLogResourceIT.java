package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.NoteTypeMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.NoteTypeMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.NoteTypeMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.NoteTypeMasterAuditLogMapper;
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
 * Integration tests for the {@link NoteTypeMasterAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NoteTypeMasterAuditLogResourceIT {

    private static final Long DEFAULT_NOT_TPE_ID = 1L;
    private static final Long UPDATED_NOT_TPE_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/note-type-master-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NoteTypeMasterAuditLogRepository noteTypeMasterAuditLogRepository;

    @Autowired
    private NoteTypeMasterAuditLogMapper noteTypeMasterAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNoteTypeMasterAuditLogMockMvc;

    private NoteTypeMasterAuditLog noteTypeMasterAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoteTypeMasterAuditLog createEntity(EntityManager em) {
        NoteTypeMasterAuditLog noteTypeMasterAuditLog = new NoteTypeMasterAuditLog()
            .notTpeId(DEFAULT_NOT_TPE_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return noteTypeMasterAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoteTypeMasterAuditLog createUpdatedEntity(EntityManager em) {
        NoteTypeMasterAuditLog noteTypeMasterAuditLog = new NoteTypeMasterAuditLog()
            .notTpeId(UPDATED_NOT_TPE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return noteTypeMasterAuditLog;
    }

    @BeforeEach
    public void initTest() {
        noteTypeMasterAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createNoteTypeMasterAuditLog() throws Exception {
        int databaseSizeBeforeCreate = noteTypeMasterAuditLogRepository.findAll().size();
        // Create the NoteTypeMasterAuditLog
        NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO = noteTypeMasterAuditLogMapper.toDto(noteTypeMasterAuditLog);
        restNoteTypeMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteTypeMasterAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the NoteTypeMasterAuditLog in the database
        List<NoteTypeMasterAuditLog> noteTypeMasterAuditLogList = noteTypeMasterAuditLogRepository.findAll();
        assertThat(noteTypeMasterAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        NoteTypeMasterAuditLog testNoteTypeMasterAuditLog = noteTypeMasterAuditLogList.get(noteTypeMasterAuditLogList.size() - 1);
        assertThat(testNoteTypeMasterAuditLog.getNotTpeId()).isEqualTo(DEFAULT_NOT_TPE_ID);
        assertThat(testNoteTypeMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testNoteTypeMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testNoteTypeMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testNoteTypeMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testNoteTypeMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createNoteTypeMasterAuditLogWithExistingId() throws Exception {
        // Create the NoteTypeMasterAuditLog with an existing ID
        noteTypeMasterAuditLog.setId(1L);
        NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO = noteTypeMasterAuditLogMapper.toDto(noteTypeMasterAuditLog);

        int databaseSizeBeforeCreate = noteTypeMasterAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNoteTypeMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteTypeMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteTypeMasterAuditLog in the database
        List<NoteTypeMasterAuditLog> noteTypeMasterAuditLogList = noteTypeMasterAuditLogRepository.findAll();
        assertThat(noteTypeMasterAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllNoteTypeMasterAuditLogs() throws Exception {
        // Initialize the database
        noteTypeMasterAuditLogRepository.saveAndFlush(noteTypeMasterAuditLog);

        // Get all the noteTypeMasterAuditLogList
        restNoteTypeMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(noteTypeMasterAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].notTpeId").value(hasItem(DEFAULT_NOT_TPE_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getNoteTypeMasterAuditLog() throws Exception {
        // Initialize the database
        noteTypeMasterAuditLogRepository.saveAndFlush(noteTypeMasterAuditLog);

        // Get the noteTypeMasterAuditLog
        restNoteTypeMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, noteTypeMasterAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(noteTypeMasterAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.notTpeId").value(DEFAULT_NOT_TPE_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingNoteTypeMasterAuditLog() throws Exception {
        // Get the noteTypeMasterAuditLog
        restNoteTypeMasterAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingNoteTypeMasterAuditLog() throws Exception {
        // Initialize the database
        noteTypeMasterAuditLogRepository.saveAndFlush(noteTypeMasterAuditLog);

        int databaseSizeBeforeUpdate = noteTypeMasterAuditLogRepository.findAll().size();

        // Update the noteTypeMasterAuditLog
        NoteTypeMasterAuditLog updatedNoteTypeMasterAuditLog = noteTypeMasterAuditLogRepository
            .findById(noteTypeMasterAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedNoteTypeMasterAuditLog are not directly saved in db
        em.detach(updatedNoteTypeMasterAuditLog);
        updatedNoteTypeMasterAuditLog
            .notTpeId(UPDATED_NOT_TPE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO = noteTypeMasterAuditLogMapper.toDto(updatedNoteTypeMasterAuditLog);

        restNoteTypeMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, noteTypeMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteTypeMasterAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the NoteTypeMasterAuditLog in the database
        List<NoteTypeMasterAuditLog> noteTypeMasterAuditLogList = noteTypeMasterAuditLogRepository.findAll();
        assertThat(noteTypeMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        NoteTypeMasterAuditLog testNoteTypeMasterAuditLog = noteTypeMasterAuditLogList.get(noteTypeMasterAuditLogList.size() - 1);
        assertThat(testNoteTypeMasterAuditLog.getNotTpeId()).isEqualTo(UPDATED_NOT_TPE_ID);
        assertThat(testNoteTypeMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testNoteTypeMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testNoteTypeMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testNoteTypeMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testNoteTypeMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingNoteTypeMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = noteTypeMasterAuditLogRepository.findAll().size();
        noteTypeMasterAuditLog.setId(count.incrementAndGet());

        // Create the NoteTypeMasterAuditLog
        NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO = noteTypeMasterAuditLogMapper.toDto(noteTypeMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoteTypeMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, noteTypeMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteTypeMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteTypeMasterAuditLog in the database
        List<NoteTypeMasterAuditLog> noteTypeMasterAuditLogList = noteTypeMasterAuditLogRepository.findAll();
        assertThat(noteTypeMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNoteTypeMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = noteTypeMasterAuditLogRepository.findAll().size();
        noteTypeMasterAuditLog.setId(count.incrementAndGet());

        // Create the NoteTypeMasterAuditLog
        NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO = noteTypeMasterAuditLogMapper.toDto(noteTypeMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoteTypeMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteTypeMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteTypeMasterAuditLog in the database
        List<NoteTypeMasterAuditLog> noteTypeMasterAuditLogList = noteTypeMasterAuditLogRepository.findAll();
        assertThat(noteTypeMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNoteTypeMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = noteTypeMasterAuditLogRepository.findAll().size();
        noteTypeMasterAuditLog.setId(count.incrementAndGet());

        // Create the NoteTypeMasterAuditLog
        NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO = noteTypeMasterAuditLogMapper.toDto(noteTypeMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoteTypeMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteTypeMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NoteTypeMasterAuditLog in the database
        List<NoteTypeMasterAuditLog> noteTypeMasterAuditLogList = noteTypeMasterAuditLogRepository.findAll();
        assertThat(noteTypeMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNoteTypeMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        noteTypeMasterAuditLogRepository.saveAndFlush(noteTypeMasterAuditLog);

        int databaseSizeBeforeUpdate = noteTypeMasterAuditLogRepository.findAll().size();

        // Update the noteTypeMasterAuditLog using partial update
        NoteTypeMasterAuditLog partialUpdatedNoteTypeMasterAuditLog = new NoteTypeMasterAuditLog();
        partialUpdatedNoteTypeMasterAuditLog.setId(noteTypeMasterAuditLog.getId());

        partialUpdatedNoteTypeMasterAuditLog
            .notTpeId(UPDATED_NOT_TPE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP);

        restNoteTypeMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNoteTypeMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNoteTypeMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the NoteTypeMasterAuditLog in the database
        List<NoteTypeMasterAuditLog> noteTypeMasterAuditLogList = noteTypeMasterAuditLogRepository.findAll();
        assertThat(noteTypeMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        NoteTypeMasterAuditLog testNoteTypeMasterAuditLog = noteTypeMasterAuditLogList.get(noteTypeMasterAuditLogList.size() - 1);
        assertThat(testNoteTypeMasterAuditLog.getNotTpeId()).isEqualTo(UPDATED_NOT_TPE_ID);
        assertThat(testNoteTypeMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testNoteTypeMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testNoteTypeMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testNoteTypeMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testNoteTypeMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateNoteTypeMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        noteTypeMasterAuditLogRepository.saveAndFlush(noteTypeMasterAuditLog);

        int databaseSizeBeforeUpdate = noteTypeMasterAuditLogRepository.findAll().size();

        // Update the noteTypeMasterAuditLog using partial update
        NoteTypeMasterAuditLog partialUpdatedNoteTypeMasterAuditLog = new NoteTypeMasterAuditLog();
        partialUpdatedNoteTypeMasterAuditLog.setId(noteTypeMasterAuditLog.getId());

        partialUpdatedNoteTypeMasterAuditLog
            .notTpeId(UPDATED_NOT_TPE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restNoteTypeMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNoteTypeMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNoteTypeMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the NoteTypeMasterAuditLog in the database
        List<NoteTypeMasterAuditLog> noteTypeMasterAuditLogList = noteTypeMasterAuditLogRepository.findAll();
        assertThat(noteTypeMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        NoteTypeMasterAuditLog testNoteTypeMasterAuditLog = noteTypeMasterAuditLogList.get(noteTypeMasterAuditLogList.size() - 1);
        assertThat(testNoteTypeMasterAuditLog.getNotTpeId()).isEqualTo(UPDATED_NOT_TPE_ID);
        assertThat(testNoteTypeMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testNoteTypeMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testNoteTypeMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testNoteTypeMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testNoteTypeMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingNoteTypeMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = noteTypeMasterAuditLogRepository.findAll().size();
        noteTypeMasterAuditLog.setId(count.incrementAndGet());

        // Create the NoteTypeMasterAuditLog
        NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO = noteTypeMasterAuditLogMapper.toDto(noteTypeMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoteTypeMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, noteTypeMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noteTypeMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteTypeMasterAuditLog in the database
        List<NoteTypeMasterAuditLog> noteTypeMasterAuditLogList = noteTypeMasterAuditLogRepository.findAll();
        assertThat(noteTypeMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNoteTypeMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = noteTypeMasterAuditLogRepository.findAll().size();
        noteTypeMasterAuditLog.setId(count.incrementAndGet());

        // Create the NoteTypeMasterAuditLog
        NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO = noteTypeMasterAuditLogMapper.toDto(noteTypeMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoteTypeMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noteTypeMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteTypeMasterAuditLog in the database
        List<NoteTypeMasterAuditLog> noteTypeMasterAuditLogList = noteTypeMasterAuditLogRepository.findAll();
        assertThat(noteTypeMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNoteTypeMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = noteTypeMasterAuditLogRepository.findAll().size();
        noteTypeMasterAuditLog.setId(count.incrementAndGet());

        // Create the NoteTypeMasterAuditLog
        NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO = noteTypeMasterAuditLogMapper.toDto(noteTypeMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoteTypeMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noteTypeMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NoteTypeMasterAuditLog in the database
        List<NoteTypeMasterAuditLog> noteTypeMasterAuditLogList = noteTypeMasterAuditLogRepository.findAll();
        assertThat(noteTypeMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNoteTypeMasterAuditLog() throws Exception {
        // Initialize the database
        noteTypeMasterAuditLogRepository.saveAndFlush(noteTypeMasterAuditLog);

        int databaseSizeBeforeDelete = noteTypeMasterAuditLogRepository.findAll().size();

        // Delete the noteTypeMasterAuditLog
        restNoteTypeMasterAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, noteTypeMasterAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NoteTypeMasterAuditLog> noteTypeMasterAuditLogList = noteTypeMasterAuditLogRepository.findAll();
        assertThat(noteTypeMasterAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
