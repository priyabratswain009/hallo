package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ItemVendorMappingAuditLog;
import com.sunknowledge.dme.rcm.repository.ItemVendorMappingAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemVendorMappingAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemVendorMappingAuditLogMapper;
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
 * Integration tests for the {@link ItemVendorMappingAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemVendorMappingAuditLogResourceIT {

    private static final Long DEFAULT_ITM_VNDOR_ID = 1L;
    private static final Long UPDATED_ITM_VNDOR_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/item-vendor-mapping-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemVendorMappingAuditLogRepository itemVendorMappingAuditLogRepository;

    @Autowired
    private ItemVendorMappingAuditLogMapper itemVendorMappingAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemVendorMappingAuditLogMockMvc;

    private ItemVendorMappingAuditLog itemVendorMappingAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemVendorMappingAuditLog createEntity(EntityManager em) {
        ItemVendorMappingAuditLog itemVendorMappingAuditLog = new ItemVendorMappingAuditLog()
            .itmVndorId(DEFAULT_ITM_VNDOR_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return itemVendorMappingAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemVendorMappingAuditLog createUpdatedEntity(EntityManager em) {
        ItemVendorMappingAuditLog itemVendorMappingAuditLog = new ItemVendorMappingAuditLog()
            .itmVndorId(UPDATED_ITM_VNDOR_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return itemVendorMappingAuditLog;
    }

    @BeforeEach
    public void initTest() {
        itemVendorMappingAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createItemVendorMappingAuditLog() throws Exception {
        int databaseSizeBeforeCreate = itemVendorMappingAuditLogRepository.findAll().size();
        // Create the ItemVendorMappingAuditLog
        ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO = itemVendorMappingAuditLogMapper.toDto(itemVendorMappingAuditLog);
        restItemVendorMappingAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemVendorMappingAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ItemVendorMappingAuditLog in the database
        List<ItemVendorMappingAuditLog> itemVendorMappingAuditLogList = itemVendorMappingAuditLogRepository.findAll();
        assertThat(itemVendorMappingAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        ItemVendorMappingAuditLog testItemVendorMappingAuditLog = itemVendorMappingAuditLogList.get(
            itemVendorMappingAuditLogList.size() - 1
        );
        assertThat(testItemVendorMappingAuditLog.getItmVndorId()).isEqualTo(DEFAULT_ITM_VNDOR_ID);
        assertThat(testItemVendorMappingAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testItemVendorMappingAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testItemVendorMappingAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testItemVendorMappingAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testItemVendorMappingAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createItemVendorMappingAuditLogWithExistingId() throws Exception {
        // Create the ItemVendorMappingAuditLog with an existing ID
        itemVendorMappingAuditLog.setId(1L);
        ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO = itemVendorMappingAuditLogMapper.toDto(itemVendorMappingAuditLog);

        int databaseSizeBeforeCreate = itemVendorMappingAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemVendorMappingAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemVendorMappingAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemVendorMappingAuditLog in the database
        List<ItemVendorMappingAuditLog> itemVendorMappingAuditLogList = itemVendorMappingAuditLogRepository.findAll();
        assertThat(itemVendorMappingAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllItemVendorMappingAuditLogs() throws Exception {
        // Initialize the database
        itemVendorMappingAuditLogRepository.saveAndFlush(itemVendorMappingAuditLog);

        // Get all the itemVendorMappingAuditLogList
        restItemVendorMappingAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemVendorMappingAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].itmVndorId").value(hasItem(DEFAULT_ITM_VNDOR_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getItemVendorMappingAuditLog() throws Exception {
        // Initialize the database
        itemVendorMappingAuditLogRepository.saveAndFlush(itemVendorMappingAuditLog);

        // Get the itemVendorMappingAuditLog
        restItemVendorMappingAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, itemVendorMappingAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(itemVendorMappingAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.itmVndorId").value(DEFAULT_ITM_VNDOR_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingItemVendorMappingAuditLog() throws Exception {
        // Get the itemVendorMappingAuditLog
        restItemVendorMappingAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewItemVendorMappingAuditLog() throws Exception {
        // Initialize the database
        itemVendorMappingAuditLogRepository.saveAndFlush(itemVendorMappingAuditLog);

        int databaseSizeBeforeUpdate = itemVendorMappingAuditLogRepository.findAll().size();

        // Update the itemVendorMappingAuditLog
        ItemVendorMappingAuditLog updatedItemVendorMappingAuditLog = itemVendorMappingAuditLogRepository
            .findById(itemVendorMappingAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedItemVendorMappingAuditLog are not directly saved in db
        em.detach(updatedItemVendorMappingAuditLog);
        updatedItemVendorMappingAuditLog
            .itmVndorId(UPDATED_ITM_VNDOR_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO = itemVendorMappingAuditLogMapper.toDto(updatedItemVendorMappingAuditLog);

        restItemVendorMappingAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemVendorMappingAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemVendorMappingAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the ItemVendorMappingAuditLog in the database
        List<ItemVendorMappingAuditLog> itemVendorMappingAuditLogList = itemVendorMappingAuditLogRepository.findAll();
        assertThat(itemVendorMappingAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemVendorMappingAuditLog testItemVendorMappingAuditLog = itemVendorMappingAuditLogList.get(
            itemVendorMappingAuditLogList.size() - 1
        );
        assertThat(testItemVendorMappingAuditLog.getItmVndorId()).isEqualTo(UPDATED_ITM_VNDOR_ID);
        assertThat(testItemVendorMappingAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemVendorMappingAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testItemVendorMappingAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testItemVendorMappingAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testItemVendorMappingAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingItemVendorMappingAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemVendorMappingAuditLogRepository.findAll().size();
        itemVendorMappingAuditLog.setId(count.incrementAndGet());

        // Create the ItemVendorMappingAuditLog
        ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO = itemVendorMappingAuditLogMapper.toDto(itemVendorMappingAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemVendorMappingAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itemVendorMappingAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemVendorMappingAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemVendorMappingAuditLog in the database
        List<ItemVendorMappingAuditLog> itemVendorMappingAuditLogList = itemVendorMappingAuditLogRepository.findAll();
        assertThat(itemVendorMappingAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItemVendorMappingAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemVendorMappingAuditLogRepository.findAll().size();
        itemVendorMappingAuditLog.setId(count.incrementAndGet());

        // Create the ItemVendorMappingAuditLog
        ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO = itemVendorMappingAuditLogMapper.toDto(itemVendorMappingAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemVendorMappingAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemVendorMappingAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemVendorMappingAuditLog in the database
        List<ItemVendorMappingAuditLog> itemVendorMappingAuditLogList = itemVendorMappingAuditLogRepository.findAll();
        assertThat(itemVendorMappingAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItemVendorMappingAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemVendorMappingAuditLogRepository.findAll().size();
        itemVendorMappingAuditLog.setId(count.incrementAndGet());

        // Create the ItemVendorMappingAuditLog
        ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO = itemVendorMappingAuditLogMapper.toDto(itemVendorMappingAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemVendorMappingAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itemVendorMappingAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemVendorMappingAuditLog in the database
        List<ItemVendorMappingAuditLog> itemVendorMappingAuditLogList = itemVendorMappingAuditLogRepository.findAll();
        assertThat(itemVendorMappingAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemVendorMappingAuditLogWithPatch() throws Exception {
        // Initialize the database
        itemVendorMappingAuditLogRepository.saveAndFlush(itemVendorMappingAuditLog);

        int databaseSizeBeforeUpdate = itemVendorMappingAuditLogRepository.findAll().size();

        // Update the itemVendorMappingAuditLog using partial update
        ItemVendorMappingAuditLog partialUpdatedItemVendorMappingAuditLog = new ItemVendorMappingAuditLog();
        partialUpdatedItemVendorMappingAuditLog.setId(itemVendorMappingAuditLog.getId());

        partialUpdatedItemVendorMappingAuditLog
            .itmVndorId(UPDATED_ITM_VNDOR_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restItemVendorMappingAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemVendorMappingAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemVendorMappingAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ItemVendorMappingAuditLog in the database
        List<ItemVendorMappingAuditLog> itemVendorMappingAuditLogList = itemVendorMappingAuditLogRepository.findAll();
        assertThat(itemVendorMappingAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemVendorMappingAuditLog testItemVendorMappingAuditLog = itemVendorMappingAuditLogList.get(
            itemVendorMappingAuditLogList.size() - 1
        );
        assertThat(testItemVendorMappingAuditLog.getItmVndorId()).isEqualTo(UPDATED_ITM_VNDOR_ID);
        assertThat(testItemVendorMappingAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemVendorMappingAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testItemVendorMappingAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testItemVendorMappingAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testItemVendorMappingAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateItemVendorMappingAuditLogWithPatch() throws Exception {
        // Initialize the database
        itemVendorMappingAuditLogRepository.saveAndFlush(itemVendorMappingAuditLog);

        int databaseSizeBeforeUpdate = itemVendorMappingAuditLogRepository.findAll().size();

        // Update the itemVendorMappingAuditLog using partial update
        ItemVendorMappingAuditLog partialUpdatedItemVendorMappingAuditLog = new ItemVendorMappingAuditLog();
        partialUpdatedItemVendorMappingAuditLog.setId(itemVendorMappingAuditLog.getId());

        partialUpdatedItemVendorMappingAuditLog
            .itmVndorId(UPDATED_ITM_VNDOR_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restItemVendorMappingAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItemVendorMappingAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItemVendorMappingAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the ItemVendorMappingAuditLog in the database
        List<ItemVendorMappingAuditLog> itemVendorMappingAuditLogList = itemVendorMappingAuditLogRepository.findAll();
        assertThat(itemVendorMappingAuditLogList).hasSize(databaseSizeBeforeUpdate);
        ItemVendorMappingAuditLog testItemVendorMappingAuditLog = itemVendorMappingAuditLogList.get(
            itemVendorMappingAuditLogList.size() - 1
        );
        assertThat(testItemVendorMappingAuditLog.getItmVndorId()).isEqualTo(UPDATED_ITM_VNDOR_ID);
        assertThat(testItemVendorMappingAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testItemVendorMappingAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testItemVendorMappingAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testItemVendorMappingAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testItemVendorMappingAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingItemVendorMappingAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemVendorMappingAuditLogRepository.findAll().size();
        itemVendorMappingAuditLog.setId(count.incrementAndGet());

        // Create the ItemVendorMappingAuditLog
        ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO = itemVendorMappingAuditLogMapper.toDto(itemVendorMappingAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemVendorMappingAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itemVendorMappingAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemVendorMappingAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemVendorMappingAuditLog in the database
        List<ItemVendorMappingAuditLog> itemVendorMappingAuditLogList = itemVendorMappingAuditLogRepository.findAll();
        assertThat(itemVendorMappingAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItemVendorMappingAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemVendorMappingAuditLogRepository.findAll().size();
        itemVendorMappingAuditLog.setId(count.incrementAndGet());

        // Create the ItemVendorMappingAuditLog
        ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO = itemVendorMappingAuditLogMapper.toDto(itemVendorMappingAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemVendorMappingAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemVendorMappingAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ItemVendorMappingAuditLog in the database
        List<ItemVendorMappingAuditLog> itemVendorMappingAuditLogList = itemVendorMappingAuditLogRepository.findAll();
        assertThat(itemVendorMappingAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItemVendorMappingAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = itemVendorMappingAuditLogRepository.findAll().size();
        itemVendorMappingAuditLog.setId(count.incrementAndGet());

        // Create the ItemVendorMappingAuditLog
        ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO = itemVendorMappingAuditLogMapper.toDto(itemVendorMappingAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemVendorMappingAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itemVendorMappingAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ItemVendorMappingAuditLog in the database
        List<ItemVendorMappingAuditLog> itemVendorMappingAuditLogList = itemVendorMappingAuditLogRepository.findAll();
        assertThat(itemVendorMappingAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItemVendorMappingAuditLog() throws Exception {
        // Initialize the database
        itemVendorMappingAuditLogRepository.saveAndFlush(itemVendorMappingAuditLog);

        int databaseSizeBeforeDelete = itemVendorMappingAuditLogRepository.findAll().size();

        // Delete the itemVendorMappingAuditLog
        restItemVendorMappingAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, itemVendorMappingAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemVendorMappingAuditLog> itemVendorMappingAuditLogList = itemVendorMappingAuditLogRepository.findAll();
        assertThat(itemVendorMappingAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
