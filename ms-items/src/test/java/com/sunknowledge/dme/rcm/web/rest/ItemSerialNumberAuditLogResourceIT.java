package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ItemSerialNumberAuditLog;
import com.sunknowledge.dme.rcm.repository.ItemSerialNumberAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemSerialNumberAuditLogMapper;
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
 * Integration tests for the {@link ItemSerialNumberAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemSerialNumberAuditLogResourceIT {

    private static final Long DEFAULT_ITM_SRIAL_NMBER_ID = 1L;
    private static final Long UPDATED_ITM_SRIAL_NMBER_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/item-serial-number-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemSerialNumberAuditLogRepository itemSerialNumberAuditLogRepository;

    @Autowired
    private ItemSerialNumberAuditLogMapper itemSerialNumberAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemSerialNumberAuditLogMockMvc;

    private ItemSerialNumberAuditLog itemSerialNumberAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemSerialNumberAuditLog createEntity(EntityManager em) {
        ItemSerialNumberAuditLog itemSerialNumberAuditLog = new ItemSerialNumberAuditLog()
            .itmSrialNmberId(DEFAULT_ITM_SRIAL_NMBER_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return itemSerialNumberAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemSerialNumberAuditLog createUpdatedEntity(EntityManager em) {
        ItemSerialNumberAuditLog itemSerialNumberAuditLog = new ItemSerialNumberAuditLog()
            .itmSrialNmberId(UPDATED_ITM_SRIAL_NMBER_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return itemSerialNumberAuditLog;
    }

    @BeforeEach
    public void initTest() {
        itemSerialNumberAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createItemSerialNumberAuditLog() throws Exception {
        int databaseSizeBeforeCreate = itemSerialNumberAuditLogRepository.findAll().size();
        // Create the ItemSerialNumberAuditLog
        ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO = itemSerialNumberAuditLogMapper.toDto(itemSerialNumberAuditLog);
        restItemSerialNumberAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemSerialNumberAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemSerialNumberAuditLog in the database
        List<ItemSerialNumberAuditLog> itemSerialNumberAuditLogList = itemSerialNumberAuditLogRepository.findAll();
        assertThat(itemSerialNumberAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        ItemSerialNumberAuditLog testItemSerialNumberAuditLog = itemSerialNumberAuditLogList.get(itemSerialNumberAuditLogList.size() - 1);
        assertThat(testItemSerialNumberAuditLog.getItmSrialNmberId()).isEqualTo(DEFAULT_ITM_SRIAL_NMBER_ID);
        assertThat(testItemSerialNumberAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testItemSerialNumberAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testItemSerialNumberAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testItemSerialNumberAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testItemSerialNumberAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createItemSerialNumberAuditLogWithExistingId() throws Exception {
        // Create the ItemSerialNumberAuditLog with an existing ID
        itemSerialNumberAuditLog.setId(1L);
        ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO = itemSerialNumberAuditLogMapper.toDto(itemSerialNumberAuditLog);

        int databaseSizeBeforeCreate = itemSerialNumberAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemSerialNumberAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemSerialNumberAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemSerialNumberAuditLog in the database
        List<ItemSerialNumberAuditLog> itemSerialNumberAuditLogList = itemSerialNumberAuditLogRepository.findAll();
        assertThat(itemSerialNumberAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllItemSerialNumberAuditLogs() throws Exception {
        // Initialize the database
        itemSerialNumberAuditLogRepository.saveAndFlush(itemSerialNumberAuditLog);

        // Get all the itemSerialNumberAuditLogList
        restItemSerialNumberAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemSerialNumberAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].itmSrialNmberId").value(hasItem(DEFAULT_ITM_SRIAL_NMBER_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getItemSerialNumberAuditLog() throws Exception {
        // Initialize the database
        itemSerialNumberAuditLogRepository.saveAndFlush(itemSerialNumberAuditLog);

        // Get the itemSerialNumberAuditLog
        restItemSerialNumberAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, itemSerialNumberAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(itemSerialNumberAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.itmSrialNmberId").value(DEFAULT_ITM_SRIAL_NMBER_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingItemSerialNumberAuditLog() throws Exception {
        // Get the itemSerialNumberAuditLog
        restItemSerialNumberAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewItemSerialNumberAuditLog() throws Exception {
        // Initialize the database
        itemSerialNumberAuditLogRepository.saveAndFlush(itemSerialNumberAuditLog);

        int databaseSizeBeforeUpdate = itemSerialNumberAuditLogRepository.findAll().size();

        // Update the itemSerialNumberAuditLog
        ItemSerialNumberAuditLog updatedItemSerialNumberAuditLog = itemSerialNumberAuditLogRepository
            .findById(itemSerialNumberAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedItemSerialNumberAuditLog are not directly saved in db
        em.detach(updatedItemSerialNumberAuditLog);
        updatedItemSerialNumberAuditLog
            .itmSrialNmberId(UPDATED_ITM_SRIAL_NMBER_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO = itemSerialNumberAuditLogMapper.toDto(updatedItemSerialNumberAuditLog);

        restItemSerialNumberAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemSerialNumberAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemSerialNumberAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the ItemSerialNumberAuditLog in the database
        List<ItemSerialNumberAuditLog> itemSerialNumberAuditLogList = itemSerialNumberAuditLogRepository.findAll();
        assertThat(itemSerialNumberAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemSerialNumberAuditLog testItemSerialNumberAuditLog = itemSerialNumberAuditLogList.get(itemSerialNumberAuditLogList.size() - 1);
        assertThat(testItemSerialNumberAuditLog.getItmSrialNmberId()).isEqualTo(UPDATED_ITM_SRIAL_NMBER_ID);
        assertThat(testItemSerialNumberAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemSerialNumberAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testItemSerialNumberAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testItemSerialNumberAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testItemSerialNumberAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingItemSerialNumberAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemSerialNumberAuditLogRepository.findAll().size();
        itemSerialNumberAuditLog.setId(count.incrementAndGet());

        // Create the ItemSerialNumberAuditLog
        ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO = itemSerialNumberAuditLogMapper.toDto(itemSerialNumberAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemSerialNumberAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemSerialNumberAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemSerialNumberAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemSerialNumberAuditLog in the database
        List<ItemSerialNumberAuditLog> itemSerialNumberAuditLogList = itemSerialNumberAuditLogRepository.findAll();
        assertThat(itemSerialNumberAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItemSerialNumberAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemSerialNumberAuditLogRepository.findAll().size();
        itemSerialNumberAuditLog.setId(count.incrementAndGet());

        // Create the ItemSerialNumberAuditLog
        ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO = itemSerialNumberAuditLogMapper.toDto(itemSerialNumberAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemSerialNumberAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemSerialNumberAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemSerialNumberAuditLog in the database
        List<ItemSerialNumberAuditLog> itemSerialNumberAuditLogList = itemSerialNumberAuditLogRepository.findAll();
        assertThat(itemSerialNumberAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItemSerialNumberAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemSerialNumberAuditLogRepository.findAll().size();
        itemSerialNumberAuditLog.setId(count.incrementAndGet());

        // Create the ItemSerialNumberAuditLog
        ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO = itemSerialNumberAuditLogMapper.toDto(itemSerialNumberAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemSerialNumberAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemSerialNumberAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemSerialNumberAuditLog in the database
        List<ItemSerialNumberAuditLog> itemSerialNumberAuditLogList = itemSerialNumberAuditLogRepository.findAll();
        assertThat(itemSerialNumberAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemSerialNumberAuditLogWithPatch() throws Exception {
        // Initialize the database
        itemSerialNumberAuditLogRepository.saveAndFlush(itemSerialNumberAuditLog);

        int databaseSizeBeforeUpdate = itemSerialNumberAuditLogRepository.findAll().size();

        // Update the itemSerialNumberAuditLog using partial update
        ItemSerialNumberAuditLog partialUpdatedItemSerialNumberAuditLog = new ItemSerialNumberAuditLog();
        partialUpdatedItemSerialNumberAuditLog.setId(itemSerialNumberAuditLog.getId());

        partialUpdatedItemSerialNumberAuditLog
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP);

        restItemSerialNumberAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemSerialNumberAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemSerialNumberAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ItemSerialNumberAuditLog in the database
        List<ItemSerialNumberAuditLog> itemSerialNumberAuditLogList = itemSerialNumberAuditLogRepository.findAll();
        assertThat(itemSerialNumberAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemSerialNumberAuditLog testItemSerialNumberAuditLog = itemSerialNumberAuditLogList.get(itemSerialNumberAuditLogList.size() - 1);
        assertThat(testItemSerialNumberAuditLog.getItmSrialNmberId()).isEqualTo(DEFAULT_ITM_SRIAL_NMBER_ID);
        assertThat(testItemSerialNumberAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemSerialNumberAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testItemSerialNumberAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testItemSerialNumberAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testItemSerialNumberAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateItemSerialNumberAuditLogWithPatch() throws Exception {
        // Initialize the database
        itemSerialNumberAuditLogRepository.saveAndFlush(itemSerialNumberAuditLog);

        int databaseSizeBeforeUpdate = itemSerialNumberAuditLogRepository.findAll().size();

        // Update the itemSerialNumberAuditLog using partial update
        ItemSerialNumberAuditLog partialUpdatedItemSerialNumberAuditLog = new ItemSerialNumberAuditLog();
        partialUpdatedItemSerialNumberAuditLog.setId(itemSerialNumberAuditLog.getId());

        partialUpdatedItemSerialNumberAuditLog
            .itmSrialNmberId(UPDATED_ITM_SRIAL_NMBER_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restItemSerialNumberAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemSerialNumberAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemSerialNumberAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ItemSerialNumberAuditLog in the database
        List<ItemSerialNumberAuditLog> itemSerialNumberAuditLogList = itemSerialNumberAuditLogRepository.findAll();
        assertThat(itemSerialNumberAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemSerialNumberAuditLog testItemSerialNumberAuditLog = itemSerialNumberAuditLogList.get(itemSerialNumberAuditLogList.size() - 1);
        assertThat(testItemSerialNumberAuditLog.getItmSrialNmberId()).isEqualTo(UPDATED_ITM_SRIAL_NMBER_ID);
        assertThat(testItemSerialNumberAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemSerialNumberAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testItemSerialNumberAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testItemSerialNumberAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testItemSerialNumberAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingItemSerialNumberAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemSerialNumberAuditLogRepository.findAll().size();
        itemSerialNumberAuditLog.setId(count.incrementAndGet());

        // Create the ItemSerialNumberAuditLog
        ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO = itemSerialNumberAuditLogMapper.toDto(itemSerialNumberAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemSerialNumberAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itemSerialNumberAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemSerialNumberAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemSerialNumberAuditLog in the database
        List<ItemSerialNumberAuditLog> itemSerialNumberAuditLogList = itemSerialNumberAuditLogRepository.findAll();
        assertThat(itemSerialNumberAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItemSerialNumberAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemSerialNumberAuditLogRepository.findAll().size();
        itemSerialNumberAuditLog.setId(count.incrementAndGet());

        // Create the ItemSerialNumberAuditLog
        ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO = itemSerialNumberAuditLogMapper.toDto(itemSerialNumberAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemSerialNumberAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemSerialNumberAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemSerialNumberAuditLog in the database
        List<ItemSerialNumberAuditLog> itemSerialNumberAuditLogList = itemSerialNumberAuditLogRepository.findAll();
        assertThat(itemSerialNumberAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItemSerialNumberAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemSerialNumberAuditLogRepository.findAll().size();
        itemSerialNumberAuditLog.setId(count.incrementAndGet());

        // Create the ItemSerialNumberAuditLog
        ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO = itemSerialNumberAuditLogMapper.toDto(itemSerialNumberAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemSerialNumberAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemSerialNumberAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemSerialNumberAuditLog in the database
        List<ItemSerialNumberAuditLog> itemSerialNumberAuditLogList = itemSerialNumberAuditLogRepository.findAll();
        assertThat(itemSerialNumberAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItemSerialNumberAuditLog() throws Exception {
        // Initialize the database
        itemSerialNumberAuditLogRepository.saveAndFlush(itemSerialNumberAuditLog);

        int databaseSizeBeforeDelete = itemSerialNumberAuditLogRepository.findAll().size();

        // Delete the itemSerialNumberAuditLog
        restItemSerialNumberAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, itemSerialNumberAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemSerialNumberAuditLog> itemSerialNumberAuditLogList = itemSerialNumberAuditLogRepository.findAll();
        assertThat(itemSerialNumberAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
