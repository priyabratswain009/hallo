package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ClaimFormMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.ClaimFormMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.ClaimFormMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimFormMasterAuditLogMapper;
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
 * Integration tests for the {@link ClaimFormMasterAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClaimFormMasterAuditLogResourceIT {

    private static final Long DEFAULT_CLM_FORM_ID = 1L;
    private static final Long UPDATED_CLM_FORM_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/claim-form-master-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClaimFormMasterAuditLogRepository claimFormMasterAuditLogRepository;

    @Autowired
    private ClaimFormMasterAuditLogMapper claimFormMasterAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClaimFormMasterAuditLogMockMvc;

    private ClaimFormMasterAuditLog claimFormMasterAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimFormMasterAuditLog createEntity(EntityManager em) {
        ClaimFormMasterAuditLog claimFormMasterAuditLog = new ClaimFormMasterAuditLog()
            .clmFormId(DEFAULT_CLM_FORM_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return claimFormMasterAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimFormMasterAuditLog createUpdatedEntity(EntityManager em) {
        ClaimFormMasterAuditLog claimFormMasterAuditLog = new ClaimFormMasterAuditLog()
            .clmFormId(UPDATED_CLM_FORM_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return claimFormMasterAuditLog;
    }

    @BeforeEach
    public void initTest() {
        claimFormMasterAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createClaimFormMasterAuditLog() throws Exception {
        int databaseSizeBeforeCreate = claimFormMasterAuditLogRepository.findAll().size();
        // Create the ClaimFormMasterAuditLog
        ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO = claimFormMasterAuditLogMapper.toDto(claimFormMasterAuditLog);
        restClaimFormMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimFormMasterAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ClaimFormMasterAuditLog in the database
        List<ClaimFormMasterAuditLog> claimFormMasterAuditLogList = claimFormMasterAuditLogRepository.findAll();
        assertThat(claimFormMasterAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimFormMasterAuditLog testClaimFormMasterAuditLog = claimFormMasterAuditLogList.get(claimFormMasterAuditLogList.size() - 1);
        assertThat(testClaimFormMasterAuditLog.getClmFormId()).isEqualTo(DEFAULT_CLM_FORM_ID);
        assertThat(testClaimFormMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testClaimFormMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testClaimFormMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testClaimFormMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testClaimFormMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createClaimFormMasterAuditLogWithExistingId() throws Exception {
        // Create the ClaimFormMasterAuditLog with an existing ID
        claimFormMasterAuditLog.setId(1L);
        ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO = claimFormMasterAuditLogMapper.toDto(claimFormMasterAuditLog);

        int databaseSizeBeforeCreate = claimFormMasterAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimFormMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimFormMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimFormMasterAuditLog in the database
        List<ClaimFormMasterAuditLog> claimFormMasterAuditLogList = claimFormMasterAuditLogRepository.findAll();
        assertThat(claimFormMasterAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClaimFormMasterAuditLogs() throws Exception {
        // Initialize the database
        claimFormMasterAuditLogRepository.saveAndFlush(claimFormMasterAuditLog);

        // Get all the claimFormMasterAuditLogList
        restClaimFormMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claimFormMasterAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].clmFormId").value(hasItem(DEFAULT_CLM_FORM_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getClaimFormMasterAuditLog() throws Exception {
        // Initialize the database
        claimFormMasterAuditLogRepository.saveAndFlush(claimFormMasterAuditLog);

        // Get the claimFormMasterAuditLog
        restClaimFormMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, claimFormMasterAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(claimFormMasterAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.clmFormId").value(DEFAULT_CLM_FORM_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingClaimFormMasterAuditLog() throws Exception {
        // Get the claimFormMasterAuditLog
        restClaimFormMasterAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewClaimFormMasterAuditLog() throws Exception {
        // Initialize the database
        claimFormMasterAuditLogRepository.saveAndFlush(claimFormMasterAuditLog);

        int databaseSizeBeforeUpdate = claimFormMasterAuditLogRepository.findAll().size();

        // Update the claimFormMasterAuditLog
        ClaimFormMasterAuditLog updatedClaimFormMasterAuditLog = claimFormMasterAuditLogRepository
            .findById(claimFormMasterAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedClaimFormMasterAuditLog are not directly saved in db
        em.detach(updatedClaimFormMasterAuditLog);
        updatedClaimFormMasterAuditLog
            .clmFormId(UPDATED_CLM_FORM_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO = claimFormMasterAuditLogMapper.toDto(updatedClaimFormMasterAuditLog);

        restClaimFormMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimFormMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimFormMasterAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the ClaimFormMasterAuditLog in the database
        List<ClaimFormMasterAuditLog> claimFormMasterAuditLogList = claimFormMasterAuditLogRepository.findAll();
        assertThat(claimFormMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ClaimFormMasterAuditLog testClaimFormMasterAuditLog = claimFormMasterAuditLogList.get(claimFormMasterAuditLogList.size() - 1);
        assertThat(testClaimFormMasterAuditLog.getClmFormId()).isEqualTo(UPDATED_CLM_FORM_ID);
        assertThat(testClaimFormMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testClaimFormMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testClaimFormMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testClaimFormMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testClaimFormMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingClaimFormMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = claimFormMasterAuditLogRepository.findAll().size();
        claimFormMasterAuditLog.setId(count.incrementAndGet());

        // Create the ClaimFormMasterAuditLog
        ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO = claimFormMasterAuditLogMapper.toDto(claimFormMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimFormMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimFormMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimFormMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimFormMasterAuditLog in the database
        List<ClaimFormMasterAuditLog> claimFormMasterAuditLogList = claimFormMasterAuditLogRepository.findAll();
        assertThat(claimFormMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClaimFormMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = claimFormMasterAuditLogRepository.findAll().size();
        claimFormMasterAuditLog.setId(count.incrementAndGet());

        // Create the ClaimFormMasterAuditLog
        ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO = claimFormMasterAuditLogMapper.toDto(claimFormMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimFormMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimFormMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimFormMasterAuditLog in the database
        List<ClaimFormMasterAuditLog> claimFormMasterAuditLogList = claimFormMasterAuditLogRepository.findAll();
        assertThat(claimFormMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClaimFormMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = claimFormMasterAuditLogRepository.findAll().size();
        claimFormMasterAuditLog.setId(count.incrementAndGet());

        // Create the ClaimFormMasterAuditLog
        ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO = claimFormMasterAuditLogMapper.toDto(claimFormMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimFormMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimFormMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimFormMasterAuditLog in the database
        List<ClaimFormMasterAuditLog> claimFormMasterAuditLogList = claimFormMasterAuditLogRepository.findAll();
        assertThat(claimFormMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClaimFormMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        claimFormMasterAuditLogRepository.saveAndFlush(claimFormMasterAuditLog);

        int databaseSizeBeforeUpdate = claimFormMasterAuditLogRepository.findAll().size();

        // Update the claimFormMasterAuditLog using partial update
        ClaimFormMasterAuditLog partialUpdatedClaimFormMasterAuditLog = new ClaimFormMasterAuditLog();
        partialUpdatedClaimFormMasterAuditLog.setId(claimFormMasterAuditLog.getId());

        partialUpdatedClaimFormMasterAuditLog
            .clmFormId(UPDATED_CLM_FORM_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP);

        restClaimFormMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimFormMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimFormMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ClaimFormMasterAuditLog in the database
        List<ClaimFormMasterAuditLog> claimFormMasterAuditLogList = claimFormMasterAuditLogRepository.findAll();
        assertThat(claimFormMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ClaimFormMasterAuditLog testClaimFormMasterAuditLog = claimFormMasterAuditLogList.get(claimFormMasterAuditLogList.size() - 1);
        assertThat(testClaimFormMasterAuditLog.getClmFormId()).isEqualTo(UPDATED_CLM_FORM_ID);
        assertThat(testClaimFormMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testClaimFormMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testClaimFormMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testClaimFormMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testClaimFormMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateClaimFormMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        claimFormMasterAuditLogRepository.saveAndFlush(claimFormMasterAuditLog);

        int databaseSizeBeforeUpdate = claimFormMasterAuditLogRepository.findAll().size();

        // Update the claimFormMasterAuditLog using partial update
        ClaimFormMasterAuditLog partialUpdatedClaimFormMasterAuditLog = new ClaimFormMasterAuditLog();
        partialUpdatedClaimFormMasterAuditLog.setId(claimFormMasterAuditLog.getId());

        partialUpdatedClaimFormMasterAuditLog
            .clmFormId(UPDATED_CLM_FORM_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restClaimFormMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimFormMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimFormMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ClaimFormMasterAuditLog in the database
        List<ClaimFormMasterAuditLog> claimFormMasterAuditLogList = claimFormMasterAuditLogRepository.findAll();
        assertThat(claimFormMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ClaimFormMasterAuditLog testClaimFormMasterAuditLog = claimFormMasterAuditLogList.get(claimFormMasterAuditLogList.size() - 1);
        assertThat(testClaimFormMasterAuditLog.getClmFormId()).isEqualTo(UPDATED_CLM_FORM_ID);
        assertThat(testClaimFormMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testClaimFormMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testClaimFormMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testClaimFormMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testClaimFormMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingClaimFormMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = claimFormMasterAuditLogRepository.findAll().size();
        claimFormMasterAuditLog.setId(count.incrementAndGet());

        // Create the ClaimFormMasterAuditLog
        ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO = claimFormMasterAuditLogMapper.toDto(claimFormMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimFormMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, claimFormMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimFormMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimFormMasterAuditLog in the database
        List<ClaimFormMasterAuditLog> claimFormMasterAuditLogList = claimFormMasterAuditLogRepository.findAll();
        assertThat(claimFormMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClaimFormMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = claimFormMasterAuditLogRepository.findAll().size();
        claimFormMasterAuditLog.setId(count.incrementAndGet());

        // Create the ClaimFormMasterAuditLog
        ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO = claimFormMasterAuditLogMapper.toDto(claimFormMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimFormMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimFormMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimFormMasterAuditLog in the database
        List<ClaimFormMasterAuditLog> claimFormMasterAuditLogList = claimFormMasterAuditLogRepository.findAll();
        assertThat(claimFormMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClaimFormMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = claimFormMasterAuditLogRepository.findAll().size();
        claimFormMasterAuditLog.setId(count.incrementAndGet());

        // Create the ClaimFormMasterAuditLog
        ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO = claimFormMasterAuditLogMapper.toDto(claimFormMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimFormMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimFormMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimFormMasterAuditLog in the database
        List<ClaimFormMasterAuditLog> claimFormMasterAuditLogList = claimFormMasterAuditLogRepository.findAll();
        assertThat(claimFormMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClaimFormMasterAuditLog() throws Exception {
        // Initialize the database
        claimFormMasterAuditLogRepository.saveAndFlush(claimFormMasterAuditLog);

        int databaseSizeBeforeDelete = claimFormMasterAuditLogRepository.findAll().size();

        // Delete the claimFormMasterAuditLog
        restClaimFormMasterAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, claimFormMasterAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimFormMasterAuditLog> claimFormMasterAuditLogList = claimFormMasterAuditLogRepository.findAll();
        assertThat(claimFormMasterAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
