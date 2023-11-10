package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PosMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.PosMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.PosMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PosMasterAuditLogMapper;
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
 * Integration tests for the {@link PosMasterAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PosMasterAuditLogResourceIT {

    private static final Long DEFAULT_PS_ID = 1L;
    private static final Long UPDATED_PS_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/pos-master-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PosMasterAuditLogRepository posMasterAuditLogRepository;

    @Autowired
    private PosMasterAuditLogMapper posMasterAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPosMasterAuditLogMockMvc;

    private PosMasterAuditLog posMasterAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PosMasterAuditLog createEntity(EntityManager em) {
        PosMasterAuditLog posMasterAuditLog = new PosMasterAuditLog()
            .psId(DEFAULT_PS_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return posMasterAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PosMasterAuditLog createUpdatedEntity(EntityManager em) {
        PosMasterAuditLog posMasterAuditLog = new PosMasterAuditLog()
            .psId(UPDATED_PS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return posMasterAuditLog;
    }

    @BeforeEach
    public void initTest() {
        posMasterAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createPosMasterAuditLog() throws Exception {
        int databaseSizeBeforeCreate = posMasterAuditLogRepository.findAll().size();
        // Create the PosMasterAuditLog
        PosMasterAuditLogDTO posMasterAuditLogDTO = posMasterAuditLogMapper.toDto(posMasterAuditLog);
        restPosMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(posMasterAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PosMasterAuditLog in the database
        List<PosMasterAuditLog> posMasterAuditLogList = posMasterAuditLogRepository.findAll();
        assertThat(posMasterAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        PosMasterAuditLog testPosMasterAuditLog = posMasterAuditLogList.get(posMasterAuditLogList.size() - 1);
        assertThat(testPosMasterAuditLog.getPsId()).isEqualTo(DEFAULT_PS_ID);
        assertThat(testPosMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPosMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testPosMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPosMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testPosMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createPosMasterAuditLogWithExistingId() throws Exception {
        // Create the PosMasterAuditLog with an existing ID
        posMasterAuditLog.setId(1L);
        PosMasterAuditLogDTO posMasterAuditLogDTO = posMasterAuditLogMapper.toDto(posMasterAuditLog);

        int databaseSizeBeforeCreate = posMasterAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPosMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(posMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PosMasterAuditLog in the database
        List<PosMasterAuditLog> posMasterAuditLogList = posMasterAuditLogRepository.findAll();
        assertThat(posMasterAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPosMasterAuditLogs() throws Exception {
        // Initialize the database
        posMasterAuditLogRepository.saveAndFlush(posMasterAuditLog);

        // Get all the posMasterAuditLogList
        restPosMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(posMasterAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].psId").value(hasItem(DEFAULT_PS_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getPosMasterAuditLog() throws Exception {
        // Initialize the database
        posMasterAuditLogRepository.saveAndFlush(posMasterAuditLog);

        // Get the posMasterAuditLog
        restPosMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, posMasterAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(posMasterAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.psId").value(DEFAULT_PS_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingPosMasterAuditLog() throws Exception {
        // Get the posMasterAuditLog
        restPosMasterAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPosMasterAuditLog() throws Exception {
        // Initialize the database
        posMasterAuditLogRepository.saveAndFlush(posMasterAuditLog);

        int databaseSizeBeforeUpdate = posMasterAuditLogRepository.findAll().size();

        // Update the posMasterAuditLog
        PosMasterAuditLog updatedPosMasterAuditLog = posMasterAuditLogRepository.findById(posMasterAuditLog.getId()).get();
        // Disconnect from session so that the updates on updatedPosMasterAuditLog are not directly saved in db
        em.detach(updatedPosMasterAuditLog);
        updatedPosMasterAuditLog
            .psId(UPDATED_PS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        PosMasterAuditLogDTO posMasterAuditLogDTO = posMasterAuditLogMapper.toDto(updatedPosMasterAuditLog);

        restPosMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, posMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(posMasterAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the PosMasterAuditLog in the database
        List<PosMasterAuditLog> posMasterAuditLogList = posMasterAuditLogRepository.findAll();
        assertThat(posMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PosMasterAuditLog testPosMasterAuditLog = posMasterAuditLogList.get(posMasterAuditLogList.size() - 1);
        assertThat(testPosMasterAuditLog.getPsId()).isEqualTo(UPDATED_PS_ID);
        assertThat(testPosMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPosMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPosMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPosMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPosMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingPosMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = posMasterAuditLogRepository.findAll().size();
        posMasterAuditLog.setId(count.incrementAndGet());

        // Create the PosMasterAuditLog
        PosMasterAuditLogDTO posMasterAuditLogDTO = posMasterAuditLogMapper.toDto(posMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPosMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, posMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(posMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PosMasterAuditLog in the database
        List<PosMasterAuditLog> posMasterAuditLogList = posMasterAuditLogRepository.findAll();
        assertThat(posMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPosMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = posMasterAuditLogRepository.findAll().size();
        posMasterAuditLog.setId(count.incrementAndGet());

        // Create the PosMasterAuditLog
        PosMasterAuditLogDTO posMasterAuditLogDTO = posMasterAuditLogMapper.toDto(posMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPosMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(posMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PosMasterAuditLog in the database
        List<PosMasterAuditLog> posMasterAuditLogList = posMasterAuditLogRepository.findAll();
        assertThat(posMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPosMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = posMasterAuditLogRepository.findAll().size();
        posMasterAuditLog.setId(count.incrementAndGet());

        // Create the PosMasterAuditLog
        PosMasterAuditLogDTO posMasterAuditLogDTO = posMasterAuditLogMapper.toDto(posMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPosMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(posMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PosMasterAuditLog in the database
        List<PosMasterAuditLog> posMasterAuditLogList = posMasterAuditLogRepository.findAll();
        assertThat(posMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePosMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        posMasterAuditLogRepository.saveAndFlush(posMasterAuditLog);

        int databaseSizeBeforeUpdate = posMasterAuditLogRepository.findAll().size();

        // Update the posMasterAuditLog using partial update
        PosMasterAuditLog partialUpdatedPosMasterAuditLog = new PosMasterAuditLog();
        partialUpdatedPosMasterAuditLog.setId(posMasterAuditLog.getId());

        partialUpdatedPosMasterAuditLog
            .psId(UPDATED_PS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP);

        restPosMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPosMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPosMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the PosMasterAuditLog in the database
        List<PosMasterAuditLog> posMasterAuditLogList = posMasterAuditLogRepository.findAll();
        assertThat(posMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PosMasterAuditLog testPosMasterAuditLog = posMasterAuditLogList.get(posMasterAuditLogList.size() - 1);
        assertThat(testPosMasterAuditLog.getPsId()).isEqualTo(UPDATED_PS_ID);
        assertThat(testPosMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPosMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testPosMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPosMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPosMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdatePosMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        posMasterAuditLogRepository.saveAndFlush(posMasterAuditLog);

        int databaseSizeBeforeUpdate = posMasterAuditLogRepository.findAll().size();

        // Update the posMasterAuditLog using partial update
        PosMasterAuditLog partialUpdatedPosMasterAuditLog = new PosMasterAuditLog();
        partialUpdatedPosMasterAuditLog.setId(posMasterAuditLog.getId());

        partialUpdatedPosMasterAuditLog
            .psId(UPDATED_PS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restPosMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPosMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPosMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the PosMasterAuditLog in the database
        List<PosMasterAuditLog> posMasterAuditLogList = posMasterAuditLogRepository.findAll();
        assertThat(posMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PosMasterAuditLog testPosMasterAuditLog = posMasterAuditLogList.get(posMasterAuditLogList.size() - 1);
        assertThat(testPosMasterAuditLog.getPsId()).isEqualTo(UPDATED_PS_ID);
        assertThat(testPosMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPosMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPosMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPosMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPosMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingPosMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = posMasterAuditLogRepository.findAll().size();
        posMasterAuditLog.setId(count.incrementAndGet());

        // Create the PosMasterAuditLog
        PosMasterAuditLogDTO posMasterAuditLogDTO = posMasterAuditLogMapper.toDto(posMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPosMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, posMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(posMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PosMasterAuditLog in the database
        List<PosMasterAuditLog> posMasterAuditLogList = posMasterAuditLogRepository.findAll();
        assertThat(posMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPosMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = posMasterAuditLogRepository.findAll().size();
        posMasterAuditLog.setId(count.incrementAndGet());

        // Create the PosMasterAuditLog
        PosMasterAuditLogDTO posMasterAuditLogDTO = posMasterAuditLogMapper.toDto(posMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPosMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(posMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PosMasterAuditLog in the database
        List<PosMasterAuditLog> posMasterAuditLogList = posMasterAuditLogRepository.findAll();
        assertThat(posMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPosMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = posMasterAuditLogRepository.findAll().size();
        posMasterAuditLog.setId(count.incrementAndGet());

        // Create the PosMasterAuditLog
        PosMasterAuditLogDTO posMasterAuditLogDTO = posMasterAuditLogMapper.toDto(posMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPosMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(posMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PosMasterAuditLog in the database
        List<PosMasterAuditLog> posMasterAuditLogList = posMasterAuditLogRepository.findAll();
        assertThat(posMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePosMasterAuditLog() throws Exception {
        // Initialize the database
        posMasterAuditLogRepository.saveAndFlush(posMasterAuditLog);

        int databaseSizeBeforeDelete = posMasterAuditLogRepository.findAll().size();

        // Delete the posMasterAuditLog
        restPosMasterAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, posMasterAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PosMasterAuditLog> posMasterAuditLogList = posMasterAuditLogRepository.findAll();
        assertThat(posMasterAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
