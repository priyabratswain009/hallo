package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SalesOrderClassificationAuditLog;
import com.sunknowledge.dme.rcm.repository.SalesOrderClassificationAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClassificationAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderClassificationAuditLogMapper;
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
 * Integration tests for the {@link SalesOrderClassificationAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SalesOrderClassificationAuditLogResourceIT {

    private static final Long DEFAULT_ORDER_CLAFICATION_ID = 1L;
    private static final Long UPDATED_ORDER_CLAFICATION_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/sales-order-classification-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SalesOrderClassificationAuditLogRepository salesOrderClassificationAuditLogRepository;

    @Autowired
    private SalesOrderClassificationAuditLogMapper salesOrderClassificationAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSalesOrderClassificationAuditLogMockMvc;

    private SalesOrderClassificationAuditLog salesOrderClassificationAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderClassificationAuditLog createEntity(EntityManager em) {
        SalesOrderClassificationAuditLog salesOrderClassificationAuditLog = new SalesOrderClassificationAuditLog()
            .orderClaficationId(DEFAULT_ORDER_CLAFICATION_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return salesOrderClassificationAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderClassificationAuditLog createUpdatedEntity(EntityManager em) {
        SalesOrderClassificationAuditLog salesOrderClassificationAuditLog = new SalesOrderClassificationAuditLog()
            .orderClaficationId(UPDATED_ORDER_CLAFICATION_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return salesOrderClassificationAuditLog;
    }

    @BeforeEach
    public void initTest() {
        salesOrderClassificationAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createSalesOrderClassificationAuditLog() throws Exception {
        int databaseSizeBeforeCreate = salesOrderClassificationAuditLogRepository.findAll().size();
        // Create the SalesOrderClassificationAuditLog
        SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO = salesOrderClassificationAuditLogMapper.toDto(
            salesOrderClassificationAuditLog
        );
        restSalesOrderClassificationAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesOrderClassificationAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SalesOrderClassificationAuditLog in the database
        List<SalesOrderClassificationAuditLog> salesOrderClassificationAuditLogList = salesOrderClassificationAuditLogRepository.findAll();
        assertThat(salesOrderClassificationAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        SalesOrderClassificationAuditLog testSalesOrderClassificationAuditLog = salesOrderClassificationAuditLogList.get(
            salesOrderClassificationAuditLogList.size() - 1
        );
        assertThat(testSalesOrderClassificationAuditLog.getOrderClaficationId()).isEqualTo(DEFAULT_ORDER_CLAFICATION_ID);
        assertThat(testSalesOrderClassificationAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testSalesOrderClassificationAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testSalesOrderClassificationAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testSalesOrderClassificationAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testSalesOrderClassificationAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createSalesOrderClassificationAuditLogWithExistingId() throws Exception {
        // Create the SalesOrderClassificationAuditLog with an existing ID
        salesOrderClassificationAuditLog.setId(1L);
        SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO = salesOrderClassificationAuditLogMapper.toDto(
            salesOrderClassificationAuditLog
        );

        int databaseSizeBeforeCreate = salesOrderClassificationAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalesOrderClassificationAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesOrderClassificationAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesOrderClassificationAuditLog in the database
        List<SalesOrderClassificationAuditLog> salesOrderClassificationAuditLogList = salesOrderClassificationAuditLogRepository.findAll();
        assertThat(salesOrderClassificationAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSalesOrderClassificationAuditLogs() throws Exception {
        // Initialize the database
        salesOrderClassificationAuditLogRepository.saveAndFlush(salesOrderClassificationAuditLog);

        // Get all the salesOrderClassificationAuditLogList
        restSalesOrderClassificationAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salesOrderClassificationAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderClaficationId").value(hasItem(DEFAULT_ORDER_CLAFICATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getSalesOrderClassificationAuditLog() throws Exception {
        // Initialize the database
        salesOrderClassificationAuditLogRepository.saveAndFlush(salesOrderClassificationAuditLog);

        // Get the salesOrderClassificationAuditLog
        restSalesOrderClassificationAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, salesOrderClassificationAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(salesOrderClassificationAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.orderClaficationId").value(DEFAULT_ORDER_CLAFICATION_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingSalesOrderClassificationAuditLog() throws Exception {
        // Get the salesOrderClassificationAuditLog
        restSalesOrderClassificationAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSalesOrderClassificationAuditLog() throws Exception {
        // Initialize the database
        salesOrderClassificationAuditLogRepository.saveAndFlush(salesOrderClassificationAuditLog);

        int databaseSizeBeforeUpdate = salesOrderClassificationAuditLogRepository.findAll().size();

        // Update the salesOrderClassificationAuditLog
        SalesOrderClassificationAuditLog updatedSalesOrderClassificationAuditLog = salesOrderClassificationAuditLogRepository
            .findById(salesOrderClassificationAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedSalesOrderClassificationAuditLog are not directly saved in db
        em.detach(updatedSalesOrderClassificationAuditLog);
        updatedSalesOrderClassificationAuditLog
            .orderClaficationId(UPDATED_ORDER_CLAFICATION_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO = salesOrderClassificationAuditLogMapper.toDto(
            updatedSalesOrderClassificationAuditLog
        );

        restSalesOrderClassificationAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, salesOrderClassificationAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesOrderClassificationAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the SalesOrderClassificationAuditLog in the database
        List<SalesOrderClassificationAuditLog> salesOrderClassificationAuditLogList = salesOrderClassificationAuditLogRepository.findAll();
        assertThat(salesOrderClassificationAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderClassificationAuditLog testSalesOrderClassificationAuditLog = salesOrderClassificationAuditLogList.get(
            salesOrderClassificationAuditLogList.size() - 1
        );
        assertThat(testSalesOrderClassificationAuditLog.getOrderClaficationId()).isEqualTo(UPDATED_ORDER_CLAFICATION_ID);
        assertThat(testSalesOrderClassificationAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testSalesOrderClassificationAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderClassificationAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testSalesOrderClassificationAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testSalesOrderClassificationAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingSalesOrderClassificationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClassificationAuditLogRepository.findAll().size();
        salesOrderClassificationAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderClassificationAuditLog
        SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO = salesOrderClassificationAuditLogMapper.toDto(
            salesOrderClassificationAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesOrderClassificationAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, salesOrderClassificationAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesOrderClassificationAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesOrderClassificationAuditLog in the database
        List<SalesOrderClassificationAuditLog> salesOrderClassificationAuditLogList = salesOrderClassificationAuditLogRepository.findAll();
        assertThat(salesOrderClassificationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSalesOrderClassificationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClassificationAuditLogRepository.findAll().size();
        salesOrderClassificationAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderClassificationAuditLog
        SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO = salesOrderClassificationAuditLogMapper.toDto(
            salesOrderClassificationAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesOrderClassificationAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesOrderClassificationAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesOrderClassificationAuditLog in the database
        List<SalesOrderClassificationAuditLog> salesOrderClassificationAuditLogList = salesOrderClassificationAuditLogRepository.findAll();
        assertThat(salesOrderClassificationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSalesOrderClassificationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClassificationAuditLogRepository.findAll().size();
        salesOrderClassificationAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderClassificationAuditLog
        SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO = salesOrderClassificationAuditLogMapper.toDto(
            salesOrderClassificationAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesOrderClassificationAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesOrderClassificationAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SalesOrderClassificationAuditLog in the database
        List<SalesOrderClassificationAuditLog> salesOrderClassificationAuditLogList = salesOrderClassificationAuditLogRepository.findAll();
        assertThat(salesOrderClassificationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSalesOrderClassificationAuditLogWithPatch() throws Exception {
        // Initialize the database
        salesOrderClassificationAuditLogRepository.saveAndFlush(salesOrderClassificationAuditLog);

        int databaseSizeBeforeUpdate = salesOrderClassificationAuditLogRepository.findAll().size();

        // Update the salesOrderClassificationAuditLog using partial update
        SalesOrderClassificationAuditLog partialUpdatedSalesOrderClassificationAuditLog = new SalesOrderClassificationAuditLog();
        partialUpdatedSalesOrderClassificationAuditLog.setId(salesOrderClassificationAuditLog.getId());

        partialUpdatedSalesOrderClassificationAuditLog.orderClaficationId(UPDATED_ORDER_CLAFICATION_ID).newRowData(UPDATED_NEW_ROW_DATA);

        restSalesOrderClassificationAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalesOrderClassificationAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderClassificationAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the SalesOrderClassificationAuditLog in the database
        List<SalesOrderClassificationAuditLog> salesOrderClassificationAuditLogList = salesOrderClassificationAuditLogRepository.findAll();
        assertThat(salesOrderClassificationAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderClassificationAuditLog testSalesOrderClassificationAuditLog = salesOrderClassificationAuditLogList.get(
            salesOrderClassificationAuditLogList.size() - 1
        );
        assertThat(testSalesOrderClassificationAuditLog.getOrderClaficationId()).isEqualTo(UPDATED_ORDER_CLAFICATION_ID);
        assertThat(testSalesOrderClassificationAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testSalesOrderClassificationAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderClassificationAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testSalesOrderClassificationAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testSalesOrderClassificationAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateSalesOrderClassificationAuditLogWithPatch() throws Exception {
        // Initialize the database
        salesOrderClassificationAuditLogRepository.saveAndFlush(salesOrderClassificationAuditLog);

        int databaseSizeBeforeUpdate = salesOrderClassificationAuditLogRepository.findAll().size();

        // Update the salesOrderClassificationAuditLog using partial update
        SalesOrderClassificationAuditLog partialUpdatedSalesOrderClassificationAuditLog = new SalesOrderClassificationAuditLog();
        partialUpdatedSalesOrderClassificationAuditLog.setId(salesOrderClassificationAuditLog.getId());

        partialUpdatedSalesOrderClassificationAuditLog
            .orderClaficationId(UPDATED_ORDER_CLAFICATION_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restSalesOrderClassificationAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalesOrderClassificationAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderClassificationAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the SalesOrderClassificationAuditLog in the database
        List<SalesOrderClassificationAuditLog> salesOrderClassificationAuditLogList = salesOrderClassificationAuditLogRepository.findAll();
        assertThat(salesOrderClassificationAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderClassificationAuditLog testSalesOrderClassificationAuditLog = salesOrderClassificationAuditLogList.get(
            salesOrderClassificationAuditLogList.size() - 1
        );
        assertThat(testSalesOrderClassificationAuditLog.getOrderClaficationId()).isEqualTo(UPDATED_ORDER_CLAFICATION_ID);
        assertThat(testSalesOrderClassificationAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testSalesOrderClassificationAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderClassificationAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testSalesOrderClassificationAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testSalesOrderClassificationAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingSalesOrderClassificationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClassificationAuditLogRepository.findAll().size();
        salesOrderClassificationAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderClassificationAuditLog
        SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO = salesOrderClassificationAuditLogMapper.toDto(
            salesOrderClassificationAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesOrderClassificationAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, salesOrderClassificationAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salesOrderClassificationAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesOrderClassificationAuditLog in the database
        List<SalesOrderClassificationAuditLog> salesOrderClassificationAuditLogList = salesOrderClassificationAuditLogRepository.findAll();
        assertThat(salesOrderClassificationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSalesOrderClassificationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClassificationAuditLogRepository.findAll().size();
        salesOrderClassificationAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderClassificationAuditLog
        SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO = salesOrderClassificationAuditLogMapper.toDto(
            salesOrderClassificationAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesOrderClassificationAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salesOrderClassificationAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesOrderClassificationAuditLog in the database
        List<SalesOrderClassificationAuditLog> salesOrderClassificationAuditLogList = salesOrderClassificationAuditLogRepository.findAll();
        assertThat(salesOrderClassificationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSalesOrderClassificationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClassificationAuditLogRepository.findAll().size();
        salesOrderClassificationAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderClassificationAuditLog
        SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO = salesOrderClassificationAuditLogMapper.toDto(
            salesOrderClassificationAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesOrderClassificationAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salesOrderClassificationAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SalesOrderClassificationAuditLog in the database
        List<SalesOrderClassificationAuditLog> salesOrderClassificationAuditLogList = salesOrderClassificationAuditLogRepository.findAll();
        assertThat(salesOrderClassificationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSalesOrderClassificationAuditLog() throws Exception {
        // Initialize the database
        salesOrderClassificationAuditLogRepository.saveAndFlush(salesOrderClassificationAuditLog);

        int databaseSizeBeforeDelete = salesOrderClassificationAuditLogRepository.findAll().size();

        // Delete the salesOrderClassificationAuditLog
        restSalesOrderClassificationAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, salesOrderClassificationAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SalesOrderClassificationAuditLog> salesOrderClassificationAuditLogList = salesOrderClassificationAuditLogRepository.findAll();
        assertThat(salesOrderClassificationAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
