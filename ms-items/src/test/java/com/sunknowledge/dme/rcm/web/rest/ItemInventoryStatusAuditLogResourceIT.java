package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ItemInventoryStatusAuditLog;
import com.sunknowledge.dme.rcm.repository.ItemInventoryStatusAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemInventoryStatusAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemInventoryStatusAuditLogMapper;
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
 * Integration tests for the {@link ItemInventoryStatusAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemInventoryStatusAuditLogResourceIT {

    private static final Long DEFAULT_ITEM_INVTORY_STAUS_ID = 1L;
    private static final Long UPDATED_ITEM_INVTORY_STAUS_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/item-inventory-status-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemInventoryStatusAuditLogRepository itemInventoryStatusAuditLogRepository;

    @Autowired
    private ItemInventoryStatusAuditLogMapper itemInventoryStatusAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemInventoryStatusAuditLogMockMvc;

    private ItemInventoryStatusAuditLog itemInventoryStatusAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemInventoryStatusAuditLog createEntity(EntityManager em) {
        ItemInventoryStatusAuditLog itemInventoryStatusAuditLog = new ItemInventoryStatusAuditLog()
            .itemInvtoryStausId(DEFAULT_ITEM_INVTORY_STAUS_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return itemInventoryStatusAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemInventoryStatusAuditLog createUpdatedEntity(EntityManager em) {
        ItemInventoryStatusAuditLog itemInventoryStatusAuditLog = new ItemInventoryStatusAuditLog()
            .itemInvtoryStausId(UPDATED_ITEM_INVTORY_STAUS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return itemInventoryStatusAuditLog;
    }

    @BeforeEach
    public void initTest() {
        itemInventoryStatusAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createItemInventoryStatusAuditLog() throws Exception {
        int databaseSizeBeforeCreate = itemInventoryStatusAuditLogRepository.findAll().size();
        // Create the ItemInventoryStatusAuditLog
        ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO = itemInventoryStatusAuditLogMapper.toDto(
            itemInventoryStatusAuditLog
        );
        restItemInventoryStatusAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemInventoryStatusAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemInventoryStatusAuditLog in the database
        List<ItemInventoryStatusAuditLog> itemInventoryStatusAuditLogList = itemInventoryStatusAuditLogRepository.findAll();
        assertThat(itemInventoryStatusAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        ItemInventoryStatusAuditLog testItemInventoryStatusAuditLog = itemInventoryStatusAuditLogList.get(
            itemInventoryStatusAuditLogList.size() - 1
        );
        assertThat(testItemInventoryStatusAuditLog.getItemInvtoryStausId()).isEqualTo(DEFAULT_ITEM_INVTORY_STAUS_ID);
        assertThat(testItemInventoryStatusAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testItemInventoryStatusAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testItemInventoryStatusAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testItemInventoryStatusAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testItemInventoryStatusAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createItemInventoryStatusAuditLogWithExistingId() throws Exception {
        // Create the ItemInventoryStatusAuditLog with an existing ID
        itemInventoryStatusAuditLog.setId(1L);
        ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO = itemInventoryStatusAuditLogMapper.toDto(
            itemInventoryStatusAuditLog
        );

        int databaseSizeBeforeCreate = itemInventoryStatusAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemInventoryStatusAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemInventoryStatusAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemInventoryStatusAuditLog in the database
        List<ItemInventoryStatusAuditLog> itemInventoryStatusAuditLogList = itemInventoryStatusAuditLogRepository.findAll();
        assertThat(itemInventoryStatusAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllItemInventoryStatusAuditLogs() throws Exception {
        // Initialize the database
        itemInventoryStatusAuditLogRepository.saveAndFlush(itemInventoryStatusAuditLog);

        // Get all the itemInventoryStatusAuditLogList
        restItemInventoryStatusAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemInventoryStatusAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemInvtoryStausId").value(hasItem(DEFAULT_ITEM_INVTORY_STAUS_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getItemInventoryStatusAuditLog() throws Exception {
        // Initialize the database
        itemInventoryStatusAuditLogRepository.saveAndFlush(itemInventoryStatusAuditLog);

        // Get the itemInventoryStatusAuditLog
        restItemInventoryStatusAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, itemInventoryStatusAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(itemInventoryStatusAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.itemInvtoryStausId").value(DEFAULT_ITEM_INVTORY_STAUS_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingItemInventoryStatusAuditLog() throws Exception {
        // Get the itemInventoryStatusAuditLog
        restItemInventoryStatusAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingItemInventoryStatusAuditLog() throws Exception {
        // Initialize the database
        itemInventoryStatusAuditLogRepository.saveAndFlush(itemInventoryStatusAuditLog);

        int databaseSizeBeforeUpdate = itemInventoryStatusAuditLogRepository.findAll().size();

        // Update the itemInventoryStatusAuditLog
        ItemInventoryStatusAuditLog updatedItemInventoryStatusAuditLog = itemInventoryStatusAuditLogRepository
            .findById(itemInventoryStatusAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedItemInventoryStatusAuditLog are not directly saved in db
        em.detach(updatedItemInventoryStatusAuditLog);
        updatedItemInventoryStatusAuditLog
            .itemInvtoryStausId(UPDATED_ITEM_INVTORY_STAUS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO = itemInventoryStatusAuditLogMapper.toDto(
            updatedItemInventoryStatusAuditLog
        );

        restItemInventoryStatusAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemInventoryStatusAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemInventoryStatusAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the ItemInventoryStatusAuditLog in the database
        List<ItemInventoryStatusAuditLog> itemInventoryStatusAuditLogList = itemInventoryStatusAuditLogRepository.findAll();
        assertThat(itemInventoryStatusAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemInventoryStatusAuditLog testItemInventoryStatusAuditLog = itemInventoryStatusAuditLogList.get(
            itemInventoryStatusAuditLogList.size() - 1
        );
        assertThat(testItemInventoryStatusAuditLog.getItemInvtoryStausId()).isEqualTo(UPDATED_ITEM_INVTORY_STAUS_ID);
        assertThat(testItemInventoryStatusAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemInventoryStatusAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testItemInventoryStatusAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testItemInventoryStatusAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testItemInventoryStatusAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingItemInventoryStatusAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemInventoryStatusAuditLogRepository.findAll().size();
        itemInventoryStatusAuditLog.setId(count.incrementAndGet());

        // Create the ItemInventoryStatusAuditLog
        ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO = itemInventoryStatusAuditLogMapper.toDto(
            itemInventoryStatusAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemInventoryStatusAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemInventoryStatusAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemInventoryStatusAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemInventoryStatusAuditLog in the database
        List<ItemInventoryStatusAuditLog> itemInventoryStatusAuditLogList = itemInventoryStatusAuditLogRepository.findAll();
        assertThat(itemInventoryStatusAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItemInventoryStatusAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemInventoryStatusAuditLogRepository.findAll().size();
        itemInventoryStatusAuditLog.setId(count.incrementAndGet());

        // Create the ItemInventoryStatusAuditLog
        ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO = itemInventoryStatusAuditLogMapper.toDto(
            itemInventoryStatusAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemInventoryStatusAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemInventoryStatusAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemInventoryStatusAuditLog in the database
        List<ItemInventoryStatusAuditLog> itemInventoryStatusAuditLogList = itemInventoryStatusAuditLogRepository.findAll();
        assertThat(itemInventoryStatusAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItemInventoryStatusAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemInventoryStatusAuditLogRepository.findAll().size();
        itemInventoryStatusAuditLog.setId(count.incrementAndGet());

        // Create the ItemInventoryStatusAuditLog
        ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO = itemInventoryStatusAuditLogMapper.toDto(
            itemInventoryStatusAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemInventoryStatusAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemInventoryStatusAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemInventoryStatusAuditLog in the database
        List<ItemInventoryStatusAuditLog> itemInventoryStatusAuditLogList = itemInventoryStatusAuditLogRepository.findAll();
        assertThat(itemInventoryStatusAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemInventoryStatusAuditLogWithPatch() throws Exception {
        // Initialize the database
        itemInventoryStatusAuditLogRepository.saveAndFlush(itemInventoryStatusAuditLog);

        int databaseSizeBeforeUpdate = itemInventoryStatusAuditLogRepository.findAll().size();

        // Update the itemInventoryStatusAuditLog using partial update
        ItemInventoryStatusAuditLog partialUpdatedItemInventoryStatusAuditLog = new ItemInventoryStatusAuditLog();
        partialUpdatedItemInventoryStatusAuditLog.setId(itemInventoryStatusAuditLog.getId());

        partialUpdatedItemInventoryStatusAuditLog
            .itemInvtoryStausId(UPDATED_ITEM_INVTORY_STAUS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restItemInventoryStatusAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemInventoryStatusAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemInventoryStatusAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ItemInventoryStatusAuditLog in the database
        List<ItemInventoryStatusAuditLog> itemInventoryStatusAuditLogList = itemInventoryStatusAuditLogRepository.findAll();
        assertThat(itemInventoryStatusAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemInventoryStatusAuditLog testItemInventoryStatusAuditLog = itemInventoryStatusAuditLogList.get(
            itemInventoryStatusAuditLogList.size() - 1
        );
        assertThat(testItemInventoryStatusAuditLog.getItemInvtoryStausId()).isEqualTo(UPDATED_ITEM_INVTORY_STAUS_ID);
        assertThat(testItemInventoryStatusAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemInventoryStatusAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testItemInventoryStatusAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testItemInventoryStatusAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testItemInventoryStatusAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateItemInventoryStatusAuditLogWithPatch() throws Exception {
        // Initialize the database
        itemInventoryStatusAuditLogRepository.saveAndFlush(itemInventoryStatusAuditLog);

        int databaseSizeBeforeUpdate = itemInventoryStatusAuditLogRepository.findAll().size();

        // Update the itemInventoryStatusAuditLog using partial update
        ItemInventoryStatusAuditLog partialUpdatedItemInventoryStatusAuditLog = new ItemInventoryStatusAuditLog();
        partialUpdatedItemInventoryStatusAuditLog.setId(itemInventoryStatusAuditLog.getId());

        partialUpdatedItemInventoryStatusAuditLog
            .itemInvtoryStausId(UPDATED_ITEM_INVTORY_STAUS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restItemInventoryStatusAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemInventoryStatusAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemInventoryStatusAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ItemInventoryStatusAuditLog in the database
        List<ItemInventoryStatusAuditLog> itemInventoryStatusAuditLogList = itemInventoryStatusAuditLogRepository.findAll();
        assertThat(itemInventoryStatusAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemInventoryStatusAuditLog testItemInventoryStatusAuditLog = itemInventoryStatusAuditLogList.get(
            itemInventoryStatusAuditLogList.size() - 1
        );
        assertThat(testItemInventoryStatusAuditLog.getItemInvtoryStausId()).isEqualTo(UPDATED_ITEM_INVTORY_STAUS_ID);
        assertThat(testItemInventoryStatusAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemInventoryStatusAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testItemInventoryStatusAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testItemInventoryStatusAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testItemInventoryStatusAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingItemInventoryStatusAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemInventoryStatusAuditLogRepository.findAll().size();
        itemInventoryStatusAuditLog.setId(count.incrementAndGet());

        // Create the ItemInventoryStatusAuditLog
        ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO = itemInventoryStatusAuditLogMapper.toDto(
            itemInventoryStatusAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemInventoryStatusAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itemInventoryStatusAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemInventoryStatusAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemInventoryStatusAuditLog in the database
        List<ItemInventoryStatusAuditLog> itemInventoryStatusAuditLogList = itemInventoryStatusAuditLogRepository.findAll();
        assertThat(itemInventoryStatusAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItemInventoryStatusAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemInventoryStatusAuditLogRepository.findAll().size();
        itemInventoryStatusAuditLog.setId(count.incrementAndGet());

        // Create the ItemInventoryStatusAuditLog
        ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO = itemInventoryStatusAuditLogMapper.toDto(
            itemInventoryStatusAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemInventoryStatusAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemInventoryStatusAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemInventoryStatusAuditLog in the database
        List<ItemInventoryStatusAuditLog> itemInventoryStatusAuditLogList = itemInventoryStatusAuditLogRepository.findAll();
        assertThat(itemInventoryStatusAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItemInventoryStatusAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemInventoryStatusAuditLogRepository.findAll().size();
        itemInventoryStatusAuditLog.setId(count.incrementAndGet());

        // Create the ItemInventoryStatusAuditLog
        ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO = itemInventoryStatusAuditLogMapper.toDto(
            itemInventoryStatusAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemInventoryStatusAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemInventoryStatusAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemInventoryStatusAuditLog in the database
        List<ItemInventoryStatusAuditLog> itemInventoryStatusAuditLogList = itemInventoryStatusAuditLogRepository.findAll();
        assertThat(itemInventoryStatusAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItemInventoryStatusAuditLog() throws Exception {
        // Initialize the database
        itemInventoryStatusAuditLogRepository.saveAndFlush(itemInventoryStatusAuditLog);

        int databaseSizeBeforeDelete = itemInventoryStatusAuditLogRepository.findAll().size();

        // Delete the itemInventoryStatusAuditLog
        restItemInventoryStatusAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, itemInventoryStatusAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemInventoryStatusAuditLog> itemInventoryStatusAuditLogList = itemInventoryStatusAuditLogRepository.findAll();
        assertThat(itemInventoryStatusAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
