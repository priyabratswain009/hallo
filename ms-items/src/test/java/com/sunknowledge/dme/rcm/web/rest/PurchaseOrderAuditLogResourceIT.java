package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PurchaseOrderAuditLog;
import com.sunknowledge.dme.rcm.repository.PurchaseOrderAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PurchaseOrderAuditLogMapper;
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
 * Integration tests for the {@link PurchaseOrderAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PurchaseOrderAuditLogResourceIT {

    private static final Long DEFAULT_P_ID = 1L;
    private static final Long UPDATED_P_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/purchase-order-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PurchaseOrderAuditLogRepository purchaseOrderAuditLogRepository;

    @Autowired
    private PurchaseOrderAuditLogMapper purchaseOrderAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPurchaseOrderAuditLogMockMvc;

    private PurchaseOrderAuditLog purchaseOrderAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseOrderAuditLog createEntity(EntityManager em) {
        PurchaseOrderAuditLog purchaseOrderAuditLog = new PurchaseOrderAuditLog()
            .pId(DEFAULT_P_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return purchaseOrderAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseOrderAuditLog createUpdatedEntity(EntityManager em) {
        PurchaseOrderAuditLog purchaseOrderAuditLog = new PurchaseOrderAuditLog()
            .pId(UPDATED_P_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return purchaseOrderAuditLog;
    }

    @BeforeEach
    public void initTest() {
        purchaseOrderAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createPurchaseOrderAuditLog() throws Exception {
        int databaseSizeBeforeCreate = purchaseOrderAuditLogRepository.findAll().size();
        // Create the PurchaseOrderAuditLog
        PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO = purchaseOrderAuditLogMapper.toDto(purchaseOrderAuditLog);
        restPurchaseOrderAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PurchaseOrderAuditLog in the database
        List<PurchaseOrderAuditLog> purchaseOrderAuditLogList = purchaseOrderAuditLogRepository.findAll();
        assertThat(purchaseOrderAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        PurchaseOrderAuditLog testPurchaseOrderAuditLog = purchaseOrderAuditLogList.get(purchaseOrderAuditLogList.size() - 1);
        assertThat(testPurchaseOrderAuditLog.getpId()).isEqualTo(DEFAULT_P_ID);
        assertThat(testPurchaseOrderAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPurchaseOrderAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testPurchaseOrderAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPurchaseOrderAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testPurchaseOrderAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createPurchaseOrderAuditLogWithExistingId() throws Exception {
        // Create the PurchaseOrderAuditLog with an existing ID
        purchaseOrderAuditLog.setId(1L);
        PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO = purchaseOrderAuditLogMapper.toDto(purchaseOrderAuditLog);

        int databaseSizeBeforeCreate = purchaseOrderAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPurchaseOrderAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderAuditLog in the database
        List<PurchaseOrderAuditLog> purchaseOrderAuditLogList = purchaseOrderAuditLogRepository.findAll();
        assertThat(purchaseOrderAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPurchaseOrderAuditLogs() throws Exception {
        // Initialize the database
        purchaseOrderAuditLogRepository.saveAndFlush(purchaseOrderAuditLog);

        // Get all the purchaseOrderAuditLogList
        restPurchaseOrderAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(purchaseOrderAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].pId").value(hasItem(DEFAULT_P_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getPurchaseOrderAuditLog() throws Exception {
        // Initialize the database
        purchaseOrderAuditLogRepository.saveAndFlush(purchaseOrderAuditLog);

        // Get the purchaseOrderAuditLog
        restPurchaseOrderAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, purchaseOrderAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(purchaseOrderAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.pId").value(DEFAULT_P_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingPurchaseOrderAuditLog() throws Exception {
        // Get the purchaseOrderAuditLog
        restPurchaseOrderAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPurchaseOrderAuditLog() throws Exception {
        // Initialize the database
        purchaseOrderAuditLogRepository.saveAndFlush(purchaseOrderAuditLog);

        int databaseSizeBeforeUpdate = purchaseOrderAuditLogRepository.findAll().size();

        // Update the purchaseOrderAuditLog
        PurchaseOrderAuditLog updatedPurchaseOrderAuditLog = purchaseOrderAuditLogRepository.findById(purchaseOrderAuditLog.getId()).get();
        // Disconnect from session so that the updates on updatedPurchaseOrderAuditLog are not directly saved in db
        em.detach(updatedPurchaseOrderAuditLog);
        updatedPurchaseOrderAuditLog
            .pId(UPDATED_P_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO = purchaseOrderAuditLogMapper.toDto(updatedPurchaseOrderAuditLog);

        restPurchaseOrderAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, purchaseOrderAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseOrderAuditLog in the database
        List<PurchaseOrderAuditLog> purchaseOrderAuditLogList = purchaseOrderAuditLogRepository.findAll();
        assertThat(purchaseOrderAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrderAuditLog testPurchaseOrderAuditLog = purchaseOrderAuditLogList.get(purchaseOrderAuditLogList.size() - 1);
        assertThat(testPurchaseOrderAuditLog.getpId()).isEqualTo(UPDATED_P_ID);
        assertThat(testPurchaseOrderAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPurchaseOrderAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPurchaseOrderAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPurchaseOrderAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPurchaseOrderAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingPurchaseOrderAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderAuditLogRepository.findAll().size();
        purchaseOrderAuditLog.setId(count.incrementAndGet());

        // Create the PurchaseOrderAuditLog
        PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO = purchaseOrderAuditLogMapper.toDto(purchaseOrderAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPurchaseOrderAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, purchaseOrderAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderAuditLog in the database
        List<PurchaseOrderAuditLog> purchaseOrderAuditLogList = purchaseOrderAuditLogRepository.findAll();
        assertThat(purchaseOrderAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPurchaseOrderAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderAuditLogRepository.findAll().size();
        purchaseOrderAuditLog.setId(count.incrementAndGet());

        // Create the PurchaseOrderAuditLog
        PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO = purchaseOrderAuditLogMapper.toDto(purchaseOrderAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderAuditLog in the database
        List<PurchaseOrderAuditLog> purchaseOrderAuditLogList = purchaseOrderAuditLogRepository.findAll();
        assertThat(purchaseOrderAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPurchaseOrderAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderAuditLogRepository.findAll().size();
        purchaseOrderAuditLog.setId(count.incrementAndGet());

        // Create the PurchaseOrderAuditLog
        PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO = purchaseOrderAuditLogMapper.toDto(purchaseOrderAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PurchaseOrderAuditLog in the database
        List<PurchaseOrderAuditLog> purchaseOrderAuditLogList = purchaseOrderAuditLogRepository.findAll();
        assertThat(purchaseOrderAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePurchaseOrderAuditLogWithPatch() throws Exception {
        // Initialize the database
        purchaseOrderAuditLogRepository.saveAndFlush(purchaseOrderAuditLog);

        int databaseSizeBeforeUpdate = purchaseOrderAuditLogRepository.findAll().size();

        // Update the purchaseOrderAuditLog using partial update
        PurchaseOrderAuditLog partialUpdatedPurchaseOrderAuditLog = new PurchaseOrderAuditLog();
        partialUpdatedPurchaseOrderAuditLog.setId(purchaseOrderAuditLog.getId());

        partialUpdatedPurchaseOrderAuditLog.oldRowData(UPDATED_OLD_ROW_DATA).dmlType(UPDATED_DML_TYPE).dmlTimestamp(UPDATED_DML_TIMESTAMP);

        restPurchaseOrderAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPurchaseOrderAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPurchaseOrderAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseOrderAuditLog in the database
        List<PurchaseOrderAuditLog> purchaseOrderAuditLogList = purchaseOrderAuditLogRepository.findAll();
        assertThat(purchaseOrderAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrderAuditLog testPurchaseOrderAuditLog = purchaseOrderAuditLogList.get(purchaseOrderAuditLogList.size() - 1);
        assertThat(testPurchaseOrderAuditLog.getpId()).isEqualTo(DEFAULT_P_ID);
        assertThat(testPurchaseOrderAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPurchaseOrderAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testPurchaseOrderAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPurchaseOrderAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPurchaseOrderAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdatePurchaseOrderAuditLogWithPatch() throws Exception {
        // Initialize the database
        purchaseOrderAuditLogRepository.saveAndFlush(purchaseOrderAuditLog);

        int databaseSizeBeforeUpdate = purchaseOrderAuditLogRepository.findAll().size();

        // Update the purchaseOrderAuditLog using partial update
        PurchaseOrderAuditLog partialUpdatedPurchaseOrderAuditLog = new PurchaseOrderAuditLog();
        partialUpdatedPurchaseOrderAuditLog.setId(purchaseOrderAuditLog.getId());

        partialUpdatedPurchaseOrderAuditLog
            .pId(UPDATED_P_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restPurchaseOrderAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPurchaseOrderAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPurchaseOrderAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseOrderAuditLog in the database
        List<PurchaseOrderAuditLog> purchaseOrderAuditLogList = purchaseOrderAuditLogRepository.findAll();
        assertThat(purchaseOrderAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrderAuditLog testPurchaseOrderAuditLog = purchaseOrderAuditLogList.get(purchaseOrderAuditLogList.size() - 1);
        assertThat(testPurchaseOrderAuditLog.getpId()).isEqualTo(UPDATED_P_ID);
        assertThat(testPurchaseOrderAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPurchaseOrderAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPurchaseOrderAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPurchaseOrderAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPurchaseOrderAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingPurchaseOrderAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderAuditLogRepository.findAll().size();
        purchaseOrderAuditLog.setId(count.incrementAndGet());

        // Create the PurchaseOrderAuditLog
        PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO = purchaseOrderAuditLogMapper.toDto(purchaseOrderAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPurchaseOrderAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, purchaseOrderAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderAuditLog in the database
        List<PurchaseOrderAuditLog> purchaseOrderAuditLogList = purchaseOrderAuditLogRepository.findAll();
        assertThat(purchaseOrderAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPurchaseOrderAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderAuditLogRepository.findAll().size();
        purchaseOrderAuditLog.setId(count.incrementAndGet());

        // Create the PurchaseOrderAuditLog
        PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO = purchaseOrderAuditLogMapper.toDto(purchaseOrderAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderAuditLog in the database
        List<PurchaseOrderAuditLog> purchaseOrderAuditLogList = purchaseOrderAuditLogRepository.findAll();
        assertThat(purchaseOrderAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPurchaseOrderAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderAuditLogRepository.findAll().size();
        purchaseOrderAuditLog.setId(count.incrementAndGet());

        // Create the PurchaseOrderAuditLog
        PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO = purchaseOrderAuditLogMapper.toDto(purchaseOrderAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PurchaseOrderAuditLog in the database
        List<PurchaseOrderAuditLog> purchaseOrderAuditLogList = purchaseOrderAuditLogRepository.findAll();
        assertThat(purchaseOrderAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePurchaseOrderAuditLog() throws Exception {
        // Initialize the database
        purchaseOrderAuditLogRepository.saveAndFlush(purchaseOrderAuditLog);

        int databaseSizeBeforeDelete = purchaseOrderAuditLogRepository.findAll().size();

        // Delete the purchaseOrderAuditLog
        restPurchaseOrderAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, purchaseOrderAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PurchaseOrderAuditLog> purchaseOrderAuditLogList = purchaseOrderAuditLogRepository.findAll();
        assertThat(purchaseOrderAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
