package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PatientDiagnosisAuditLog;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PatientDiagnosisAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientDiagnosisAuditLogMapper;
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
 * Integration tests for the {@link PatientDiagnosisAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PatientDiagnosisAuditLogResourceIT {

    private static final Long DEFAULT_PATINT_DIAGOIS_ID = 1L;
    private static final Long UPDATED_PATINT_DIAGOIS_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/patient-diagnosis-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PatientDiagnosisAuditLogRepository patientDiagnosisAuditLogRepository;

    @Autowired
    private PatientDiagnosisAuditLogMapper patientDiagnosisAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PatientDiagnosisAuditLog patientDiagnosisAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientDiagnosisAuditLog createEntity(EntityManager em) {
        PatientDiagnosisAuditLog patientDiagnosisAuditLog = new PatientDiagnosisAuditLog()
            .patintDiagoisId(DEFAULT_PATINT_DIAGOIS_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return patientDiagnosisAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientDiagnosisAuditLog createUpdatedEntity(EntityManager em) {
        PatientDiagnosisAuditLog patientDiagnosisAuditLog = new PatientDiagnosisAuditLog()
            .patintDiagoisId(UPDATED_PATINT_DIAGOIS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return patientDiagnosisAuditLog;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PatientDiagnosisAuditLog.class).block();
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
        patientDiagnosisAuditLog = createEntity(em);
    }

    @Test
    void createPatientDiagnosisAuditLog() throws Exception {
        int databaseSizeBeforeCreate = patientDiagnosisAuditLogRepository.findAll().collectList().block().size();
        // Create the PatientDiagnosisAuditLog
        PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO = patientDiagnosisAuditLogMapper.toDto(patientDiagnosisAuditLog);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDiagnosisAuditLogDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PatientDiagnosisAuditLog in the database
        List<PatientDiagnosisAuditLog> patientDiagnosisAuditLogList = patientDiagnosisAuditLogRepository.findAll().collectList().block();
        assertThat(patientDiagnosisAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        PatientDiagnosisAuditLog testPatientDiagnosisAuditLog = patientDiagnosisAuditLogList.get(patientDiagnosisAuditLogList.size() - 1);
        assertThat(testPatientDiagnosisAuditLog.getPatintDiagoisId()).isEqualTo(DEFAULT_PATINT_DIAGOIS_ID);
        assertThat(testPatientDiagnosisAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPatientDiagnosisAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testPatientDiagnosisAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPatientDiagnosisAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testPatientDiagnosisAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void createPatientDiagnosisAuditLogWithExistingId() throws Exception {
        // Create the PatientDiagnosisAuditLog with an existing ID
        patientDiagnosisAuditLog.setId(1L);
        PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO = patientDiagnosisAuditLogMapper.toDto(patientDiagnosisAuditLog);

        int databaseSizeBeforeCreate = patientDiagnosisAuditLogRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDiagnosisAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDiagnosisAuditLog in the database
        List<PatientDiagnosisAuditLog> patientDiagnosisAuditLogList = patientDiagnosisAuditLogRepository.findAll().collectList().block();
        assertThat(patientDiagnosisAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPatientDiagnosisAuditLogs() {
        // Initialize the database
        patientDiagnosisAuditLogRepository.save(patientDiagnosisAuditLog).block();

        // Get all the patientDiagnosisAuditLogList
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
            .value(hasItem(patientDiagnosisAuditLog.getId().intValue()))
            .jsonPath("$.[*].patintDiagoisId")
            .value(hasItem(DEFAULT_PATINT_DIAGOIS_ID.intValue()))
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
    void getPatientDiagnosisAuditLog() {
        // Initialize the database
        patientDiagnosisAuditLogRepository.save(patientDiagnosisAuditLog).block();

        // Get the patientDiagnosisAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, patientDiagnosisAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(patientDiagnosisAuditLog.getId().intValue()))
            .jsonPath("$.patintDiagoisId")
            .value(is(DEFAULT_PATINT_DIAGOIS_ID.intValue()))
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
    void getNonExistingPatientDiagnosisAuditLog() {
        // Get the patientDiagnosisAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPatientDiagnosisAuditLog() throws Exception {
        // Initialize the database
        patientDiagnosisAuditLogRepository.save(patientDiagnosisAuditLog).block();

        int databaseSizeBeforeUpdate = patientDiagnosisAuditLogRepository.findAll().collectList().block().size();

        // Update the patientDiagnosisAuditLog
        PatientDiagnosisAuditLog updatedPatientDiagnosisAuditLog = patientDiagnosisAuditLogRepository
            .findById(patientDiagnosisAuditLog.getId())
            .block();
        updatedPatientDiagnosisAuditLog
            .patintDiagoisId(UPDATED_PATINT_DIAGOIS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO = patientDiagnosisAuditLogMapper.toDto(updatedPatientDiagnosisAuditLog);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientDiagnosisAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDiagnosisAuditLogDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDiagnosisAuditLog in the database
        List<PatientDiagnosisAuditLog> patientDiagnosisAuditLogList = patientDiagnosisAuditLogRepository.findAll().collectList().block();
        assertThat(patientDiagnosisAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientDiagnosisAuditLog testPatientDiagnosisAuditLog = patientDiagnosisAuditLogList.get(patientDiagnosisAuditLogList.size() - 1);
        assertThat(testPatientDiagnosisAuditLog.getPatintDiagoisId()).isEqualTo(UPDATED_PATINT_DIAGOIS_ID);
        assertThat(testPatientDiagnosisAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPatientDiagnosisAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPatientDiagnosisAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPatientDiagnosisAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPatientDiagnosisAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void putNonExistingPatientDiagnosisAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientDiagnosisAuditLogRepository.findAll().collectList().block().size();
        patientDiagnosisAuditLog.setId(count.incrementAndGet());

        // Create the PatientDiagnosisAuditLog
        PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO = patientDiagnosisAuditLogMapper.toDto(patientDiagnosisAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientDiagnosisAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDiagnosisAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDiagnosisAuditLog in the database
        List<PatientDiagnosisAuditLog> patientDiagnosisAuditLogList = patientDiagnosisAuditLogRepository.findAll().collectList().block();
        assertThat(patientDiagnosisAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPatientDiagnosisAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientDiagnosisAuditLogRepository.findAll().collectList().block().size();
        patientDiagnosisAuditLog.setId(count.incrementAndGet());

        // Create the PatientDiagnosisAuditLog
        PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO = patientDiagnosisAuditLogMapper.toDto(patientDiagnosisAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDiagnosisAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDiagnosisAuditLog in the database
        List<PatientDiagnosisAuditLog> patientDiagnosisAuditLogList = patientDiagnosisAuditLogRepository.findAll().collectList().block();
        assertThat(patientDiagnosisAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPatientDiagnosisAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientDiagnosisAuditLogRepository.findAll().collectList().block().size();
        patientDiagnosisAuditLog.setId(count.incrementAndGet());

        // Create the PatientDiagnosisAuditLog
        PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO = patientDiagnosisAuditLogMapper.toDto(patientDiagnosisAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDiagnosisAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientDiagnosisAuditLog in the database
        List<PatientDiagnosisAuditLog> patientDiagnosisAuditLogList = patientDiagnosisAuditLogRepository.findAll().collectList().block();
        assertThat(patientDiagnosisAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePatientDiagnosisAuditLogWithPatch() throws Exception {
        // Initialize the database
        patientDiagnosisAuditLogRepository.save(patientDiagnosisAuditLog).block();

        int databaseSizeBeforeUpdate = patientDiagnosisAuditLogRepository.findAll().collectList().block().size();

        // Update the patientDiagnosisAuditLog using partial update
        PatientDiagnosisAuditLog partialUpdatedPatientDiagnosisAuditLog = new PatientDiagnosisAuditLog();
        partialUpdatedPatientDiagnosisAuditLog.setId(patientDiagnosisAuditLog.getId());

        partialUpdatedPatientDiagnosisAuditLog.newRowData(UPDATED_NEW_ROW_DATA);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientDiagnosisAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientDiagnosisAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDiagnosisAuditLog in the database
        List<PatientDiagnosisAuditLog> patientDiagnosisAuditLogList = patientDiagnosisAuditLogRepository.findAll().collectList().block();
        assertThat(patientDiagnosisAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientDiagnosisAuditLog testPatientDiagnosisAuditLog = patientDiagnosisAuditLogList.get(patientDiagnosisAuditLogList.size() - 1);
        assertThat(testPatientDiagnosisAuditLog.getPatintDiagoisId()).isEqualTo(DEFAULT_PATINT_DIAGOIS_ID);
        assertThat(testPatientDiagnosisAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPatientDiagnosisAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPatientDiagnosisAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPatientDiagnosisAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testPatientDiagnosisAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void fullUpdatePatientDiagnosisAuditLogWithPatch() throws Exception {
        // Initialize the database
        patientDiagnosisAuditLogRepository.save(patientDiagnosisAuditLog).block();

        int databaseSizeBeforeUpdate = patientDiagnosisAuditLogRepository.findAll().collectList().block().size();

        // Update the patientDiagnosisAuditLog using partial update
        PatientDiagnosisAuditLog partialUpdatedPatientDiagnosisAuditLog = new PatientDiagnosisAuditLog();
        partialUpdatedPatientDiagnosisAuditLog.setId(patientDiagnosisAuditLog.getId());

        partialUpdatedPatientDiagnosisAuditLog
            .patintDiagoisId(UPDATED_PATINT_DIAGOIS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientDiagnosisAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientDiagnosisAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDiagnosisAuditLog in the database
        List<PatientDiagnosisAuditLog> patientDiagnosisAuditLogList = patientDiagnosisAuditLogRepository.findAll().collectList().block();
        assertThat(patientDiagnosisAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientDiagnosisAuditLog testPatientDiagnosisAuditLog = patientDiagnosisAuditLogList.get(patientDiagnosisAuditLogList.size() - 1);
        assertThat(testPatientDiagnosisAuditLog.getPatintDiagoisId()).isEqualTo(UPDATED_PATINT_DIAGOIS_ID);
        assertThat(testPatientDiagnosisAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPatientDiagnosisAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPatientDiagnosisAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPatientDiagnosisAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPatientDiagnosisAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void patchNonExistingPatientDiagnosisAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientDiagnosisAuditLogRepository.findAll().collectList().block().size();
        patientDiagnosisAuditLog.setId(count.incrementAndGet());

        // Create the PatientDiagnosisAuditLog
        PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO = patientDiagnosisAuditLogMapper.toDto(patientDiagnosisAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, patientDiagnosisAuditLogDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDiagnosisAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDiagnosisAuditLog in the database
        List<PatientDiagnosisAuditLog> patientDiagnosisAuditLogList = patientDiagnosisAuditLogRepository.findAll().collectList().block();
        assertThat(patientDiagnosisAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPatientDiagnosisAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientDiagnosisAuditLogRepository.findAll().collectList().block().size();
        patientDiagnosisAuditLog.setId(count.incrementAndGet());

        // Create the PatientDiagnosisAuditLog
        PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO = patientDiagnosisAuditLogMapper.toDto(patientDiagnosisAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDiagnosisAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDiagnosisAuditLog in the database
        List<PatientDiagnosisAuditLog> patientDiagnosisAuditLogList = patientDiagnosisAuditLogRepository.findAll().collectList().block();
        assertThat(patientDiagnosisAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPatientDiagnosisAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientDiagnosisAuditLogRepository.findAll().collectList().block().size();
        patientDiagnosisAuditLog.setId(count.incrementAndGet());

        // Create the PatientDiagnosisAuditLog
        PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO = patientDiagnosisAuditLogMapper.toDto(patientDiagnosisAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDiagnosisAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientDiagnosisAuditLog in the database
        List<PatientDiagnosisAuditLog> patientDiagnosisAuditLogList = patientDiagnosisAuditLogRepository.findAll().collectList().block();
        assertThat(patientDiagnosisAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePatientDiagnosisAuditLog() {
        // Initialize the database
        patientDiagnosisAuditLogRepository.save(patientDiagnosisAuditLog).block();

        int databaseSizeBeforeDelete = patientDiagnosisAuditLogRepository.findAll().collectList().block().size();

        // Delete the patientDiagnosisAuditLog
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, patientDiagnosisAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PatientDiagnosisAuditLog> patientDiagnosisAuditLogList = patientDiagnosisAuditLogRepository.findAll().collectList().block();
        assertThat(patientDiagnosisAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
