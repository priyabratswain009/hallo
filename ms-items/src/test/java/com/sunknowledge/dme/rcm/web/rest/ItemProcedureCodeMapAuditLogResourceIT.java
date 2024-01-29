package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ItemProcedureCodeMapAuditLog;
import com.sunknowledge.dme.rcm.repository.ItemProcedureCodeMapAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemProcedureCodeMapAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemProcedureCodeMapAuditLogMapper;
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
 * Integration tests for the {@link ItemProcedureCodeMapAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemProcedureCodeMapAuditLogResourceIT {

    private static final Long DEFAULT_ITEM_PROCDRE_CDE_MAP_ID = 1L;
    private static final Long UPDATED_ITEM_PROCDRE_CDE_MAP_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/item-procedure-code-map-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemProcedureCodeMapAuditLogRepository itemProcedureCodeMapAuditLogRepository;

    @Autowired
    private ItemProcedureCodeMapAuditLogMapper itemProcedureCodeMapAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemProcedureCodeMapAuditLogMockMvc;

    private ItemProcedureCodeMapAuditLog itemProcedureCodeMapAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemProcedureCodeMapAuditLog createEntity(EntityManager em) {
        ItemProcedureCodeMapAuditLog itemProcedureCodeMapAuditLog = new ItemProcedureCodeMapAuditLog()
            .itemProcdreCdeMapId(DEFAULT_ITEM_PROCDRE_CDE_MAP_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return itemProcedureCodeMapAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemProcedureCodeMapAuditLog createUpdatedEntity(EntityManager em) {
        ItemProcedureCodeMapAuditLog itemProcedureCodeMapAuditLog = new ItemProcedureCodeMapAuditLog()
            .itemProcdreCdeMapId(UPDATED_ITEM_PROCDRE_CDE_MAP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return itemProcedureCodeMapAuditLog;
    }

    @BeforeEach
    public void initTest() {
        itemProcedureCodeMapAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createItemProcedureCodeMapAuditLog() throws Exception {
        int databaseSizeBeforeCreate = itemProcedureCodeMapAuditLogRepository.findAll().size();
        // Create the ItemProcedureCodeMapAuditLog
        ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO = itemProcedureCodeMapAuditLogMapper.toDto(
            itemProcedureCodeMapAuditLog
        );
        restItemProcedureCodeMapAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemProcedureCodeMapAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemProcedureCodeMapAuditLog in the database
        List<ItemProcedureCodeMapAuditLog> itemProcedureCodeMapAuditLogList = itemProcedureCodeMapAuditLogRepository.findAll();
        assertThat(itemProcedureCodeMapAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        ItemProcedureCodeMapAuditLog testItemProcedureCodeMapAuditLog = itemProcedureCodeMapAuditLogList.get(
            itemProcedureCodeMapAuditLogList.size() - 1
        );
        assertThat(testItemProcedureCodeMapAuditLog.getItemProcdreCdeMapId()).isEqualTo(DEFAULT_ITEM_PROCDRE_CDE_MAP_ID);
        assertThat(testItemProcedureCodeMapAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testItemProcedureCodeMapAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testItemProcedureCodeMapAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testItemProcedureCodeMapAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testItemProcedureCodeMapAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createItemProcedureCodeMapAuditLogWithExistingId() throws Exception {
        // Create the ItemProcedureCodeMapAuditLog with an existing ID
        itemProcedureCodeMapAuditLog.setId(1L);
        ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO = itemProcedureCodeMapAuditLogMapper.toDto(
            itemProcedureCodeMapAuditLog
        );

        int databaseSizeBeforeCreate = itemProcedureCodeMapAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemProcedureCodeMapAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemProcedureCodeMapAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemProcedureCodeMapAuditLog in the database
        List<ItemProcedureCodeMapAuditLog> itemProcedureCodeMapAuditLogList = itemProcedureCodeMapAuditLogRepository.findAll();
        assertThat(itemProcedureCodeMapAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllItemProcedureCodeMapAuditLogs() throws Exception {
        // Initialize the database
        itemProcedureCodeMapAuditLogRepository.saveAndFlush(itemProcedureCodeMapAuditLog);

        // Get all the itemProcedureCodeMapAuditLogList
        restItemProcedureCodeMapAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemProcedureCodeMapAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemProcdreCdeMapId").value(hasItem(DEFAULT_ITEM_PROCDRE_CDE_MAP_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getItemProcedureCodeMapAuditLog() throws Exception {
        // Initialize the database
        itemProcedureCodeMapAuditLogRepository.saveAndFlush(itemProcedureCodeMapAuditLog);

        // Get the itemProcedureCodeMapAuditLog
        restItemProcedureCodeMapAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, itemProcedureCodeMapAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(itemProcedureCodeMapAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.itemProcdreCdeMapId").value(DEFAULT_ITEM_PROCDRE_CDE_MAP_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingItemProcedureCodeMapAuditLog() throws Exception {
        // Get the itemProcedureCodeMapAuditLog
        restItemProcedureCodeMapAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewItemProcedureCodeMapAuditLog() throws Exception {
        // Initialize the database
        itemProcedureCodeMapAuditLogRepository.saveAndFlush(itemProcedureCodeMapAuditLog);

        int databaseSizeBeforeUpdate = itemProcedureCodeMapAuditLogRepository.findAll().size();

        // Update the itemProcedureCodeMapAuditLog
        ItemProcedureCodeMapAuditLog updatedItemProcedureCodeMapAuditLog = itemProcedureCodeMapAuditLogRepository
            .findById(itemProcedureCodeMapAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedItemProcedureCodeMapAuditLog are not directly saved in db
        em.detach(updatedItemProcedureCodeMapAuditLog);
        updatedItemProcedureCodeMapAuditLog
            .itemProcdreCdeMapId(UPDATED_ITEM_PROCDRE_CDE_MAP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO = itemProcedureCodeMapAuditLogMapper.toDto(
            updatedItemProcedureCodeMapAuditLog
        );

        restItemProcedureCodeMapAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemProcedureCodeMapAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemProcedureCodeMapAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the ItemProcedureCodeMapAuditLog in the database
        List<ItemProcedureCodeMapAuditLog> itemProcedureCodeMapAuditLogList = itemProcedureCodeMapAuditLogRepository.findAll();
        assertThat(itemProcedureCodeMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemProcedureCodeMapAuditLog testItemProcedureCodeMapAuditLog = itemProcedureCodeMapAuditLogList.get(
            itemProcedureCodeMapAuditLogList.size() - 1
        );
        assertThat(testItemProcedureCodeMapAuditLog.getItemProcdreCdeMapId()).isEqualTo(UPDATED_ITEM_PROCDRE_CDE_MAP_ID);
        assertThat(testItemProcedureCodeMapAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemProcedureCodeMapAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testItemProcedureCodeMapAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testItemProcedureCodeMapAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testItemProcedureCodeMapAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingItemProcedureCodeMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemProcedureCodeMapAuditLogRepository.findAll().size();
        itemProcedureCodeMapAuditLog.setId(count.incrementAndGet());

        // Create the ItemProcedureCodeMapAuditLog
        ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO = itemProcedureCodeMapAuditLogMapper.toDto(
            itemProcedureCodeMapAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemProcedureCodeMapAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemProcedureCodeMapAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemProcedureCodeMapAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemProcedureCodeMapAuditLog in the database
        List<ItemProcedureCodeMapAuditLog> itemProcedureCodeMapAuditLogList = itemProcedureCodeMapAuditLogRepository.findAll();
        assertThat(itemProcedureCodeMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItemProcedureCodeMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemProcedureCodeMapAuditLogRepository.findAll().size();
        itemProcedureCodeMapAuditLog.setId(count.incrementAndGet());

        // Create the ItemProcedureCodeMapAuditLog
        ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO = itemProcedureCodeMapAuditLogMapper.toDto(
            itemProcedureCodeMapAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemProcedureCodeMapAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemProcedureCodeMapAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemProcedureCodeMapAuditLog in the database
        List<ItemProcedureCodeMapAuditLog> itemProcedureCodeMapAuditLogList = itemProcedureCodeMapAuditLogRepository.findAll();
        assertThat(itemProcedureCodeMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItemProcedureCodeMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemProcedureCodeMapAuditLogRepository.findAll().size();
        itemProcedureCodeMapAuditLog.setId(count.incrementAndGet());

        // Create the ItemProcedureCodeMapAuditLog
        ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO = itemProcedureCodeMapAuditLogMapper.toDto(
            itemProcedureCodeMapAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemProcedureCodeMapAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemProcedureCodeMapAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemProcedureCodeMapAuditLog in the database
        List<ItemProcedureCodeMapAuditLog> itemProcedureCodeMapAuditLogList = itemProcedureCodeMapAuditLogRepository.findAll();
        assertThat(itemProcedureCodeMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemProcedureCodeMapAuditLogWithPatch() throws Exception {
        // Initialize the database
        itemProcedureCodeMapAuditLogRepository.saveAndFlush(itemProcedureCodeMapAuditLog);

        int databaseSizeBeforeUpdate = itemProcedureCodeMapAuditLogRepository.findAll().size();

        // Update the itemProcedureCodeMapAuditLog using partial update
        ItemProcedureCodeMapAuditLog partialUpdatedItemProcedureCodeMapAuditLog = new ItemProcedureCodeMapAuditLog();
        partialUpdatedItemProcedureCodeMapAuditLog.setId(itemProcedureCodeMapAuditLog.getId());

        partialUpdatedItemProcedureCodeMapAuditLog
            .itemProcdreCdeMapId(UPDATED_ITEM_PROCDRE_CDE_MAP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restItemProcedureCodeMapAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemProcedureCodeMapAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemProcedureCodeMapAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ItemProcedureCodeMapAuditLog in the database
        List<ItemProcedureCodeMapAuditLog> itemProcedureCodeMapAuditLogList = itemProcedureCodeMapAuditLogRepository.findAll();
        assertThat(itemProcedureCodeMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemProcedureCodeMapAuditLog testItemProcedureCodeMapAuditLog = itemProcedureCodeMapAuditLogList.get(
            itemProcedureCodeMapAuditLogList.size() - 1
        );
        assertThat(testItemProcedureCodeMapAuditLog.getItemProcdreCdeMapId()).isEqualTo(UPDATED_ITEM_PROCDRE_CDE_MAP_ID);
        assertThat(testItemProcedureCodeMapAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemProcedureCodeMapAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testItemProcedureCodeMapAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testItemProcedureCodeMapAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testItemProcedureCodeMapAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateItemProcedureCodeMapAuditLogWithPatch() throws Exception {
        // Initialize the database
        itemProcedureCodeMapAuditLogRepository.saveAndFlush(itemProcedureCodeMapAuditLog);

        int databaseSizeBeforeUpdate = itemProcedureCodeMapAuditLogRepository.findAll().size();

        // Update the itemProcedureCodeMapAuditLog using partial update
        ItemProcedureCodeMapAuditLog partialUpdatedItemProcedureCodeMapAuditLog = new ItemProcedureCodeMapAuditLog();
        partialUpdatedItemProcedureCodeMapAuditLog.setId(itemProcedureCodeMapAuditLog.getId());

        partialUpdatedItemProcedureCodeMapAuditLog
            .itemProcdreCdeMapId(UPDATED_ITEM_PROCDRE_CDE_MAP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restItemProcedureCodeMapAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemProcedureCodeMapAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemProcedureCodeMapAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ItemProcedureCodeMapAuditLog in the database
        List<ItemProcedureCodeMapAuditLog> itemProcedureCodeMapAuditLogList = itemProcedureCodeMapAuditLogRepository.findAll();
        assertThat(itemProcedureCodeMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemProcedureCodeMapAuditLog testItemProcedureCodeMapAuditLog = itemProcedureCodeMapAuditLogList.get(
            itemProcedureCodeMapAuditLogList.size() - 1
        );
        assertThat(testItemProcedureCodeMapAuditLog.getItemProcdreCdeMapId()).isEqualTo(UPDATED_ITEM_PROCDRE_CDE_MAP_ID);
        assertThat(testItemProcedureCodeMapAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemProcedureCodeMapAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testItemProcedureCodeMapAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testItemProcedureCodeMapAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testItemProcedureCodeMapAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingItemProcedureCodeMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemProcedureCodeMapAuditLogRepository.findAll().size();
        itemProcedureCodeMapAuditLog.setId(count.incrementAndGet());

        // Create the ItemProcedureCodeMapAuditLog
        ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO = itemProcedureCodeMapAuditLogMapper.toDto(
            itemProcedureCodeMapAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemProcedureCodeMapAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itemProcedureCodeMapAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemProcedureCodeMapAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemProcedureCodeMapAuditLog in the database
        List<ItemProcedureCodeMapAuditLog> itemProcedureCodeMapAuditLogList = itemProcedureCodeMapAuditLogRepository.findAll();
        assertThat(itemProcedureCodeMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItemProcedureCodeMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemProcedureCodeMapAuditLogRepository.findAll().size();
        itemProcedureCodeMapAuditLog.setId(count.incrementAndGet());

        // Create the ItemProcedureCodeMapAuditLog
        ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO = itemProcedureCodeMapAuditLogMapper.toDto(
            itemProcedureCodeMapAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemProcedureCodeMapAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemProcedureCodeMapAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemProcedureCodeMapAuditLog in the database
        List<ItemProcedureCodeMapAuditLog> itemProcedureCodeMapAuditLogList = itemProcedureCodeMapAuditLogRepository.findAll();
        assertThat(itemProcedureCodeMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItemProcedureCodeMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemProcedureCodeMapAuditLogRepository.findAll().size();
        itemProcedureCodeMapAuditLog.setId(count.incrementAndGet());

        // Create the ItemProcedureCodeMapAuditLog
        ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO = itemProcedureCodeMapAuditLogMapper.toDto(
            itemProcedureCodeMapAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemProcedureCodeMapAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemProcedureCodeMapAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemProcedureCodeMapAuditLog in the database
        List<ItemProcedureCodeMapAuditLog> itemProcedureCodeMapAuditLogList = itemProcedureCodeMapAuditLogRepository.findAll();
        assertThat(itemProcedureCodeMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItemProcedureCodeMapAuditLog() throws Exception {
        // Initialize the database
        itemProcedureCodeMapAuditLogRepository.saveAndFlush(itemProcedureCodeMapAuditLog);

        int databaseSizeBeforeDelete = itemProcedureCodeMapAuditLogRepository.findAll().size();

        // Delete the itemProcedureCodeMapAuditLog
        restItemProcedureCodeMapAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, itemProcedureCodeMapAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemProcedureCodeMapAuditLog> itemProcedureCodeMapAuditLogList = itemProcedureCodeMapAuditLogRepository.findAll();
        assertThat(itemProcedureCodeMapAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
