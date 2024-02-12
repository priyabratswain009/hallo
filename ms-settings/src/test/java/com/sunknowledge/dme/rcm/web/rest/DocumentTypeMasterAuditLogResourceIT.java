package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.DocumentTypeMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.DocumentTypeMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.DocumentTypeMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.DocumentTypeMasterAuditLogMapper;
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
 * Integration tests for the {@link DocumentTypeMasterAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DocumentTypeMasterAuditLogResourceIT {

    private static final Long DEFAULT_DCUMET_TPE_ID = 1L;
    private static final Long UPDATED_DCUMET_TPE_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/document-type-master-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DocumentTypeMasterAuditLogRepository documentTypeMasterAuditLogRepository;

    @Autowired
    private DocumentTypeMasterAuditLogMapper documentTypeMasterAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDocumentTypeMasterAuditLogMockMvc;

    private DocumentTypeMasterAuditLog documentTypeMasterAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DocumentTypeMasterAuditLog createEntity(EntityManager em) {
        DocumentTypeMasterAuditLog documentTypeMasterAuditLog = new DocumentTypeMasterAuditLog()
            .dcumetTpeId(DEFAULT_DCUMET_TPE_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return documentTypeMasterAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DocumentTypeMasterAuditLog createUpdatedEntity(EntityManager em) {
        DocumentTypeMasterAuditLog documentTypeMasterAuditLog = new DocumentTypeMasterAuditLog()
            .dcumetTpeId(UPDATED_DCUMET_TPE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return documentTypeMasterAuditLog;
    }

    @BeforeEach
    public void initTest() {
        documentTypeMasterAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createDocumentTypeMasterAuditLog() throws Exception {
        int databaseSizeBeforeCreate = documentTypeMasterAuditLogRepository.findAll().size();
        // Create the DocumentTypeMasterAuditLog
        DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO = documentTypeMasterAuditLogMapper.toDto(documentTypeMasterAuditLog);
        restDocumentTypeMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentTypeMasterAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DocumentTypeMasterAuditLog in the database
        List<DocumentTypeMasterAuditLog> documentTypeMasterAuditLogList = documentTypeMasterAuditLogRepository.findAll();
        assertThat(documentTypeMasterAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        DocumentTypeMasterAuditLog testDocumentTypeMasterAuditLog = documentTypeMasterAuditLogList.get(
            documentTypeMasterAuditLogList.size() - 1
        );
        assertThat(testDocumentTypeMasterAuditLog.getDcumetTpeId()).isEqualTo(DEFAULT_DCUMET_TPE_ID);
        assertThat(testDocumentTypeMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testDocumentTypeMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testDocumentTypeMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testDocumentTypeMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testDocumentTypeMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createDocumentTypeMasterAuditLogWithExistingId() throws Exception {
        // Create the DocumentTypeMasterAuditLog with an existing ID
        documentTypeMasterAuditLog.setId(1L);
        DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO = documentTypeMasterAuditLogMapper.toDto(documentTypeMasterAuditLog);

        int databaseSizeBeforeCreate = documentTypeMasterAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDocumentTypeMasterAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentTypeMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentTypeMasterAuditLog in the database
        List<DocumentTypeMasterAuditLog> documentTypeMasterAuditLogList = documentTypeMasterAuditLogRepository.findAll();
        assertThat(documentTypeMasterAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDocumentTypeMasterAuditLogs() throws Exception {
        // Initialize the database
        documentTypeMasterAuditLogRepository.saveAndFlush(documentTypeMasterAuditLog);

        // Get all the documentTypeMasterAuditLogList
        restDocumentTypeMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(documentTypeMasterAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].dcumetTpeId").value(hasItem(DEFAULT_DCUMET_TPE_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getDocumentTypeMasterAuditLog() throws Exception {
        // Initialize the database
        documentTypeMasterAuditLogRepository.saveAndFlush(documentTypeMasterAuditLog);

        // Get the documentTypeMasterAuditLog
        restDocumentTypeMasterAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, documentTypeMasterAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(documentTypeMasterAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.dcumetTpeId").value(DEFAULT_DCUMET_TPE_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingDocumentTypeMasterAuditLog() throws Exception {
        // Get the documentTypeMasterAuditLog
        restDocumentTypeMasterAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDocumentTypeMasterAuditLog() throws Exception {
        // Initialize the database
        documentTypeMasterAuditLogRepository.saveAndFlush(documentTypeMasterAuditLog);

        int databaseSizeBeforeUpdate = documentTypeMasterAuditLogRepository.findAll().size();

        // Update the documentTypeMasterAuditLog
        DocumentTypeMasterAuditLog updatedDocumentTypeMasterAuditLog = documentTypeMasterAuditLogRepository
            .findById(documentTypeMasterAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedDocumentTypeMasterAuditLog are not directly saved in db
        em.detach(updatedDocumentTypeMasterAuditLog);
        updatedDocumentTypeMasterAuditLog
            .dcumetTpeId(UPDATED_DCUMET_TPE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO = documentTypeMasterAuditLogMapper.toDto(
            updatedDocumentTypeMasterAuditLog
        );

        restDocumentTypeMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, documentTypeMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentTypeMasterAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the DocumentTypeMasterAuditLog in the database
        List<DocumentTypeMasterAuditLog> documentTypeMasterAuditLogList = documentTypeMasterAuditLogRepository.findAll();
        assertThat(documentTypeMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        DocumentTypeMasterAuditLog testDocumentTypeMasterAuditLog = documentTypeMasterAuditLogList.get(
            documentTypeMasterAuditLogList.size() - 1
        );
        assertThat(testDocumentTypeMasterAuditLog.getDcumetTpeId()).isEqualTo(UPDATED_DCUMET_TPE_ID);
        assertThat(testDocumentTypeMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testDocumentTypeMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testDocumentTypeMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testDocumentTypeMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testDocumentTypeMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingDocumentTypeMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = documentTypeMasterAuditLogRepository.findAll().size();
        documentTypeMasterAuditLog.setId(count.incrementAndGet());

        // Create the DocumentTypeMasterAuditLog
        DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO = documentTypeMasterAuditLogMapper.toDto(documentTypeMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentTypeMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, documentTypeMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentTypeMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentTypeMasterAuditLog in the database
        List<DocumentTypeMasterAuditLog> documentTypeMasterAuditLogList = documentTypeMasterAuditLogRepository.findAll();
        assertThat(documentTypeMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDocumentTypeMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = documentTypeMasterAuditLogRepository.findAll().size();
        documentTypeMasterAuditLog.setId(count.incrementAndGet());

        // Create the DocumentTypeMasterAuditLog
        DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO = documentTypeMasterAuditLogMapper.toDto(documentTypeMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentTypeMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentTypeMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentTypeMasterAuditLog in the database
        List<DocumentTypeMasterAuditLog> documentTypeMasterAuditLogList = documentTypeMasterAuditLogRepository.findAll();
        assertThat(documentTypeMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDocumentTypeMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = documentTypeMasterAuditLogRepository.findAll().size();
        documentTypeMasterAuditLog.setId(count.incrementAndGet());

        // Create the DocumentTypeMasterAuditLog
        DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO = documentTypeMasterAuditLogMapper.toDto(documentTypeMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentTypeMasterAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentTypeMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DocumentTypeMasterAuditLog in the database
        List<DocumentTypeMasterAuditLog> documentTypeMasterAuditLogList = documentTypeMasterAuditLogRepository.findAll();
        assertThat(documentTypeMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDocumentTypeMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        documentTypeMasterAuditLogRepository.saveAndFlush(documentTypeMasterAuditLog);

        int databaseSizeBeforeUpdate = documentTypeMasterAuditLogRepository.findAll().size();

        // Update the documentTypeMasterAuditLog using partial update
        DocumentTypeMasterAuditLog partialUpdatedDocumentTypeMasterAuditLog = new DocumentTypeMasterAuditLog();
        partialUpdatedDocumentTypeMasterAuditLog.setId(documentTypeMasterAuditLog.getId());

        partialUpdatedDocumentTypeMasterAuditLog
            .dcumetTpeId(UPDATED_DCUMET_TPE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP);

        restDocumentTypeMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDocumentTypeMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDocumentTypeMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the DocumentTypeMasterAuditLog in the database
        List<DocumentTypeMasterAuditLog> documentTypeMasterAuditLogList = documentTypeMasterAuditLogRepository.findAll();
        assertThat(documentTypeMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        DocumentTypeMasterAuditLog testDocumentTypeMasterAuditLog = documentTypeMasterAuditLogList.get(
            documentTypeMasterAuditLogList.size() - 1
        );
        assertThat(testDocumentTypeMasterAuditLog.getDcumetTpeId()).isEqualTo(UPDATED_DCUMET_TPE_ID);
        assertThat(testDocumentTypeMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testDocumentTypeMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testDocumentTypeMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testDocumentTypeMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testDocumentTypeMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateDocumentTypeMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        documentTypeMasterAuditLogRepository.saveAndFlush(documentTypeMasterAuditLog);

        int databaseSizeBeforeUpdate = documentTypeMasterAuditLogRepository.findAll().size();

        // Update the documentTypeMasterAuditLog using partial update
        DocumentTypeMasterAuditLog partialUpdatedDocumentTypeMasterAuditLog = new DocumentTypeMasterAuditLog();
        partialUpdatedDocumentTypeMasterAuditLog.setId(documentTypeMasterAuditLog.getId());

        partialUpdatedDocumentTypeMasterAuditLog
            .dcumetTpeId(UPDATED_DCUMET_TPE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restDocumentTypeMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDocumentTypeMasterAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDocumentTypeMasterAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the DocumentTypeMasterAuditLog in the database
        List<DocumentTypeMasterAuditLog> documentTypeMasterAuditLogList = documentTypeMasterAuditLogRepository.findAll();
        assertThat(documentTypeMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        DocumentTypeMasterAuditLog testDocumentTypeMasterAuditLog = documentTypeMasterAuditLogList.get(
            documentTypeMasterAuditLogList.size() - 1
        );
        assertThat(testDocumentTypeMasterAuditLog.getDcumetTpeId()).isEqualTo(UPDATED_DCUMET_TPE_ID);
        assertThat(testDocumentTypeMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testDocumentTypeMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testDocumentTypeMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testDocumentTypeMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testDocumentTypeMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingDocumentTypeMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = documentTypeMasterAuditLogRepository.findAll().size();
        documentTypeMasterAuditLog.setId(count.incrementAndGet());

        // Create the DocumentTypeMasterAuditLog
        DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO = documentTypeMasterAuditLogMapper.toDto(documentTypeMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentTypeMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, documentTypeMasterAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(documentTypeMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentTypeMasterAuditLog in the database
        List<DocumentTypeMasterAuditLog> documentTypeMasterAuditLogList = documentTypeMasterAuditLogRepository.findAll();
        assertThat(documentTypeMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDocumentTypeMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = documentTypeMasterAuditLogRepository.findAll().size();
        documentTypeMasterAuditLog.setId(count.incrementAndGet());

        // Create the DocumentTypeMasterAuditLog
        DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO = documentTypeMasterAuditLogMapper.toDto(documentTypeMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentTypeMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(documentTypeMasterAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentTypeMasterAuditLog in the database
        List<DocumentTypeMasterAuditLog> documentTypeMasterAuditLogList = documentTypeMasterAuditLogRepository.findAll();
        assertThat(documentTypeMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDocumentTypeMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = documentTypeMasterAuditLogRepository.findAll().size();
        documentTypeMasterAuditLog.setId(count.incrementAndGet());

        // Create the DocumentTypeMasterAuditLog
        DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO = documentTypeMasterAuditLogMapper.toDto(documentTypeMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentTypeMasterAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(documentTypeMasterAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DocumentTypeMasterAuditLog in the database
        List<DocumentTypeMasterAuditLog> documentTypeMasterAuditLogList = documentTypeMasterAuditLogRepository.findAll();
        assertThat(documentTypeMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDocumentTypeMasterAuditLog() throws Exception {
        // Initialize the database
        documentTypeMasterAuditLogRepository.saveAndFlush(documentTypeMasterAuditLog);

        int databaseSizeBeforeDelete = documentTypeMasterAuditLogRepository.findAll().size();

        // Delete the documentTypeMasterAuditLog
        restDocumentTypeMasterAuditLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, documentTypeMasterAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DocumentTypeMasterAuditLog> documentTypeMasterAuditLogList = documentTypeMasterAuditLogRepository.findAll();
        assertThat(documentTypeMasterAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
