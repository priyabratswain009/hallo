package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ManufacturerAuditLog;
import com.sunknowledge.dme.rcm.repository.ManufacturerAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.ManufacturerAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ManufacturerAuditLogMapper;
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
 * Integration tests for the {@link ManufacturerAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ManufacturerAuditLogResourceIT {

    private static final Long DEFAULT_MNUFCTURER_ID = 1L;
    private static final Long UPDATED_MNUFCTURER_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/manufacturer-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ManufacturerAuditLogRepository manufacturerAuditLogRepository;

    @Autowired
    private ManufacturerAuditLogMapper manufacturerAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restManufacturerAuditLogMockMvc;

    private ManufacturerAuditLog manufacturerAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ManufacturerAuditLog createEntity(EntityManager em) {
        ManufacturerAuditLog manufacturerAuditLog = new ManufacturerAuditLog()
            .mnufcturerId(DEFAULT_MNUFCTURER_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return manufacturerAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ManufacturerAuditLog createUpdatedEntity(EntityManager em) {
        ManufacturerAuditLog manufacturerAuditLog = new ManufacturerAuditLog()
            .mnufcturerId(UPDATED_MNUFCTURER_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return manufacturerAuditLog;
    }

    @BeforeEach
    public void initTest() {
        manufacturerAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createManufacturerAuditLog() throws Exception {
        int databaseSizeBeforeCreate = manufacturerAuditLogRepository.findAll().size();
        // Create the ManufacturerAuditLog
        ManufacturerAuditLogDTO manufacturerAuditLogDTO = manufacturerAuditLogMapper.toDto(manufacturerAuditLog);
        restManufacturerAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(manufacturerAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ManufacturerAuditLog in the database
        List<ManufacturerAuditLog> manufacturerAuditLogList = manufacturerAuditLogRepository.findAll();
        assertThat(manufacturerAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        ManufacturerAuditLog testManufacturerAuditLog = manufacturerAuditLogList.get(manufacturerAuditLogList.size() - 1);
        assertThat(testManufacturerAuditLog.getMnufcturerId()).isEqualTo(DEFAULT_MNUFCTURER_ID);
        assertThat(testManufacturerAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testManufacturerAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testManufacturerAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testManufacturerAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testManufacturerAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createManufacturerAuditLogWithExistingId() throws Exception {
        // Create the ManufacturerAuditLog with an existing ID
        manufacturerAuditLog.setId(1L);
        ManufacturerAuditLogDTO manufacturerAuditLogDTO = manufacturerAuditLogMapper.toDto(manufacturerAuditLog);

        int databaseSizeBeforeCreate = manufacturerAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restManufacturerAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(manufacturerAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ManufacturerAuditLog in the database
        List<ManufacturerAuditLog> manufacturerAuditLogList = manufacturerAuditLogRepository.findAll();
        assertThat(manufacturerAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllManufacturerAuditLogs() throws Exception {
        // Initialize the database
        manufacturerAuditLogRepository.saveAndFlush(manufacturerAuditLog);

        // Get all the manufacturerAuditLogList
        restManufacturerAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(manufacturerAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].mnufcturerId").value(hasItem(DEFAULT_MNUFCTURER_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getManufacturerAuditLog() throws Exception {
        // Initialize the database
        manufacturerAuditLogRepository.saveAndFlush(manufacturerAuditLog);

        // Get the manufacturerAuditLog
        restManufacturerAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, manufacturerAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(manufacturerAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.mnufcturerId").value(DEFAULT_MNUFCTURER_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingManufacturerAuditLog() throws Exception {
        // Get the manufacturerAuditLog
        restManufacturerAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewManufacturerAuditLog() throws Exception {
        // Initialize the database
        manufacturerAuditLogRepository.saveAndFlush(manufacturerAuditLog);

        int databaseSizeBeforeUpdate = manufacturerAuditLogRepository.findAll().size();

        // Update the manufacturerAuditLog
        ManufacturerAuditLog updatedManufacturerAuditLog = manufacturerAuditLogRepository.findById(manufacturerAuditLog.getId()).get();
        // Disconnect from session so that the updates on updatedManufacturerAuditLog are not directly saved in db
        em.detach(updatedManufacturerAuditLog);
        updatedManufacturerAuditLog
            .mnufcturerId(UPDATED_MNUFCTURER_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        ManufacturerAuditLogDTO manufacturerAuditLogDTO = manufacturerAuditLogMapper.toDto(updatedManufacturerAuditLog);

        restManufacturerAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, manufacturerAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(manufacturerAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the ManufacturerAuditLog in the database
        List<ManufacturerAuditLog> manufacturerAuditLogList = manufacturerAuditLogRepository.findAll();
        assertThat(manufacturerAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ManufacturerAuditLog testManufacturerAuditLog = manufacturerAuditLogList.get(manufacturerAuditLogList.size() - 1);
        assertThat(testManufacturerAuditLog.getMnufcturerId()).isEqualTo(UPDATED_MNUFCTURER_ID);
        assertThat(testManufacturerAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testManufacturerAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testManufacturerAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testManufacturerAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testManufacturerAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingManufacturerAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = manufacturerAuditLogRepository.findAll().size();
        manufacturerAuditLog.setId(count.incrementAndGet());

        // Create the ManufacturerAuditLog
        ManufacturerAuditLogDTO manufacturerAuditLogDTO = manufacturerAuditLogMapper.toDto(manufacturerAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restManufacturerAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, manufacturerAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(manufacturerAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ManufacturerAuditLog in the database
        List<ManufacturerAuditLog> manufacturerAuditLogList = manufacturerAuditLogRepository.findAll();
        assertThat(manufacturerAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchManufacturerAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = manufacturerAuditLogRepository.findAll().size();
        manufacturerAuditLog.setId(count.incrementAndGet());

        // Create the ManufacturerAuditLog
        ManufacturerAuditLogDTO manufacturerAuditLogDTO = manufacturerAuditLogMapper.toDto(manufacturerAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restManufacturerAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(manufacturerAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ManufacturerAuditLog in the database
        List<ManufacturerAuditLog> manufacturerAuditLogList = manufacturerAuditLogRepository.findAll();
        assertThat(manufacturerAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamManufacturerAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = manufacturerAuditLogRepository.findAll().size();
        manufacturerAuditLog.setId(count.incrementAndGet());

        // Create the ManufacturerAuditLog
        ManufacturerAuditLogDTO manufacturerAuditLogDTO = manufacturerAuditLogMapper.toDto(manufacturerAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restManufacturerAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(manufacturerAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ManufacturerAuditLog in the database
        List<ManufacturerAuditLog> manufacturerAuditLogList = manufacturerAuditLogRepository.findAll();
        assertThat(manufacturerAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateManufacturerAuditLogWithPatch() throws Exception {
        // Initialize the database
        manufacturerAuditLogRepository.saveAndFlush(manufacturerAuditLog);

        int databaseSizeBeforeUpdate = manufacturerAuditLogRepository.findAll().size();

        // Update the manufacturerAuditLog using partial update
        ManufacturerAuditLog partialUpdatedManufacturerAuditLog = new ManufacturerAuditLog();
        partialUpdatedManufacturerAuditLog.setId(manufacturerAuditLog.getId());

        partialUpdatedManufacturerAuditLog.dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restManufacturerAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedManufacturerAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedManufacturerAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ManufacturerAuditLog in the database
        List<ManufacturerAuditLog> manufacturerAuditLogList = manufacturerAuditLogRepository.findAll();
        assertThat(manufacturerAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ManufacturerAuditLog testManufacturerAuditLog = manufacturerAuditLogList.get(manufacturerAuditLogList.size() - 1);
        assertThat(testManufacturerAuditLog.getMnufcturerId()).isEqualTo(DEFAULT_MNUFCTURER_ID);
        assertThat(testManufacturerAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testManufacturerAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testManufacturerAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testManufacturerAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testManufacturerAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateManufacturerAuditLogWithPatch() throws Exception {
        // Initialize the database
        manufacturerAuditLogRepository.saveAndFlush(manufacturerAuditLog);

        int databaseSizeBeforeUpdate = manufacturerAuditLogRepository.findAll().size();

        // Update the manufacturerAuditLog using partial update
        ManufacturerAuditLog partialUpdatedManufacturerAuditLog = new ManufacturerAuditLog();
        partialUpdatedManufacturerAuditLog.setId(manufacturerAuditLog.getId());

        partialUpdatedManufacturerAuditLog
            .mnufcturerId(UPDATED_MNUFCTURER_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restManufacturerAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedManufacturerAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedManufacturerAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ManufacturerAuditLog in the database
        List<ManufacturerAuditLog> manufacturerAuditLogList = manufacturerAuditLogRepository.findAll();
        assertThat(manufacturerAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ManufacturerAuditLog testManufacturerAuditLog = manufacturerAuditLogList.get(manufacturerAuditLogList.size() - 1);
        assertThat(testManufacturerAuditLog.getMnufcturerId()).isEqualTo(UPDATED_MNUFCTURER_ID);
        assertThat(testManufacturerAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testManufacturerAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testManufacturerAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testManufacturerAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testManufacturerAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingManufacturerAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = manufacturerAuditLogRepository.findAll().size();
        manufacturerAuditLog.setId(count.incrementAndGet());

        // Create the ManufacturerAuditLog
        ManufacturerAuditLogDTO manufacturerAuditLogDTO = manufacturerAuditLogMapper.toDto(manufacturerAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restManufacturerAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, manufacturerAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(manufacturerAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ManufacturerAuditLog in the database
        List<ManufacturerAuditLog> manufacturerAuditLogList = manufacturerAuditLogRepository.findAll();
        assertThat(manufacturerAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchManufacturerAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = manufacturerAuditLogRepository.findAll().size();
        manufacturerAuditLog.setId(count.incrementAndGet());

        // Create the ManufacturerAuditLog
        ManufacturerAuditLogDTO manufacturerAuditLogDTO = manufacturerAuditLogMapper.toDto(manufacturerAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restManufacturerAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(manufacturerAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ManufacturerAuditLog in the database
        List<ManufacturerAuditLog> manufacturerAuditLogList = manufacturerAuditLogRepository.findAll();
        assertThat(manufacturerAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamManufacturerAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = manufacturerAuditLogRepository.findAll().size();
        manufacturerAuditLog.setId(count.incrementAndGet());

        // Create the ManufacturerAuditLog
        ManufacturerAuditLogDTO manufacturerAuditLogDTO = manufacturerAuditLogMapper.toDto(manufacturerAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restManufacturerAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(manufacturerAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ManufacturerAuditLog in the database
        List<ManufacturerAuditLog> manufacturerAuditLogList = manufacturerAuditLogRepository.findAll();
        assertThat(manufacturerAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteManufacturerAuditLog() throws Exception {
        // Initialize the database
        manufacturerAuditLogRepository.saveAndFlush(manufacturerAuditLog);

        int databaseSizeBeforeDelete = manufacturerAuditLogRepository.findAll().size();

        // Delete the manufacturerAuditLog
        restManufacturerAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, manufacturerAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ManufacturerAuditLog> manufacturerAuditLogList = manufacturerAuditLogRepository.findAll();
        assertThat(manufacturerAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
