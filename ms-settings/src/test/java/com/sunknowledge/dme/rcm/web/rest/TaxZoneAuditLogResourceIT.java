package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.TaxZoneAuditLog;
import com.sunknowledge.dme.rcm.repository.TaxZoneAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.TaxZoneAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaxZoneAuditLogMapper;
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
 * Integration tests for the {@link TaxZoneAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaxZoneAuditLogResourceIT {

    private static final Long DEFAULT_TX_ZNE_ID = 1L;
    private static final Long UPDATED_TX_ZNE_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/tax-zone-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TaxZoneAuditLogRepository taxZoneAuditLogRepository;

    @Autowired
    private TaxZoneAuditLogMapper taxZoneAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaxZoneAuditLogMockMvc;

    private TaxZoneAuditLog taxZoneAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaxZoneAuditLog createEntity(EntityManager em) {
        TaxZoneAuditLog taxZoneAuditLog = new TaxZoneAuditLog()
            .txZneId(DEFAULT_TX_ZNE_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return taxZoneAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaxZoneAuditLog createUpdatedEntity(EntityManager em) {
        TaxZoneAuditLog taxZoneAuditLog = new TaxZoneAuditLog()
            .txZneId(UPDATED_TX_ZNE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return taxZoneAuditLog;
    }

    @BeforeEach
    public void initTest() {
        taxZoneAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createTaxZoneAuditLog() throws Exception {
        int databaseSizeBeforeCreate = taxZoneAuditLogRepository.findAll().size();
        // Create the TaxZoneAuditLog
        TaxZoneAuditLogDTO taxZoneAuditLogDTO = taxZoneAuditLogMapper.toDto(taxZoneAuditLog);
        restTaxZoneAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taxZoneAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TaxZoneAuditLog in the database
        List<TaxZoneAuditLog> taxZoneAuditLogList = taxZoneAuditLogRepository.findAll();
        assertThat(taxZoneAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        TaxZoneAuditLog testTaxZoneAuditLog = taxZoneAuditLogList.get(taxZoneAuditLogList.size() - 1);
        assertThat(testTaxZoneAuditLog.getTxZneId()).isEqualTo(DEFAULT_TX_ZNE_ID);
        assertThat(testTaxZoneAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testTaxZoneAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testTaxZoneAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testTaxZoneAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testTaxZoneAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createTaxZoneAuditLogWithExistingId() throws Exception {
        // Create the TaxZoneAuditLog with an existing ID
        taxZoneAuditLog.setId(1L);
        TaxZoneAuditLogDTO taxZoneAuditLogDTO = taxZoneAuditLogMapper.toDto(taxZoneAuditLog);

        int databaseSizeBeforeCreate = taxZoneAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaxZoneAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taxZoneAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaxZoneAuditLog in the database
        List<TaxZoneAuditLog> taxZoneAuditLogList = taxZoneAuditLogRepository.findAll();
        assertThat(taxZoneAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTaxZoneAuditLogs() throws Exception {
        // Initialize the database
        taxZoneAuditLogRepository.saveAndFlush(taxZoneAuditLog);

        // Get all the taxZoneAuditLogList
        restTaxZoneAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taxZoneAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].txZneId").value(hasItem(DEFAULT_TX_ZNE_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getTaxZoneAuditLog() throws Exception {
        // Initialize the database
        taxZoneAuditLogRepository.saveAndFlush(taxZoneAuditLog);

        // Get the taxZoneAuditLog
        restTaxZoneAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, taxZoneAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taxZoneAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.txZneId").value(DEFAULT_TX_ZNE_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingTaxZoneAuditLog() throws Exception {
        // Get the taxZoneAuditLog
        restTaxZoneAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTaxZoneAuditLog() throws Exception {
        // Initialize the database
        taxZoneAuditLogRepository.saveAndFlush(taxZoneAuditLog);

        int databaseSizeBeforeUpdate = taxZoneAuditLogRepository.findAll().size();

        // Update the taxZoneAuditLog
        TaxZoneAuditLog updatedTaxZoneAuditLog = taxZoneAuditLogRepository.findById(taxZoneAuditLog.getId()).get();
        // Disconnect from session so that the updates on updatedTaxZoneAuditLog are not directly saved in db
        em.detach(updatedTaxZoneAuditLog);
        updatedTaxZoneAuditLog
            .txZneId(UPDATED_TX_ZNE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        TaxZoneAuditLogDTO taxZoneAuditLogDTO = taxZoneAuditLogMapper.toDto(updatedTaxZoneAuditLog);

        restTaxZoneAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taxZoneAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taxZoneAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the TaxZoneAuditLog in the database
        List<TaxZoneAuditLog> taxZoneAuditLogList = taxZoneAuditLogRepository.findAll();
        assertThat(taxZoneAuditLogList).hasSize(databaseSizeBeforeUpdate);
        TaxZoneAuditLog testTaxZoneAuditLog = taxZoneAuditLogList.get(taxZoneAuditLogList.size() - 1);
        assertThat(testTaxZoneAuditLog.getTxZneId()).isEqualTo(UPDATED_TX_ZNE_ID);
        assertThat(testTaxZoneAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testTaxZoneAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testTaxZoneAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testTaxZoneAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testTaxZoneAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingTaxZoneAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = taxZoneAuditLogRepository.findAll().size();
        taxZoneAuditLog.setId(count.incrementAndGet());

        // Create the TaxZoneAuditLog
        TaxZoneAuditLogDTO taxZoneAuditLogDTO = taxZoneAuditLogMapper.toDto(taxZoneAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaxZoneAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taxZoneAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taxZoneAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaxZoneAuditLog in the database
        List<TaxZoneAuditLog> taxZoneAuditLogList = taxZoneAuditLogRepository.findAll();
        assertThat(taxZoneAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaxZoneAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = taxZoneAuditLogRepository.findAll().size();
        taxZoneAuditLog.setId(count.incrementAndGet());

        // Create the TaxZoneAuditLog
        TaxZoneAuditLogDTO taxZoneAuditLogDTO = taxZoneAuditLogMapper.toDto(taxZoneAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaxZoneAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taxZoneAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaxZoneAuditLog in the database
        List<TaxZoneAuditLog> taxZoneAuditLogList = taxZoneAuditLogRepository.findAll();
        assertThat(taxZoneAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaxZoneAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = taxZoneAuditLogRepository.findAll().size();
        taxZoneAuditLog.setId(count.incrementAndGet());

        // Create the TaxZoneAuditLog
        TaxZoneAuditLogDTO taxZoneAuditLogDTO = taxZoneAuditLogMapper.toDto(taxZoneAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaxZoneAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taxZoneAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaxZoneAuditLog in the database
        List<TaxZoneAuditLog> taxZoneAuditLogList = taxZoneAuditLogRepository.findAll();
        assertThat(taxZoneAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaxZoneAuditLogWithPatch() throws Exception {
        // Initialize the database
        taxZoneAuditLogRepository.saveAndFlush(taxZoneAuditLog);

        int databaseSizeBeforeUpdate = taxZoneAuditLogRepository.findAll().size();

        // Update the taxZoneAuditLog using partial update
        TaxZoneAuditLog partialUpdatedTaxZoneAuditLog = new TaxZoneAuditLog();
        partialUpdatedTaxZoneAuditLog.setId(taxZoneAuditLog.getId());

        partialUpdatedTaxZoneAuditLog.txZneId(UPDATED_TX_ZNE_ID).oldRowData(UPDATED_OLD_ROW_DATA).dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restTaxZoneAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaxZoneAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaxZoneAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the TaxZoneAuditLog in the database
        List<TaxZoneAuditLog> taxZoneAuditLogList = taxZoneAuditLogRepository.findAll();
        assertThat(taxZoneAuditLogList).hasSize(databaseSizeBeforeUpdate);
        TaxZoneAuditLog testTaxZoneAuditLog = taxZoneAuditLogList.get(taxZoneAuditLogList.size() - 1);
        assertThat(testTaxZoneAuditLog.getTxZneId()).isEqualTo(UPDATED_TX_ZNE_ID);
        assertThat(testTaxZoneAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testTaxZoneAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testTaxZoneAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testTaxZoneAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testTaxZoneAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateTaxZoneAuditLogWithPatch() throws Exception {
        // Initialize the database
        taxZoneAuditLogRepository.saveAndFlush(taxZoneAuditLog);

        int databaseSizeBeforeUpdate = taxZoneAuditLogRepository.findAll().size();

        // Update the taxZoneAuditLog using partial update
        TaxZoneAuditLog partialUpdatedTaxZoneAuditLog = new TaxZoneAuditLog();
        partialUpdatedTaxZoneAuditLog.setId(taxZoneAuditLog.getId());

        partialUpdatedTaxZoneAuditLog
            .txZneId(UPDATED_TX_ZNE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restTaxZoneAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaxZoneAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaxZoneAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the TaxZoneAuditLog in the database
        List<TaxZoneAuditLog> taxZoneAuditLogList = taxZoneAuditLogRepository.findAll();
        assertThat(taxZoneAuditLogList).hasSize(databaseSizeBeforeUpdate);
        TaxZoneAuditLog testTaxZoneAuditLog = taxZoneAuditLogList.get(taxZoneAuditLogList.size() - 1);
        assertThat(testTaxZoneAuditLog.getTxZneId()).isEqualTo(UPDATED_TX_ZNE_ID);
        assertThat(testTaxZoneAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testTaxZoneAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testTaxZoneAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testTaxZoneAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testTaxZoneAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingTaxZoneAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = taxZoneAuditLogRepository.findAll().size();
        taxZoneAuditLog.setId(count.incrementAndGet());

        // Create the TaxZoneAuditLog
        TaxZoneAuditLogDTO taxZoneAuditLogDTO = taxZoneAuditLogMapper.toDto(taxZoneAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaxZoneAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taxZoneAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taxZoneAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaxZoneAuditLog in the database
        List<TaxZoneAuditLog> taxZoneAuditLogList = taxZoneAuditLogRepository.findAll();
        assertThat(taxZoneAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaxZoneAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = taxZoneAuditLogRepository.findAll().size();
        taxZoneAuditLog.setId(count.incrementAndGet());

        // Create the TaxZoneAuditLog
        TaxZoneAuditLogDTO taxZoneAuditLogDTO = taxZoneAuditLogMapper.toDto(taxZoneAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaxZoneAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taxZoneAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaxZoneAuditLog in the database
        List<TaxZoneAuditLog> taxZoneAuditLogList = taxZoneAuditLogRepository.findAll();
        assertThat(taxZoneAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaxZoneAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = taxZoneAuditLogRepository.findAll().size();
        taxZoneAuditLog.setId(count.incrementAndGet());

        // Create the TaxZoneAuditLog
        TaxZoneAuditLogDTO taxZoneAuditLogDTO = taxZoneAuditLogMapper.toDto(taxZoneAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaxZoneAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taxZoneAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaxZoneAuditLog in the database
        List<TaxZoneAuditLog> taxZoneAuditLogList = taxZoneAuditLogRepository.findAll();
        assertThat(taxZoneAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaxZoneAuditLog() throws Exception {
        // Initialize the database
        taxZoneAuditLogRepository.saveAndFlush(taxZoneAuditLog);

        int databaseSizeBeforeDelete = taxZoneAuditLogRepository.findAll().size();

        // Delete the taxZoneAuditLog
        restTaxZoneAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, taxZoneAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TaxZoneAuditLog> taxZoneAuditLogList = taxZoneAuditLogRepository.findAll();
        assertThat(taxZoneAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
