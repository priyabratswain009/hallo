package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.BranchOfficeAuditLog;
import com.sunknowledge.dme.rcm.repository.BranchOfficeAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchOfficeAuditLogMapper;
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
 * Integration tests for the {@link BranchOfficeAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BranchOfficeAuditLogResourceIT {

    private static final Long DEFAULT_BRNCH_ID = 1L;
    private static final Long UPDATED_BRNCH_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/branch-office-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BranchOfficeAuditLogRepository branchOfficeAuditLogRepository;

    @Autowired
    private BranchOfficeAuditLogMapper branchOfficeAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBranchOfficeAuditLogMockMvc;

    private BranchOfficeAuditLog branchOfficeAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchOfficeAuditLog createEntity(EntityManager em) {
        BranchOfficeAuditLog branchOfficeAuditLog = new BranchOfficeAuditLog()
            .brnchId(DEFAULT_BRNCH_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return branchOfficeAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchOfficeAuditLog createUpdatedEntity(EntityManager em) {
        BranchOfficeAuditLog branchOfficeAuditLog = new BranchOfficeAuditLog()
            .brnchId(UPDATED_BRNCH_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return branchOfficeAuditLog;
    }

    @BeforeEach
    public void initTest() {
        branchOfficeAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createBranchOfficeAuditLog() throws Exception {
        int databaseSizeBeforeCreate = branchOfficeAuditLogRepository.findAll().size();
        // Create the BranchOfficeAuditLog
        BranchOfficeAuditLogDTO branchOfficeAuditLogDTO = branchOfficeAuditLogMapper.toDto(branchOfficeAuditLog);
        restBranchOfficeAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchOfficeAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BranchOfficeAuditLog in the database
        List<BranchOfficeAuditLog> branchOfficeAuditLogList = branchOfficeAuditLogRepository.findAll();
        assertThat(branchOfficeAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        BranchOfficeAuditLog testBranchOfficeAuditLog = branchOfficeAuditLogList.get(branchOfficeAuditLogList.size() - 1);
        assertThat(testBranchOfficeAuditLog.getBrnchId()).isEqualTo(DEFAULT_BRNCH_ID);
        assertThat(testBranchOfficeAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testBranchOfficeAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testBranchOfficeAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testBranchOfficeAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testBranchOfficeAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createBranchOfficeAuditLogWithExistingId() throws Exception {
        // Create the BranchOfficeAuditLog with an existing ID
        branchOfficeAuditLog.setId(1L);
        BranchOfficeAuditLogDTO branchOfficeAuditLogDTO = branchOfficeAuditLogMapper.toDto(branchOfficeAuditLog);

        int databaseSizeBeforeCreate = branchOfficeAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBranchOfficeAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchOfficeAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchOfficeAuditLog in the database
        List<BranchOfficeAuditLog> branchOfficeAuditLogList = branchOfficeAuditLogRepository.findAll();
        assertThat(branchOfficeAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBranchOfficeAuditLogs() throws Exception {
        // Initialize the database
        branchOfficeAuditLogRepository.saveAndFlush(branchOfficeAuditLog);

        // Get all the branchOfficeAuditLogList
        restBranchOfficeAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(branchOfficeAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].brnchId").value(hasItem(DEFAULT_BRNCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getBranchOfficeAuditLog() throws Exception {
        // Initialize the database
        branchOfficeAuditLogRepository.saveAndFlush(branchOfficeAuditLog);

        // Get the branchOfficeAuditLog
        restBranchOfficeAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, branchOfficeAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(branchOfficeAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.brnchId").value(DEFAULT_BRNCH_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingBranchOfficeAuditLog() throws Exception {
        // Get the branchOfficeAuditLog
        restBranchOfficeAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBranchOfficeAuditLog() throws Exception {
        // Initialize the database
        branchOfficeAuditLogRepository.saveAndFlush(branchOfficeAuditLog);

        int databaseSizeBeforeUpdate = branchOfficeAuditLogRepository.findAll().size();

        // Update the branchOfficeAuditLog
        BranchOfficeAuditLog updatedBranchOfficeAuditLog = branchOfficeAuditLogRepository.findById(branchOfficeAuditLog.getId()).get();
        // Disconnect from session so that the updates on updatedBranchOfficeAuditLog are not directly saved in db
        em.detach(updatedBranchOfficeAuditLog);
        updatedBranchOfficeAuditLog
            .brnchId(UPDATED_BRNCH_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        BranchOfficeAuditLogDTO branchOfficeAuditLogDTO = branchOfficeAuditLogMapper.toDto(updatedBranchOfficeAuditLog);

        restBranchOfficeAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchOfficeAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchOfficeAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the BranchOfficeAuditLog in the database
        List<BranchOfficeAuditLog> branchOfficeAuditLogList = branchOfficeAuditLogRepository.findAll();
        assertThat(branchOfficeAuditLogList).hasSize(databaseSizeBeforeUpdate);
        BranchOfficeAuditLog testBranchOfficeAuditLog = branchOfficeAuditLogList.get(branchOfficeAuditLogList.size() - 1);
        assertThat(testBranchOfficeAuditLog.getBrnchId()).isEqualTo(UPDATED_BRNCH_ID);
        assertThat(testBranchOfficeAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testBranchOfficeAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testBranchOfficeAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testBranchOfficeAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testBranchOfficeAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingBranchOfficeAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchOfficeAuditLogRepository.findAll().size();
        branchOfficeAuditLog.setId(count.incrementAndGet());

        // Create the BranchOfficeAuditLog
        BranchOfficeAuditLogDTO branchOfficeAuditLogDTO = branchOfficeAuditLogMapper.toDto(branchOfficeAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchOfficeAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchOfficeAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchOfficeAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchOfficeAuditLog in the database
        List<BranchOfficeAuditLog> branchOfficeAuditLogList = branchOfficeAuditLogRepository.findAll();
        assertThat(branchOfficeAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBranchOfficeAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchOfficeAuditLogRepository.findAll().size();
        branchOfficeAuditLog.setId(count.incrementAndGet());

        // Create the BranchOfficeAuditLog
        BranchOfficeAuditLogDTO branchOfficeAuditLogDTO = branchOfficeAuditLogMapper.toDto(branchOfficeAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchOfficeAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchOfficeAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchOfficeAuditLog in the database
        List<BranchOfficeAuditLog> branchOfficeAuditLogList = branchOfficeAuditLogRepository.findAll();
        assertThat(branchOfficeAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBranchOfficeAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchOfficeAuditLogRepository.findAll().size();
        branchOfficeAuditLog.setId(count.incrementAndGet());

        // Create the BranchOfficeAuditLog
        BranchOfficeAuditLogDTO branchOfficeAuditLogDTO = branchOfficeAuditLogMapper.toDto(branchOfficeAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchOfficeAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchOfficeAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchOfficeAuditLog in the database
        List<BranchOfficeAuditLog> branchOfficeAuditLogList = branchOfficeAuditLogRepository.findAll();
        assertThat(branchOfficeAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBranchOfficeAuditLogWithPatch() throws Exception {
        // Initialize the database
        branchOfficeAuditLogRepository.saveAndFlush(branchOfficeAuditLog);

        int databaseSizeBeforeUpdate = branchOfficeAuditLogRepository.findAll().size();

        // Update the branchOfficeAuditLog using partial update
        BranchOfficeAuditLog partialUpdatedBranchOfficeAuditLog = new BranchOfficeAuditLog();
        partialUpdatedBranchOfficeAuditLog.setId(branchOfficeAuditLog.getId());

        partialUpdatedBranchOfficeAuditLog
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP);

        restBranchOfficeAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchOfficeAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchOfficeAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the BranchOfficeAuditLog in the database
        List<BranchOfficeAuditLog> branchOfficeAuditLogList = branchOfficeAuditLogRepository.findAll();
        assertThat(branchOfficeAuditLogList).hasSize(databaseSizeBeforeUpdate);
        BranchOfficeAuditLog testBranchOfficeAuditLog = branchOfficeAuditLogList.get(branchOfficeAuditLogList.size() - 1);
        assertThat(testBranchOfficeAuditLog.getBrnchId()).isEqualTo(DEFAULT_BRNCH_ID);
        assertThat(testBranchOfficeAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testBranchOfficeAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testBranchOfficeAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testBranchOfficeAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testBranchOfficeAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateBranchOfficeAuditLogWithPatch() throws Exception {
        // Initialize the database
        branchOfficeAuditLogRepository.saveAndFlush(branchOfficeAuditLog);

        int databaseSizeBeforeUpdate = branchOfficeAuditLogRepository.findAll().size();

        // Update the branchOfficeAuditLog using partial update
        BranchOfficeAuditLog partialUpdatedBranchOfficeAuditLog = new BranchOfficeAuditLog();
        partialUpdatedBranchOfficeAuditLog.setId(branchOfficeAuditLog.getId());

        partialUpdatedBranchOfficeAuditLog
            .brnchId(UPDATED_BRNCH_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restBranchOfficeAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchOfficeAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchOfficeAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the BranchOfficeAuditLog in the database
        List<BranchOfficeAuditLog> branchOfficeAuditLogList = branchOfficeAuditLogRepository.findAll();
        assertThat(branchOfficeAuditLogList).hasSize(databaseSizeBeforeUpdate);
        BranchOfficeAuditLog testBranchOfficeAuditLog = branchOfficeAuditLogList.get(branchOfficeAuditLogList.size() - 1);
        assertThat(testBranchOfficeAuditLog.getBrnchId()).isEqualTo(UPDATED_BRNCH_ID);
        assertThat(testBranchOfficeAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testBranchOfficeAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testBranchOfficeAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testBranchOfficeAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testBranchOfficeAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingBranchOfficeAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchOfficeAuditLogRepository.findAll().size();
        branchOfficeAuditLog.setId(count.incrementAndGet());

        // Create the BranchOfficeAuditLog
        BranchOfficeAuditLogDTO branchOfficeAuditLogDTO = branchOfficeAuditLogMapper.toDto(branchOfficeAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchOfficeAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, branchOfficeAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchOfficeAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchOfficeAuditLog in the database
        List<BranchOfficeAuditLog> branchOfficeAuditLogList = branchOfficeAuditLogRepository.findAll();
        assertThat(branchOfficeAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBranchOfficeAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchOfficeAuditLogRepository.findAll().size();
        branchOfficeAuditLog.setId(count.incrementAndGet());

        // Create the BranchOfficeAuditLog
        BranchOfficeAuditLogDTO branchOfficeAuditLogDTO = branchOfficeAuditLogMapper.toDto(branchOfficeAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchOfficeAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchOfficeAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchOfficeAuditLog in the database
        List<BranchOfficeAuditLog> branchOfficeAuditLogList = branchOfficeAuditLogRepository.findAll();
        assertThat(branchOfficeAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBranchOfficeAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchOfficeAuditLogRepository.findAll().size();
        branchOfficeAuditLog.setId(count.incrementAndGet());

        // Create the BranchOfficeAuditLog
        BranchOfficeAuditLogDTO branchOfficeAuditLogDTO = branchOfficeAuditLogMapper.toDto(branchOfficeAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchOfficeAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchOfficeAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchOfficeAuditLog in the database
        List<BranchOfficeAuditLog> branchOfficeAuditLogList = branchOfficeAuditLogRepository.findAll();
        assertThat(branchOfficeAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBranchOfficeAuditLog() throws Exception {
        // Initialize the database
        branchOfficeAuditLogRepository.saveAndFlush(branchOfficeAuditLog);

        int databaseSizeBeforeDelete = branchOfficeAuditLogRepository.findAll().size();

        // Delete the branchOfficeAuditLog
        restBranchOfficeAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, branchOfficeAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BranchOfficeAuditLog> branchOfficeAuditLogList = branchOfficeAuditLogRepository.findAll();
        assertThat(branchOfficeAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
