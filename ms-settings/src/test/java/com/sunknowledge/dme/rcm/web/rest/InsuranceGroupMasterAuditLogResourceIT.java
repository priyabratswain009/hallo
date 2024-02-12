package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.InsuranceGroupMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.InsuranceGroupMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.InsuranceGroupMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.InsuranceGroupMasterAuditLogMapper;
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
 * Integration tests for the {@link InsuranceGroupMasterAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InsuranceGroupMasterAuditLogResourceIT {

    private static final Long DEFAULT_INSRNCE_GRP_ID = 1L;
    private static final Long UPDATED_INSRNCE_GRP_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/insurance-group-master-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InsuranceGroupMasterAuditLogRepository insuranceGroupMasterAuditLogRepository;

    @Autowired
    private InsuranceGroupMasterAuditLogMapper insuranceGroupMasterAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInsuranceGroupMasterAuditLogMockMvc;

    private InsuranceGroupMasterAuditLog insuranceGroupMasterAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceGroupMasterAuditLog createEntity(EntityManager em) {
        InsuranceGroupMasterAuditLog insuranceGroupMasterAuditLog = new InsuranceGroupMasterAuditLog()
            .insrnceGrpId(DEFAULT_INSRNCE_GRP_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return insuranceGroupMasterAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceGroupMasterAuditLog createUpdatedEntity(EntityManager em) {
        InsuranceGroupMasterAuditLog insuranceGroupMasterAuditLog = new InsuranceGroupMasterAuditLog()
            .insrnceGrpId(UPDATED_INSRNCE_GRP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return insuranceGroupMasterAuditLog;
    }

    @BeforeEach
    public void initTest() {
        insuranceGroupMasterAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createInsuranceGroupMasterAuditLog() throws Exception {
        int databaseSizeBeforeCreate = insuranceGroupMasterAuditLogRepository.findAll().size();
        // Create the InsuranceGroupMasterAuditLog
        InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO = insuranceGroupMasterAuditLogMapper.toDto(
            insuranceGroupMasterAuditLog
        );
        restInsuranceGroupMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceGroupMasterAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InsuranceGroupMasterAuditLog in the database
        List<InsuranceGroupMasterAuditLog> insuranceGroupMasterAuditLogList = insuranceGroupMasterAuditLogRepository.findAll();
        assertThat(insuranceGroupMasterAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        InsuranceGroupMasterAuditLog testInsuranceGroupMasterAuditLog = insuranceGroupMasterAuditLogList.get(
            insuranceGroupMasterAuditLogList.size() - 1
        );
        assertThat(testInsuranceGroupMasterAuditLog.getInsrnceGrpId()).isEqualTo(DEFAULT_INSRNCE_GRP_ID);
        assertThat(testInsuranceGroupMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testInsuranceGroupMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testInsuranceGroupMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testInsuranceGroupMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testInsuranceGroupMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createInsuranceGroupMasterAuditLogWithExistingId() throws Exception {
        // Create the InsuranceGroupMasterAuditLog with an existing ID
        insuranceGroupMasterAuditLog.setId(1L);
        InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO = insuranceGroupMasterAuditLogMapper.toDto(
            insuranceGroupMasterAuditLog
        );

        int databaseSizeBeforeCreate = insuranceGroupMasterAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsuranceGroupMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceGroupMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceGroupMasterAuditLog in the database
        List<InsuranceGroupMasterAuditLog> insuranceGroupMasterAuditLogList = insuranceGroupMasterAuditLogRepository.findAll();
        assertThat(insuranceGroupMasterAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInsuranceGroupMasterAuditLogs() throws Exception {
        // Initialize the database
        insuranceGroupMasterAuditLogRepository.saveAndFlush(insuranceGroupMasterAuditLog);

        // Get all the insuranceGroupMasterAuditLogList
        restInsuranceGroupMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(insuranceGroupMasterAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].insrnceGrpId").value(hasItem(DEFAULT_INSRNCE_GRP_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getInsuranceGroupMasterAuditLog() throws Exception {
        // Initialize the database
        insuranceGroupMasterAuditLogRepository.saveAndFlush(insuranceGroupMasterAuditLog);

        // Get the insuranceGroupMasterAuditLog
        restInsuranceGroupMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, insuranceGroupMasterAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(insuranceGroupMasterAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.insrnceGrpId").value(DEFAULT_INSRNCE_GRP_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingInsuranceGroupMasterAuditLog() throws Exception {
        // Get the insuranceGroupMasterAuditLog
        restInsuranceGroupMasterAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingInsuranceGroupMasterAuditLog() throws Exception {
        // Initialize the database
        insuranceGroupMasterAuditLogRepository.saveAndFlush(insuranceGroupMasterAuditLog);

        int databaseSizeBeforeUpdate = insuranceGroupMasterAuditLogRepository.findAll().size();

        // Update the insuranceGroupMasterAuditLog
        InsuranceGroupMasterAuditLog updatedInsuranceGroupMasterAuditLog = insuranceGroupMasterAuditLogRepository
            .findById(insuranceGroupMasterAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedInsuranceGroupMasterAuditLog are not directly saved in db
        em.detach(updatedInsuranceGroupMasterAuditLog);
        updatedInsuranceGroupMasterAuditLog
            .insrnceGrpId(UPDATED_INSRNCE_GRP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO = insuranceGroupMasterAuditLogMapper.toDto(
            updatedInsuranceGroupMasterAuditLog
        );

        restInsuranceGroupMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, insuranceGroupMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceGroupMasterAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceGroupMasterAuditLog in the database
        List<InsuranceGroupMasterAuditLog> insuranceGroupMasterAuditLogList = insuranceGroupMasterAuditLogRepository.findAll();
        assertThat(insuranceGroupMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        InsuranceGroupMasterAuditLog testInsuranceGroupMasterAuditLog = insuranceGroupMasterAuditLogList.get(
            insuranceGroupMasterAuditLogList.size() - 1
        );
        assertThat(testInsuranceGroupMasterAuditLog.getInsrnceGrpId()).isEqualTo(UPDATED_INSRNCE_GRP_ID);
        assertThat(testInsuranceGroupMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testInsuranceGroupMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testInsuranceGroupMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testInsuranceGroupMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testInsuranceGroupMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingInsuranceGroupMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = insuranceGroupMasterAuditLogRepository.findAll().size();
        insuranceGroupMasterAuditLog.setId(count.incrementAndGet());

        // Create the InsuranceGroupMasterAuditLog
        InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO = insuranceGroupMasterAuditLogMapper.toDto(
            insuranceGroupMasterAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceGroupMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, insuranceGroupMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceGroupMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceGroupMasterAuditLog in the database
        List<InsuranceGroupMasterAuditLog> insuranceGroupMasterAuditLogList = insuranceGroupMasterAuditLogRepository.findAll();
        assertThat(insuranceGroupMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInsuranceGroupMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = insuranceGroupMasterAuditLogRepository.findAll().size();
        insuranceGroupMasterAuditLog.setId(count.incrementAndGet());

        // Create the InsuranceGroupMasterAuditLog
        InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO = insuranceGroupMasterAuditLogMapper.toDto(
            insuranceGroupMasterAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceGroupMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceGroupMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceGroupMasterAuditLog in the database
        List<InsuranceGroupMasterAuditLog> insuranceGroupMasterAuditLogList = insuranceGroupMasterAuditLogRepository.findAll();
        assertThat(insuranceGroupMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInsuranceGroupMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = insuranceGroupMasterAuditLogRepository.findAll().size();
        insuranceGroupMasterAuditLog.setId(count.incrementAndGet());

        // Create the InsuranceGroupMasterAuditLog
        InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO = insuranceGroupMasterAuditLogMapper.toDto(
            insuranceGroupMasterAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceGroupMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceGroupMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InsuranceGroupMasterAuditLog in the database
        List<InsuranceGroupMasterAuditLog> insuranceGroupMasterAuditLogList = insuranceGroupMasterAuditLogRepository.findAll();
        assertThat(insuranceGroupMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInsuranceGroupMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        insuranceGroupMasterAuditLogRepository.saveAndFlush(insuranceGroupMasterAuditLog);

        int databaseSizeBeforeUpdate = insuranceGroupMasterAuditLogRepository.findAll().size();

        // Update the insuranceGroupMasterAuditLog using partial update
        InsuranceGroupMasterAuditLog partialUpdatedInsuranceGroupMasterAuditLog = new InsuranceGroupMasterAuditLog();
        partialUpdatedInsuranceGroupMasterAuditLog.setId(insuranceGroupMasterAuditLog.getId());

        partialUpdatedInsuranceGroupMasterAuditLog.dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restInsuranceGroupMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInsuranceGroupMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInsuranceGroupMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceGroupMasterAuditLog in the database
        List<InsuranceGroupMasterAuditLog> insuranceGroupMasterAuditLogList = insuranceGroupMasterAuditLogRepository.findAll();
        assertThat(insuranceGroupMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        InsuranceGroupMasterAuditLog testInsuranceGroupMasterAuditLog = insuranceGroupMasterAuditLogList.get(
            insuranceGroupMasterAuditLogList.size() - 1
        );
        assertThat(testInsuranceGroupMasterAuditLog.getInsrnceGrpId()).isEqualTo(DEFAULT_INSRNCE_GRP_ID);
        assertThat(testInsuranceGroupMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testInsuranceGroupMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testInsuranceGroupMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testInsuranceGroupMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testInsuranceGroupMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateInsuranceGroupMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        insuranceGroupMasterAuditLogRepository.saveAndFlush(insuranceGroupMasterAuditLog);

        int databaseSizeBeforeUpdate = insuranceGroupMasterAuditLogRepository.findAll().size();

        // Update the insuranceGroupMasterAuditLog using partial update
        InsuranceGroupMasterAuditLog partialUpdatedInsuranceGroupMasterAuditLog = new InsuranceGroupMasterAuditLog();
        partialUpdatedInsuranceGroupMasterAuditLog.setId(insuranceGroupMasterAuditLog.getId());

        partialUpdatedInsuranceGroupMasterAuditLog
            .insrnceGrpId(UPDATED_INSRNCE_GRP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restInsuranceGroupMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInsuranceGroupMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInsuranceGroupMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceGroupMasterAuditLog in the database
        List<InsuranceGroupMasterAuditLog> insuranceGroupMasterAuditLogList = insuranceGroupMasterAuditLogRepository.findAll();
        assertThat(insuranceGroupMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        InsuranceGroupMasterAuditLog testInsuranceGroupMasterAuditLog = insuranceGroupMasterAuditLogList.get(
            insuranceGroupMasterAuditLogList.size() - 1
        );
        assertThat(testInsuranceGroupMasterAuditLog.getInsrnceGrpId()).isEqualTo(UPDATED_INSRNCE_GRP_ID);
        assertThat(testInsuranceGroupMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testInsuranceGroupMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testInsuranceGroupMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testInsuranceGroupMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testInsuranceGroupMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingInsuranceGroupMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = insuranceGroupMasterAuditLogRepository.findAll().size();
        insuranceGroupMasterAuditLog.setId(count.incrementAndGet());

        // Create the InsuranceGroupMasterAuditLog
        InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO = insuranceGroupMasterAuditLogMapper.toDto(
            insuranceGroupMasterAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceGroupMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, insuranceGroupMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(insuranceGroupMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceGroupMasterAuditLog in the database
        List<InsuranceGroupMasterAuditLog> insuranceGroupMasterAuditLogList = insuranceGroupMasterAuditLogRepository.findAll();
        assertThat(insuranceGroupMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInsuranceGroupMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = insuranceGroupMasterAuditLogRepository.findAll().size();
        insuranceGroupMasterAuditLog.setId(count.incrementAndGet());

        // Create the InsuranceGroupMasterAuditLog
        InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO = insuranceGroupMasterAuditLogMapper.toDto(
            insuranceGroupMasterAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceGroupMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(insuranceGroupMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceGroupMasterAuditLog in the database
        List<InsuranceGroupMasterAuditLog> insuranceGroupMasterAuditLogList = insuranceGroupMasterAuditLogRepository.findAll();
        assertThat(insuranceGroupMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInsuranceGroupMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = insuranceGroupMasterAuditLogRepository.findAll().size();
        insuranceGroupMasterAuditLog.setId(count.incrementAndGet());

        // Create the InsuranceGroupMasterAuditLog
        InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO = insuranceGroupMasterAuditLogMapper.toDto(
            insuranceGroupMasterAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceGroupMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(insuranceGroupMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InsuranceGroupMasterAuditLog in the database
        List<InsuranceGroupMasterAuditLog> insuranceGroupMasterAuditLogList = insuranceGroupMasterAuditLogRepository.findAll();
        assertThat(insuranceGroupMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInsuranceGroupMasterAuditLog() throws Exception {
        // Initialize the database
        insuranceGroupMasterAuditLogRepository.saveAndFlush(insuranceGroupMasterAuditLog);

        int databaseSizeBeforeDelete = insuranceGroupMasterAuditLogRepository.findAll().size();

        // Delete the insuranceGroupMasterAuditLog
        restInsuranceGroupMasterAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, insuranceGroupMasterAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InsuranceGroupMasterAuditLog> insuranceGroupMasterAuditLogList = insuranceGroupMasterAuditLogRepository.findAll();
        assertThat(insuranceGroupMasterAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
