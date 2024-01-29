package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ItemTypeAuditLog;
import com.sunknowledge.dme.rcm.repository.ItemTypeAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemTypeAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemTypeAuditLogMapper;
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
 * Integration tests for the {@link ItemTypeAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemTypeAuditLogResourceIT {

    private static final Long DEFAULT_ITM_TYP_ID = 1L;
    private static final Long UPDATED_ITM_TYP_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/item-type-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemTypeAuditLogRepository itemTypeAuditLogRepository;

    @Autowired
    private ItemTypeAuditLogMapper itemTypeAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemTypeAuditLogMockMvc;

    private ItemTypeAuditLog itemTypeAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemTypeAuditLog createEntity(EntityManager em) {
        ItemTypeAuditLog itemTypeAuditLog = new ItemTypeAuditLog()
            .itmTypId(DEFAULT_ITM_TYP_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return itemTypeAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemTypeAuditLog createUpdatedEntity(EntityManager em) {
        ItemTypeAuditLog itemTypeAuditLog = new ItemTypeAuditLog()
            .itmTypId(UPDATED_ITM_TYP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return itemTypeAuditLog;
    }

    @BeforeEach
    public void initTest() {
        itemTypeAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createItemTypeAuditLog() throws Exception {
        int databaseSizeBeforeCreate = itemTypeAuditLogRepository.findAll().size();
        // Create the ItemTypeAuditLog
        ItemTypeAuditLogDTO itemTypeAuditLogDTO = itemTypeAuditLogMapper.toDto(itemTypeAuditLog);
        restItemTypeAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemTypeAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemTypeAuditLog in the database
        List<ItemTypeAuditLog> itemTypeAuditLogList = itemTypeAuditLogRepository.findAll();
        assertThat(itemTypeAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        ItemTypeAuditLog testItemTypeAuditLog = itemTypeAuditLogList.get(itemTypeAuditLogList.size() - 1);
        assertThat(testItemTypeAuditLog.getItmTypId()).isEqualTo(DEFAULT_ITM_TYP_ID);
        assertThat(testItemTypeAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testItemTypeAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testItemTypeAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testItemTypeAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testItemTypeAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createItemTypeAuditLogWithExistingId() throws Exception {
        // Create the ItemTypeAuditLog with an existing ID
        itemTypeAuditLog.setId(1L);
        ItemTypeAuditLogDTO itemTypeAuditLogDTO = itemTypeAuditLogMapper.toDto(itemTypeAuditLog);

        int databaseSizeBeforeCreate = itemTypeAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemTypeAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemTypeAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemTypeAuditLog in the database
        List<ItemTypeAuditLog> itemTypeAuditLogList = itemTypeAuditLogRepository.findAll();
        assertThat(itemTypeAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllItemTypeAuditLogs() throws Exception {
        // Initialize the database
        itemTypeAuditLogRepository.saveAndFlush(itemTypeAuditLog);

        // Get all the itemTypeAuditLogList
        restItemTypeAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemTypeAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].itmTypId").value(hasItem(DEFAULT_ITM_TYP_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getItemTypeAuditLog() throws Exception {
        // Initialize the database
        itemTypeAuditLogRepository.saveAndFlush(itemTypeAuditLog);

        // Get the itemTypeAuditLog
        restItemTypeAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, itemTypeAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(itemTypeAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.itmTypId").value(DEFAULT_ITM_TYP_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingItemTypeAuditLog() throws Exception {
        // Get the itemTypeAuditLog
        restItemTypeAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewItemTypeAuditLog() throws Exception {
        // Initialize the database
        itemTypeAuditLogRepository.saveAndFlush(itemTypeAuditLog);

        int databaseSizeBeforeUpdate = itemTypeAuditLogRepository.findAll().size();

        // Update the itemTypeAuditLog
        ItemTypeAuditLog updatedItemTypeAuditLog = itemTypeAuditLogRepository.findById(itemTypeAuditLog.getId()).get();
        // Disconnect from session so that the updates on updatedItemTypeAuditLog are not directly saved in db
        em.detach(updatedItemTypeAuditLog);
        updatedItemTypeAuditLog
            .itmTypId(UPDATED_ITM_TYP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        ItemTypeAuditLogDTO itemTypeAuditLogDTO = itemTypeAuditLogMapper.toDto(updatedItemTypeAuditLog);

        restItemTypeAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemTypeAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemTypeAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the ItemTypeAuditLog in the database
        List<ItemTypeAuditLog> itemTypeAuditLogList = itemTypeAuditLogRepository.findAll();
        assertThat(itemTypeAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemTypeAuditLog testItemTypeAuditLog = itemTypeAuditLogList.get(itemTypeAuditLogList.size() - 1);
        assertThat(testItemTypeAuditLog.getItmTypId()).isEqualTo(UPDATED_ITM_TYP_ID);
        assertThat(testItemTypeAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemTypeAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testItemTypeAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testItemTypeAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testItemTypeAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingItemTypeAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemTypeAuditLogRepository.findAll().size();
        itemTypeAuditLog.setId(count.incrementAndGet());

        // Create the ItemTypeAuditLog
        ItemTypeAuditLogDTO itemTypeAuditLogDTO = itemTypeAuditLogMapper.toDto(itemTypeAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemTypeAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemTypeAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemTypeAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemTypeAuditLog in the database
        List<ItemTypeAuditLog> itemTypeAuditLogList = itemTypeAuditLogRepository.findAll();
        assertThat(itemTypeAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItemTypeAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemTypeAuditLogRepository.findAll().size();
        itemTypeAuditLog.setId(count.incrementAndGet());

        // Create the ItemTypeAuditLog
        ItemTypeAuditLogDTO itemTypeAuditLogDTO = itemTypeAuditLogMapper.toDto(itemTypeAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemTypeAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemTypeAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemTypeAuditLog in the database
        List<ItemTypeAuditLog> itemTypeAuditLogList = itemTypeAuditLogRepository.findAll();
        assertThat(itemTypeAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItemTypeAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemTypeAuditLogRepository.findAll().size();
        itemTypeAuditLog.setId(count.incrementAndGet());

        // Create the ItemTypeAuditLog
        ItemTypeAuditLogDTO itemTypeAuditLogDTO = itemTypeAuditLogMapper.toDto(itemTypeAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemTypeAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemTypeAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemTypeAuditLog in the database
        List<ItemTypeAuditLog> itemTypeAuditLogList = itemTypeAuditLogRepository.findAll();
        assertThat(itemTypeAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemTypeAuditLogWithPatch() throws Exception {
        // Initialize the database
        itemTypeAuditLogRepository.saveAndFlush(itemTypeAuditLog);

        int databaseSizeBeforeUpdate = itemTypeAuditLogRepository.findAll().size();

        // Update the itemTypeAuditLog using partial update
        ItemTypeAuditLog partialUpdatedItemTypeAuditLog = new ItemTypeAuditLog();
        partialUpdatedItemTypeAuditLog.setId(itemTypeAuditLog.getId());

        partialUpdatedItemTypeAuditLog.itmTypId(UPDATED_ITM_TYP_ID).dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restItemTypeAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemTypeAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemTypeAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ItemTypeAuditLog in the database
        List<ItemTypeAuditLog> itemTypeAuditLogList = itemTypeAuditLogRepository.findAll();
        assertThat(itemTypeAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemTypeAuditLog testItemTypeAuditLog = itemTypeAuditLogList.get(itemTypeAuditLogList.size() - 1);
        assertThat(testItemTypeAuditLog.getItmTypId()).isEqualTo(UPDATED_ITM_TYP_ID);
        assertThat(testItemTypeAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testItemTypeAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testItemTypeAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testItemTypeAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testItemTypeAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateItemTypeAuditLogWithPatch() throws Exception {
        // Initialize the database
        itemTypeAuditLogRepository.saveAndFlush(itemTypeAuditLog);

        int databaseSizeBeforeUpdate = itemTypeAuditLogRepository.findAll().size();

        // Update the itemTypeAuditLog using partial update
        ItemTypeAuditLog partialUpdatedItemTypeAuditLog = new ItemTypeAuditLog();
        partialUpdatedItemTypeAuditLog.setId(itemTypeAuditLog.getId());

        partialUpdatedItemTypeAuditLog
            .itmTypId(UPDATED_ITM_TYP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restItemTypeAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemTypeAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemTypeAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ItemTypeAuditLog in the database
        List<ItemTypeAuditLog> itemTypeAuditLogList = itemTypeAuditLogRepository.findAll();
        assertThat(itemTypeAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemTypeAuditLog testItemTypeAuditLog = itemTypeAuditLogList.get(itemTypeAuditLogList.size() - 1);
        assertThat(testItemTypeAuditLog.getItmTypId()).isEqualTo(UPDATED_ITM_TYP_ID);
        assertThat(testItemTypeAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemTypeAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testItemTypeAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testItemTypeAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testItemTypeAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingItemTypeAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemTypeAuditLogRepository.findAll().size();
        itemTypeAuditLog.setId(count.incrementAndGet());

        // Create the ItemTypeAuditLog
        ItemTypeAuditLogDTO itemTypeAuditLogDTO = itemTypeAuditLogMapper.toDto(itemTypeAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemTypeAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itemTypeAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemTypeAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemTypeAuditLog in the database
        List<ItemTypeAuditLog> itemTypeAuditLogList = itemTypeAuditLogRepository.findAll();
        assertThat(itemTypeAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItemTypeAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemTypeAuditLogRepository.findAll().size();
        itemTypeAuditLog.setId(count.incrementAndGet());

        // Create the ItemTypeAuditLog
        ItemTypeAuditLogDTO itemTypeAuditLogDTO = itemTypeAuditLogMapper.toDto(itemTypeAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemTypeAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemTypeAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemTypeAuditLog in the database
        List<ItemTypeAuditLog> itemTypeAuditLogList = itemTypeAuditLogRepository.findAll();
        assertThat(itemTypeAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItemTypeAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemTypeAuditLogRepository.findAll().size();
        itemTypeAuditLog.setId(count.incrementAndGet());

        // Create the ItemTypeAuditLog
        ItemTypeAuditLogDTO itemTypeAuditLogDTO = itemTypeAuditLogMapper.toDto(itemTypeAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemTypeAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemTypeAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemTypeAuditLog in the database
        List<ItemTypeAuditLog> itemTypeAuditLogList = itemTypeAuditLogRepository.findAll();
        assertThat(itemTypeAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItemTypeAuditLog() throws Exception {
        // Initialize the database
        itemTypeAuditLogRepository.saveAndFlush(itemTypeAuditLog);

        int databaseSizeBeforeDelete = itemTypeAuditLogRepository.findAll().size();

        // Delete the itemTypeAuditLog
        restItemTypeAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, itemTypeAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemTypeAuditLog> itemTypeAuditLogList = itemTypeAuditLogRepository.findAll();
        assertThat(itemTypeAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
