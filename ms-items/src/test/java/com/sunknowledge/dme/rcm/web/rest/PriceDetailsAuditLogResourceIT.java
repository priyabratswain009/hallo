package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PriceDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.PriceDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PriceDetailsAuditLogMapper;
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
 * Integration tests for the {@link PriceDetailsAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PriceDetailsAuditLogResourceIT {

    private static final Long DEFAULT_PRCE_DETL_ID = 1L;
    private static final Long UPDATED_PRCE_DETL_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/price-details-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PriceDetailsAuditLogRepository priceDetailsAuditLogRepository;

    @Autowired
    private PriceDetailsAuditLogMapper priceDetailsAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPriceDetailsAuditLogMockMvc;

    private PriceDetailsAuditLog priceDetailsAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PriceDetailsAuditLog createEntity(EntityManager em) {
        PriceDetailsAuditLog priceDetailsAuditLog = new PriceDetailsAuditLog()
            .prceDetlId(DEFAULT_PRCE_DETL_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return priceDetailsAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PriceDetailsAuditLog createUpdatedEntity(EntityManager em) {
        PriceDetailsAuditLog priceDetailsAuditLog = new PriceDetailsAuditLog()
            .prceDetlId(UPDATED_PRCE_DETL_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return priceDetailsAuditLog;
    }

    @BeforeEach
    public void initTest() {
        priceDetailsAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createPriceDetailsAuditLog() throws Exception {
        int databaseSizeBeforeCreate = priceDetailsAuditLogRepository.findAll().size();
        // Create the PriceDetailsAuditLog
        PriceDetailsAuditLogDTO priceDetailsAuditLogDTO = priceDetailsAuditLogMapper.toDto(priceDetailsAuditLog);
        restPriceDetailsAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceDetailsAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PriceDetailsAuditLog in the database
        List<PriceDetailsAuditLog> priceDetailsAuditLogList = priceDetailsAuditLogRepository.findAll();
        assertThat(priceDetailsAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        PriceDetailsAuditLog testPriceDetailsAuditLog = priceDetailsAuditLogList.get(priceDetailsAuditLogList.size() - 1);
        assertThat(testPriceDetailsAuditLog.getPrceDetlId()).isEqualTo(DEFAULT_PRCE_DETL_ID);
        assertThat(testPriceDetailsAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPriceDetailsAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testPriceDetailsAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPriceDetailsAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testPriceDetailsAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createPriceDetailsAuditLogWithExistingId() throws Exception {
        // Create the PriceDetailsAuditLog with an existing ID
        priceDetailsAuditLog.setId(1L);
        PriceDetailsAuditLogDTO priceDetailsAuditLogDTO = priceDetailsAuditLogMapper.toDto(priceDetailsAuditLog);

        int databaseSizeBeforeCreate = priceDetailsAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPriceDetailsAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceDetailsAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceDetailsAuditLog in the database
        List<PriceDetailsAuditLog> priceDetailsAuditLogList = priceDetailsAuditLogRepository.findAll();
        assertThat(priceDetailsAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPriceDetailsAuditLogs() throws Exception {
        // Initialize the database
        priceDetailsAuditLogRepository.saveAndFlush(priceDetailsAuditLog);

        // Get all the priceDetailsAuditLogList
        restPriceDetailsAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(priceDetailsAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].prceDetlId").value(hasItem(DEFAULT_PRCE_DETL_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getPriceDetailsAuditLog() throws Exception {
        // Initialize the database
        priceDetailsAuditLogRepository.saveAndFlush(priceDetailsAuditLog);

        // Get the priceDetailsAuditLog
        restPriceDetailsAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, priceDetailsAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(priceDetailsAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.prceDetlId").value(DEFAULT_PRCE_DETL_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingPriceDetailsAuditLog() throws Exception {
        // Get the priceDetailsAuditLog
        restPriceDetailsAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPriceDetailsAuditLog() throws Exception {
        // Initialize the database
        priceDetailsAuditLogRepository.saveAndFlush(priceDetailsAuditLog);

        int databaseSizeBeforeUpdate = priceDetailsAuditLogRepository.findAll().size();

        // Update the priceDetailsAuditLog
        PriceDetailsAuditLog updatedPriceDetailsAuditLog = priceDetailsAuditLogRepository.findById(priceDetailsAuditLog.getId()).get();
        // Disconnect from session so that the updates on updatedPriceDetailsAuditLog are not directly saved in db
        em.detach(updatedPriceDetailsAuditLog);
        updatedPriceDetailsAuditLog
            .prceDetlId(UPDATED_PRCE_DETL_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        PriceDetailsAuditLogDTO priceDetailsAuditLogDTO = priceDetailsAuditLogMapper.toDto(updatedPriceDetailsAuditLog);

        restPriceDetailsAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, priceDetailsAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceDetailsAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the PriceDetailsAuditLog in the database
        List<PriceDetailsAuditLog> priceDetailsAuditLogList = priceDetailsAuditLogRepository.findAll();
        assertThat(priceDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PriceDetailsAuditLog testPriceDetailsAuditLog = priceDetailsAuditLogList.get(priceDetailsAuditLogList.size() - 1);
        assertThat(testPriceDetailsAuditLog.getPrceDetlId()).isEqualTo(UPDATED_PRCE_DETL_ID);
        assertThat(testPriceDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPriceDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPriceDetailsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPriceDetailsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPriceDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingPriceDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = priceDetailsAuditLogRepository.findAll().size();
        priceDetailsAuditLog.setId(count.incrementAndGet());

        // Create the PriceDetailsAuditLog
        PriceDetailsAuditLogDTO priceDetailsAuditLogDTO = priceDetailsAuditLogMapper.toDto(priceDetailsAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPriceDetailsAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, priceDetailsAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceDetailsAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceDetailsAuditLog in the database
        List<PriceDetailsAuditLog> priceDetailsAuditLogList = priceDetailsAuditLogRepository.findAll();
        assertThat(priceDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPriceDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = priceDetailsAuditLogRepository.findAll().size();
        priceDetailsAuditLog.setId(count.incrementAndGet());

        // Create the PriceDetailsAuditLog
        PriceDetailsAuditLogDTO priceDetailsAuditLogDTO = priceDetailsAuditLogMapper.toDto(priceDetailsAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceDetailsAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceDetailsAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceDetailsAuditLog in the database
        List<PriceDetailsAuditLog> priceDetailsAuditLogList = priceDetailsAuditLogRepository.findAll();
        assertThat(priceDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPriceDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = priceDetailsAuditLogRepository.findAll().size();
        priceDetailsAuditLog.setId(count.incrementAndGet());

        // Create the PriceDetailsAuditLog
        PriceDetailsAuditLogDTO priceDetailsAuditLogDTO = priceDetailsAuditLogMapper.toDto(priceDetailsAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceDetailsAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceDetailsAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PriceDetailsAuditLog in the database
        List<PriceDetailsAuditLog> priceDetailsAuditLogList = priceDetailsAuditLogRepository.findAll();
        assertThat(priceDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePriceDetailsAuditLogWithPatch() throws Exception {
        // Initialize the database
        priceDetailsAuditLogRepository.saveAndFlush(priceDetailsAuditLog);

        int databaseSizeBeforeUpdate = priceDetailsAuditLogRepository.findAll().size();

        // Update the priceDetailsAuditLog using partial update
        PriceDetailsAuditLog partialUpdatedPriceDetailsAuditLog = new PriceDetailsAuditLog();
        partialUpdatedPriceDetailsAuditLog.setId(priceDetailsAuditLog.getId());

        partialUpdatedPriceDetailsAuditLog.newRowData(UPDATED_NEW_ROW_DATA).dmlType(UPDATED_DML_TYPE).dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restPriceDetailsAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPriceDetailsAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPriceDetailsAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the PriceDetailsAuditLog in the database
        List<PriceDetailsAuditLog> priceDetailsAuditLogList = priceDetailsAuditLogRepository.findAll();
        assertThat(priceDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PriceDetailsAuditLog testPriceDetailsAuditLog = priceDetailsAuditLogList.get(priceDetailsAuditLogList.size() - 1);
        assertThat(testPriceDetailsAuditLog.getPrceDetlId()).isEqualTo(DEFAULT_PRCE_DETL_ID);
        assertThat(testPriceDetailsAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPriceDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPriceDetailsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPriceDetailsAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testPriceDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdatePriceDetailsAuditLogWithPatch() throws Exception {
        // Initialize the database
        priceDetailsAuditLogRepository.saveAndFlush(priceDetailsAuditLog);

        int databaseSizeBeforeUpdate = priceDetailsAuditLogRepository.findAll().size();

        // Update the priceDetailsAuditLog using partial update
        PriceDetailsAuditLog partialUpdatedPriceDetailsAuditLog = new PriceDetailsAuditLog();
        partialUpdatedPriceDetailsAuditLog.setId(priceDetailsAuditLog.getId());

        partialUpdatedPriceDetailsAuditLog
            .prceDetlId(UPDATED_PRCE_DETL_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restPriceDetailsAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPriceDetailsAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPriceDetailsAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the PriceDetailsAuditLog in the database
        List<PriceDetailsAuditLog> priceDetailsAuditLogList = priceDetailsAuditLogRepository.findAll();
        assertThat(priceDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PriceDetailsAuditLog testPriceDetailsAuditLog = priceDetailsAuditLogList.get(priceDetailsAuditLogList.size() - 1);
        assertThat(testPriceDetailsAuditLog.getPrceDetlId()).isEqualTo(UPDATED_PRCE_DETL_ID);
        assertThat(testPriceDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPriceDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPriceDetailsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPriceDetailsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPriceDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingPriceDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = priceDetailsAuditLogRepository.findAll().size();
        priceDetailsAuditLog.setId(count.incrementAndGet());

        // Create the PriceDetailsAuditLog
        PriceDetailsAuditLogDTO priceDetailsAuditLogDTO = priceDetailsAuditLogMapper.toDto(priceDetailsAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPriceDetailsAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, priceDetailsAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(priceDetailsAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceDetailsAuditLog in the database
        List<PriceDetailsAuditLog> priceDetailsAuditLogList = priceDetailsAuditLogRepository.findAll();
        assertThat(priceDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPriceDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = priceDetailsAuditLogRepository.findAll().size();
        priceDetailsAuditLog.setId(count.incrementAndGet());

        // Create the PriceDetailsAuditLog
        PriceDetailsAuditLogDTO priceDetailsAuditLogDTO = priceDetailsAuditLogMapper.toDto(priceDetailsAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceDetailsAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(priceDetailsAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceDetailsAuditLog in the database
        List<PriceDetailsAuditLog> priceDetailsAuditLogList = priceDetailsAuditLogRepository.findAll();
        assertThat(priceDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPriceDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = priceDetailsAuditLogRepository.findAll().size();
        priceDetailsAuditLog.setId(count.incrementAndGet());

        // Create the PriceDetailsAuditLog
        PriceDetailsAuditLogDTO priceDetailsAuditLogDTO = priceDetailsAuditLogMapper.toDto(priceDetailsAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceDetailsAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(priceDetailsAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PriceDetailsAuditLog in the database
        List<PriceDetailsAuditLog> priceDetailsAuditLogList = priceDetailsAuditLogRepository.findAll();
        assertThat(priceDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePriceDetailsAuditLog() throws Exception {
        // Initialize the database
        priceDetailsAuditLogRepository.saveAndFlush(priceDetailsAuditLog);

        int databaseSizeBeforeDelete = priceDetailsAuditLogRepository.findAll().size();

        // Delete the priceDetailsAuditLog
        restPriceDetailsAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, priceDetailsAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PriceDetailsAuditLog> priceDetailsAuditLogList = priceDetailsAuditLogRepository.findAll();
        assertThat(priceDetailsAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
