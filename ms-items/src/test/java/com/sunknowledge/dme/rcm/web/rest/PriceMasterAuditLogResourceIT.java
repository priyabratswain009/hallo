package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PriceMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.PriceMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.PriceMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PriceMasterAuditLogMapper;
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
 * Integration tests for the {@link PriceMasterAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PriceMasterAuditLogResourceIT {

    private static final Long DEFAULT_PRCE_TBLE_ID = 1L;
    private static final Long UPDATED_PRCE_TBLE_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/price-master-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PriceMasterAuditLogRepository priceMasterAuditLogRepository;

    @Autowired
    private PriceMasterAuditLogMapper priceMasterAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPriceMasterAuditLogMockMvc;

    private PriceMasterAuditLog priceMasterAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PriceMasterAuditLog createEntity(EntityManager em) {
        PriceMasterAuditLog priceMasterAuditLog = new PriceMasterAuditLog()
            .prceTbleId(DEFAULT_PRCE_TBLE_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return priceMasterAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PriceMasterAuditLog createUpdatedEntity(EntityManager em) {
        PriceMasterAuditLog priceMasterAuditLog = new PriceMasterAuditLog()
            .prceTbleId(UPDATED_PRCE_TBLE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return priceMasterAuditLog;
    }

    @BeforeEach
    public void initTest() {
        priceMasterAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createPriceMasterAuditLog() throws Exception {
        int databaseSizeBeforeCreate = priceMasterAuditLogRepository.findAll().size();
        // Create the PriceMasterAuditLog
        PriceMasterAuditLogDTO priceMasterAuditLogDTO = priceMasterAuditLogMapper.toDto(priceMasterAuditLog);
        restPriceMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceMasterAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PriceMasterAuditLog in the database
        List<PriceMasterAuditLog> priceMasterAuditLogList = priceMasterAuditLogRepository.findAll();
        assertThat(priceMasterAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        PriceMasterAuditLog testPriceMasterAuditLog = priceMasterAuditLogList.get(priceMasterAuditLogList.size() - 1);
        assertThat(testPriceMasterAuditLog.getPrceTbleId()).isEqualTo(DEFAULT_PRCE_TBLE_ID);
        assertThat(testPriceMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPriceMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testPriceMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPriceMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testPriceMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createPriceMasterAuditLogWithExistingId() throws Exception {
        // Create the PriceMasterAuditLog with an existing ID
        priceMasterAuditLog.setId(1L);
        PriceMasterAuditLogDTO priceMasterAuditLogDTO = priceMasterAuditLogMapper.toDto(priceMasterAuditLog);

        int databaseSizeBeforeCreate = priceMasterAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPriceMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceMasterAuditLog in the database
        List<PriceMasterAuditLog> priceMasterAuditLogList = priceMasterAuditLogRepository.findAll();
        assertThat(priceMasterAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPriceMasterAuditLogs() throws Exception {
        // Initialize the database
        priceMasterAuditLogRepository.saveAndFlush(priceMasterAuditLog);

        // Get all the priceMasterAuditLogList
        restPriceMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(priceMasterAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].prceTbleId").value(hasItem(DEFAULT_PRCE_TBLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getPriceMasterAuditLog() throws Exception {
        // Initialize the database
        priceMasterAuditLogRepository.saveAndFlush(priceMasterAuditLog);

        // Get the priceMasterAuditLog
        restPriceMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, priceMasterAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(priceMasterAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.prceTbleId").value(DEFAULT_PRCE_TBLE_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingPriceMasterAuditLog() throws Exception {
        // Get the priceMasterAuditLog
        restPriceMasterAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPriceMasterAuditLog() throws Exception {
        // Initialize the database
        priceMasterAuditLogRepository.saveAndFlush(priceMasterAuditLog);

        int databaseSizeBeforeUpdate = priceMasterAuditLogRepository.findAll().size();

        // Update the priceMasterAuditLog
        PriceMasterAuditLog updatedPriceMasterAuditLog = priceMasterAuditLogRepository.findById(priceMasterAuditLog.getId()).get();
        // Disconnect from session so that the updates on updatedPriceMasterAuditLog are not directly saved in db
        em.detach(updatedPriceMasterAuditLog);
        updatedPriceMasterAuditLog
            .prceTbleId(UPDATED_PRCE_TBLE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        PriceMasterAuditLogDTO priceMasterAuditLogDTO = priceMasterAuditLogMapper.toDto(updatedPriceMasterAuditLog);

        restPriceMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, priceMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceMasterAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the PriceMasterAuditLog in the database
        List<PriceMasterAuditLog> priceMasterAuditLogList = priceMasterAuditLogRepository.findAll();
        assertThat(priceMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PriceMasterAuditLog testPriceMasterAuditLog = priceMasterAuditLogList.get(priceMasterAuditLogList.size() - 1);
        assertThat(testPriceMasterAuditLog.getPrceTbleId()).isEqualTo(UPDATED_PRCE_TBLE_ID);
        assertThat(testPriceMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPriceMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPriceMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPriceMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPriceMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingPriceMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = priceMasterAuditLogRepository.findAll().size();
        priceMasterAuditLog.setId(count.incrementAndGet());

        // Create the PriceMasterAuditLog
        PriceMasterAuditLogDTO priceMasterAuditLogDTO = priceMasterAuditLogMapper.toDto(priceMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPriceMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, priceMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceMasterAuditLog in the database
        List<PriceMasterAuditLog> priceMasterAuditLogList = priceMasterAuditLogRepository.findAll();
        assertThat(priceMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPriceMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = priceMasterAuditLogRepository.findAll().size();
        priceMasterAuditLog.setId(count.incrementAndGet());

        // Create the PriceMasterAuditLog
        PriceMasterAuditLogDTO priceMasterAuditLogDTO = priceMasterAuditLogMapper.toDto(priceMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceMasterAuditLog in the database
        List<PriceMasterAuditLog> priceMasterAuditLogList = priceMasterAuditLogRepository.findAll();
        assertThat(priceMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPriceMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = priceMasterAuditLogRepository.findAll().size();
        priceMasterAuditLog.setId(count.incrementAndGet());

        // Create the PriceMasterAuditLog
        PriceMasterAuditLogDTO priceMasterAuditLogDTO = priceMasterAuditLogMapper.toDto(priceMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(priceMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PriceMasterAuditLog in the database
        List<PriceMasterAuditLog> priceMasterAuditLogList = priceMasterAuditLogRepository.findAll();
        assertThat(priceMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePriceMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        priceMasterAuditLogRepository.saveAndFlush(priceMasterAuditLog);

        int databaseSizeBeforeUpdate = priceMasterAuditLogRepository.findAll().size();

        // Update the priceMasterAuditLog using partial update
        PriceMasterAuditLog partialUpdatedPriceMasterAuditLog = new PriceMasterAuditLog();
        partialUpdatedPriceMasterAuditLog.setId(priceMasterAuditLog.getId());

        partialUpdatedPriceMasterAuditLog.newRowData(UPDATED_NEW_ROW_DATA).dmlTimestamp(UPDATED_DML_TIMESTAMP);

        restPriceMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPriceMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPriceMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the PriceMasterAuditLog in the database
        List<PriceMasterAuditLog> priceMasterAuditLogList = priceMasterAuditLogRepository.findAll();
        assertThat(priceMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PriceMasterAuditLog testPriceMasterAuditLog = priceMasterAuditLogList.get(priceMasterAuditLogList.size() - 1);
        assertThat(testPriceMasterAuditLog.getPrceTbleId()).isEqualTo(DEFAULT_PRCE_TBLE_ID);
        assertThat(testPriceMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPriceMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPriceMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPriceMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPriceMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdatePriceMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        priceMasterAuditLogRepository.saveAndFlush(priceMasterAuditLog);

        int databaseSizeBeforeUpdate = priceMasterAuditLogRepository.findAll().size();

        // Update the priceMasterAuditLog using partial update
        PriceMasterAuditLog partialUpdatedPriceMasterAuditLog = new PriceMasterAuditLog();
        partialUpdatedPriceMasterAuditLog.setId(priceMasterAuditLog.getId());

        partialUpdatedPriceMasterAuditLog
            .prceTbleId(UPDATED_PRCE_TBLE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restPriceMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPriceMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPriceMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the PriceMasterAuditLog in the database
        List<PriceMasterAuditLog> priceMasterAuditLogList = priceMasterAuditLogRepository.findAll();
        assertThat(priceMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PriceMasterAuditLog testPriceMasterAuditLog = priceMasterAuditLogList.get(priceMasterAuditLogList.size() - 1);
        assertThat(testPriceMasterAuditLog.getPrceTbleId()).isEqualTo(UPDATED_PRCE_TBLE_ID);
        assertThat(testPriceMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPriceMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPriceMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPriceMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPriceMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingPriceMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = priceMasterAuditLogRepository.findAll().size();
        priceMasterAuditLog.setId(count.incrementAndGet());

        // Create the PriceMasterAuditLog
        PriceMasterAuditLogDTO priceMasterAuditLogDTO = priceMasterAuditLogMapper.toDto(priceMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPriceMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, priceMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(priceMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceMasterAuditLog in the database
        List<PriceMasterAuditLog> priceMasterAuditLogList = priceMasterAuditLogRepository.findAll();
        assertThat(priceMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPriceMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = priceMasterAuditLogRepository.findAll().size();
        priceMasterAuditLog.setId(count.incrementAndGet());

        // Create the PriceMasterAuditLog
        PriceMasterAuditLogDTO priceMasterAuditLogDTO = priceMasterAuditLogMapper.toDto(priceMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(priceMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PriceMasterAuditLog in the database
        List<PriceMasterAuditLog> priceMasterAuditLogList = priceMasterAuditLogRepository.findAll();
        assertThat(priceMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPriceMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = priceMasterAuditLogRepository.findAll().size();
        priceMasterAuditLog.setId(count.incrementAndGet());

        // Create the PriceMasterAuditLog
        PriceMasterAuditLogDTO priceMasterAuditLogDTO = priceMasterAuditLogMapper.toDto(priceMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPriceMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(priceMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PriceMasterAuditLog in the database
        List<PriceMasterAuditLog> priceMasterAuditLogList = priceMasterAuditLogRepository.findAll();
        assertThat(priceMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePriceMasterAuditLog() throws Exception {
        // Initialize the database
        priceMasterAuditLogRepository.saveAndFlush(priceMasterAuditLog);

        int databaseSizeBeforeDelete = priceMasterAuditLogRepository.findAll().size();

        // Delete the priceMasterAuditLog
        restPriceMasterAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, priceMasterAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PriceMasterAuditLog> priceMasterAuditLogList = priceMasterAuditLogRepository.findAll();
        assertThat(priceMasterAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
