package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.InsuranceDocumentAuditLog;
import com.sunknowledge.dme.rcm.repository.InsuranceDocumentAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.InsuranceDocumentAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.InsuranceDocumentAuditLogMapper;
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
 * Integration tests for the {@link InsuranceDocumentAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InsuranceDocumentAuditLogResourceIT {

    private static final Long DEFAULT_INRANCE_DOCMET_ID = 1L;
    private static final Long UPDATED_INRANCE_DOCMET_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/insurance-document-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InsuranceDocumentAuditLogRepository insuranceDocumentAuditLogRepository;

    @Autowired
    private InsuranceDocumentAuditLogMapper insuranceDocumentAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInsuranceDocumentAuditLogMockMvc;

    private InsuranceDocumentAuditLog insuranceDocumentAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceDocumentAuditLog createEntity(EntityManager em) {
        InsuranceDocumentAuditLog insuranceDocumentAuditLog = new InsuranceDocumentAuditLog()
            .inranceDocmetId(DEFAULT_INRANCE_DOCMET_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return insuranceDocumentAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceDocumentAuditLog createUpdatedEntity(EntityManager em) {
        InsuranceDocumentAuditLog insuranceDocumentAuditLog = new InsuranceDocumentAuditLog()
            .inranceDocmetId(UPDATED_INRANCE_DOCMET_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return insuranceDocumentAuditLog;
    }

    @BeforeEach
    public void initTest() {
        insuranceDocumentAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createInsuranceDocumentAuditLog() throws Exception {
        int databaseSizeBeforeCreate = insuranceDocumentAuditLogRepository.findAll().size();
        // Create the InsuranceDocumentAuditLog
        InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO = insuranceDocumentAuditLogMapper.toDto(insuranceDocumentAuditLog);
        restInsuranceDocumentAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceDocumentAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InsuranceDocumentAuditLog in the database
        List<InsuranceDocumentAuditLog> insuranceDocumentAuditLogList = insuranceDocumentAuditLogRepository.findAll();
        assertThat(insuranceDocumentAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        InsuranceDocumentAuditLog testInsuranceDocumentAuditLog = insuranceDocumentAuditLogList.get(
            insuranceDocumentAuditLogList.size() - 1
        );
        assertThat(testInsuranceDocumentAuditLog.getInranceDocmetId()).isEqualTo(DEFAULT_INRANCE_DOCMET_ID);
        assertThat(testInsuranceDocumentAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testInsuranceDocumentAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testInsuranceDocumentAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testInsuranceDocumentAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testInsuranceDocumentAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createInsuranceDocumentAuditLogWithExistingId() throws Exception {
        // Create the InsuranceDocumentAuditLog with an existing ID
        insuranceDocumentAuditLog.setId(1L);
        InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO = insuranceDocumentAuditLogMapper.toDto(insuranceDocumentAuditLog);

        int databaseSizeBeforeCreate = insuranceDocumentAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsuranceDocumentAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceDocumentAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceDocumentAuditLog in the database
        List<InsuranceDocumentAuditLog> insuranceDocumentAuditLogList = insuranceDocumentAuditLogRepository.findAll();
        assertThat(insuranceDocumentAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInsuranceDocumentAuditLogs() throws Exception {
        // Initialize the database
        insuranceDocumentAuditLogRepository.saveAndFlush(insuranceDocumentAuditLog);

        // Get all the insuranceDocumentAuditLogList
        restInsuranceDocumentAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(insuranceDocumentAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].inranceDocmetId").value(hasItem(DEFAULT_INRANCE_DOCMET_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getInsuranceDocumentAuditLog() throws Exception {
        // Initialize the database
        insuranceDocumentAuditLogRepository.saveAndFlush(insuranceDocumentAuditLog);

        // Get the insuranceDocumentAuditLog
        restInsuranceDocumentAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, insuranceDocumentAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(insuranceDocumentAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.inranceDocmetId").value(DEFAULT_INRANCE_DOCMET_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingInsuranceDocumentAuditLog() throws Exception {
        // Get the insuranceDocumentAuditLog
        restInsuranceDocumentAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewInsuranceDocumentAuditLog() throws Exception {
        // Initialize the database
        insuranceDocumentAuditLogRepository.saveAndFlush(insuranceDocumentAuditLog);

        int databaseSizeBeforeUpdate = insuranceDocumentAuditLogRepository.findAll().size();

        // Update the insuranceDocumentAuditLog
        InsuranceDocumentAuditLog updatedInsuranceDocumentAuditLog = insuranceDocumentAuditLogRepository
            .findById(insuranceDocumentAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedInsuranceDocumentAuditLog are not directly saved in db
        em.detach(updatedInsuranceDocumentAuditLog);
        updatedInsuranceDocumentAuditLog
            .inranceDocmetId(UPDATED_INRANCE_DOCMET_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO = insuranceDocumentAuditLogMapper.toDto(updatedInsuranceDocumentAuditLog);

        restInsuranceDocumentAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, insuranceDocumentAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceDocumentAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceDocumentAuditLog in the database
        List<InsuranceDocumentAuditLog> insuranceDocumentAuditLogList = insuranceDocumentAuditLogRepository.findAll();
        assertThat(insuranceDocumentAuditLogList).hasSize(databaseSizeBeforeUpdate);
        InsuranceDocumentAuditLog testInsuranceDocumentAuditLog = insuranceDocumentAuditLogList.get(
            insuranceDocumentAuditLogList.size() - 1
        );
        assertThat(testInsuranceDocumentAuditLog.getInranceDocmetId()).isEqualTo(UPDATED_INRANCE_DOCMET_ID);
        assertThat(testInsuranceDocumentAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testInsuranceDocumentAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testInsuranceDocumentAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testInsuranceDocumentAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testInsuranceDocumentAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingInsuranceDocumentAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = insuranceDocumentAuditLogRepository.findAll().size();
        insuranceDocumentAuditLog.setId(count.incrementAndGet());

        // Create the InsuranceDocumentAuditLog
        InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO = insuranceDocumentAuditLogMapper.toDto(insuranceDocumentAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceDocumentAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, insuranceDocumentAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceDocumentAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceDocumentAuditLog in the database
        List<InsuranceDocumentAuditLog> insuranceDocumentAuditLogList = insuranceDocumentAuditLogRepository.findAll();
        assertThat(insuranceDocumentAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInsuranceDocumentAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = insuranceDocumentAuditLogRepository.findAll().size();
        insuranceDocumentAuditLog.setId(count.incrementAndGet());

        // Create the InsuranceDocumentAuditLog
        InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO = insuranceDocumentAuditLogMapper.toDto(insuranceDocumentAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceDocumentAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceDocumentAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceDocumentAuditLog in the database
        List<InsuranceDocumentAuditLog> insuranceDocumentAuditLogList = insuranceDocumentAuditLogRepository.findAll();
        assertThat(insuranceDocumentAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInsuranceDocumentAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = insuranceDocumentAuditLogRepository.findAll().size();
        insuranceDocumentAuditLog.setId(count.incrementAndGet());

        // Create the InsuranceDocumentAuditLog
        InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO = insuranceDocumentAuditLogMapper.toDto(insuranceDocumentAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceDocumentAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceDocumentAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InsuranceDocumentAuditLog in the database
        List<InsuranceDocumentAuditLog> insuranceDocumentAuditLogList = insuranceDocumentAuditLogRepository.findAll();
        assertThat(insuranceDocumentAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInsuranceDocumentAuditLogWithPatch() throws Exception {
        // Initialize the database
        insuranceDocumentAuditLogRepository.saveAndFlush(insuranceDocumentAuditLog);

        int databaseSizeBeforeUpdate = insuranceDocumentAuditLogRepository.findAll().size();

        // Update the insuranceDocumentAuditLog using partial update
        InsuranceDocumentAuditLog partialUpdatedInsuranceDocumentAuditLog = new InsuranceDocumentAuditLog();
        partialUpdatedInsuranceDocumentAuditLog.setId(insuranceDocumentAuditLog.getId());

        partialUpdatedInsuranceDocumentAuditLog
            .inranceDocmetId(UPDATED_INRANCE_DOCMET_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE);

        restInsuranceDocumentAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInsuranceDocumentAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInsuranceDocumentAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceDocumentAuditLog in the database
        List<InsuranceDocumentAuditLog> insuranceDocumentAuditLogList = insuranceDocumentAuditLogRepository.findAll();
        assertThat(insuranceDocumentAuditLogList).hasSize(databaseSizeBeforeUpdate);
        InsuranceDocumentAuditLog testInsuranceDocumentAuditLog = insuranceDocumentAuditLogList.get(
            insuranceDocumentAuditLogList.size() - 1
        );
        assertThat(testInsuranceDocumentAuditLog.getInranceDocmetId()).isEqualTo(UPDATED_INRANCE_DOCMET_ID);
        assertThat(testInsuranceDocumentAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testInsuranceDocumentAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testInsuranceDocumentAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testInsuranceDocumentAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testInsuranceDocumentAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateInsuranceDocumentAuditLogWithPatch() throws Exception {
        // Initialize the database
        insuranceDocumentAuditLogRepository.saveAndFlush(insuranceDocumentAuditLog);

        int databaseSizeBeforeUpdate = insuranceDocumentAuditLogRepository.findAll().size();

        // Update the insuranceDocumentAuditLog using partial update
        InsuranceDocumentAuditLog partialUpdatedInsuranceDocumentAuditLog = new InsuranceDocumentAuditLog();
        partialUpdatedInsuranceDocumentAuditLog.setId(insuranceDocumentAuditLog.getId());

        partialUpdatedInsuranceDocumentAuditLog
            .inranceDocmetId(UPDATED_INRANCE_DOCMET_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restInsuranceDocumentAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInsuranceDocumentAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInsuranceDocumentAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceDocumentAuditLog in the database
        List<InsuranceDocumentAuditLog> insuranceDocumentAuditLogList = insuranceDocumentAuditLogRepository.findAll();
        assertThat(insuranceDocumentAuditLogList).hasSize(databaseSizeBeforeUpdate);
        InsuranceDocumentAuditLog testInsuranceDocumentAuditLog = insuranceDocumentAuditLogList.get(
            insuranceDocumentAuditLogList.size() - 1
        );
        assertThat(testInsuranceDocumentAuditLog.getInranceDocmetId()).isEqualTo(UPDATED_INRANCE_DOCMET_ID);
        assertThat(testInsuranceDocumentAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testInsuranceDocumentAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testInsuranceDocumentAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testInsuranceDocumentAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testInsuranceDocumentAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingInsuranceDocumentAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = insuranceDocumentAuditLogRepository.findAll().size();
        insuranceDocumentAuditLog.setId(count.incrementAndGet());

        // Create the InsuranceDocumentAuditLog
        InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO = insuranceDocumentAuditLogMapper.toDto(insuranceDocumentAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceDocumentAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, insuranceDocumentAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(insuranceDocumentAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceDocumentAuditLog in the database
        List<InsuranceDocumentAuditLog> insuranceDocumentAuditLogList = insuranceDocumentAuditLogRepository.findAll();
        assertThat(insuranceDocumentAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInsuranceDocumentAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = insuranceDocumentAuditLogRepository.findAll().size();
        insuranceDocumentAuditLog.setId(count.incrementAndGet());

        // Create the InsuranceDocumentAuditLog
        InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO = insuranceDocumentAuditLogMapper.toDto(insuranceDocumentAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceDocumentAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(insuranceDocumentAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceDocumentAuditLog in the database
        List<InsuranceDocumentAuditLog> insuranceDocumentAuditLogList = insuranceDocumentAuditLogRepository.findAll();
        assertThat(insuranceDocumentAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInsuranceDocumentAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = insuranceDocumentAuditLogRepository.findAll().size();
        insuranceDocumentAuditLog.setId(count.incrementAndGet());

        // Create the InsuranceDocumentAuditLog
        InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO = insuranceDocumentAuditLogMapper.toDto(insuranceDocumentAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceDocumentAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(insuranceDocumentAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InsuranceDocumentAuditLog in the database
        List<InsuranceDocumentAuditLog> insuranceDocumentAuditLogList = insuranceDocumentAuditLogRepository.findAll();
        assertThat(insuranceDocumentAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInsuranceDocumentAuditLog() throws Exception {
        // Initialize the database
        insuranceDocumentAuditLogRepository.saveAndFlush(insuranceDocumentAuditLog);

        int databaseSizeBeforeDelete = insuranceDocumentAuditLogRepository.findAll().size();

        // Delete the insuranceDocumentAuditLog
        restInsuranceDocumentAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, insuranceDocumentAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InsuranceDocumentAuditLog> insuranceDocumentAuditLogList = insuranceDocumentAuditLogRepository.findAll();
        assertThat(insuranceDocumentAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
