package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ItemGroupAuditLog;
import com.sunknowledge.dme.rcm.repository.ItemGroupAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemGroupAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemGroupAuditLogMapper;
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
 * Integration tests for the {@link ItemGroupAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemGroupAuditLogResourceIT {

    private static final Long DEFAULT_ITEM_GRP_ID = 1L;
    private static final Long UPDATED_ITEM_GRP_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/item-group-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemGroupAuditLogRepository itemGroupAuditLogRepository;

    @Autowired
    private ItemGroupAuditLogMapper itemGroupAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemGroupAuditLogMockMvc;

    private ItemGroupAuditLog itemGroupAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemGroupAuditLog createEntity(EntityManager em) {
        ItemGroupAuditLog itemGroupAuditLog = new ItemGroupAuditLog()
            .itemGrpId(DEFAULT_ITEM_GRP_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return itemGroupAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemGroupAuditLog createUpdatedEntity(EntityManager em) {
        ItemGroupAuditLog itemGroupAuditLog = new ItemGroupAuditLog()
            .itemGrpId(UPDATED_ITEM_GRP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return itemGroupAuditLog;
    }

    @BeforeEach
    public void initTest() {
        itemGroupAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createItemGroupAuditLog() throws Exception {
        int databaseSizeBeforeCreate = itemGroupAuditLogRepository.findAll().size();
        // Create the ItemGroupAuditLog
        ItemGroupAuditLogDTO itemGroupAuditLogDTO = itemGroupAuditLogMapper.toDto(itemGroupAuditLog);
        restItemGroupAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemGroupAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemGroupAuditLog in the database
        List<ItemGroupAuditLog> itemGroupAuditLogList = itemGroupAuditLogRepository.findAll();
        assertThat(itemGroupAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        ItemGroupAuditLog testItemGroupAuditLog = itemGroupAuditLogList.get(itemGroupAuditLogList.size() - 1);
        assertThat(testItemGroupAuditLog.getItemGrpId()).isEqualTo(DEFAULT_ITEM_GRP_ID);
        assertThat(testItemGroupAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testItemGroupAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testItemGroupAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testItemGroupAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testItemGroupAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createItemGroupAuditLogWithExistingId() throws Exception {
        // Create the ItemGroupAuditLog with an existing ID
        itemGroupAuditLog.setId(1L);
        ItemGroupAuditLogDTO itemGroupAuditLogDTO = itemGroupAuditLogMapper.toDto(itemGroupAuditLog);

        int databaseSizeBeforeCreate = itemGroupAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemGroupAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemGroupAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemGroupAuditLog in the database
        List<ItemGroupAuditLog> itemGroupAuditLogList = itemGroupAuditLogRepository.findAll();
        assertThat(itemGroupAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllItemGroupAuditLogs() throws Exception {
        // Initialize the database
        itemGroupAuditLogRepository.saveAndFlush(itemGroupAuditLog);

        // Get all the itemGroupAuditLogList
        restItemGroupAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemGroupAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemGrpId").value(hasItem(DEFAULT_ITEM_GRP_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getItemGroupAuditLog() throws Exception {
        // Initialize the database
        itemGroupAuditLogRepository.saveAndFlush(itemGroupAuditLog);

        // Get the itemGroupAuditLog
        restItemGroupAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, itemGroupAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(itemGroupAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.itemGrpId").value(DEFAULT_ITEM_GRP_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingItemGroupAuditLog() throws Exception {
        // Get the itemGroupAuditLog
        restItemGroupAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingItemGroupAuditLog() throws Exception {
        // Initialize the database
        itemGroupAuditLogRepository.saveAndFlush(itemGroupAuditLog);

        int databaseSizeBeforeUpdate = itemGroupAuditLogRepository.findAll().size();

        // Update the itemGroupAuditLog
        ItemGroupAuditLog updatedItemGroupAuditLog = itemGroupAuditLogRepository.findById(itemGroupAuditLog.getId()).get();
        // Disconnect from session so that the updates on updatedItemGroupAuditLog are not directly saved in db
        em.detach(updatedItemGroupAuditLog);
        updatedItemGroupAuditLog
            .itemGrpId(UPDATED_ITEM_GRP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        ItemGroupAuditLogDTO itemGroupAuditLogDTO = itemGroupAuditLogMapper.toDto(updatedItemGroupAuditLog);

        restItemGroupAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemGroupAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemGroupAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the ItemGroupAuditLog in the database
        List<ItemGroupAuditLog> itemGroupAuditLogList = itemGroupAuditLogRepository.findAll();
        assertThat(itemGroupAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemGroupAuditLog testItemGroupAuditLog = itemGroupAuditLogList.get(itemGroupAuditLogList.size() - 1);
        assertThat(testItemGroupAuditLog.getItemGrpId()).isEqualTo(UPDATED_ITEM_GRP_ID);
        assertThat(testItemGroupAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemGroupAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testItemGroupAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testItemGroupAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testItemGroupAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingItemGroupAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemGroupAuditLogRepository.findAll().size();
        itemGroupAuditLog.setId(count.incrementAndGet());

        // Create the ItemGroupAuditLog
        ItemGroupAuditLogDTO itemGroupAuditLogDTO = itemGroupAuditLogMapper.toDto(itemGroupAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemGroupAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemGroupAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemGroupAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemGroupAuditLog in the database
        List<ItemGroupAuditLog> itemGroupAuditLogList = itemGroupAuditLogRepository.findAll();
        assertThat(itemGroupAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItemGroupAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemGroupAuditLogRepository.findAll().size();
        itemGroupAuditLog.setId(count.incrementAndGet());

        // Create the ItemGroupAuditLog
        ItemGroupAuditLogDTO itemGroupAuditLogDTO = itemGroupAuditLogMapper.toDto(itemGroupAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemGroupAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemGroupAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemGroupAuditLog in the database
        List<ItemGroupAuditLog> itemGroupAuditLogList = itemGroupAuditLogRepository.findAll();
        assertThat(itemGroupAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItemGroupAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemGroupAuditLogRepository.findAll().size();
        itemGroupAuditLog.setId(count.incrementAndGet());

        // Create the ItemGroupAuditLog
        ItemGroupAuditLogDTO itemGroupAuditLogDTO = itemGroupAuditLogMapper.toDto(itemGroupAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemGroupAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemGroupAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemGroupAuditLog in the database
        List<ItemGroupAuditLog> itemGroupAuditLogList = itemGroupAuditLogRepository.findAll();
        assertThat(itemGroupAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemGroupAuditLogWithPatch() throws Exception {
        // Initialize the database
        itemGroupAuditLogRepository.saveAndFlush(itemGroupAuditLog);

        int databaseSizeBeforeUpdate = itemGroupAuditLogRepository.findAll().size();

        // Update the itemGroupAuditLog using partial update
        ItemGroupAuditLog partialUpdatedItemGroupAuditLog = new ItemGroupAuditLog();
        partialUpdatedItemGroupAuditLog.setId(itemGroupAuditLog.getId());

        partialUpdatedItemGroupAuditLog
            .itemGrpId(UPDATED_ITEM_GRP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restItemGroupAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemGroupAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemGroupAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ItemGroupAuditLog in the database
        List<ItemGroupAuditLog> itemGroupAuditLogList = itemGroupAuditLogRepository.findAll();
        assertThat(itemGroupAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemGroupAuditLog testItemGroupAuditLog = itemGroupAuditLogList.get(itemGroupAuditLogList.size() - 1);
        assertThat(testItemGroupAuditLog.getItemGrpId()).isEqualTo(UPDATED_ITEM_GRP_ID);
        assertThat(testItemGroupAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemGroupAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testItemGroupAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testItemGroupAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testItemGroupAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateItemGroupAuditLogWithPatch() throws Exception {
        // Initialize the database
        itemGroupAuditLogRepository.saveAndFlush(itemGroupAuditLog);

        int databaseSizeBeforeUpdate = itemGroupAuditLogRepository.findAll().size();

        // Update the itemGroupAuditLog using partial update
        ItemGroupAuditLog partialUpdatedItemGroupAuditLog = new ItemGroupAuditLog();
        partialUpdatedItemGroupAuditLog.setId(itemGroupAuditLog.getId());

        partialUpdatedItemGroupAuditLog
            .itemGrpId(UPDATED_ITEM_GRP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restItemGroupAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemGroupAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemGroupAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ItemGroupAuditLog in the database
        List<ItemGroupAuditLog> itemGroupAuditLogList = itemGroupAuditLogRepository.findAll();
        assertThat(itemGroupAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemGroupAuditLog testItemGroupAuditLog = itemGroupAuditLogList.get(itemGroupAuditLogList.size() - 1);
        assertThat(testItemGroupAuditLog.getItemGrpId()).isEqualTo(UPDATED_ITEM_GRP_ID);
        assertThat(testItemGroupAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemGroupAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testItemGroupAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testItemGroupAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testItemGroupAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingItemGroupAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemGroupAuditLogRepository.findAll().size();
        itemGroupAuditLog.setId(count.incrementAndGet());

        // Create the ItemGroupAuditLog
        ItemGroupAuditLogDTO itemGroupAuditLogDTO = itemGroupAuditLogMapper.toDto(itemGroupAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemGroupAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itemGroupAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemGroupAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemGroupAuditLog in the database
        List<ItemGroupAuditLog> itemGroupAuditLogList = itemGroupAuditLogRepository.findAll();
        assertThat(itemGroupAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItemGroupAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemGroupAuditLogRepository.findAll().size();
        itemGroupAuditLog.setId(count.incrementAndGet());

        // Create the ItemGroupAuditLog
        ItemGroupAuditLogDTO itemGroupAuditLogDTO = itemGroupAuditLogMapper.toDto(itemGroupAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemGroupAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemGroupAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemGroupAuditLog in the database
        List<ItemGroupAuditLog> itemGroupAuditLogList = itemGroupAuditLogRepository.findAll();
        assertThat(itemGroupAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItemGroupAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemGroupAuditLogRepository.findAll().size();
        itemGroupAuditLog.setId(count.incrementAndGet());

        // Create the ItemGroupAuditLog
        ItemGroupAuditLogDTO itemGroupAuditLogDTO = itemGroupAuditLogMapper.toDto(itemGroupAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemGroupAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemGroupAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemGroupAuditLog in the database
        List<ItemGroupAuditLog> itemGroupAuditLogList = itemGroupAuditLogRepository.findAll();
        assertThat(itemGroupAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItemGroupAuditLog() throws Exception {
        // Initialize the database
        itemGroupAuditLogRepository.saveAndFlush(itemGroupAuditLog);

        int databaseSizeBeforeDelete = itemGroupAuditLogRepository.findAll().size();

        // Delete the itemGroupAuditLog
        restItemGroupAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, itemGroupAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemGroupAuditLog> itemGroupAuditLogList = itemGroupAuditLogRepository.findAll();
        assertThat(itemGroupAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
