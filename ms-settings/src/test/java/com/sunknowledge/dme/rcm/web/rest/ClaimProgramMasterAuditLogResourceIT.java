package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ClaimProgramMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.ClaimProgramMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.ClaimProgramMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimProgramMasterAuditLogMapper;
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
 * Integration tests for the {@link ClaimProgramMasterAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClaimProgramMasterAuditLogResourceIT {

    private static final Long DEFAULT_INSRNCE_INCATOR_ID = 1L;
    private static final Long UPDATED_INSRNCE_INCATOR_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/claim-program-master-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClaimProgramMasterAuditLogRepository claimProgramMasterAuditLogRepository;

    @Autowired
    private ClaimProgramMasterAuditLogMapper claimProgramMasterAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClaimProgramMasterAuditLogMockMvc;

    private ClaimProgramMasterAuditLog claimProgramMasterAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimProgramMasterAuditLog createEntity(EntityManager em) {
        ClaimProgramMasterAuditLog claimProgramMasterAuditLog = new ClaimProgramMasterAuditLog()
            .insrnceIncatorId(DEFAULT_INSRNCE_INCATOR_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return claimProgramMasterAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimProgramMasterAuditLog createUpdatedEntity(EntityManager em) {
        ClaimProgramMasterAuditLog claimProgramMasterAuditLog = new ClaimProgramMasterAuditLog()
            .insrnceIncatorId(UPDATED_INSRNCE_INCATOR_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return claimProgramMasterAuditLog;
    }

    @BeforeEach
    public void initTest() {
        claimProgramMasterAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createClaimProgramMasterAuditLog() throws Exception {
        int databaseSizeBeforeCreate = claimProgramMasterAuditLogRepository.findAll().size();
        // Create the ClaimProgramMasterAuditLog
        ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO = claimProgramMasterAuditLogMapper.toDto(claimProgramMasterAuditLog);
        restClaimProgramMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimProgramMasterAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ClaimProgramMasterAuditLog in the database
        List<ClaimProgramMasterAuditLog> claimProgramMasterAuditLogList = claimProgramMasterAuditLogRepository.findAll();
        assertThat(claimProgramMasterAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimProgramMasterAuditLog testClaimProgramMasterAuditLog = claimProgramMasterAuditLogList.get(
            claimProgramMasterAuditLogList.size() - 1
        );
        assertThat(testClaimProgramMasterAuditLog.getInsrnceIncatorId()).isEqualTo(DEFAULT_INSRNCE_INCATOR_ID);
        assertThat(testClaimProgramMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testClaimProgramMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testClaimProgramMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testClaimProgramMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testClaimProgramMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createClaimProgramMasterAuditLogWithExistingId() throws Exception {
        // Create the ClaimProgramMasterAuditLog with an existing ID
        claimProgramMasterAuditLog.setId(1L);
        ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO = claimProgramMasterAuditLogMapper.toDto(claimProgramMasterAuditLog);

        int databaseSizeBeforeCreate = claimProgramMasterAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimProgramMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimProgramMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimProgramMasterAuditLog in the database
        List<ClaimProgramMasterAuditLog> claimProgramMasterAuditLogList = claimProgramMasterAuditLogRepository.findAll();
        assertThat(claimProgramMasterAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClaimProgramMasterAuditLogs() throws Exception {
        // Initialize the database
        claimProgramMasterAuditLogRepository.saveAndFlush(claimProgramMasterAuditLog);

        // Get all the claimProgramMasterAuditLogList
        restClaimProgramMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claimProgramMasterAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].insrnceIncatorId").value(hasItem(DEFAULT_INSRNCE_INCATOR_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getClaimProgramMasterAuditLog() throws Exception {
        // Initialize the database
        claimProgramMasterAuditLogRepository.saveAndFlush(claimProgramMasterAuditLog);

        // Get the claimProgramMasterAuditLog
        restClaimProgramMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, claimProgramMasterAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(claimProgramMasterAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.insrnceIncatorId").value(DEFAULT_INSRNCE_INCATOR_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingClaimProgramMasterAuditLog() throws Exception {
        // Get the claimProgramMasterAuditLog
        restClaimProgramMasterAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingClaimProgramMasterAuditLog() throws Exception {
        // Initialize the database
        claimProgramMasterAuditLogRepository.saveAndFlush(claimProgramMasterAuditLog);

        int databaseSizeBeforeUpdate = claimProgramMasterAuditLogRepository.findAll().size();

        // Update the claimProgramMasterAuditLog
        ClaimProgramMasterAuditLog updatedClaimProgramMasterAuditLog = claimProgramMasterAuditLogRepository
            .findById(claimProgramMasterAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedClaimProgramMasterAuditLog are not directly saved in db
        em.detach(updatedClaimProgramMasterAuditLog);
        updatedClaimProgramMasterAuditLog
            .insrnceIncatorId(UPDATED_INSRNCE_INCATOR_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO = claimProgramMasterAuditLogMapper.toDto(
            updatedClaimProgramMasterAuditLog
        );

        restClaimProgramMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimProgramMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimProgramMasterAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the ClaimProgramMasterAuditLog in the database
        List<ClaimProgramMasterAuditLog> claimProgramMasterAuditLogList = claimProgramMasterAuditLogRepository.findAll();
        assertThat(claimProgramMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ClaimProgramMasterAuditLog testClaimProgramMasterAuditLog = claimProgramMasterAuditLogList.get(
            claimProgramMasterAuditLogList.size() - 1
        );
        assertThat(testClaimProgramMasterAuditLog.getInsrnceIncatorId()).isEqualTo(UPDATED_INSRNCE_INCATOR_ID);
        assertThat(testClaimProgramMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testClaimProgramMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testClaimProgramMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testClaimProgramMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testClaimProgramMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingClaimProgramMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = claimProgramMasterAuditLogRepository.findAll().size();
        claimProgramMasterAuditLog.setId(count.incrementAndGet());

        // Create the ClaimProgramMasterAuditLog
        ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO = claimProgramMasterAuditLogMapper.toDto(claimProgramMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimProgramMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimProgramMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimProgramMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimProgramMasterAuditLog in the database
        List<ClaimProgramMasterAuditLog> claimProgramMasterAuditLogList = claimProgramMasterAuditLogRepository.findAll();
        assertThat(claimProgramMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClaimProgramMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = claimProgramMasterAuditLogRepository.findAll().size();
        claimProgramMasterAuditLog.setId(count.incrementAndGet());

        // Create the ClaimProgramMasterAuditLog
        ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO = claimProgramMasterAuditLogMapper.toDto(claimProgramMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimProgramMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimProgramMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimProgramMasterAuditLog in the database
        List<ClaimProgramMasterAuditLog> claimProgramMasterAuditLogList = claimProgramMasterAuditLogRepository.findAll();
        assertThat(claimProgramMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClaimProgramMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = claimProgramMasterAuditLogRepository.findAll().size();
        claimProgramMasterAuditLog.setId(count.incrementAndGet());

        // Create the ClaimProgramMasterAuditLog
        ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO = claimProgramMasterAuditLogMapper.toDto(claimProgramMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimProgramMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimProgramMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimProgramMasterAuditLog in the database
        List<ClaimProgramMasterAuditLog> claimProgramMasterAuditLogList = claimProgramMasterAuditLogRepository.findAll();
        assertThat(claimProgramMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClaimProgramMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        claimProgramMasterAuditLogRepository.saveAndFlush(claimProgramMasterAuditLog);

        int databaseSizeBeforeUpdate = claimProgramMasterAuditLogRepository.findAll().size();

        // Update the claimProgramMasterAuditLog using partial update
        ClaimProgramMasterAuditLog partialUpdatedClaimProgramMasterAuditLog = new ClaimProgramMasterAuditLog();
        partialUpdatedClaimProgramMasterAuditLog.setId(claimProgramMasterAuditLog.getId());

        partialUpdatedClaimProgramMasterAuditLog
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restClaimProgramMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimProgramMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimProgramMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ClaimProgramMasterAuditLog in the database
        List<ClaimProgramMasterAuditLog> claimProgramMasterAuditLogList = claimProgramMasterAuditLogRepository.findAll();
        assertThat(claimProgramMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ClaimProgramMasterAuditLog testClaimProgramMasterAuditLog = claimProgramMasterAuditLogList.get(
            claimProgramMasterAuditLogList.size() - 1
        );
        assertThat(testClaimProgramMasterAuditLog.getInsrnceIncatorId()).isEqualTo(DEFAULT_INSRNCE_INCATOR_ID);
        assertThat(testClaimProgramMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testClaimProgramMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testClaimProgramMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testClaimProgramMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testClaimProgramMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateClaimProgramMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        claimProgramMasterAuditLogRepository.saveAndFlush(claimProgramMasterAuditLog);

        int databaseSizeBeforeUpdate = claimProgramMasterAuditLogRepository.findAll().size();

        // Update the claimProgramMasterAuditLog using partial update
        ClaimProgramMasterAuditLog partialUpdatedClaimProgramMasterAuditLog = new ClaimProgramMasterAuditLog();
        partialUpdatedClaimProgramMasterAuditLog.setId(claimProgramMasterAuditLog.getId());

        partialUpdatedClaimProgramMasterAuditLog
            .insrnceIncatorId(UPDATED_INSRNCE_INCATOR_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restClaimProgramMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimProgramMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimProgramMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ClaimProgramMasterAuditLog in the database
        List<ClaimProgramMasterAuditLog> claimProgramMasterAuditLogList = claimProgramMasterAuditLogRepository.findAll();
        assertThat(claimProgramMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ClaimProgramMasterAuditLog testClaimProgramMasterAuditLog = claimProgramMasterAuditLogList.get(
            claimProgramMasterAuditLogList.size() - 1
        );
        assertThat(testClaimProgramMasterAuditLog.getInsrnceIncatorId()).isEqualTo(UPDATED_INSRNCE_INCATOR_ID);
        assertThat(testClaimProgramMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testClaimProgramMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testClaimProgramMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testClaimProgramMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testClaimProgramMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingClaimProgramMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = claimProgramMasterAuditLogRepository.findAll().size();
        claimProgramMasterAuditLog.setId(count.incrementAndGet());

        // Create the ClaimProgramMasterAuditLog
        ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO = claimProgramMasterAuditLogMapper.toDto(claimProgramMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimProgramMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, claimProgramMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimProgramMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimProgramMasterAuditLog in the database
        List<ClaimProgramMasterAuditLog> claimProgramMasterAuditLogList = claimProgramMasterAuditLogRepository.findAll();
        assertThat(claimProgramMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClaimProgramMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = claimProgramMasterAuditLogRepository.findAll().size();
        claimProgramMasterAuditLog.setId(count.incrementAndGet());

        // Create the ClaimProgramMasterAuditLog
        ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO = claimProgramMasterAuditLogMapper.toDto(claimProgramMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimProgramMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimProgramMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimProgramMasterAuditLog in the database
        List<ClaimProgramMasterAuditLog> claimProgramMasterAuditLogList = claimProgramMasterAuditLogRepository.findAll();
        assertThat(claimProgramMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClaimProgramMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = claimProgramMasterAuditLogRepository.findAll().size();
        claimProgramMasterAuditLog.setId(count.incrementAndGet());

        // Create the ClaimProgramMasterAuditLog
        ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO = claimProgramMasterAuditLogMapper.toDto(claimProgramMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimProgramMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimProgramMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimProgramMasterAuditLog in the database
        List<ClaimProgramMasterAuditLog> claimProgramMasterAuditLogList = claimProgramMasterAuditLogRepository.findAll();
        assertThat(claimProgramMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClaimProgramMasterAuditLog() throws Exception {
        // Initialize the database
        claimProgramMasterAuditLogRepository.saveAndFlush(claimProgramMasterAuditLog);

        int databaseSizeBeforeDelete = claimProgramMasterAuditLogRepository.findAll().size();

        // Delete the claimProgramMasterAuditLog
        restClaimProgramMasterAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, claimProgramMasterAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimProgramMasterAuditLog> claimProgramMasterAuditLogList = claimProgramMasterAuditLogRepository.findAll();
        assertThat(claimProgramMasterAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
