package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.StockTransferAuditLog;
import com.sunknowledge.dme.rcm.repository.StockTransferAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.StockTransferAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.StockTransferAuditLogMapper;
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
 * Integration tests for the {@link StockTransferAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StockTransferAuditLogResourceIT {

    private static final Long DEFAULT_STCK_TRASFER_ID = 1L;
    private static final Long UPDATED_STCK_TRASFER_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/stock-transfer-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StockTransferAuditLogRepository stockTransferAuditLogRepository;

    @Autowired
    private StockTransferAuditLogMapper stockTransferAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStockTransferAuditLogMockMvc;

    private StockTransferAuditLog stockTransferAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StockTransferAuditLog createEntity(EntityManager em) {
        StockTransferAuditLog stockTransferAuditLog = new StockTransferAuditLog()
            .stckTrasferId(DEFAULT_STCK_TRASFER_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return stockTransferAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StockTransferAuditLog createUpdatedEntity(EntityManager em) {
        StockTransferAuditLog stockTransferAuditLog = new StockTransferAuditLog()
            .stckTrasferId(UPDATED_STCK_TRASFER_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return stockTransferAuditLog;
    }

    @BeforeEach
    public void initTest() {
        stockTransferAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createStockTransferAuditLog() throws Exception {
        int databaseSizeBeforeCreate = stockTransferAuditLogRepository.findAll().size();
        // Create the StockTransferAuditLog
        StockTransferAuditLogDTO stockTransferAuditLogDTO = stockTransferAuditLogMapper.toDto(stockTransferAuditLog);
        restStockTransferAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockTransferAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the StockTransferAuditLog in the database
        List<StockTransferAuditLog> stockTransferAuditLogList = stockTransferAuditLogRepository.findAll();
        assertThat(stockTransferAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        StockTransferAuditLog testStockTransferAuditLog = stockTransferAuditLogList.get(stockTransferAuditLogList.size() - 1);
        assertThat(testStockTransferAuditLog.getStckTrasferId()).isEqualTo(DEFAULT_STCK_TRASFER_ID);
        assertThat(testStockTransferAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testStockTransferAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testStockTransferAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testStockTransferAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testStockTransferAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createStockTransferAuditLogWithExistingId() throws Exception {
        // Create the StockTransferAuditLog with an existing ID
        stockTransferAuditLog.setId(1L);
        StockTransferAuditLogDTO stockTransferAuditLogDTO = stockTransferAuditLogMapper.toDto(stockTransferAuditLog);

        int databaseSizeBeforeCreate = stockTransferAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStockTransferAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockTransferAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockTransferAuditLog in the database
        List<StockTransferAuditLog> stockTransferAuditLogList = stockTransferAuditLogRepository.findAll();
        assertThat(stockTransferAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllStockTransferAuditLogs() throws Exception {
        // Initialize the database
        stockTransferAuditLogRepository.saveAndFlush(stockTransferAuditLog);

        // Get all the stockTransferAuditLogList
        restStockTransferAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stockTransferAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].stckTrasferId").value(hasItem(DEFAULT_STCK_TRASFER_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getStockTransferAuditLog() throws Exception {
        // Initialize the database
        stockTransferAuditLogRepository.saveAndFlush(stockTransferAuditLog);

        // Get the stockTransferAuditLog
        restStockTransferAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, stockTransferAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(stockTransferAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.stckTrasferId").value(DEFAULT_STCK_TRASFER_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingStockTransferAuditLog() throws Exception {
        // Get the stockTransferAuditLog
        restStockTransferAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewStockTransferAuditLog() throws Exception {
        // Initialize the database
        stockTransferAuditLogRepository.saveAndFlush(stockTransferAuditLog);

        int databaseSizeBeforeUpdate = stockTransferAuditLogRepository.findAll().size();

        // Update the stockTransferAuditLog
        StockTransferAuditLog updatedStockTransferAuditLog = stockTransferAuditLogRepository.findById(stockTransferAuditLog.getId()).get();
        // Disconnect from session so that the updates on updatedStockTransferAuditLog are not directly saved in db
        em.detach(updatedStockTransferAuditLog);
        updatedStockTransferAuditLog
            .stckTrasferId(UPDATED_STCK_TRASFER_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        StockTransferAuditLogDTO stockTransferAuditLogDTO = stockTransferAuditLogMapper.toDto(updatedStockTransferAuditLog);

        restStockTransferAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, stockTransferAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockTransferAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the StockTransferAuditLog in the database
        List<StockTransferAuditLog> stockTransferAuditLogList = stockTransferAuditLogRepository.findAll();
        assertThat(stockTransferAuditLogList).hasSize(databaseSizeBeforeUpdate);
        StockTransferAuditLog testStockTransferAuditLog = stockTransferAuditLogList.get(stockTransferAuditLogList.size() - 1);
        assertThat(testStockTransferAuditLog.getStckTrasferId()).isEqualTo(UPDATED_STCK_TRASFER_ID);
        assertThat(testStockTransferAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testStockTransferAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testStockTransferAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testStockTransferAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testStockTransferAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingStockTransferAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = stockTransferAuditLogRepository.findAll().size();
        stockTransferAuditLog.setId(count.incrementAndGet());

        // Create the StockTransferAuditLog
        StockTransferAuditLogDTO stockTransferAuditLogDTO = stockTransferAuditLogMapper.toDto(stockTransferAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStockTransferAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, stockTransferAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockTransferAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockTransferAuditLog in the database
        List<StockTransferAuditLog> stockTransferAuditLogList = stockTransferAuditLogRepository.findAll();
        assertThat(stockTransferAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchStockTransferAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = stockTransferAuditLogRepository.findAll().size();
        stockTransferAuditLog.setId(count.incrementAndGet());

        // Create the StockTransferAuditLog
        StockTransferAuditLogDTO stockTransferAuditLogDTO = stockTransferAuditLogMapper.toDto(stockTransferAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStockTransferAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockTransferAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockTransferAuditLog in the database
        List<StockTransferAuditLog> stockTransferAuditLogList = stockTransferAuditLogRepository.findAll();
        assertThat(stockTransferAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamStockTransferAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = stockTransferAuditLogRepository.findAll().size();
        stockTransferAuditLog.setId(count.incrementAndGet());

        // Create the StockTransferAuditLog
        StockTransferAuditLogDTO stockTransferAuditLogDTO = stockTransferAuditLogMapper.toDto(stockTransferAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStockTransferAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stockTransferAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the StockTransferAuditLog in the database
        List<StockTransferAuditLog> stockTransferAuditLogList = stockTransferAuditLogRepository.findAll();
        assertThat(stockTransferAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateStockTransferAuditLogWithPatch() throws Exception {
        // Initialize the database
        stockTransferAuditLogRepository.saveAndFlush(stockTransferAuditLog);

        int databaseSizeBeforeUpdate = stockTransferAuditLogRepository.findAll().size();

        // Update the stockTransferAuditLog using partial update
        StockTransferAuditLog partialUpdatedStockTransferAuditLog = new StockTransferAuditLog();
        partialUpdatedStockTransferAuditLog.setId(stockTransferAuditLog.getId());

        partialUpdatedStockTransferAuditLog
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP);

        restStockTransferAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStockTransferAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStockTransferAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the StockTransferAuditLog in the database
        List<StockTransferAuditLog> stockTransferAuditLogList = stockTransferAuditLogRepository.findAll();
        assertThat(stockTransferAuditLogList).hasSize(databaseSizeBeforeUpdate);
        StockTransferAuditLog testStockTransferAuditLog = stockTransferAuditLogList.get(stockTransferAuditLogList.size() - 1);
        assertThat(testStockTransferAuditLog.getStckTrasferId()).isEqualTo(DEFAULT_STCK_TRASFER_ID);
        assertThat(testStockTransferAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testStockTransferAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testStockTransferAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testStockTransferAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testStockTransferAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateStockTransferAuditLogWithPatch() throws Exception {
        // Initialize the database
        stockTransferAuditLogRepository.saveAndFlush(stockTransferAuditLog);

        int databaseSizeBeforeUpdate = stockTransferAuditLogRepository.findAll().size();

        // Update the stockTransferAuditLog using partial update
        StockTransferAuditLog partialUpdatedStockTransferAuditLog = new StockTransferAuditLog();
        partialUpdatedStockTransferAuditLog.setId(stockTransferAuditLog.getId());

        partialUpdatedStockTransferAuditLog
            .stckTrasferId(UPDATED_STCK_TRASFER_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restStockTransferAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStockTransferAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStockTransferAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the StockTransferAuditLog in the database
        List<StockTransferAuditLog> stockTransferAuditLogList = stockTransferAuditLogRepository.findAll();
        assertThat(stockTransferAuditLogList).hasSize(databaseSizeBeforeUpdate);
        StockTransferAuditLog testStockTransferAuditLog = stockTransferAuditLogList.get(stockTransferAuditLogList.size() - 1);
        assertThat(testStockTransferAuditLog.getStckTrasferId()).isEqualTo(UPDATED_STCK_TRASFER_ID);
        assertThat(testStockTransferAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testStockTransferAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testStockTransferAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testStockTransferAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testStockTransferAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingStockTransferAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = stockTransferAuditLogRepository.findAll().size();
        stockTransferAuditLog.setId(count.incrementAndGet());

        // Create the StockTransferAuditLog
        StockTransferAuditLogDTO stockTransferAuditLogDTO = stockTransferAuditLogMapper.toDto(stockTransferAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStockTransferAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, stockTransferAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stockTransferAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockTransferAuditLog in the database
        List<StockTransferAuditLog> stockTransferAuditLogList = stockTransferAuditLogRepository.findAll();
        assertThat(stockTransferAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchStockTransferAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = stockTransferAuditLogRepository.findAll().size();
        stockTransferAuditLog.setId(count.incrementAndGet());

        // Create the StockTransferAuditLog
        StockTransferAuditLogDTO stockTransferAuditLogDTO = stockTransferAuditLogMapper.toDto(stockTransferAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStockTransferAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stockTransferAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StockTransferAuditLog in the database
        List<StockTransferAuditLog> stockTransferAuditLogList = stockTransferAuditLogRepository.findAll();
        assertThat(stockTransferAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamStockTransferAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = stockTransferAuditLogRepository.findAll().size();
        stockTransferAuditLog.setId(count.incrementAndGet());

        // Create the StockTransferAuditLog
        StockTransferAuditLogDTO stockTransferAuditLogDTO = stockTransferAuditLogMapper.toDto(stockTransferAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStockTransferAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stockTransferAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the StockTransferAuditLog in the database
        List<StockTransferAuditLog> stockTransferAuditLogList = stockTransferAuditLogRepository.findAll();
        assertThat(stockTransferAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteStockTransferAuditLog() throws Exception {
        // Initialize the database
        stockTransferAuditLogRepository.saveAndFlush(stockTransferAuditLog);

        int databaseSizeBeforeDelete = stockTransferAuditLogRepository.findAll().size();

        // Delete the stockTransferAuditLog
        restStockTransferAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, stockTransferAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StockTransferAuditLog> stockTransferAuditLogList = stockTransferAuditLogRepository.findAll();
        assertThat(stockTransferAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
