package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.BranchInsuranceMapAuditLog;
import com.sunknowledge.dme.rcm.repository.BranchInsuranceMapAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.BranchInsuranceMapAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchInsuranceMapAuditLogMapper;
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
 * Integration tests for the {@link BranchInsuranceMapAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BranchInsuranceMapAuditLogResourceIT {

    private static final Long DEFAULT_BRACH_INSANCE_MAP_ID = 1L;
    private static final Long UPDATED_BRACH_INSANCE_MAP_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/branch-insurance-map-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BranchInsuranceMapAuditLogRepository branchInsuranceMapAuditLogRepository;

    @Autowired
    private BranchInsuranceMapAuditLogMapper branchInsuranceMapAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBranchInsuranceMapAuditLogMockMvc;

    private BranchInsuranceMapAuditLog branchInsuranceMapAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchInsuranceMapAuditLog createEntity(EntityManager em) {
        BranchInsuranceMapAuditLog branchInsuranceMapAuditLog = new BranchInsuranceMapAuditLog()
            .brachInsanceMapId(DEFAULT_BRACH_INSANCE_MAP_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return branchInsuranceMapAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchInsuranceMapAuditLog createUpdatedEntity(EntityManager em) {
        BranchInsuranceMapAuditLog branchInsuranceMapAuditLog = new BranchInsuranceMapAuditLog()
            .brachInsanceMapId(UPDATED_BRACH_INSANCE_MAP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return branchInsuranceMapAuditLog;
    }

    @BeforeEach
    public void initTest() {
        branchInsuranceMapAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createBranchInsuranceMapAuditLog() throws Exception {
        int databaseSizeBeforeCreate = branchInsuranceMapAuditLogRepository.findAll().size();
        // Create the BranchInsuranceMapAuditLog
        BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO = branchInsuranceMapAuditLogMapper.toDto(branchInsuranceMapAuditLog);
        restBranchInsuranceMapAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchInsuranceMapAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BranchInsuranceMapAuditLog in the database
        List<BranchInsuranceMapAuditLog> branchInsuranceMapAuditLogList = branchInsuranceMapAuditLogRepository.findAll();
        assertThat(branchInsuranceMapAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        BranchInsuranceMapAuditLog testBranchInsuranceMapAuditLog = branchInsuranceMapAuditLogList.get(
            branchInsuranceMapAuditLogList.size() - 1
        );
        assertThat(testBranchInsuranceMapAuditLog.getBrachInsanceMapId()).isEqualTo(DEFAULT_BRACH_INSANCE_MAP_ID);
        assertThat(testBranchInsuranceMapAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testBranchInsuranceMapAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testBranchInsuranceMapAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testBranchInsuranceMapAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testBranchInsuranceMapAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createBranchInsuranceMapAuditLogWithExistingId() throws Exception {
        // Create the BranchInsuranceMapAuditLog with an existing ID
        branchInsuranceMapAuditLog.setId(1L);
        BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO = branchInsuranceMapAuditLogMapper.toDto(branchInsuranceMapAuditLog);

        int databaseSizeBeforeCreate = branchInsuranceMapAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBranchInsuranceMapAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchInsuranceMapAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchInsuranceMapAuditLog in the database
        List<BranchInsuranceMapAuditLog> branchInsuranceMapAuditLogList = branchInsuranceMapAuditLogRepository.findAll();
        assertThat(branchInsuranceMapAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBranchInsuranceMapAuditLogs() throws Exception {
        // Initialize the database
        branchInsuranceMapAuditLogRepository.saveAndFlush(branchInsuranceMapAuditLog);

        // Get all the branchInsuranceMapAuditLogList
        restBranchInsuranceMapAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(branchInsuranceMapAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].brachInsanceMapId").value(hasItem(DEFAULT_BRACH_INSANCE_MAP_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getBranchInsuranceMapAuditLog() throws Exception {
        // Initialize the database
        branchInsuranceMapAuditLogRepository.saveAndFlush(branchInsuranceMapAuditLog);

        // Get the branchInsuranceMapAuditLog
        restBranchInsuranceMapAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, branchInsuranceMapAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(branchInsuranceMapAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.brachInsanceMapId").value(DEFAULT_BRACH_INSANCE_MAP_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingBranchInsuranceMapAuditLog() throws Exception {
        // Get the branchInsuranceMapAuditLog
        restBranchInsuranceMapAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBranchInsuranceMapAuditLog() throws Exception {
        // Initialize the database
        branchInsuranceMapAuditLogRepository.saveAndFlush(branchInsuranceMapAuditLog);

        int databaseSizeBeforeUpdate = branchInsuranceMapAuditLogRepository.findAll().size();

        // Update the branchInsuranceMapAuditLog
        BranchInsuranceMapAuditLog updatedBranchInsuranceMapAuditLog = branchInsuranceMapAuditLogRepository
            .findById(branchInsuranceMapAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedBranchInsuranceMapAuditLog are not directly saved in db
        em.detach(updatedBranchInsuranceMapAuditLog);
        updatedBranchInsuranceMapAuditLog
            .brachInsanceMapId(UPDATED_BRACH_INSANCE_MAP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO = branchInsuranceMapAuditLogMapper.toDto(
            updatedBranchInsuranceMapAuditLog
        );

        restBranchInsuranceMapAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchInsuranceMapAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchInsuranceMapAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the BranchInsuranceMapAuditLog in the database
        List<BranchInsuranceMapAuditLog> branchInsuranceMapAuditLogList = branchInsuranceMapAuditLogRepository.findAll();
        assertThat(branchInsuranceMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
        BranchInsuranceMapAuditLog testBranchInsuranceMapAuditLog = branchInsuranceMapAuditLogList.get(
            branchInsuranceMapAuditLogList.size() - 1
        );
        assertThat(testBranchInsuranceMapAuditLog.getBrachInsanceMapId()).isEqualTo(UPDATED_BRACH_INSANCE_MAP_ID);
        assertThat(testBranchInsuranceMapAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testBranchInsuranceMapAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testBranchInsuranceMapAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testBranchInsuranceMapAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testBranchInsuranceMapAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingBranchInsuranceMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchInsuranceMapAuditLogRepository.findAll().size();
        branchInsuranceMapAuditLog.setId(count.incrementAndGet());

        // Create the BranchInsuranceMapAuditLog
        BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO = branchInsuranceMapAuditLogMapper.toDto(branchInsuranceMapAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchInsuranceMapAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchInsuranceMapAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchInsuranceMapAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchInsuranceMapAuditLog in the database
        List<BranchInsuranceMapAuditLog> branchInsuranceMapAuditLogList = branchInsuranceMapAuditLogRepository.findAll();
        assertThat(branchInsuranceMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBranchInsuranceMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchInsuranceMapAuditLogRepository.findAll().size();
        branchInsuranceMapAuditLog.setId(count.incrementAndGet());

        // Create the BranchInsuranceMapAuditLog
        BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO = branchInsuranceMapAuditLogMapper.toDto(branchInsuranceMapAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchInsuranceMapAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchInsuranceMapAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchInsuranceMapAuditLog in the database
        List<BranchInsuranceMapAuditLog> branchInsuranceMapAuditLogList = branchInsuranceMapAuditLogRepository.findAll();
        assertThat(branchInsuranceMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBranchInsuranceMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchInsuranceMapAuditLogRepository.findAll().size();
        branchInsuranceMapAuditLog.setId(count.incrementAndGet());

        // Create the BranchInsuranceMapAuditLog
        BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO = branchInsuranceMapAuditLogMapper.toDto(branchInsuranceMapAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchInsuranceMapAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchInsuranceMapAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchInsuranceMapAuditLog in the database
        List<BranchInsuranceMapAuditLog> branchInsuranceMapAuditLogList = branchInsuranceMapAuditLogRepository.findAll();
        assertThat(branchInsuranceMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBranchInsuranceMapAuditLogWithPatch() throws Exception {
        // Initialize the database
        branchInsuranceMapAuditLogRepository.saveAndFlush(branchInsuranceMapAuditLog);

        int databaseSizeBeforeUpdate = branchInsuranceMapAuditLogRepository.findAll().size();

        // Update the branchInsuranceMapAuditLog using partial update
        BranchInsuranceMapAuditLog partialUpdatedBranchInsuranceMapAuditLog = new BranchInsuranceMapAuditLog();
        partialUpdatedBranchInsuranceMapAuditLog.setId(branchInsuranceMapAuditLog.getId());

        partialUpdatedBranchInsuranceMapAuditLog.oldRowData(UPDATED_OLD_ROW_DATA).dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restBranchInsuranceMapAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchInsuranceMapAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchInsuranceMapAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the BranchInsuranceMapAuditLog in the database
        List<BranchInsuranceMapAuditLog> branchInsuranceMapAuditLogList = branchInsuranceMapAuditLogRepository.findAll();
        assertThat(branchInsuranceMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
        BranchInsuranceMapAuditLog testBranchInsuranceMapAuditLog = branchInsuranceMapAuditLogList.get(
            branchInsuranceMapAuditLogList.size() - 1
        );
        assertThat(testBranchInsuranceMapAuditLog.getBrachInsanceMapId()).isEqualTo(DEFAULT_BRACH_INSANCE_MAP_ID);
        assertThat(testBranchInsuranceMapAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testBranchInsuranceMapAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testBranchInsuranceMapAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testBranchInsuranceMapAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testBranchInsuranceMapAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateBranchInsuranceMapAuditLogWithPatch() throws Exception {
        // Initialize the database
        branchInsuranceMapAuditLogRepository.saveAndFlush(branchInsuranceMapAuditLog);

        int databaseSizeBeforeUpdate = branchInsuranceMapAuditLogRepository.findAll().size();

        // Update the branchInsuranceMapAuditLog using partial update
        BranchInsuranceMapAuditLog partialUpdatedBranchInsuranceMapAuditLog = new BranchInsuranceMapAuditLog();
        partialUpdatedBranchInsuranceMapAuditLog.setId(branchInsuranceMapAuditLog.getId());

        partialUpdatedBranchInsuranceMapAuditLog
            .brachInsanceMapId(UPDATED_BRACH_INSANCE_MAP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restBranchInsuranceMapAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchInsuranceMapAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchInsuranceMapAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the BranchInsuranceMapAuditLog in the database
        List<BranchInsuranceMapAuditLog> branchInsuranceMapAuditLogList = branchInsuranceMapAuditLogRepository.findAll();
        assertThat(branchInsuranceMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
        BranchInsuranceMapAuditLog testBranchInsuranceMapAuditLog = branchInsuranceMapAuditLogList.get(
            branchInsuranceMapAuditLogList.size() - 1
        );
        assertThat(testBranchInsuranceMapAuditLog.getBrachInsanceMapId()).isEqualTo(UPDATED_BRACH_INSANCE_MAP_ID);
        assertThat(testBranchInsuranceMapAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testBranchInsuranceMapAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testBranchInsuranceMapAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testBranchInsuranceMapAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testBranchInsuranceMapAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingBranchInsuranceMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchInsuranceMapAuditLogRepository.findAll().size();
        branchInsuranceMapAuditLog.setId(count.incrementAndGet());

        // Create the BranchInsuranceMapAuditLog
        BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO = branchInsuranceMapAuditLogMapper.toDto(branchInsuranceMapAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchInsuranceMapAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, branchInsuranceMapAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchInsuranceMapAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchInsuranceMapAuditLog in the database
        List<BranchInsuranceMapAuditLog> branchInsuranceMapAuditLogList = branchInsuranceMapAuditLogRepository.findAll();
        assertThat(branchInsuranceMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBranchInsuranceMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchInsuranceMapAuditLogRepository.findAll().size();
        branchInsuranceMapAuditLog.setId(count.incrementAndGet());

        // Create the BranchInsuranceMapAuditLog
        BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO = branchInsuranceMapAuditLogMapper.toDto(branchInsuranceMapAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchInsuranceMapAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchInsuranceMapAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchInsuranceMapAuditLog in the database
        List<BranchInsuranceMapAuditLog> branchInsuranceMapAuditLogList = branchInsuranceMapAuditLogRepository.findAll();
        assertThat(branchInsuranceMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBranchInsuranceMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = branchInsuranceMapAuditLogRepository.findAll().size();
        branchInsuranceMapAuditLog.setId(count.incrementAndGet());

        // Create the BranchInsuranceMapAuditLog
        BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO = branchInsuranceMapAuditLogMapper.toDto(branchInsuranceMapAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchInsuranceMapAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchInsuranceMapAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchInsuranceMapAuditLog in the database
        List<BranchInsuranceMapAuditLog> branchInsuranceMapAuditLogList = branchInsuranceMapAuditLogRepository.findAll();
        assertThat(branchInsuranceMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBranchInsuranceMapAuditLog() throws Exception {
        // Initialize the database
        branchInsuranceMapAuditLogRepository.saveAndFlush(branchInsuranceMapAuditLog);

        int databaseSizeBeforeDelete = branchInsuranceMapAuditLogRepository.findAll().size();

        // Delete the branchInsuranceMapAuditLog
        restBranchInsuranceMapAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, branchInsuranceMapAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BranchInsuranceMapAuditLog> branchInsuranceMapAuditLogList = branchInsuranceMapAuditLogRepository.findAll();
        assertThat(branchInsuranceMapAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
