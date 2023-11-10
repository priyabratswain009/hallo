package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ItemMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.ItemMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemMasterAuditLogMapper;
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
 * Integration tests for the {@link ItemMasterAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemMasterAuditLogResourceIT {

    private static final Long DEFAULT_ITM_ID = 1L;
    private static final Long UPDATED_ITM_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/item-master-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemMasterAuditLogRepository itemMasterAuditLogRepository;

    @Autowired
    private ItemMasterAuditLogMapper itemMasterAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemMasterAuditLogMockMvc;

    private ItemMasterAuditLog itemMasterAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemMasterAuditLog createEntity(EntityManager em) {
        ItemMasterAuditLog itemMasterAuditLog = new ItemMasterAuditLog()
            .itmId(DEFAULT_ITM_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return itemMasterAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemMasterAuditLog createUpdatedEntity(EntityManager em) {
        ItemMasterAuditLog itemMasterAuditLog = new ItemMasterAuditLog()
            .itmId(UPDATED_ITM_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return itemMasterAuditLog;
    }

    @BeforeEach
    public void initTest() {
        itemMasterAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createItemMasterAuditLog() throws Exception {
        int databaseSizeBeforeCreate = itemMasterAuditLogRepository.findAll().size();
        // Create the ItemMasterAuditLog
        ItemMasterAuditLogDTO itemMasterAuditLogDTO = itemMasterAuditLogMapper.toDto(itemMasterAuditLog);
        restItemMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemMasterAuditLog in the database
        List<ItemMasterAuditLog> itemMasterAuditLogList = itemMasterAuditLogRepository.findAll();
        assertThat(itemMasterAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        ItemMasterAuditLog testItemMasterAuditLog = itemMasterAuditLogList.get(itemMasterAuditLogList.size() - 1);
        assertThat(testItemMasterAuditLog.getItmId()).isEqualTo(DEFAULT_ITM_ID);
        assertThat(testItemMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testItemMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testItemMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testItemMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testItemMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createItemMasterAuditLogWithExistingId() throws Exception {
        // Create the ItemMasterAuditLog with an existing ID
        itemMasterAuditLog.setId(1L);
        ItemMasterAuditLogDTO itemMasterAuditLogDTO = itemMasterAuditLogMapper.toDto(itemMasterAuditLog);

        int databaseSizeBeforeCreate = itemMasterAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemMasterAuditLog in the database
        List<ItemMasterAuditLog> itemMasterAuditLogList = itemMasterAuditLogRepository.findAll();
        assertThat(itemMasterAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllItemMasterAuditLogs() throws Exception {
        // Initialize the database
        itemMasterAuditLogRepository.saveAndFlush(itemMasterAuditLog);

        // Get all the itemMasterAuditLogList
        restItemMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemMasterAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].itmId").value(hasItem(DEFAULT_ITM_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getItemMasterAuditLog() throws Exception {
        // Initialize the database
        itemMasterAuditLogRepository.saveAndFlush(itemMasterAuditLog);

        // Get the itemMasterAuditLog
        restItemMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, itemMasterAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(itemMasterAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.itmId").value(DEFAULT_ITM_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingItemMasterAuditLog() throws Exception {
        // Get the itemMasterAuditLog
        restItemMasterAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingItemMasterAuditLog() throws Exception {
        // Initialize the database
        itemMasterAuditLogRepository.saveAndFlush(itemMasterAuditLog);

        int databaseSizeBeforeUpdate = itemMasterAuditLogRepository.findAll().size();

        // Update the itemMasterAuditLog
        ItemMasterAuditLog updatedItemMasterAuditLog = itemMasterAuditLogRepository.findById(itemMasterAuditLog.getId()).get();
        // Disconnect from session so that the updates on updatedItemMasterAuditLog are not directly saved in db
        em.detach(updatedItemMasterAuditLog);
        updatedItemMasterAuditLog
            .itmId(UPDATED_ITM_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        ItemMasterAuditLogDTO itemMasterAuditLogDTO = itemMasterAuditLogMapper.toDto(updatedItemMasterAuditLog);

        restItemMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the ItemMasterAuditLog in the database
        List<ItemMasterAuditLog> itemMasterAuditLogList = itemMasterAuditLogRepository.findAll();
        assertThat(itemMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemMasterAuditLog testItemMasterAuditLog = itemMasterAuditLogList.get(itemMasterAuditLogList.size() - 1);
        assertThat(testItemMasterAuditLog.getItmId()).isEqualTo(UPDATED_ITM_ID);
        assertThat(testItemMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testItemMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testItemMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testItemMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingItemMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemMasterAuditLogRepository.findAll().size();
        itemMasterAuditLog.setId(count.incrementAndGet());

        // Create the ItemMasterAuditLog
        ItemMasterAuditLogDTO itemMasterAuditLogDTO = itemMasterAuditLogMapper.toDto(itemMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemMasterAuditLog in the database
        List<ItemMasterAuditLog> itemMasterAuditLogList = itemMasterAuditLogRepository.findAll();
        assertThat(itemMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItemMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemMasterAuditLogRepository.findAll().size();
        itemMasterAuditLog.setId(count.incrementAndGet());

        // Create the ItemMasterAuditLog
        ItemMasterAuditLogDTO itemMasterAuditLogDTO = itemMasterAuditLogMapper.toDto(itemMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemMasterAuditLog in the database
        List<ItemMasterAuditLog> itemMasterAuditLogList = itemMasterAuditLogRepository.findAll();
        assertThat(itemMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItemMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemMasterAuditLogRepository.findAll().size();
        itemMasterAuditLog.setId(count.incrementAndGet());

        // Create the ItemMasterAuditLog
        ItemMasterAuditLogDTO itemMasterAuditLogDTO = itemMasterAuditLogMapper.toDto(itemMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemMasterAuditLog in the database
        List<ItemMasterAuditLog> itemMasterAuditLogList = itemMasterAuditLogRepository.findAll();
        assertThat(itemMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        itemMasterAuditLogRepository.saveAndFlush(itemMasterAuditLog);

        int databaseSizeBeforeUpdate = itemMasterAuditLogRepository.findAll().size();

        // Update the itemMasterAuditLog using partial update
        ItemMasterAuditLog partialUpdatedItemMasterAuditLog = new ItemMasterAuditLog();
        partialUpdatedItemMasterAuditLog.setId(itemMasterAuditLog.getId());

        partialUpdatedItemMasterAuditLog.itmId(UPDATED_ITM_ID).oldRowData(UPDATED_OLD_ROW_DATA).dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restItemMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ItemMasterAuditLog in the database
        List<ItemMasterAuditLog> itemMasterAuditLogList = itemMasterAuditLogRepository.findAll();
        assertThat(itemMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemMasterAuditLog testItemMasterAuditLog = itemMasterAuditLogList.get(itemMasterAuditLogList.size() - 1);
        assertThat(testItemMasterAuditLog.getItmId()).isEqualTo(UPDATED_ITM_ID);
        assertThat(testItemMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testItemMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testItemMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testItemMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateItemMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        itemMasterAuditLogRepository.saveAndFlush(itemMasterAuditLog);

        int databaseSizeBeforeUpdate = itemMasterAuditLogRepository.findAll().size();

        // Update the itemMasterAuditLog using partial update
        ItemMasterAuditLog partialUpdatedItemMasterAuditLog = new ItemMasterAuditLog();
        partialUpdatedItemMasterAuditLog.setId(itemMasterAuditLog.getId());

        partialUpdatedItemMasterAuditLog
            .itmId(UPDATED_ITM_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restItemMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ItemMasterAuditLog in the database
        List<ItemMasterAuditLog> itemMasterAuditLogList = itemMasterAuditLogRepository.findAll();
        assertThat(itemMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemMasterAuditLog testItemMasterAuditLog = itemMasterAuditLogList.get(itemMasterAuditLogList.size() - 1);
        assertThat(testItemMasterAuditLog.getItmId()).isEqualTo(UPDATED_ITM_ID);
        assertThat(testItemMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testItemMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testItemMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testItemMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingItemMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemMasterAuditLogRepository.findAll().size();
        itemMasterAuditLog.setId(count.incrementAndGet());

        // Create the ItemMasterAuditLog
        ItemMasterAuditLogDTO itemMasterAuditLogDTO = itemMasterAuditLogMapper.toDto(itemMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itemMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemMasterAuditLog in the database
        List<ItemMasterAuditLog> itemMasterAuditLogList = itemMasterAuditLogRepository.findAll();
        assertThat(itemMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItemMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemMasterAuditLogRepository.findAll().size();
        itemMasterAuditLog.setId(count.incrementAndGet());

        // Create the ItemMasterAuditLog
        ItemMasterAuditLogDTO itemMasterAuditLogDTO = itemMasterAuditLogMapper.toDto(itemMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemMasterAuditLog in the database
        List<ItemMasterAuditLog> itemMasterAuditLogList = itemMasterAuditLogRepository.findAll();
        assertThat(itemMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItemMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemMasterAuditLogRepository.findAll().size();
        itemMasterAuditLog.setId(count.incrementAndGet());

        // Create the ItemMasterAuditLog
        ItemMasterAuditLogDTO itemMasterAuditLogDTO = itemMasterAuditLogMapper.toDto(itemMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemMasterAuditLog in the database
        List<ItemMasterAuditLog> itemMasterAuditLogList = itemMasterAuditLogRepository.findAll();
        assertThat(itemMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItemMasterAuditLog() throws Exception {
        // Initialize the database
        itemMasterAuditLogRepository.saveAndFlush(itemMasterAuditLog);

        int databaseSizeBeforeDelete = itemMasterAuditLogRepository.findAll().size();

        // Delete the itemMasterAuditLog
        restItemMasterAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, itemMasterAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemMasterAuditLog> itemMasterAuditLogList = itemMasterAuditLogRepository.findAll();
        assertThat(itemMasterAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
