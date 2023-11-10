package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PatientMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PatientMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientMasterAuditLogMapper;
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
 * Integration tests for the {@link PatientMasterAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PatientMasterAuditLogResourceIT {

    private static final Long DEFAULT_PATINT_ID = 1L;
    private static final Long UPDATED_PATINT_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/patient-master-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PatientMasterAuditLogRepository patientMasterAuditLogRepository;

    @Autowired
    private PatientMasterAuditLogMapper patientMasterAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PatientMasterAuditLog patientMasterAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientMasterAuditLog createEntity(EntityManager em) {
        PatientMasterAuditLog patientMasterAuditLog = new PatientMasterAuditLog()
            .patintId(DEFAULT_PATINT_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return patientMasterAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientMasterAuditLog createUpdatedEntity(EntityManager em) {
        PatientMasterAuditLog patientMasterAuditLog = new PatientMasterAuditLog()
            .patintId(UPDATED_PATINT_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return patientMasterAuditLog;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PatientMasterAuditLog.class).block();
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
        patientMasterAuditLog = createEntity(em);
    }

    @Test
    void createPatientMasterAuditLog() throws Exception {
        int databaseSizeBeforeCreate = patientMasterAuditLogRepository.findAll().collectList().block().size();
        // Create the PatientMasterAuditLog
        PatientMasterAuditLogDTO patientMasterAuditLogDTO = patientMasterAuditLogMapper.toDto(patientMasterAuditLog);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PatientMasterAuditLog in the database
        List<PatientMasterAuditLog> patientMasterAuditLogList = patientMasterAuditLogRepository.findAll().collectList().block();
        assertThat(patientMasterAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        PatientMasterAuditLog testPatientMasterAuditLog = patientMasterAuditLogList.get(patientMasterAuditLogList.size() - 1);
        assertThat(testPatientMasterAuditLog.getPatintId()).isEqualTo(DEFAULT_PATINT_ID);
        assertThat(testPatientMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPatientMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testPatientMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPatientMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testPatientMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void createPatientMasterAuditLogWithExistingId() throws Exception {
        // Create the PatientMasterAuditLog with an existing ID
        patientMasterAuditLog.setId(1L);
        PatientMasterAuditLogDTO patientMasterAuditLogDTO = patientMasterAuditLogMapper.toDto(patientMasterAuditLog);

        int databaseSizeBeforeCreate = patientMasterAuditLogRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientMasterAuditLog in the database
        List<PatientMasterAuditLog> patientMasterAuditLogList = patientMasterAuditLogRepository.findAll().collectList().block();
        assertThat(patientMasterAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPatientMasterAuditLogs() {
        // Initialize the database
        patientMasterAuditLogRepository.save(patientMasterAuditLog).block();

        // Get all the patientMasterAuditLogList
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
            .value(hasItem(patientMasterAuditLog.getId().intValue()))
            .jsonPath("$.[*].patintId")
            .value(hasItem(DEFAULT_PATINT_ID.intValue()))
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
    void getPatientMasterAuditLog() {
        // Initialize the database
        patientMasterAuditLogRepository.save(patientMasterAuditLog).block();

        // Get the patientMasterAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, patientMasterAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(patientMasterAuditLog.getId().intValue()))
            .jsonPath("$.patintId")
            .value(is(DEFAULT_PATINT_ID.intValue()))
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
    void getNonExistingPatientMasterAuditLog() {
        // Get the patientMasterAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPatientMasterAuditLog() throws Exception {
        // Initialize the database
        patientMasterAuditLogRepository.save(patientMasterAuditLog).block();

        int databaseSizeBeforeUpdate = patientMasterAuditLogRepository.findAll().collectList().block().size();

        // Update the patientMasterAuditLog
        PatientMasterAuditLog updatedPatientMasterAuditLog = patientMasterAuditLogRepository
            .findById(patientMasterAuditLog.getId())
            .block();
        updatedPatientMasterAuditLog
            .patintId(UPDATED_PATINT_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        PatientMasterAuditLogDTO patientMasterAuditLogDTO = patientMasterAuditLogMapper.toDto(updatedPatientMasterAuditLog);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientMasterAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientMasterAuditLog in the database
        List<PatientMasterAuditLog> patientMasterAuditLogList = patientMasterAuditLogRepository.findAll().collectList().block();
        assertThat(patientMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientMasterAuditLog testPatientMasterAuditLog = patientMasterAuditLogList.get(patientMasterAuditLogList.size() - 1);
        assertThat(testPatientMasterAuditLog.getPatintId()).isEqualTo(UPDATED_PATINT_ID);
        assertThat(testPatientMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPatientMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPatientMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPatientMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPatientMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void putNonExistingPatientMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientMasterAuditLogRepository.findAll().collectList().block().size();
        patientMasterAuditLog.setId(count.incrementAndGet());

        // Create the PatientMasterAuditLog
        PatientMasterAuditLogDTO patientMasterAuditLogDTO = patientMasterAuditLogMapper.toDto(patientMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientMasterAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientMasterAuditLog in the database
        List<PatientMasterAuditLog> patientMasterAuditLogList = patientMasterAuditLogRepository.findAll().collectList().block();
        assertThat(patientMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPatientMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientMasterAuditLogRepository.findAll().collectList().block().size();
        patientMasterAuditLog.setId(count.incrementAndGet());

        // Create the PatientMasterAuditLog
        PatientMasterAuditLogDTO patientMasterAuditLogDTO = patientMasterAuditLogMapper.toDto(patientMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientMasterAuditLog in the database
        List<PatientMasterAuditLog> patientMasterAuditLogList = patientMasterAuditLogRepository.findAll().collectList().block();
        assertThat(patientMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPatientMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientMasterAuditLogRepository.findAll().collectList().block().size();
        patientMasterAuditLog.setId(count.incrementAndGet());

        // Create the PatientMasterAuditLog
        PatientMasterAuditLogDTO patientMasterAuditLogDTO = patientMasterAuditLogMapper.toDto(patientMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientMasterAuditLog in the database
        List<PatientMasterAuditLog> patientMasterAuditLogList = patientMasterAuditLogRepository.findAll().collectList().block();
        assertThat(patientMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePatientMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        patientMasterAuditLogRepository.save(patientMasterAuditLog).block();

        int databaseSizeBeforeUpdate = patientMasterAuditLogRepository.findAll().collectList().block().size();

        // Update the patientMasterAuditLog using partial update
        PatientMasterAuditLog partialUpdatedPatientMasterAuditLog = new PatientMasterAuditLog();
        partialUpdatedPatientMasterAuditLog.setId(patientMasterAuditLog.getId());

        partialUpdatedPatientMasterAuditLog.dmlTimestamp(UPDATED_DML_TIMESTAMP);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientMasterAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientMasterAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientMasterAuditLog in the database
        List<PatientMasterAuditLog> patientMasterAuditLogList = patientMasterAuditLogRepository.findAll().collectList().block();
        assertThat(patientMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientMasterAuditLog testPatientMasterAuditLog = patientMasterAuditLogList.get(patientMasterAuditLogList.size() - 1);
        assertThat(testPatientMasterAuditLog.getPatintId()).isEqualTo(DEFAULT_PATINT_ID);
        assertThat(testPatientMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPatientMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testPatientMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPatientMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPatientMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void fullUpdatePatientMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        patientMasterAuditLogRepository.save(patientMasterAuditLog).block();

        int databaseSizeBeforeUpdate = patientMasterAuditLogRepository.findAll().collectList().block().size();

        // Update the patientMasterAuditLog using partial update
        PatientMasterAuditLog partialUpdatedPatientMasterAuditLog = new PatientMasterAuditLog();
        partialUpdatedPatientMasterAuditLog.setId(patientMasterAuditLog.getId());

        partialUpdatedPatientMasterAuditLog
            .patintId(UPDATED_PATINT_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientMasterAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientMasterAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientMasterAuditLog in the database
        List<PatientMasterAuditLog> patientMasterAuditLogList = patientMasterAuditLogRepository.findAll().collectList().block();
        assertThat(patientMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientMasterAuditLog testPatientMasterAuditLog = patientMasterAuditLogList.get(patientMasterAuditLogList.size() - 1);
        assertThat(testPatientMasterAuditLog.getPatintId()).isEqualTo(UPDATED_PATINT_ID);
        assertThat(testPatientMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPatientMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPatientMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPatientMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPatientMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void patchNonExistingPatientMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientMasterAuditLogRepository.findAll().collectList().block().size();
        patientMasterAuditLog.setId(count.incrementAndGet());

        // Create the PatientMasterAuditLog
        PatientMasterAuditLogDTO patientMasterAuditLogDTO = patientMasterAuditLogMapper.toDto(patientMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, patientMasterAuditLogDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientMasterAuditLog in the database
        List<PatientMasterAuditLog> patientMasterAuditLogList = patientMasterAuditLogRepository.findAll().collectList().block();
        assertThat(patientMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPatientMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientMasterAuditLogRepository.findAll().collectList().block().size();
        patientMasterAuditLog.setId(count.incrementAndGet());

        // Create the PatientMasterAuditLog
        PatientMasterAuditLogDTO patientMasterAuditLogDTO = patientMasterAuditLogMapper.toDto(patientMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientMasterAuditLog in the database
        List<PatientMasterAuditLog> patientMasterAuditLogList = patientMasterAuditLogRepository.findAll().collectList().block();
        assertThat(patientMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPatientMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientMasterAuditLogRepository.findAll().collectList().block().size();
        patientMasterAuditLog.setId(count.incrementAndGet());

        // Create the PatientMasterAuditLog
        PatientMasterAuditLogDTO patientMasterAuditLogDTO = patientMasterAuditLogMapper.toDto(patientMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientMasterAuditLog in the database
        List<PatientMasterAuditLog> patientMasterAuditLogList = patientMasterAuditLogRepository.findAll().collectList().block();
        assertThat(patientMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePatientMasterAuditLog() {
        // Initialize the database
        patientMasterAuditLogRepository.save(patientMasterAuditLog).block();

        int databaseSizeBeforeDelete = patientMasterAuditLogRepository.findAll().collectList().block().size();

        // Delete the patientMasterAuditLog
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, patientMasterAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PatientMasterAuditLog> patientMasterAuditLogList = patientMasterAuditLogRepository.findAll().collectList().block();
        assertThat(patientMasterAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
