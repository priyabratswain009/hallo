package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsReceivedAuditLog;
import com.sunknowledge.dme.rcm.repository.PurchaseOrderItemsReceivedAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsReceivedAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PurchaseOrderItemsReceivedAuditLogMapper;
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
 * Integration tests for the {@link PurchaseOrderItemsReceivedAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PurchaseOrderItemsReceivedAuditLogResourceIT {

    private static final Long DEFAULT_PO_ITM_RECIVED_ID = 1L;
    private static final Long UPDATED_PO_ITM_RECIVED_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/purchase-order-items-received-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PurchaseOrderItemsReceivedAuditLogRepository purchaseOrderItemsReceivedAuditLogRepository;

    @Autowired
    private PurchaseOrderItemsReceivedAuditLogMapper purchaseOrderItemsReceivedAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPurchaseOrderItemsReceivedAuditLogMockMvc;

    private PurchaseOrderItemsReceivedAuditLog purchaseOrderItemsReceivedAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseOrderItemsReceivedAuditLog createEntity(EntityManager em) {
        PurchaseOrderItemsReceivedAuditLog purchaseOrderItemsReceivedAuditLog = new PurchaseOrderItemsReceivedAuditLog()
            .poItmRecivedId(DEFAULT_PO_ITM_RECIVED_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return purchaseOrderItemsReceivedAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseOrderItemsReceivedAuditLog createUpdatedEntity(EntityManager em) {
        PurchaseOrderItemsReceivedAuditLog purchaseOrderItemsReceivedAuditLog = new PurchaseOrderItemsReceivedAuditLog()
            .poItmRecivedId(UPDATED_PO_ITM_RECIVED_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return purchaseOrderItemsReceivedAuditLog;
    }

    @BeforeEach
    public void initTest() {
        purchaseOrderItemsReceivedAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createPurchaseOrderItemsReceivedAuditLog() throws Exception {
        int databaseSizeBeforeCreate = purchaseOrderItemsReceivedAuditLogRepository.findAll().size();
        // Create the PurchaseOrderItemsReceivedAuditLog
        PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO = purchaseOrderItemsReceivedAuditLogMapper.toDto(
            purchaseOrderItemsReceivedAuditLog
        );
        restPurchaseOrderItemsReceivedAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsReceivedAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PurchaseOrderItemsReceivedAuditLog in the database
        List<PurchaseOrderItemsReceivedAuditLog> purchaseOrderItemsReceivedAuditLogList = purchaseOrderItemsReceivedAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsReceivedAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        PurchaseOrderItemsReceivedAuditLog testPurchaseOrderItemsReceivedAuditLog = purchaseOrderItemsReceivedAuditLogList.get(
            purchaseOrderItemsReceivedAuditLogList.size() - 1
        );
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getPoItmRecivedId()).isEqualTo(DEFAULT_PO_ITM_RECIVED_ID);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createPurchaseOrderItemsReceivedAuditLogWithExistingId() throws Exception {
        // Create the PurchaseOrderItemsReceivedAuditLog with an existing ID
        purchaseOrderItemsReceivedAuditLog.setId(1L);
        PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO = purchaseOrderItemsReceivedAuditLogMapper.toDto(
            purchaseOrderItemsReceivedAuditLog
        );

        int databaseSizeBeforeCreate = purchaseOrderItemsReceivedAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPurchaseOrderItemsReceivedAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsReceivedAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItemsReceivedAuditLog in the database
        List<PurchaseOrderItemsReceivedAuditLog> purchaseOrderItemsReceivedAuditLogList = purchaseOrderItemsReceivedAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsReceivedAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPurchaseOrderItemsReceivedAuditLogs() throws Exception {
        // Initialize the database
        purchaseOrderItemsReceivedAuditLogRepository.saveAndFlush(purchaseOrderItemsReceivedAuditLog);

        // Get all the purchaseOrderItemsReceivedAuditLogList
        restPurchaseOrderItemsReceivedAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(purchaseOrderItemsReceivedAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].poItmRecivedId").value(hasItem(DEFAULT_PO_ITM_RECIVED_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getPurchaseOrderItemsReceivedAuditLog() throws Exception {
        // Initialize the database
        purchaseOrderItemsReceivedAuditLogRepository.saveAndFlush(purchaseOrderItemsReceivedAuditLog);

        // Get the purchaseOrderItemsReceivedAuditLog
        restPurchaseOrderItemsReceivedAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, purchaseOrderItemsReceivedAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(purchaseOrderItemsReceivedAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.poItmRecivedId").value(DEFAULT_PO_ITM_RECIVED_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingPurchaseOrderItemsReceivedAuditLog() throws Exception {
        // Get the purchaseOrderItemsReceivedAuditLog
        restPurchaseOrderItemsReceivedAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPurchaseOrderItemsReceivedAuditLog() throws Exception {
        // Initialize the database
        purchaseOrderItemsReceivedAuditLogRepository.saveAndFlush(purchaseOrderItemsReceivedAuditLog);

        int databaseSizeBeforeUpdate = purchaseOrderItemsReceivedAuditLogRepository.findAll().size();

        // Update the purchaseOrderItemsReceivedAuditLog
        PurchaseOrderItemsReceivedAuditLog updatedPurchaseOrderItemsReceivedAuditLog = purchaseOrderItemsReceivedAuditLogRepository
            .findById(purchaseOrderItemsReceivedAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedPurchaseOrderItemsReceivedAuditLog are not directly saved in db
        em.detach(updatedPurchaseOrderItemsReceivedAuditLog);
        updatedPurchaseOrderItemsReceivedAuditLog
            .poItmRecivedId(UPDATED_PO_ITM_RECIVED_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO = purchaseOrderItemsReceivedAuditLogMapper.toDto(
            updatedPurchaseOrderItemsReceivedAuditLog
        );

        restPurchaseOrderItemsReceivedAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, purchaseOrderItemsReceivedAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsReceivedAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseOrderItemsReceivedAuditLog in the database
        List<PurchaseOrderItemsReceivedAuditLog> purchaseOrderItemsReceivedAuditLogList = purchaseOrderItemsReceivedAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsReceivedAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrderItemsReceivedAuditLog testPurchaseOrderItemsReceivedAuditLog = purchaseOrderItemsReceivedAuditLogList.get(
            purchaseOrderItemsReceivedAuditLogList.size() - 1
        );
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getPoItmRecivedId()).isEqualTo(UPDATED_PO_ITM_RECIVED_ID);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingPurchaseOrderItemsReceivedAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsReceivedAuditLogRepository.findAll().size();
        purchaseOrderItemsReceivedAuditLog.setId(count.incrementAndGet());

        // Create the PurchaseOrderItemsReceivedAuditLog
        PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO = purchaseOrderItemsReceivedAuditLogMapper.toDto(
            purchaseOrderItemsReceivedAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsReceivedAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, purchaseOrderItemsReceivedAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsReceivedAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItemsReceivedAuditLog in the database
        List<PurchaseOrderItemsReceivedAuditLog> purchaseOrderItemsReceivedAuditLogList = purchaseOrderItemsReceivedAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsReceivedAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPurchaseOrderItemsReceivedAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsReceivedAuditLogRepository.findAll().size();
        purchaseOrderItemsReceivedAuditLog.setId(count.incrementAndGet());

        // Create the PurchaseOrderItemsReceivedAuditLog
        PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO = purchaseOrderItemsReceivedAuditLogMapper.toDto(
            purchaseOrderItemsReceivedAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsReceivedAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsReceivedAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItemsReceivedAuditLog in the database
        List<PurchaseOrderItemsReceivedAuditLog> purchaseOrderItemsReceivedAuditLogList = purchaseOrderItemsReceivedAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsReceivedAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPurchaseOrderItemsReceivedAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsReceivedAuditLogRepository.findAll().size();
        purchaseOrderItemsReceivedAuditLog.setId(count.incrementAndGet());

        // Create the PurchaseOrderItemsReceivedAuditLog
        PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO = purchaseOrderItemsReceivedAuditLogMapper.toDto(
            purchaseOrderItemsReceivedAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsReceivedAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsReceivedAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PurchaseOrderItemsReceivedAuditLog in the database
        List<PurchaseOrderItemsReceivedAuditLog> purchaseOrderItemsReceivedAuditLogList = purchaseOrderItemsReceivedAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsReceivedAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePurchaseOrderItemsReceivedAuditLogWithPatch() throws Exception {
        // Initialize the database
        purchaseOrderItemsReceivedAuditLogRepository.saveAndFlush(purchaseOrderItemsReceivedAuditLog);

        int databaseSizeBeforeUpdate = purchaseOrderItemsReceivedAuditLogRepository.findAll().size();

        // Update the purchaseOrderItemsReceivedAuditLog using partial update
        PurchaseOrderItemsReceivedAuditLog partialUpdatedPurchaseOrderItemsReceivedAuditLog = new PurchaseOrderItemsReceivedAuditLog();
        partialUpdatedPurchaseOrderItemsReceivedAuditLog.setId(purchaseOrderItemsReceivedAuditLog.getId());

        partialUpdatedPurchaseOrderItemsReceivedAuditLog
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restPurchaseOrderItemsReceivedAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPurchaseOrderItemsReceivedAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPurchaseOrderItemsReceivedAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseOrderItemsReceivedAuditLog in the database
        List<PurchaseOrderItemsReceivedAuditLog> purchaseOrderItemsReceivedAuditLogList = purchaseOrderItemsReceivedAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsReceivedAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrderItemsReceivedAuditLog testPurchaseOrderItemsReceivedAuditLog = purchaseOrderItemsReceivedAuditLogList.get(
            purchaseOrderItemsReceivedAuditLogList.size() - 1
        );
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getPoItmRecivedId()).isEqualTo(DEFAULT_PO_ITM_RECIVED_ID);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdatePurchaseOrderItemsReceivedAuditLogWithPatch() throws Exception {
        // Initialize the database
        purchaseOrderItemsReceivedAuditLogRepository.saveAndFlush(purchaseOrderItemsReceivedAuditLog);

        int databaseSizeBeforeUpdate = purchaseOrderItemsReceivedAuditLogRepository.findAll().size();

        // Update the purchaseOrderItemsReceivedAuditLog using partial update
        PurchaseOrderItemsReceivedAuditLog partialUpdatedPurchaseOrderItemsReceivedAuditLog = new PurchaseOrderItemsReceivedAuditLog();
        partialUpdatedPurchaseOrderItemsReceivedAuditLog.setId(purchaseOrderItemsReceivedAuditLog.getId());

        partialUpdatedPurchaseOrderItemsReceivedAuditLog
            .poItmRecivedId(UPDATED_PO_ITM_RECIVED_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restPurchaseOrderItemsReceivedAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPurchaseOrderItemsReceivedAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPurchaseOrderItemsReceivedAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseOrderItemsReceivedAuditLog in the database
        List<PurchaseOrderItemsReceivedAuditLog> purchaseOrderItemsReceivedAuditLogList = purchaseOrderItemsReceivedAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsReceivedAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrderItemsReceivedAuditLog testPurchaseOrderItemsReceivedAuditLog = purchaseOrderItemsReceivedAuditLogList.get(
            purchaseOrderItemsReceivedAuditLogList.size() - 1
        );
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getPoItmRecivedId()).isEqualTo(UPDATED_PO_ITM_RECIVED_ID);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPurchaseOrderItemsReceivedAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingPurchaseOrderItemsReceivedAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsReceivedAuditLogRepository.findAll().size();
        purchaseOrderItemsReceivedAuditLog.setId(count.incrementAndGet());

        // Create the PurchaseOrderItemsReceivedAuditLog
        PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO = purchaseOrderItemsReceivedAuditLogMapper.toDto(
            purchaseOrderItemsReceivedAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsReceivedAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, purchaseOrderItemsReceivedAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsReceivedAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItemsReceivedAuditLog in the database
        List<PurchaseOrderItemsReceivedAuditLog> purchaseOrderItemsReceivedAuditLogList = purchaseOrderItemsReceivedAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsReceivedAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPurchaseOrderItemsReceivedAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsReceivedAuditLogRepository.findAll().size();
        purchaseOrderItemsReceivedAuditLog.setId(count.incrementAndGet());

        // Create the PurchaseOrderItemsReceivedAuditLog
        PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO = purchaseOrderItemsReceivedAuditLogMapper.toDto(
            purchaseOrderItemsReceivedAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsReceivedAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsReceivedAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderItemsReceivedAuditLog in the database
        List<PurchaseOrderItemsReceivedAuditLog> purchaseOrderItemsReceivedAuditLogList = purchaseOrderItemsReceivedAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsReceivedAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPurchaseOrderItemsReceivedAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderItemsReceivedAuditLogRepository.findAll().size();
        purchaseOrderItemsReceivedAuditLog.setId(count.incrementAndGet());

        // Create the PurchaseOrderItemsReceivedAuditLog
        PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO = purchaseOrderItemsReceivedAuditLogMapper.toDto(
            purchaseOrderItemsReceivedAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderItemsReceivedAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderItemsReceivedAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PurchaseOrderItemsReceivedAuditLog in the database
        List<PurchaseOrderItemsReceivedAuditLog> purchaseOrderItemsReceivedAuditLogList = purchaseOrderItemsReceivedAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsReceivedAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePurchaseOrderItemsReceivedAuditLog() throws Exception {
        // Initialize the database
        purchaseOrderItemsReceivedAuditLogRepository.saveAndFlush(purchaseOrderItemsReceivedAuditLog);

        int databaseSizeBeforeDelete = purchaseOrderItemsReceivedAuditLogRepository.findAll().size();

        // Delete the purchaseOrderItemsReceivedAuditLog
        restPurchaseOrderItemsReceivedAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, purchaseOrderItemsReceivedAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PurchaseOrderItemsReceivedAuditLog> purchaseOrderItemsReceivedAuditLogList = purchaseOrderItemsReceivedAuditLogRepository.findAll();
        assertThat(purchaseOrderItemsReceivedAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
