package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.InsuranceMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.InsuranceMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.InsuranceMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.InsuranceMasterAuditLogMapper;
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
 * Integration tests for the {@link InsuranceMasterAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InsuranceMasterAuditLogResourceIT {

    private static final Long DEFAULT_INSRNCE_ID = 1L;
    private static final Long UPDATED_INSRNCE_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/insurance-master-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InsuranceMasterAuditLogRepository insuranceMasterAuditLogRepository;

    @Autowired
    private InsuranceMasterAuditLogMapper insuranceMasterAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInsuranceMasterAuditLogMockMvc;

    private InsuranceMasterAuditLog insuranceMasterAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceMasterAuditLog createEntity(EntityManager em) {
        InsuranceMasterAuditLog insuranceMasterAuditLog = new InsuranceMasterAuditLog()
            .insrnceId(DEFAULT_INSRNCE_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return insuranceMasterAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceMasterAuditLog createUpdatedEntity(EntityManager em) {
        InsuranceMasterAuditLog insuranceMasterAuditLog = new InsuranceMasterAuditLog()
            .insrnceId(UPDATED_INSRNCE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return insuranceMasterAuditLog;
    }

    @BeforeEach
    public void initTest() {
        insuranceMasterAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createInsuranceMasterAuditLog() throws Exception {
        int databaseSizeBeforeCreate = insuranceMasterAuditLogRepository.findAll().size();
        // Create the InsuranceMasterAuditLog
        InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO = insuranceMasterAuditLogMapper.toDto(insuranceMasterAuditLog);
        restInsuranceMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceMasterAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InsuranceMasterAuditLog in the database
        List<InsuranceMasterAuditLog> insuranceMasterAuditLogList = insuranceMasterAuditLogRepository.findAll();
        assertThat(insuranceMasterAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        InsuranceMasterAuditLog testInsuranceMasterAuditLog = insuranceMasterAuditLogList.get(insuranceMasterAuditLogList.size() - 1);
        assertThat(testInsuranceMasterAuditLog.getInsrnceId()).isEqualTo(DEFAULT_INSRNCE_ID);
        assertThat(testInsuranceMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testInsuranceMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testInsuranceMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testInsuranceMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testInsuranceMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createInsuranceMasterAuditLogWithExistingId() throws Exception {
        // Create the InsuranceMasterAuditLog with an existing ID
        insuranceMasterAuditLog.setId(1L);
        InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO = insuranceMasterAuditLogMapper.toDto(insuranceMasterAuditLog);

        int databaseSizeBeforeCreate = insuranceMasterAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsuranceMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceMasterAuditLog in the database
        List<InsuranceMasterAuditLog> insuranceMasterAuditLogList = insuranceMasterAuditLogRepository.findAll();
        assertThat(insuranceMasterAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInsuranceMasterAuditLogs() throws Exception {
        // Initialize the database
        insuranceMasterAuditLogRepository.saveAndFlush(insuranceMasterAuditLog);

        // Get all the insuranceMasterAuditLogList
        restInsuranceMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(insuranceMasterAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].insrnceId").value(hasItem(DEFAULT_INSRNCE_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getInsuranceMasterAuditLog() throws Exception {
        // Initialize the database
        insuranceMasterAuditLogRepository.saveAndFlush(insuranceMasterAuditLog);

        // Get the insuranceMasterAuditLog
        restInsuranceMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, insuranceMasterAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(insuranceMasterAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.insrnceId").value(DEFAULT_INSRNCE_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingInsuranceMasterAuditLog() throws Exception {
        // Get the insuranceMasterAuditLog
        restInsuranceMasterAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewInsuranceMasterAuditLog() throws Exception {
        // Initialize the database
        insuranceMasterAuditLogRepository.saveAndFlush(insuranceMasterAuditLog);

        int databaseSizeBeforeUpdate = insuranceMasterAuditLogRepository.findAll().size();

        // Update the insuranceMasterAuditLog
        InsuranceMasterAuditLog updatedInsuranceMasterAuditLog = insuranceMasterAuditLogRepository
            .findById(insuranceMasterAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedInsuranceMasterAuditLog are not directly saved in db
        em.detach(updatedInsuranceMasterAuditLog);
        updatedInsuranceMasterAuditLog
            .insrnceId(UPDATED_INSRNCE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO = insuranceMasterAuditLogMapper.toDto(updatedInsuranceMasterAuditLog);

        restInsuranceMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, insuranceMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceMasterAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceMasterAuditLog in the database
        List<InsuranceMasterAuditLog> insuranceMasterAuditLogList = insuranceMasterAuditLogRepository.findAll();
        assertThat(insuranceMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        InsuranceMasterAuditLog testInsuranceMasterAuditLog = insuranceMasterAuditLogList.get(insuranceMasterAuditLogList.size() - 1);
        assertThat(testInsuranceMasterAuditLog.getInsrnceId()).isEqualTo(UPDATED_INSRNCE_ID);
        assertThat(testInsuranceMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testInsuranceMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testInsuranceMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testInsuranceMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testInsuranceMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingInsuranceMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = insuranceMasterAuditLogRepository.findAll().size();
        insuranceMasterAuditLog.setId(count.incrementAndGet());

        // Create the InsuranceMasterAuditLog
        InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO = insuranceMasterAuditLogMapper.toDto(insuranceMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, insuranceMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceMasterAuditLog in the database
        List<InsuranceMasterAuditLog> insuranceMasterAuditLogList = insuranceMasterAuditLogRepository.findAll();
        assertThat(insuranceMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInsuranceMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = insuranceMasterAuditLogRepository.findAll().size();
        insuranceMasterAuditLog.setId(count.incrementAndGet());

        // Create the InsuranceMasterAuditLog
        InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO = insuranceMasterAuditLogMapper.toDto(insuranceMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceMasterAuditLog in the database
        List<InsuranceMasterAuditLog> insuranceMasterAuditLogList = insuranceMasterAuditLogRepository.findAll();
        assertThat(insuranceMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInsuranceMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = insuranceMasterAuditLogRepository.findAll().size();
        insuranceMasterAuditLog.setId(count.incrementAndGet());

        // Create the InsuranceMasterAuditLog
        InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO = insuranceMasterAuditLogMapper.toDto(insuranceMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InsuranceMasterAuditLog in the database
        List<InsuranceMasterAuditLog> insuranceMasterAuditLogList = insuranceMasterAuditLogRepository.findAll();
        assertThat(insuranceMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInsuranceMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        insuranceMasterAuditLogRepository.saveAndFlush(insuranceMasterAuditLog);

        int databaseSizeBeforeUpdate = insuranceMasterAuditLogRepository.findAll().size();

        // Update the insuranceMasterAuditLog using partial update
        InsuranceMasterAuditLog partialUpdatedInsuranceMasterAuditLog = new InsuranceMasterAuditLog();
        partialUpdatedInsuranceMasterAuditLog.setId(insuranceMasterAuditLog.getId());

        partialUpdatedInsuranceMasterAuditLog
            .insrnceId(UPDATED_INSRNCE_ID)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restInsuranceMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInsuranceMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInsuranceMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceMasterAuditLog in the database
        List<InsuranceMasterAuditLog> insuranceMasterAuditLogList = insuranceMasterAuditLogRepository.findAll();
        assertThat(insuranceMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        InsuranceMasterAuditLog testInsuranceMasterAuditLog = insuranceMasterAuditLogList.get(insuranceMasterAuditLogList.size() - 1);
        assertThat(testInsuranceMasterAuditLog.getInsrnceId()).isEqualTo(UPDATED_INSRNCE_ID);
        assertThat(testInsuranceMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testInsuranceMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testInsuranceMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testInsuranceMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testInsuranceMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateInsuranceMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        insuranceMasterAuditLogRepository.saveAndFlush(insuranceMasterAuditLog);

        int databaseSizeBeforeUpdate = insuranceMasterAuditLogRepository.findAll().size();

        // Update the insuranceMasterAuditLog using partial update
        InsuranceMasterAuditLog partialUpdatedInsuranceMasterAuditLog = new InsuranceMasterAuditLog();
        partialUpdatedInsuranceMasterAuditLog.setId(insuranceMasterAuditLog.getId());

        partialUpdatedInsuranceMasterAuditLog
            .insrnceId(UPDATED_INSRNCE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restInsuranceMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInsuranceMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInsuranceMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceMasterAuditLog in the database
        List<InsuranceMasterAuditLog> insuranceMasterAuditLogList = insuranceMasterAuditLogRepository.findAll();
        assertThat(insuranceMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        InsuranceMasterAuditLog testInsuranceMasterAuditLog = insuranceMasterAuditLogList.get(insuranceMasterAuditLogList.size() - 1);
        assertThat(testInsuranceMasterAuditLog.getInsrnceId()).isEqualTo(UPDATED_INSRNCE_ID);
        assertThat(testInsuranceMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testInsuranceMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testInsuranceMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testInsuranceMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testInsuranceMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingInsuranceMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = insuranceMasterAuditLogRepository.findAll().size();
        insuranceMasterAuditLog.setId(count.incrementAndGet());

        // Create the InsuranceMasterAuditLog
        InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO = insuranceMasterAuditLogMapper.toDto(insuranceMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, insuranceMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(insuranceMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceMasterAuditLog in the database
        List<InsuranceMasterAuditLog> insuranceMasterAuditLogList = insuranceMasterAuditLogRepository.findAll();
        assertThat(insuranceMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInsuranceMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = insuranceMasterAuditLogRepository.findAll().size();
        insuranceMasterAuditLog.setId(count.incrementAndGet());

        // Create the InsuranceMasterAuditLog
        InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO = insuranceMasterAuditLogMapper.toDto(insuranceMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(insuranceMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceMasterAuditLog in the database
        List<InsuranceMasterAuditLog> insuranceMasterAuditLogList = insuranceMasterAuditLogRepository.findAll();
        assertThat(insuranceMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInsuranceMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = insuranceMasterAuditLogRepository.findAll().size();
        insuranceMasterAuditLog.setId(count.incrementAndGet());

        // Create the InsuranceMasterAuditLog
        InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO = insuranceMasterAuditLogMapper.toDto(insuranceMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(insuranceMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InsuranceMasterAuditLog in the database
        List<InsuranceMasterAuditLog> insuranceMasterAuditLogList = insuranceMasterAuditLogRepository.findAll();
        assertThat(insuranceMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInsuranceMasterAuditLog() throws Exception {
        // Initialize the database
        insuranceMasterAuditLogRepository.saveAndFlush(insuranceMasterAuditLog);

        int databaseSizeBeforeDelete = insuranceMasterAuditLogRepository.findAll().size();

        // Delete the insuranceMasterAuditLog
        restInsuranceMasterAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, insuranceMasterAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InsuranceMasterAuditLog> insuranceMasterAuditLogList = insuranceMasterAuditLogRepository.findAll();
        assertThat(insuranceMasterAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
