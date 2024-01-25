package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ItemLocationAuditLog;
import com.sunknowledge.dme.rcm.repository.ItemLocationAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemLocationAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemLocationAuditLogMapper;
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
 * Integration tests for the {@link ItemLocationAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemLocationAuditLogResourceIT {

    private static final Long DEFAULT_ITM_LCTION_ID = 1L;
    private static final Long UPDATED_ITM_LCTION_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/item-location-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemLocationAuditLogRepository itemLocationAuditLogRepository;

    @Autowired
    private ItemLocationAuditLogMapper itemLocationAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemLocationAuditLogMockMvc;

    private ItemLocationAuditLog itemLocationAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemLocationAuditLog createEntity(EntityManager em) {
        ItemLocationAuditLog itemLocationAuditLog = new ItemLocationAuditLog()
            .itmLctionId(DEFAULT_ITM_LCTION_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return itemLocationAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemLocationAuditLog createUpdatedEntity(EntityManager em) {
        ItemLocationAuditLog itemLocationAuditLog = new ItemLocationAuditLog()
            .itmLctionId(UPDATED_ITM_LCTION_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return itemLocationAuditLog;
    }

    @BeforeEach
    public void initTest() {
        itemLocationAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createItemLocationAuditLog() throws Exception {
        int databaseSizeBeforeCreate = itemLocationAuditLogRepository.findAll().size();
        // Create the ItemLocationAuditLog
        ItemLocationAuditLogDTO itemLocationAuditLogDTO = itemLocationAuditLogMapper.toDto(itemLocationAuditLog);
        restItemLocationAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemLocationAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemLocationAuditLog in the database
        List<ItemLocationAuditLog> itemLocationAuditLogList = itemLocationAuditLogRepository.findAll();
        assertThat(itemLocationAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        ItemLocationAuditLog testItemLocationAuditLog = itemLocationAuditLogList.get(itemLocationAuditLogList.size() - 1);
        assertThat(testItemLocationAuditLog.getItmLctionId()).isEqualTo(DEFAULT_ITM_LCTION_ID);
        assertThat(testItemLocationAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testItemLocationAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testItemLocationAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testItemLocationAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testItemLocationAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createItemLocationAuditLogWithExistingId() throws Exception {
        // Create the ItemLocationAuditLog with an existing ID
        itemLocationAuditLog.setId(1L);
        ItemLocationAuditLogDTO itemLocationAuditLogDTO = itemLocationAuditLogMapper.toDto(itemLocationAuditLog);

        int databaseSizeBeforeCreate = itemLocationAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemLocationAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemLocationAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemLocationAuditLog in the database
        List<ItemLocationAuditLog> itemLocationAuditLogList = itemLocationAuditLogRepository.findAll();
        assertThat(itemLocationAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllItemLocationAuditLogs() throws Exception {
        // Initialize the database
        itemLocationAuditLogRepository.saveAndFlush(itemLocationAuditLog);

        // Get all the itemLocationAuditLogList
        restItemLocationAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemLocationAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].itmLctionId").value(hasItem(DEFAULT_ITM_LCTION_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getItemLocationAuditLog() throws Exception {
        // Initialize the database
        itemLocationAuditLogRepository.saveAndFlush(itemLocationAuditLog);

        // Get the itemLocationAuditLog
        restItemLocationAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, itemLocationAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(itemLocationAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.itmLctionId").value(DEFAULT_ITM_LCTION_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingItemLocationAuditLog() throws Exception {
        // Get the itemLocationAuditLog
        restItemLocationAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingItemLocationAuditLog() throws Exception {
        // Initialize the database
        itemLocationAuditLogRepository.saveAndFlush(itemLocationAuditLog);

        int databaseSizeBeforeUpdate = itemLocationAuditLogRepository.findAll().size();

        // Update the itemLocationAuditLog
        ItemLocationAuditLog updatedItemLocationAuditLog = itemLocationAuditLogRepository.findById(itemLocationAuditLog.getId()).get();
        // Disconnect from session so that the updates on updatedItemLocationAuditLog are not directly saved in db
        em.detach(updatedItemLocationAuditLog);
        updatedItemLocationAuditLog
            .itmLctionId(UPDATED_ITM_LCTION_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        ItemLocationAuditLogDTO itemLocationAuditLogDTO = itemLocationAuditLogMapper.toDto(updatedItemLocationAuditLog);

        restItemLocationAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemLocationAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemLocationAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the ItemLocationAuditLog in the database
        List<ItemLocationAuditLog> itemLocationAuditLogList = itemLocationAuditLogRepository.findAll();
        assertThat(itemLocationAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemLocationAuditLog testItemLocationAuditLog = itemLocationAuditLogList.get(itemLocationAuditLogList.size() - 1);
        assertThat(testItemLocationAuditLog.getItmLctionId()).isEqualTo(UPDATED_ITM_LCTION_ID);
        assertThat(testItemLocationAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemLocationAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testItemLocationAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testItemLocationAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testItemLocationAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingItemLocationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemLocationAuditLogRepository.findAll().size();
        itemLocationAuditLog.setId(count.incrementAndGet());

        // Create the ItemLocationAuditLog
        ItemLocationAuditLogDTO itemLocationAuditLogDTO = itemLocationAuditLogMapper.toDto(itemLocationAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemLocationAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemLocationAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemLocationAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemLocationAuditLog in the database
        List<ItemLocationAuditLog> itemLocationAuditLogList = itemLocationAuditLogRepository.findAll();
        assertThat(itemLocationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItemLocationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemLocationAuditLogRepository.findAll().size();
        itemLocationAuditLog.setId(count.incrementAndGet());

        // Create the ItemLocationAuditLog
        ItemLocationAuditLogDTO itemLocationAuditLogDTO = itemLocationAuditLogMapper.toDto(itemLocationAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemLocationAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemLocationAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemLocationAuditLog in the database
        List<ItemLocationAuditLog> itemLocationAuditLogList = itemLocationAuditLogRepository.findAll();
        assertThat(itemLocationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItemLocationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemLocationAuditLogRepository.findAll().size();
        itemLocationAuditLog.setId(count.incrementAndGet());

        // Create the ItemLocationAuditLog
        ItemLocationAuditLogDTO itemLocationAuditLogDTO = itemLocationAuditLogMapper.toDto(itemLocationAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemLocationAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemLocationAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemLocationAuditLog in the database
        List<ItemLocationAuditLog> itemLocationAuditLogList = itemLocationAuditLogRepository.findAll();
        assertThat(itemLocationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemLocationAuditLogWithPatch() throws Exception {
        // Initialize the database
        itemLocationAuditLogRepository.saveAndFlush(itemLocationAuditLog);

        int databaseSizeBeforeUpdate = itemLocationAuditLogRepository.findAll().size();

        // Update the itemLocationAuditLog using partial update
        ItemLocationAuditLog partialUpdatedItemLocationAuditLog = new ItemLocationAuditLog();
        partialUpdatedItemLocationAuditLog.setId(itemLocationAuditLog.getId());

        partialUpdatedItemLocationAuditLog.oldRowData(UPDATED_OLD_ROW_DATA);

        restItemLocationAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemLocationAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemLocationAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ItemLocationAuditLog in the database
        List<ItemLocationAuditLog> itemLocationAuditLogList = itemLocationAuditLogRepository.findAll();
        assertThat(itemLocationAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemLocationAuditLog testItemLocationAuditLog = itemLocationAuditLogList.get(itemLocationAuditLogList.size() - 1);
        assertThat(testItemLocationAuditLog.getItmLctionId()).isEqualTo(DEFAULT_ITM_LCTION_ID);
        assertThat(testItemLocationAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemLocationAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testItemLocationAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testItemLocationAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testItemLocationAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateItemLocationAuditLogWithPatch() throws Exception {
        // Initialize the database
        itemLocationAuditLogRepository.saveAndFlush(itemLocationAuditLog);

        int databaseSizeBeforeUpdate = itemLocationAuditLogRepository.findAll().size();

        // Update the itemLocationAuditLog using partial update
        ItemLocationAuditLog partialUpdatedItemLocationAuditLog = new ItemLocationAuditLog();
        partialUpdatedItemLocationAuditLog.setId(itemLocationAuditLog.getId());

        partialUpdatedItemLocationAuditLog
            .itmLctionId(UPDATED_ITM_LCTION_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restItemLocationAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemLocationAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemLocationAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ItemLocationAuditLog in the database
        List<ItemLocationAuditLog> itemLocationAuditLogList = itemLocationAuditLogRepository.findAll();
        assertThat(itemLocationAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemLocationAuditLog testItemLocationAuditLog = itemLocationAuditLogList.get(itemLocationAuditLogList.size() - 1);
        assertThat(testItemLocationAuditLog.getItmLctionId()).isEqualTo(UPDATED_ITM_LCTION_ID);
        assertThat(testItemLocationAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemLocationAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testItemLocationAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testItemLocationAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testItemLocationAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingItemLocationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemLocationAuditLogRepository.findAll().size();
        itemLocationAuditLog.setId(count.incrementAndGet());

        // Create the ItemLocationAuditLog
        ItemLocationAuditLogDTO itemLocationAuditLogDTO = itemLocationAuditLogMapper.toDto(itemLocationAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemLocationAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itemLocationAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemLocationAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemLocationAuditLog in the database
        List<ItemLocationAuditLog> itemLocationAuditLogList = itemLocationAuditLogRepository.findAll();
        assertThat(itemLocationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItemLocationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemLocationAuditLogRepository.findAll().size();
        itemLocationAuditLog.setId(count.incrementAndGet());

        // Create the ItemLocationAuditLog
        ItemLocationAuditLogDTO itemLocationAuditLogDTO = itemLocationAuditLogMapper.toDto(itemLocationAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemLocationAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemLocationAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemLocationAuditLog in the database
        List<ItemLocationAuditLog> itemLocationAuditLogList = itemLocationAuditLogRepository.findAll();
        assertThat(itemLocationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItemLocationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemLocationAuditLogRepository.findAll().size();
        itemLocationAuditLog.setId(count.incrementAndGet());

        // Create the ItemLocationAuditLog
        ItemLocationAuditLogDTO itemLocationAuditLogDTO = itemLocationAuditLogMapper.toDto(itemLocationAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemLocationAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemLocationAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemLocationAuditLog in the database
        List<ItemLocationAuditLog> itemLocationAuditLogList = itemLocationAuditLogRepository.findAll();
        assertThat(itemLocationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItemLocationAuditLog() throws Exception {
        // Initialize the database
        itemLocationAuditLogRepository.saveAndFlush(itemLocationAuditLog);

        int databaseSizeBeforeDelete = itemLocationAuditLogRepository.findAll().size();

        // Delete the itemLocationAuditLog
        restItemLocationAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, itemLocationAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemLocationAuditLog> itemLocationAuditLogList = itemLocationAuditLogRepository.findAll();
        assertThat(itemLocationAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
