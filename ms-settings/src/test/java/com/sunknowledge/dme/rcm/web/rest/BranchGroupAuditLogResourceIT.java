package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.BranchGroupAuditLog;
import com.sunknowledge.dme.rcm.repository.BranchGroupAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.BranchGroupAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchGroupAuditLogMapper;
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
 * Integration tests for the {@link BranchGroupAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BranchGroupAuditLogResourceIT {

    private static final Long DEFAULT_BRNCH_GRP_ID = 1L;
    private static final Long UPDATED_BRNCH_GRP_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/branch-group-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BranchGroupAuditLogRepository branchGroupAuditLogRepository;

    @Autowired
    private BranchGroupAuditLogMapper branchGroupAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBranchGroupAuditLogMockMvc;

    private BranchGroupAuditLog branchGroupAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchGroupAuditLog createEntity(EntityManager em) {
        BranchGroupAuditLog branchGroupAuditLog = new BranchGroupAuditLog()
            .brnchGrpId(DEFAULT_BRNCH_GRP_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return branchGroupAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchGroupAuditLog createUpdatedEntity(EntityManager em) {
        BranchGroupAuditLog branchGroupAuditLog = new BranchGroupAuditLog()
            .brnchGrpId(UPDATED_BRNCH_GRP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return branchGroupAuditLog;
    }

    @BeforeEach
    public void initTest() {
        branchGroupAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createBranchGroupAuditLog() throws Exception {
        int databaseSizeBeforeCreate = branchGroupAuditLogRepository.findAll().size();
        // Create the BranchGroupAuditLog
        BranchGroupAuditLogDTO branchGroupAuditLogDTO = branchGroupAuditLogMapper.toDto(branchGroupAuditLog);
        restBranchGroupAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchGroupAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BranchGroupAuditLog in the database
        List<BranchGroupAuditLog> branchGroupAuditLogList = branchGroupAuditLogRepository.findAll();
        assertThat(branchGroupAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        BranchGroupAuditLog testBranchGroupAuditLog = branchGroupAuditLogList.get(branchGroupAuditLogList.size() - 1);
        assertThat(testBranchGroupAuditLog.getBrnchGrpId()).isEqualTo(DEFAULT_BRNCH_GRP_ID);
        assertThat(testBranchGroupAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testBranchGroupAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testBranchGroupAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testBranchGroupAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testBranchGroupAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createBranchGroupAuditLogWithExistingId() throws Exception {
        // Create the BranchGroupAuditLog with an existing ID
        branchGroupAuditLog.setId(1L);
        BranchGroupAuditLogDTO branchGroupAuditLogDTO = branchGroupAuditLogMapper.toDto(branchGroupAuditLog);

        int databaseSizeBeforeCreate = branchGroupAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBranchGroupAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchGroupAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchGroupAuditLog in the database
        List<BranchGroupAuditLog> branchGroupAuditLogList = branchGroupAuditLogRepository.findAll();
        assertThat(branchGroupAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBranchGroupAuditLogs() throws Exception {
        // Initialize the database
        branchGroupAuditLogRepository.saveAndFlush(branchGroupAuditLog);

        // Get all the branchGroupAuditLogList
        restBranchGroupAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(branchGroupAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].brnchGrpId").value(hasItem(DEFAULT_BRNCH_GRP_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getBranchGroupAuditLog() throws Exception {
        // Initialize the database
        branchGroupAuditLogRepository.saveAndFlush(branchGroupAuditLog);

        // Get the branchGroupAuditLog
        restBranchGroupAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, branchGroupAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(branchGroupAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.brnchGrpId").value(DEFAULT_BRNCH_GRP_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingBranchGroupAuditLog() throws Exception {
        // Get the branchGroupAuditLog
        restBranchGroupAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBranchGroupAuditLog() throws Exception {
        // Initialize the database
        branchGroupAuditLogRepository.saveAndFlush(branchGroupAuditLog);

        int databaseSizeBeforeUpdate = branchGroupAuditLogRepository.findAll().size();

        // Update the branchGroupAuditLog
        BranchGroupAuditLog updatedBranchGroupAuditLog = branchGroupAuditLogRepository.findById(branchGroupAuditLog.getId()).get();
        // Disconnect from session so that the updates on updatedBranchGroupAuditLog are not directly saved in db
        em.detach(updatedBranchGroupAuditLog);
        updatedBranchGroupAuditLog
            .brnchGrpId(UPDATED_BRNCH_GRP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        BranchGroupAuditLogDTO branchGroupAuditLogDTO = branchGroupAuditLogMapper.toDto(updatedBranchGroupAuditLog);

        restBranchGroupAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchGroupAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchGroupAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the BranchGroupAuditLog in the database
        List<BranchGroupAuditLog> branchGroupAuditLogList = branchGroupAuditLogRepository.findAll();
        assertThat(branchGroupAuditLogList).hasSize(databaseSizeBeforeUpdate);
        BranchGroupAuditLog testBranchGroupAuditLog = branchGroupAuditLogList.get(branchGroupAuditLogList.size() - 1);
        assertThat(testBranchGroupAuditLog.getBrnchGrpId()).isEqualTo(UPDATED_BRNCH_GRP_ID);
        assertThat(testBranchGroupAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testBranchGroupAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testBranchGroupAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testBranchGroupAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testBranchGroupAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingBranchGroupAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchGroupAuditLogRepository.findAll().size();
        branchGroupAuditLog.setId(count.incrementAndGet());

        // Create the BranchGroupAuditLog
        BranchGroupAuditLogDTO branchGroupAuditLogDTO = branchGroupAuditLogMapper.toDto(branchGroupAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchGroupAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchGroupAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchGroupAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchGroupAuditLog in the database
        List<BranchGroupAuditLog> branchGroupAuditLogList = branchGroupAuditLogRepository.findAll();
        assertThat(branchGroupAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBranchGroupAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchGroupAuditLogRepository.findAll().size();
        branchGroupAuditLog.setId(count.incrementAndGet());

        // Create the BranchGroupAuditLog
        BranchGroupAuditLogDTO branchGroupAuditLogDTO = branchGroupAuditLogMapper.toDto(branchGroupAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchGroupAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchGroupAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchGroupAuditLog in the database
        List<BranchGroupAuditLog> branchGroupAuditLogList = branchGroupAuditLogRepository.findAll();
        assertThat(branchGroupAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBranchGroupAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchGroupAuditLogRepository.findAll().size();
        branchGroupAuditLog.setId(count.incrementAndGet());

        // Create the BranchGroupAuditLog
        BranchGroupAuditLogDTO branchGroupAuditLogDTO = branchGroupAuditLogMapper.toDto(branchGroupAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchGroupAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchGroupAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchGroupAuditLog in the database
        List<BranchGroupAuditLog> branchGroupAuditLogList = branchGroupAuditLogRepository.findAll();
        assertThat(branchGroupAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBranchGroupAuditLogWithPatch() throws Exception {
        // Initialize the database
        branchGroupAuditLogRepository.saveAndFlush(branchGroupAuditLog);

        int databaseSizeBeforeUpdate = branchGroupAuditLogRepository.findAll().size();

        // Update the branchGroupAuditLog using partial update
        BranchGroupAuditLog partialUpdatedBranchGroupAuditLog = new BranchGroupAuditLog();
        partialUpdatedBranchGroupAuditLog.setId(branchGroupAuditLog.getId());

        partialUpdatedBranchGroupAuditLog.newRowData(UPDATED_NEW_ROW_DATA).dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restBranchGroupAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchGroupAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchGroupAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the BranchGroupAuditLog in the database
        List<BranchGroupAuditLog> branchGroupAuditLogList = branchGroupAuditLogRepository.findAll();
        assertThat(branchGroupAuditLogList).hasSize(databaseSizeBeforeUpdate);
        BranchGroupAuditLog testBranchGroupAuditLog = branchGroupAuditLogList.get(branchGroupAuditLogList.size() - 1);
        assertThat(testBranchGroupAuditLog.getBrnchGrpId()).isEqualTo(DEFAULT_BRNCH_GRP_ID);
        assertThat(testBranchGroupAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testBranchGroupAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testBranchGroupAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testBranchGroupAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testBranchGroupAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateBranchGroupAuditLogWithPatch() throws Exception {
        // Initialize the database
        branchGroupAuditLogRepository.saveAndFlush(branchGroupAuditLog);

        int databaseSizeBeforeUpdate = branchGroupAuditLogRepository.findAll().size();

        // Update the branchGroupAuditLog using partial update
        BranchGroupAuditLog partialUpdatedBranchGroupAuditLog = new BranchGroupAuditLog();
        partialUpdatedBranchGroupAuditLog.setId(branchGroupAuditLog.getId());

        partialUpdatedBranchGroupAuditLog
            .brnchGrpId(UPDATED_BRNCH_GRP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restBranchGroupAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchGroupAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchGroupAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the BranchGroupAuditLog in the database
        List<BranchGroupAuditLog> branchGroupAuditLogList = branchGroupAuditLogRepository.findAll();
        assertThat(branchGroupAuditLogList).hasSize(databaseSizeBeforeUpdate);
        BranchGroupAuditLog testBranchGroupAuditLog = branchGroupAuditLogList.get(branchGroupAuditLogList.size() - 1);
        assertThat(testBranchGroupAuditLog.getBrnchGrpId()).isEqualTo(UPDATED_BRNCH_GRP_ID);
        assertThat(testBranchGroupAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testBranchGroupAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testBranchGroupAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testBranchGroupAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testBranchGroupAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingBranchGroupAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchGroupAuditLogRepository.findAll().size();
        branchGroupAuditLog.setId(count.incrementAndGet());

        // Create the BranchGroupAuditLog
        BranchGroupAuditLogDTO branchGroupAuditLogDTO = branchGroupAuditLogMapper.toDto(branchGroupAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchGroupAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, branchGroupAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchGroupAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchGroupAuditLog in the database
        List<BranchGroupAuditLog> branchGroupAuditLogList = branchGroupAuditLogRepository.findAll();
        assertThat(branchGroupAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBranchGroupAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchGroupAuditLogRepository.findAll().size();
        branchGroupAuditLog.setId(count.incrementAndGet());

        // Create the BranchGroupAuditLog
        BranchGroupAuditLogDTO branchGroupAuditLogDTO = branchGroupAuditLogMapper.toDto(branchGroupAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchGroupAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchGroupAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchGroupAuditLog in the database
        List<BranchGroupAuditLog> branchGroupAuditLogList = branchGroupAuditLogRepository.findAll();
        assertThat(branchGroupAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBranchGroupAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchGroupAuditLogRepository.findAll().size();
        branchGroupAuditLog.setId(count.incrementAndGet());

        // Create the BranchGroupAuditLog
        BranchGroupAuditLogDTO branchGroupAuditLogDTO = branchGroupAuditLogMapper.toDto(branchGroupAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchGroupAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchGroupAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchGroupAuditLog in the database
        List<BranchGroupAuditLog> branchGroupAuditLogList = branchGroupAuditLogRepository.findAll();
        assertThat(branchGroupAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBranchGroupAuditLog() throws Exception {
        // Initialize the database
        branchGroupAuditLogRepository.saveAndFlush(branchGroupAuditLog);

        int databaseSizeBeforeDelete = branchGroupAuditLogRepository.findAll().size();

        // Delete the branchGroupAuditLog
        restBranchGroupAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, branchGroupAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BranchGroupAuditLog> branchGroupAuditLogList = branchGroupAuditLogRepository.findAll();
        assertThat(branchGroupAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
