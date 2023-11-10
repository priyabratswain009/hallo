package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PatientInsuranceAuditLog;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PatientInsuranceAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientInsuranceAuditLogMapper;
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
 * Integration tests for the {@link PatientInsuranceAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PatientInsuranceAuditLogResourceIT {

    private static final Long DEFAULT_PATINT_INSNCE_ID = 1L;
    private static final Long UPDATED_PATINT_INSNCE_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/patient-insurance-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PatientInsuranceAuditLogRepository patientInsuranceAuditLogRepository;

    @Autowired
    private PatientInsuranceAuditLogMapper patientInsuranceAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PatientInsuranceAuditLog patientInsuranceAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientInsuranceAuditLog createEntity(EntityManager em) {
        PatientInsuranceAuditLog patientInsuranceAuditLog = new PatientInsuranceAuditLog()
            .patintInsnceId(DEFAULT_PATINT_INSNCE_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return patientInsuranceAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientInsuranceAuditLog createUpdatedEntity(EntityManager em) {
        PatientInsuranceAuditLog patientInsuranceAuditLog = new PatientInsuranceAuditLog()
            .patintInsnceId(UPDATED_PATINT_INSNCE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return patientInsuranceAuditLog;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PatientInsuranceAuditLog.class).block();
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
        patientInsuranceAuditLog = createEntity(em);
    }

    @Test
    void createPatientInsuranceAuditLog() throws Exception {
        int databaseSizeBeforeCreate = patientInsuranceAuditLogRepository.findAll().collectList().block().size();
        // Create the PatientInsuranceAuditLog
        PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO = patientInsuranceAuditLogMapper.toDto(patientInsuranceAuditLog);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsuranceAuditLogDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PatientInsuranceAuditLog in the database
        List<PatientInsuranceAuditLog> patientInsuranceAuditLogList = patientInsuranceAuditLogRepository.findAll().collectList().block();
        assertThat(patientInsuranceAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        PatientInsuranceAuditLog testPatientInsuranceAuditLog = patientInsuranceAuditLogList.get(patientInsuranceAuditLogList.size() - 1);
        assertThat(testPatientInsuranceAuditLog.getPatintInsnceId()).isEqualTo(DEFAULT_PATINT_INSNCE_ID);
        assertThat(testPatientInsuranceAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPatientInsuranceAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testPatientInsuranceAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPatientInsuranceAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testPatientInsuranceAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void createPatientInsuranceAuditLogWithExistingId() throws Exception {
        // Create the PatientInsuranceAuditLog with an existing ID
        patientInsuranceAuditLog.setId(1L);
        PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO = patientInsuranceAuditLogMapper.toDto(patientInsuranceAuditLog);

        int databaseSizeBeforeCreate = patientInsuranceAuditLogRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsuranceAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsuranceAuditLog in the database
        List<PatientInsuranceAuditLog> patientInsuranceAuditLogList = patientInsuranceAuditLogRepository.findAll().collectList().block();
        assertThat(patientInsuranceAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPatientInsuranceAuditLogs() {
        // Initialize the database
        patientInsuranceAuditLogRepository.save(patientInsuranceAuditLog).block();

        // Get all the patientInsuranceAuditLogList
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
            .value(hasItem(patientInsuranceAuditLog.getId().intValue()))
            .jsonPath("$.[*].patintInsnceId")
            .value(hasItem(DEFAULT_PATINT_INSNCE_ID.intValue()))
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
    void getPatientInsuranceAuditLog() {
        // Initialize the database
        patientInsuranceAuditLogRepository.save(patientInsuranceAuditLog).block();

        // Get the patientInsuranceAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, patientInsuranceAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(patientInsuranceAuditLog.getId().intValue()))
            .jsonPath("$.patintInsnceId")
            .value(is(DEFAULT_PATINT_INSNCE_ID.intValue()))
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
    void getNonExistingPatientInsuranceAuditLog() {
        // Get the patientInsuranceAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPatientInsuranceAuditLog() throws Exception {
        // Initialize the database
        patientInsuranceAuditLogRepository.save(patientInsuranceAuditLog).block();

        int databaseSizeBeforeUpdate = patientInsuranceAuditLogRepository.findAll().collectList().block().size();

        // Update the patientInsuranceAuditLog
        PatientInsuranceAuditLog updatedPatientInsuranceAuditLog = patientInsuranceAuditLogRepository
            .findById(patientInsuranceAuditLog.getId())
            .block();
        updatedPatientInsuranceAuditLog
            .patintInsnceId(UPDATED_PATINT_INSNCE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO = patientInsuranceAuditLogMapper.toDto(updatedPatientInsuranceAuditLog);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientInsuranceAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsuranceAuditLogDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientInsuranceAuditLog in the database
        List<PatientInsuranceAuditLog> patientInsuranceAuditLogList = patientInsuranceAuditLogRepository.findAll().collectList().block();
        assertThat(patientInsuranceAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientInsuranceAuditLog testPatientInsuranceAuditLog = patientInsuranceAuditLogList.get(patientInsuranceAuditLogList.size() - 1);
        assertThat(testPatientInsuranceAuditLog.getPatintInsnceId()).isEqualTo(UPDATED_PATINT_INSNCE_ID);
        assertThat(testPatientInsuranceAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPatientInsuranceAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPatientInsuranceAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPatientInsuranceAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPatientInsuranceAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void putNonExistingPatientInsuranceAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientInsuranceAuditLogRepository.findAll().collectList().block().size();
        patientInsuranceAuditLog.setId(count.incrementAndGet());

        // Create the PatientInsuranceAuditLog
        PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO = patientInsuranceAuditLogMapper.toDto(patientInsuranceAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientInsuranceAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsuranceAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsuranceAuditLog in the database
        List<PatientInsuranceAuditLog> patientInsuranceAuditLogList = patientInsuranceAuditLogRepository.findAll().collectList().block();
        assertThat(patientInsuranceAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPatientInsuranceAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientInsuranceAuditLogRepository.findAll().collectList().block().size();
        patientInsuranceAuditLog.setId(count.incrementAndGet());

        // Create the PatientInsuranceAuditLog
        PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO = patientInsuranceAuditLogMapper.toDto(patientInsuranceAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsuranceAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsuranceAuditLog in the database
        List<PatientInsuranceAuditLog> patientInsuranceAuditLogList = patientInsuranceAuditLogRepository.findAll().collectList().block();
        assertThat(patientInsuranceAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPatientInsuranceAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientInsuranceAuditLogRepository.findAll().collectList().block().size();
        patientInsuranceAuditLog.setId(count.incrementAndGet());

        // Create the PatientInsuranceAuditLog
        PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO = patientInsuranceAuditLogMapper.toDto(patientInsuranceAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsuranceAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientInsuranceAuditLog in the database
        List<PatientInsuranceAuditLog> patientInsuranceAuditLogList = patientInsuranceAuditLogRepository.findAll().collectList().block();
        assertThat(patientInsuranceAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePatientInsuranceAuditLogWithPatch() throws Exception {
        // Initialize the database
        patientInsuranceAuditLogRepository.save(patientInsuranceAuditLog).block();

        int databaseSizeBeforeUpdate = patientInsuranceAuditLogRepository.findAll().collectList().block().size();

        // Update the patientInsuranceAuditLog using partial update
        PatientInsuranceAuditLog partialUpdatedPatientInsuranceAuditLog = new PatientInsuranceAuditLog();
        partialUpdatedPatientInsuranceAuditLog.setId(patientInsuranceAuditLog.getId());

        partialUpdatedPatientInsuranceAuditLog.patintInsnceId(UPDATED_PATINT_INSNCE_ID).dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientInsuranceAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientInsuranceAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientInsuranceAuditLog in the database
        List<PatientInsuranceAuditLog> patientInsuranceAuditLogList = patientInsuranceAuditLogRepository.findAll().collectList().block();
        assertThat(patientInsuranceAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientInsuranceAuditLog testPatientInsuranceAuditLog = patientInsuranceAuditLogList.get(patientInsuranceAuditLogList.size() - 1);
        assertThat(testPatientInsuranceAuditLog.getPatintInsnceId()).isEqualTo(UPDATED_PATINT_INSNCE_ID);
        assertThat(testPatientInsuranceAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPatientInsuranceAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testPatientInsuranceAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPatientInsuranceAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testPatientInsuranceAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void fullUpdatePatientInsuranceAuditLogWithPatch() throws Exception {
        // Initialize the database
        patientInsuranceAuditLogRepository.save(patientInsuranceAuditLog).block();

        int databaseSizeBeforeUpdate = patientInsuranceAuditLogRepository.findAll().collectList().block().size();

        // Update the patientInsuranceAuditLog using partial update
        PatientInsuranceAuditLog partialUpdatedPatientInsuranceAuditLog = new PatientInsuranceAuditLog();
        partialUpdatedPatientInsuranceAuditLog.setId(patientInsuranceAuditLog.getId());

        partialUpdatedPatientInsuranceAuditLog
            .patintInsnceId(UPDATED_PATINT_INSNCE_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientInsuranceAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientInsuranceAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientInsuranceAuditLog in the database
        List<PatientInsuranceAuditLog> patientInsuranceAuditLogList = patientInsuranceAuditLogRepository.findAll().collectList().block();
        assertThat(patientInsuranceAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientInsuranceAuditLog testPatientInsuranceAuditLog = patientInsuranceAuditLogList.get(patientInsuranceAuditLogList.size() - 1);
        assertThat(testPatientInsuranceAuditLog.getPatintInsnceId()).isEqualTo(UPDATED_PATINT_INSNCE_ID);
        assertThat(testPatientInsuranceAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPatientInsuranceAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPatientInsuranceAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPatientInsuranceAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPatientInsuranceAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void patchNonExistingPatientInsuranceAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientInsuranceAuditLogRepository.findAll().collectList().block().size();
        patientInsuranceAuditLog.setId(count.incrementAndGet());

        // Create the PatientInsuranceAuditLog
        PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO = patientInsuranceAuditLogMapper.toDto(patientInsuranceAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, patientInsuranceAuditLogDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsuranceAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsuranceAuditLog in the database
        List<PatientInsuranceAuditLog> patientInsuranceAuditLogList = patientInsuranceAuditLogRepository.findAll().collectList().block();
        assertThat(patientInsuranceAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPatientInsuranceAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientInsuranceAuditLogRepository.findAll().collectList().block().size();
        patientInsuranceAuditLog.setId(count.incrementAndGet());

        // Create the PatientInsuranceAuditLog
        PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO = patientInsuranceAuditLogMapper.toDto(patientInsuranceAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsuranceAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsuranceAuditLog in the database
        List<PatientInsuranceAuditLog> patientInsuranceAuditLogList = patientInsuranceAuditLogRepository.findAll().collectList().block();
        assertThat(patientInsuranceAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPatientInsuranceAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientInsuranceAuditLogRepository.findAll().collectList().block().size();
        patientInsuranceAuditLog.setId(count.incrementAndGet());

        // Create the PatientInsuranceAuditLog
        PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO = patientInsuranceAuditLogMapper.toDto(patientInsuranceAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsuranceAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientInsuranceAuditLog in the database
        List<PatientInsuranceAuditLog> patientInsuranceAuditLogList = patientInsuranceAuditLogRepository.findAll().collectList().block();
        assertThat(patientInsuranceAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePatientInsuranceAuditLog() {
        // Initialize the database
        patientInsuranceAuditLogRepository.save(patientInsuranceAuditLog).block();

        int databaseSizeBeforeDelete = patientInsuranceAuditLogRepository.findAll().collectList().block().size();

        // Delete the patientInsuranceAuditLog
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, patientInsuranceAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PatientInsuranceAuditLog> patientInsuranceAuditLogList = patientInsuranceAuditLogRepository.findAll().collectList().block();
        assertThat(patientInsuranceAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
