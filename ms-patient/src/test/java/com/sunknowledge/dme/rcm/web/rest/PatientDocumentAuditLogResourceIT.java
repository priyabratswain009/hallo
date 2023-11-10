package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PatientDocumentAuditLog;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PatientDocumentAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientDocumentAuditLogMapper;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link PatientDocumentAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PatientDocumentAuditLogResourceIT {

    private static final Long DEFAULT_PTIENT_DOCMT_ID = 1L;
    private static final Long UPDATED_PTIENT_DOCMT_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/patient-document-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PatientDocumentAuditLogRepository patientDocumentAuditLogRepository;

    @Autowired
    private PatientDocumentAuditLogMapper patientDocumentAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PatientDocumentAuditLog patientDocumentAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientDocumentAuditLog createEntity(EntityManager em) {
        PatientDocumentAuditLog patientDocumentAuditLog = new PatientDocumentAuditLog()
            .ptientDocmtId(DEFAULT_PTIENT_DOCMT_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return patientDocumentAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientDocumentAuditLog createUpdatedEntity(EntityManager em) {
        PatientDocumentAuditLog patientDocumentAuditLog = new PatientDocumentAuditLog()
            .ptientDocmtId(UPDATED_PTIENT_DOCMT_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return patientDocumentAuditLog;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PatientDocumentAuditLog.class).block();
        } catch (Exception e) {
            // It can fail, if other entities are still referring this - it will be removed later.
        }
    }

    @AfterEach
    public void cleanup() {
        deleteEntities(em);
    }

    @BeforeEach
    public void setupCsrf() {
        webTestClient = webTestClient.mutateWith(csrf());
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        patientDocumentAuditLog = createEntity(em);
    }

    @Test
    void createPatientDocumentAuditLog() throws Exception {
        int databaseSizeBeforeCreate = patientDocumentAuditLogRepository.findAll().collectList().block().size();
        // Create the PatientDocumentAuditLog
        PatientDocumentAuditLogDTO patientDocumentAuditLogDTO = patientDocumentAuditLogMapper.toDto(patientDocumentAuditLog);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentAuditLogDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PatientDocumentAuditLog in the database
        List<PatientDocumentAuditLog> patientDocumentAuditLogList = patientDocumentAuditLogRepository.findAll().collectList().block();
        assertThat(patientDocumentAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        PatientDocumentAuditLog testPatientDocumentAuditLog = patientDocumentAuditLogList.get(patientDocumentAuditLogList.size() - 1);
        assertThat(testPatientDocumentAuditLog.getPtientDocmtId()).isEqualTo(DEFAULT_PTIENT_DOCMT_ID);
        assertThat(testPatientDocumentAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPatientDocumentAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testPatientDocumentAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPatientDocumentAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testPatientDocumentAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void createPatientDocumentAuditLogWithExistingId() throws Exception {
        // Create the PatientDocumentAuditLog with an existing ID
        patientDocumentAuditLog.setId(1L);
        PatientDocumentAuditLogDTO patientDocumentAuditLogDTO = patientDocumentAuditLogMapper.toDto(patientDocumentAuditLog);

        int databaseSizeBeforeCreate = patientDocumentAuditLogRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDocumentAuditLog in the database
        List<PatientDocumentAuditLog> patientDocumentAuditLogList = patientDocumentAuditLogRepository.findAll().collectList().block();
        assertThat(patientDocumentAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPatientDocumentAuditLogs() {
        // Initialize the database
        patientDocumentAuditLogRepository.save(patientDocumentAuditLog).block();

        // Get all the patientDocumentAuditLogList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(patientDocumentAuditLog.getId().intValue()))
            .jsonPath("$.[*].ptientDocmtId")
            .value(hasItem(DEFAULT_PTIENT_DOCMT_ID.intValue()))
            .jsonPath("$.[*].oldRowData")
            .value(hasItem(DEFAULT_OLD_ROW_DATA))
            .jsonPath("$.[*].newRowData")
            .value(hasItem(DEFAULT_NEW_ROW_DATA))
            .jsonPath("$.[*].dmlType")
            .value(hasItem(DEFAULT_DML_TYPE))
            .jsonPath("$.[*].dmlTimestamp")
            .value(hasItem(DEFAULT_DML_TIMESTAMP.toString()))
            .jsonPath("$.[*].dmlCreatedBy")
            .value(hasItem(DEFAULT_DML_CREATED_BY));
    }

    @Test
    void getPatientDocumentAuditLog() {
        // Initialize the database
        patientDocumentAuditLogRepository.save(patientDocumentAuditLog).block();

        // Get the patientDocumentAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, patientDocumentAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(patientDocumentAuditLog.getId().intValue()))
            .jsonPath("$.ptientDocmtId")
            .value(is(DEFAULT_PTIENT_DOCMT_ID.intValue()))
            .jsonPath("$.oldRowData")
            .value(is(DEFAULT_OLD_ROW_DATA))
            .jsonPath("$.newRowData")
            .value(is(DEFAULT_NEW_ROW_DATA))
            .jsonPath("$.dmlType")
            .value(is(DEFAULT_DML_TYPE))
            .jsonPath("$.dmlTimestamp")
            .value(is(DEFAULT_DML_TIMESTAMP.toString()))
            .jsonPath("$.dmlCreatedBy")
            .value(is(DEFAULT_DML_CREATED_BY));
    }

    @Test
    void getNonExistingPatientDocumentAuditLog() {
        // Get the patientDocumentAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPatientDocumentAuditLog() throws Exception {
        // Initialize the database
        patientDocumentAuditLogRepository.save(patientDocumentAuditLog).block();

        int databaseSizeBeforeUpdate = patientDocumentAuditLogRepository.findAll().collectList().block().size();

        // Update the patientDocumentAuditLog
        PatientDocumentAuditLog updatedPatientDocumentAuditLog = patientDocumentAuditLogRepository
            .findById(patientDocumentAuditLog.getId())
            .block();
        updatedPatientDocumentAuditLog
            .ptientDocmtId(UPDATED_PTIENT_DOCMT_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        PatientDocumentAuditLogDTO patientDocumentAuditLogDTO = patientDocumentAuditLogMapper.toDto(updatedPatientDocumentAuditLog);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientDocumentAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentAuditLogDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDocumentAuditLog in the database
        List<PatientDocumentAuditLog> patientDocumentAuditLogList = patientDocumentAuditLogRepository.findAll().collectList().block();
        assertThat(patientDocumentAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientDocumentAuditLog testPatientDocumentAuditLog = patientDocumentAuditLogList.get(patientDocumentAuditLogList.size() - 1);
        assertThat(testPatientDocumentAuditLog.getPtientDocmtId()).isEqualTo(UPDATED_PTIENT_DOCMT_ID);
        assertThat(testPatientDocumentAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPatientDocumentAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPatientDocumentAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPatientDocumentAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPatientDocumentAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void putNonExistingPatientDocumentAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientDocumentAuditLogRepository.findAll().collectList().block().size();
        patientDocumentAuditLog.setId(count.incrementAndGet());

        // Create the PatientDocumentAuditLog
        PatientDocumentAuditLogDTO patientDocumentAuditLogDTO = patientDocumentAuditLogMapper.toDto(patientDocumentAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientDocumentAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDocumentAuditLog in the database
        List<PatientDocumentAuditLog> patientDocumentAuditLogList = patientDocumentAuditLogRepository.findAll().collectList().block();
        assertThat(patientDocumentAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPatientDocumentAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientDocumentAuditLogRepository.findAll().collectList().block().size();
        patientDocumentAuditLog.setId(count.incrementAndGet());

        // Create the PatientDocumentAuditLog
        PatientDocumentAuditLogDTO patientDocumentAuditLogDTO = patientDocumentAuditLogMapper.toDto(patientDocumentAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDocumentAuditLog in the database
        List<PatientDocumentAuditLog> patientDocumentAuditLogList = patientDocumentAuditLogRepository.findAll().collectList().block();
        assertThat(patientDocumentAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPatientDocumentAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientDocumentAuditLogRepository.findAll().collectList().block().size();
        patientDocumentAuditLog.setId(count.incrementAndGet());

        // Create the PatientDocumentAuditLog
        PatientDocumentAuditLogDTO patientDocumentAuditLogDTO = patientDocumentAuditLogMapper.toDto(patientDocumentAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientDocumentAuditLog in the database
        List<PatientDocumentAuditLog> patientDocumentAuditLogList = patientDocumentAuditLogRepository.findAll().collectList().block();
        assertThat(patientDocumentAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePatientDocumentAuditLogWithPatch() throws Exception {
        // Initialize the database
        patientDocumentAuditLogRepository.save(patientDocumentAuditLog).block();

        int databaseSizeBeforeUpdate = patientDocumentAuditLogRepository.findAll().collectList().block().size();

        // Update the patientDocumentAuditLog using partial update
        PatientDocumentAuditLog partialUpdatedPatientDocumentAuditLog = new PatientDocumentAuditLog();
        partialUpdatedPatientDocumentAuditLog.setId(patientDocumentAuditLog.getId());

        partialUpdatedPatientDocumentAuditLog
            .ptientDocmtId(UPDATED_PTIENT_DOCMT_ID)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientDocumentAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientDocumentAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDocumentAuditLog in the database
        List<PatientDocumentAuditLog> patientDocumentAuditLogList = patientDocumentAuditLogRepository.findAll().collectList().block();
        assertThat(patientDocumentAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientDocumentAuditLog testPatientDocumentAuditLog = patientDocumentAuditLogList.get(patientDocumentAuditLogList.size() - 1);
        assertThat(testPatientDocumentAuditLog.getPtientDocmtId()).isEqualTo(UPDATED_PTIENT_DOCMT_ID);
        assertThat(testPatientDocumentAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPatientDocumentAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPatientDocumentAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPatientDocumentAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPatientDocumentAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void fullUpdatePatientDocumentAuditLogWithPatch() throws Exception {
        // Initialize the database
        patientDocumentAuditLogRepository.save(patientDocumentAuditLog).block();

        int databaseSizeBeforeUpdate = patientDocumentAuditLogRepository.findAll().collectList().block().size();

        // Update the patientDocumentAuditLog using partial update
        PatientDocumentAuditLog partialUpdatedPatientDocumentAuditLog = new PatientDocumentAuditLog();
        partialUpdatedPatientDocumentAuditLog.setId(patientDocumentAuditLog.getId());

        partialUpdatedPatientDocumentAuditLog
            .ptientDocmtId(UPDATED_PTIENT_DOCMT_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientDocumentAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientDocumentAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDocumentAuditLog in the database
        List<PatientDocumentAuditLog> patientDocumentAuditLogList = patientDocumentAuditLogRepository.findAll().collectList().block();
        assertThat(patientDocumentAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientDocumentAuditLog testPatientDocumentAuditLog = patientDocumentAuditLogList.get(patientDocumentAuditLogList.size() - 1);
        assertThat(testPatientDocumentAuditLog.getPtientDocmtId()).isEqualTo(UPDATED_PTIENT_DOCMT_ID);
        assertThat(testPatientDocumentAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPatientDocumentAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPatientDocumentAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPatientDocumentAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPatientDocumentAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void patchNonExistingPatientDocumentAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientDocumentAuditLogRepository.findAll().collectList().block().size();
        patientDocumentAuditLog.setId(count.incrementAndGet());

        // Create the PatientDocumentAuditLog
        PatientDocumentAuditLogDTO patientDocumentAuditLogDTO = patientDocumentAuditLogMapper.toDto(patientDocumentAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, patientDocumentAuditLogDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDocumentAuditLog in the database
        List<PatientDocumentAuditLog> patientDocumentAuditLogList = patientDocumentAuditLogRepository.findAll().collectList().block();
        assertThat(patientDocumentAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPatientDocumentAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientDocumentAuditLogRepository.findAll().collectList().block().size();
        patientDocumentAuditLog.setId(count.incrementAndGet());

        // Create the PatientDocumentAuditLog
        PatientDocumentAuditLogDTO patientDocumentAuditLogDTO = patientDocumentAuditLogMapper.toDto(patientDocumentAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDocumentAuditLog in the database
        List<PatientDocumentAuditLog> patientDocumentAuditLogList = patientDocumentAuditLogRepository.findAll().collectList().block();
        assertThat(patientDocumentAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPatientDocumentAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientDocumentAuditLogRepository.findAll().collectList().block().size();
        patientDocumentAuditLog.setId(count.incrementAndGet());

        // Create the PatientDocumentAuditLog
        PatientDocumentAuditLogDTO patientDocumentAuditLogDTO = patientDocumentAuditLogMapper.toDto(patientDocumentAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDocumentAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientDocumentAuditLog in the database
        List<PatientDocumentAuditLog> patientDocumentAuditLogList = patientDocumentAuditLogRepository.findAll().collectList().block();
        assertThat(patientDocumentAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePatientDocumentAuditLog() {
        // Initialize the database
        patientDocumentAuditLogRepository.save(patientDocumentAuditLog).block();

        int databaseSizeBeforeDelete = patientDocumentAuditLogRepository.findAll().collectList().block().size();

        // Delete the patientDocumentAuditLog
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, patientDocumentAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PatientDocumentAuditLog> patientDocumentAuditLogList = patientDocumentAuditLogRepository.findAll().collectList().block();
        assertThat(patientDocumentAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
