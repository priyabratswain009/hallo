package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsAuditLog;
import com.sunknowledge.dme.rcm.repository.PurchaseOrderItemsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PurchaseOrderItemsAuditLogMapper;
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
 * Integration tests for the {@link PurchaseOrderItemsAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PurchaseOrderItemsAuditLogResourceIT {

    private static final Long DEFAULT_PO_ITMS_ID = 1L;
    private static final Long UPDATED_PO_ITMS_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/purchase-order-items-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PurchaseOrderItemsAuditLogRepository purchaseOrderItemsAuditLogRepository;

    @Autowired
    private PurchaseOrderItemsAuditLogMapper purchaseOrderItemsAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPurchaseOrderItemsAuditLogMockMvc;

    private PurchaseOrderItemsAuditLog purchaseOrderItemsAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseOrderItemsAuditLog createEntity(EntityManager em) {
        PurchaseOrderItemsAuditLog purchaseOrderItemsAuditLog = new PurchaseOrderItemsAuditLog()
            .poItmsId(DEFAULT_PO_ITMS_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return purchaseOrderItemsAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseOrderItemsAuditLog createUpdatedEntity(EntityManager em) {
        PurchaseOrderItemsAuditLog purchaseOrderItemsAuditLog = new PurchaseOrderItemsAuditLog()
            .poItmsId(UPDATED_PO_ITMS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return purchaseOrderItemsAuditLog;
    }

    @BeforeEach
    public void initTest() {
        purchaseOrderItemsAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createPurchaseOrderItemsAuditLog() throws Exception {
        int databaseSizeBeforeCreate = purchaseOrderItemsAuditLogRepository.findAll().size();
        // Create the PurchaseOrderItemsAuditLog
        PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO = purchaseOrderItemsAuditLogMapper.toDto(purchaseOrderItemsAuditLog);
        restPurchaseOrderItemsAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PurchaseOrderItemsAuditLog in the database
        List<PurchaseOrderItemsAuditLog> purchaseOrderItemsAuditLogList = purchaseOrderItemsAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        PurchaseOrderItemsAuditLog testPurchaseOrderItemsAuditLog = purchaseOrderItemsAuditLogList.get(
            purchaseOrderItemsAuditLogList.size() - 1
        );
        assertThat(testPurchaseOrderItemsAuditLog.getPoItmsId()).isEqualTo(DEFAULT_PO_ITMS_ID);
        assertThat(testPurchaseOrderItemsAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPurchaseOrderItemsAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testPurchaseOrderItemsAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPurchaseOrderItemsAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testPurchaseOrderItemsAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createPurchaseOrderItemsAuditLogWithExistingId() throws Exception {
        // Create the PurchaseOrderItemsAuditLog with an existing ID
        purchaseOrderItemsAuditLog.setId(1L);
        PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO = purchaseOrderItemsAuditLogMapper.toDto(purchaseOrderItemsAuditLog);

        int databaseSizeBeforeCreate = purchaseOrderItemsAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPurchaseOrderItemsAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItemsAuditLog in the database
        List<PurchaseOrderItemsAuditLog> purchaseOrderItemsAuditLogList = purchaseOrderItemsAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPurchaseOrderItemsAuditLogs() throws Exception {
        // Initialize the database
        purchaseOrderItemsAuditLogRepository.saveAndFlush(purchaseOrderItemsAuditLog);

        // Get all the purchaseOrderItemsAuditLogList
        restPurchaseOrderItemsAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(purchaseOrderItemsAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].poItmsId").value(hasItem(DEFAULT_PO_ITMS_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getPurchaseOrderItemsAuditLog() throws Exception {
        // Initialize the database
        purchaseOrderItemsAuditLogRepository.saveAndFlush(purchaseOrderItemsAuditLog);

        // Get the purchaseOrderItemsAuditLog
        restPurchaseOrderItemsAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, purchaseOrderItemsAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(purchaseOrderItemsAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.poItmsId").value(DEFAULT_PO_ITMS_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingPurchaseOrderItemsAuditLog() throws Exception {
        // Get the purchaseOrderItemsAuditLog
        restPurchaseOrderItemsAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPurchaseOrderItemsAuditLog() throws Exception {
        // Initialize the database
        purchaseOrderItemsAuditLogRepository.saveAndFlush(purchaseOrderItemsAuditLog);

        int databaseSizeBeforeUpdate = purchaseOrderItemsAuditLogRepository.findAll().size();

        // Update the purchaseOrderItemsAuditLog
        PurchaseOrderItemsAuditLog updatedPurchaseOrderItemsAuditLog = purchaseOrderItemsAuditLogRepository
            .findById(purchaseOrderItemsAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedPurchaseOrderItemsAuditLog are not directly saved in db
        em.detach(updatedPurchaseOrderItemsAuditLog);
        updatedPurchaseOrderItemsAuditLog
            .poItmsId(UPDATED_PO_ITMS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO = purchaseOrderItemsAuditLogMapper.toDto(
            updatedPurchaseOrderItemsAuditLog
        );

        restPurchaseOrderItemsAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, purchaseOrderItemsAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseOrderItemsAuditLog in the database
        List<PurchaseOrderItemsAuditLog> purchaseOrderItemsAuditLogList = purchaseOrderItemsAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrderItemsAuditLog testPurchaseOrderItemsAuditLog = purchaseOrderItemsAuditLogList.get(
            purchaseOrderItemsAuditLogList.size() - 1
        );
        assertThat(testPurchaseOrderItemsAuditLog.getPoItmsId()).isEqualTo(UPDATED_PO_ITMS_ID);
        assertThat(testPurchaseOrderItemsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPurchaseOrderItemsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPurchaseOrderItemsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPurchaseOrderItemsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPurchaseOrderItemsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingPurchaseOrderItemsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsAuditLogRepository.findAll().size();
        purchaseOrderItemsAuditLog.setId(count.incrementAndGet());

        // Create the PurchaseOrderItemsAuditLog
        PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO = purchaseOrderItemsAuditLogMapper.toDto(purchaseOrderItemsAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, purchaseOrderItemsAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItemsAuditLog in the database
        List<PurchaseOrderItemsAuditLog> purchaseOrderItemsAuditLogList = purchaseOrderItemsAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPurchaseOrderItemsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsAuditLogRepository.findAll().size();
        purchaseOrderItemsAuditLog.setId(count.incrementAndGet());

        // Create the PurchaseOrderItemsAuditLog
        PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO = purchaseOrderItemsAuditLogMapper.toDto(purchaseOrderItemsAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItemsAuditLog in the database
        List<PurchaseOrderItemsAuditLog> purchaseOrderItemsAuditLogList = purchaseOrderItemsAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPurchaseOrderItemsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsAuditLogRepository.findAll().size();
        purchaseOrderItemsAuditLog.setId(count.incrementAndGet());

        // Create the PurchaseOrderItemsAuditLog
        PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO = purchaseOrderItemsAuditLogMapper.toDto(purchaseOrderItemsAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PurchaseOrderItemsAuditLog in the database
        List<PurchaseOrderItemsAuditLog> purchaseOrderItemsAuditLogList = purchaseOrderItemsAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePurchaseOrderItemsAuditLogWithPatch() throws Exception {
        // Initialize the database
        purchaseOrderItemsAuditLogRepository.saveAndFlush(purchaseOrderItemsAuditLog);

        int databaseSizeBeforeUpdate = purchaseOrderItemsAuditLogRepository.findAll().size();

        // Update the purchaseOrderItemsAuditLog using partial update
        PurchaseOrderItemsAuditLog partialUpdatedPurchaseOrderItemsAuditLog = new PurchaseOrderItemsAuditLog();
        partialUpdatedPurchaseOrderItemsAuditLog.setId(purchaseOrderItemsAuditLog.getId());

        partialUpdatedPurchaseOrderItemsAuditLog
            .poItmsId(UPDATED_PO_ITMS_ID)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP);

        restPurchaseOrderItemsAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPurchaseOrderItemsAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPurchaseOrderItemsAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseOrderItemsAuditLog in the database
        List<PurchaseOrderItemsAuditLog> purchaseOrderItemsAuditLogList = purchaseOrderItemsAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrderItemsAuditLog testPurchaseOrderItemsAuditLog = purchaseOrderItemsAuditLogList.get(
            purchaseOrderItemsAuditLogList.size() - 1
        );
        assertThat(testPurchaseOrderItemsAuditLog.getPoItmsId()).isEqualTo(UPDATED_PO_ITMS_ID);
        assertThat(testPurchaseOrderItemsAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPurchaseOrderItemsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPurchaseOrderItemsAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPurchaseOrderItemsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPurchaseOrderItemsAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdatePurchaseOrderItemsAuditLogWithPatch() throws Exception {
        // Initialize the database
        purchaseOrderItemsAuditLogRepository.saveAndFlush(purchaseOrderItemsAuditLog);

        int databaseSizeBeforeUpdate = purchaseOrderItemsAuditLogRepository.findAll().size();

        // Update the purchaseOrderItemsAuditLog using partial update
        PurchaseOrderItemsAuditLog partialUpdatedPurchaseOrderItemsAuditLog = new PurchaseOrderItemsAuditLog();
        partialUpdatedPurchaseOrderItemsAuditLog.setId(purchaseOrderItemsAuditLog.getId());

        partialUpdatedPurchaseOrderItemsAuditLog
            .poItmsId(UPDATED_PO_ITMS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restPurchaseOrderItemsAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPurchaseOrderItemsAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPurchaseOrderItemsAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseOrderItemsAuditLog in the database
        List<PurchaseOrderItemsAuditLog> purchaseOrderItemsAuditLogList = purchaseOrderItemsAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrderItemsAuditLog testPurchaseOrderItemsAuditLog = purchaseOrderItemsAuditLogList.get(
            purchaseOrderItemsAuditLogList.size() - 1
        );
        assertThat(testPurchaseOrderItemsAuditLog.getPoItmsId()).isEqualTo(UPDATED_PO_ITMS_ID);
        assertThat(testPurchaseOrderItemsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPurchaseOrderItemsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPurchaseOrderItemsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPurchaseOrderItemsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPurchaseOrderItemsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingPurchaseOrderItemsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsAuditLogRepository.findAll().size();
        purchaseOrderItemsAuditLog.setId(count.incrementAndGet());

        // Create the PurchaseOrderItemsAuditLog
        PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO = purchaseOrderItemsAuditLogMapper.toDto(purchaseOrderItemsAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, purchaseOrderItemsAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItemsAuditLog in the database
        List<PurchaseOrderItemsAuditLog> purchaseOrderItemsAuditLogList = purchaseOrderItemsAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPurchaseOrderItemsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsAuditLogRepository.findAll().size();
        purchaseOrderItemsAuditLog.setId(count.incrementAndGet());

        // Create the PurchaseOrderItemsAuditLog
        PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO = purchaseOrderItemsAuditLogMapper.toDto(purchaseOrderItemsAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItemsAuditLog in the database
        List<PurchaseOrderItemsAuditLog> purchaseOrderItemsAuditLogList = purchaseOrderItemsAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPurchaseOrderItemsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsAuditLogRepository.findAll().size();
        purchaseOrderItemsAuditLog.setId(count.incrementAndGet());

        // Create the PurchaseOrderItemsAuditLog
        PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO = purchaseOrderItemsAuditLogMapper.toDto(purchaseOrderItemsAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PurchaseOrderItemsAuditLog in the database
        List<PurchaseOrderItemsAuditLog> purchaseOrderItemsAuditLogList = purchaseOrderItemsAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePurchaseOrderItemsAuditLog() throws Exception {
        // Initialize the database
        purchaseOrderItemsAuditLogRepository.saveAndFlush(purchaseOrderItemsAuditLog);

        int databaseSizeBeforeDelete = purchaseOrderItemsAuditLogRepository.findAll().size();

        // Delete the purchaseOrderItemsAuditLog
        restPurchaseOrderItemsAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, purchaseOrderItemsAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PurchaseOrderItemsAuditLog> purchaseOrderItemsAuditLogList = purchaseOrderItemsAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
