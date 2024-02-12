package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.StockAdjustmentAuditLog;
import com.sunknowledge.dme.rcm.repository.StockAdjustmentAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.StockAdjustmentAuditLogMapper;
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
 * Integration tests for the {@link StockAdjustmentAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StockAdjustmentAuditLogResourceIT {

    private static final Long DEFAULT_STCK_ADJSTENT_ID = 1L;
    private static final Long UPDATED_STCK_ADJSTENT_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/stock-adjustment-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StockAdjustmentAuditLogRepository stockAdjustmentAuditLogRepository;

    @Autowired
    private StockAdjustmentAuditLogMapper stockAdjustmentAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStockAdjustmentAuditLogMockMvc;

    private StockAdjustmentAuditLog stockAdjustmentAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StockAdjustmentAuditLog createEntity(EntityManager em) {
        StockAdjustmentAuditLog stockAdjustmentAuditLog = new StockAdjustmentAuditLog()
            .stckAdjstentId(DEFAULT_STCK_ADJSTENT_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return stockAdjustmentAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StockAdjustmentAuditLog createUpdatedEntity(EntityManager em) {
        StockAdjustmentAuditLog stockAdjustmentAuditLog = new StockAdjustmentAuditLog()
            .stckAdjstentId(UPDATED_STCK_ADJSTENT_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return stockAdjustmentAuditLog;
    }

    @BeforeEach
    public void initTest() {
        stockAdjustmentAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createStockAdjustmentAuditLog() throws Exception {
        int databaseSizeBeforeCreate = stockAdjustmentAuditLogRepository.findAll().size();
        // Create the StockAdjustmentAuditLog
        StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO = stockAdjustmentAuditLogMapper.toDto(stockAdjustmentAuditLog);
        restStockAdjustmentAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockAdjustmentAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the StockAdjustmentAuditLog in the database
        List<StockAdjustmentAuditLog> stockAdjustmentAuditLogList = stockAdjustmentAuditLogRepository.findAll();
        assertThat(stockAdjustmentAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        StockAdjustmentAuditLog testStockAdjustmentAuditLog = stockAdjustmentAuditLogList.get(stockAdjustmentAuditLogList.size() - 1);
        assertThat(testStockAdjustmentAuditLog.getStckAdjstentId()).isEqualTo(DEFAULT_STCK_ADJSTENT_ID);
        assertThat(testStockAdjustmentAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testStockAdjustmentAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testStockAdjustmentAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testStockAdjustmentAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testStockAdjustmentAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createStockAdjustmentAuditLogWithExistingId() throws Exception {
        // Create the StockAdjustmentAuditLog with an existing ID
        stockAdjustmentAuditLog.setId(1L);
        StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO = stockAdjustmentAuditLogMapper.toDto(stockAdjustmentAuditLog);

        int databaseSizeBeforeCreate = stockAdjustmentAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStockAdjustmentAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockAdjustmentAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockAdjustmentAuditLog in the database
        List<StockAdjustmentAuditLog> stockAdjustmentAuditLogList = stockAdjustmentAuditLogRepository.findAll();
        assertThat(stockAdjustmentAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllStockAdjustmentAuditLogs() throws Exception {
        // Initialize the database
        stockAdjustmentAuditLogRepository.saveAndFlush(stockAdjustmentAuditLog);

        // Get all the stockAdjustmentAuditLogList
        restStockAdjustmentAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stockAdjustmentAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].stckAdjstentId").value(hasItem(DEFAULT_STCK_ADJSTENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getStockAdjustmentAuditLog() throws Exception {
        // Initialize the database
        stockAdjustmentAuditLogRepository.saveAndFlush(stockAdjustmentAuditLog);

        // Get the stockAdjustmentAuditLog
        restStockAdjustmentAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, stockAdjustmentAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(stockAdjustmentAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.stckAdjstentId").value(DEFAULT_STCK_ADJSTENT_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingStockAdjustmentAuditLog() throws Exception {
        // Get the stockAdjustmentAuditLog
        restStockAdjustmentAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewStockAdjustmentAuditLog() throws Exception {
        // Initialize the database
        stockAdjustmentAuditLogRepository.saveAndFlush(stockAdjustmentAuditLog);

        int databaseSizeBeforeUpdate = stockAdjustmentAuditLogRepository.findAll().size();

        // Update the stockAdjustmentAuditLog
        StockAdjustmentAuditLog updatedStockAdjustmentAuditLog = stockAdjustmentAuditLogRepository
            .findById(stockAdjustmentAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedStockAdjustmentAuditLog are not directly saved in db
        em.detach(updatedStockAdjustmentAuditLog);
        updatedStockAdjustmentAuditLog
            .stckAdjstentId(UPDATED_STCK_ADJSTENT_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO = stockAdjustmentAuditLogMapper.toDto(updatedStockAdjustmentAuditLog);

        restStockAdjustmentAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, stockAdjustmentAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockAdjustmentAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the StockAdjustmentAuditLog in the database
        List<StockAdjustmentAuditLog> stockAdjustmentAuditLogList = stockAdjustmentAuditLogRepository.findAll();
        assertThat(stockAdjustmentAuditLogList).hasSize(databaseSizeBeforeUpdate);
        StockAdjustmentAuditLog testStockAdjustmentAuditLog = stockAdjustmentAuditLogList.get(stockAdjustmentAuditLogList.size() - 1);
        assertThat(testStockAdjustmentAuditLog.getStckAdjstentId()).isEqualTo(UPDATED_STCK_ADJSTENT_ID);
        assertThat(testStockAdjustmentAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testStockAdjustmentAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testStockAdjustmentAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testStockAdjustmentAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testStockAdjustmentAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingStockAdjustmentAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = stockAdjustmentAuditLogRepository.findAll().size();
        stockAdjustmentAuditLog.setId(count.incrementAndGet());

        // Create the StockAdjustmentAuditLog
        StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO = stockAdjustmentAuditLogMapper.toDto(stockAdjustmentAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStockAdjustmentAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, stockAdjustmentAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockAdjustmentAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockAdjustmentAuditLog in the database
        List<StockAdjustmentAuditLog> stockAdjustmentAuditLogList = stockAdjustmentAuditLogRepository.findAll();
        assertThat(stockAdjustmentAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchStockAdjustmentAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = stockAdjustmentAuditLogRepository.findAll().size();
        stockAdjustmentAuditLog.setId(count.incrementAndGet());

        // Create the StockAdjustmentAuditLog
        StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO = stockAdjustmentAuditLogMapper.toDto(stockAdjustmentAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStockAdjustmentAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockAdjustmentAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockAdjustmentAuditLog in the database
        List<StockAdjustmentAuditLog> stockAdjustmentAuditLogList = stockAdjustmentAuditLogRepository.findAll();
        assertThat(stockAdjustmentAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamStockAdjustmentAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = stockAdjustmentAuditLogRepository.findAll().size();
        stockAdjustmentAuditLog.setId(count.incrementAndGet());

        // Create the StockAdjustmentAuditLog
        StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO = stockAdjustmentAuditLogMapper.toDto(stockAdjustmentAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStockAdjustmentAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockAdjustmentAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the StockAdjustmentAuditLog in the database
        List<StockAdjustmentAuditLog> stockAdjustmentAuditLogList = stockAdjustmentAuditLogRepository.findAll();
        assertThat(stockAdjustmentAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateStockAdjustmentAuditLogWithPatch() throws Exception {
        // Initialize the database
        stockAdjustmentAuditLogRepository.saveAndFlush(stockAdjustmentAuditLog);

        int databaseSizeBeforeUpdate = stockAdjustmentAuditLogRepository.findAll().size();

        // Update the stockAdjustmentAuditLog using partial update
        StockAdjustmentAuditLog partialUpdatedStockAdjustmentAuditLog = new StockAdjustmentAuditLog();
        partialUpdatedStockAdjustmentAuditLog.setId(stockAdjustmentAuditLog.getId());

        partialUpdatedStockAdjustmentAuditLog
            .stckAdjstentId(UPDATED_STCK_ADJSTENT_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP);

        restStockAdjustmentAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStockAdjustmentAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStockAdjustmentAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the StockAdjustmentAuditLog in the database
        List<StockAdjustmentAuditLog> stockAdjustmentAuditLogList = stockAdjustmentAuditLogRepository.findAll();
        assertThat(stockAdjustmentAuditLogList).hasSize(databaseSizeBeforeUpdate);
        StockAdjustmentAuditLog testStockAdjustmentAuditLog = stockAdjustmentAuditLogList.get(stockAdjustmentAuditLogList.size() - 1);
        assertThat(testStockAdjustmentAuditLog.getStckAdjstentId()).isEqualTo(UPDATED_STCK_ADJSTENT_ID);
        assertThat(testStockAdjustmentAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testStockAdjustmentAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testStockAdjustmentAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testStockAdjustmentAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testStockAdjustmentAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateStockAdjustmentAuditLogWithPatch() throws Exception {
        // Initialize the database
        stockAdjustmentAuditLogRepository.saveAndFlush(stockAdjustmentAuditLog);

        int databaseSizeBeforeUpdate = stockAdjustmentAuditLogRepository.findAll().size();

        // Update the stockAdjustmentAuditLog using partial update
        StockAdjustmentAuditLog partialUpdatedStockAdjustmentAuditLog = new StockAdjustmentAuditLog();
        partialUpdatedStockAdjustmentAuditLog.setId(stockAdjustmentAuditLog.getId());

        partialUpdatedStockAdjustmentAuditLog
            .stckAdjstentId(UPDATED_STCK_ADJSTENT_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restStockAdjustmentAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStockAdjustmentAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStockAdjustmentAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the StockAdjustmentAuditLog in the database
        List<StockAdjustmentAuditLog> stockAdjustmentAuditLogList = stockAdjustmentAuditLogRepository.findAll();
        assertThat(stockAdjustmentAuditLogList).hasSize(databaseSizeBeforeUpdate);
        StockAdjustmentAuditLog testStockAdjustmentAuditLog = stockAdjustmentAuditLogList.get(stockAdjustmentAuditLogList.size() - 1);
        assertThat(testStockAdjustmentAuditLog.getStckAdjstentId()).isEqualTo(UPDATED_STCK_ADJSTENT_ID);
        assertThat(testStockAdjustmentAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testStockAdjustmentAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testStockAdjustmentAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testStockAdjustmentAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testStockAdjustmentAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingStockAdjustmentAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = stockAdjustmentAuditLogRepository.findAll().size();
        stockAdjustmentAuditLog.setId(count.incrementAndGet());

        // Create the StockAdjustmentAuditLog
        StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO = stockAdjustmentAuditLogMapper.toDto(stockAdjustmentAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStockAdjustmentAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, stockAdjustmentAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stockAdjustmentAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockAdjustmentAuditLog in the database
        List<StockAdjustmentAuditLog> stockAdjustmentAuditLogList = stockAdjustmentAuditLogRepository.findAll();
        assertThat(stockAdjustmentAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchStockAdjustmentAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = stockAdjustmentAuditLogRepository.findAll().size();
        stockAdjustmentAuditLog.setId(count.incrementAndGet());

        // Create the StockAdjustmentAuditLog
        StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO = stockAdjustmentAuditLogMapper.toDto(stockAdjustmentAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStockAdjustmentAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stockAdjustmentAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockAdjustmentAuditLog in the database
        List<StockAdjustmentAuditLog> stockAdjustmentAuditLogList = stockAdjustmentAuditLogRepository.findAll();
        assertThat(stockAdjustmentAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamStockAdjustmentAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = stockAdjustmentAuditLogRepository.findAll().size();
        stockAdjustmentAuditLog.setId(count.incrementAndGet());

        // Create the StockAdjustmentAuditLog
        StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO = stockAdjustmentAuditLogMapper.toDto(stockAdjustmentAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStockAdjustmentAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stockAdjustmentAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the StockAdjustmentAuditLog in the database
        List<StockAdjustmentAuditLog> stockAdjustmentAuditLogList = stockAdjustmentAuditLogRepository.findAll();
        assertThat(stockAdjustmentAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteStockAdjustmentAuditLog() throws Exception {
        // Initialize the database
        stockAdjustmentAuditLogRepository.saveAndFlush(stockAdjustmentAuditLog);

        int databaseSizeBeforeDelete = stockAdjustmentAuditLogRepository.findAll().size();

        // Delete the stockAdjustmentAuditLog
        restStockAdjustmentAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, stockAdjustmentAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StockAdjustmentAuditLog> stockAdjustmentAuditLogList = stockAdjustmentAuditLogRepository.findAll();
        assertThat(stockAdjustmentAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
