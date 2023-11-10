package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PatientDoctorMapAuditLog;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PatientDoctorMapAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientDoctorMapAuditLogMapper;
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
 * Integration tests for the {@link PatientDoctorMapAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PatientDoctorMapAuditLogResourceIT {

    private static final Long DEFAULT_PAENT_DCTOR_MAP_ID = 1L;
    private static final Long UPDATED_PAENT_DCTOR_MAP_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/patient-doctor-map-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PatientDoctorMapAuditLogRepository patientDoctorMapAuditLogRepository;

    @Autowired
    private PatientDoctorMapAuditLogMapper patientDoctorMapAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PatientDoctorMapAuditLog patientDoctorMapAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientDoctorMapAuditLog createEntity(EntityManager em) {
        PatientDoctorMapAuditLog patientDoctorMapAuditLog = new PatientDoctorMapAuditLog()
            .paentDctorMapId(DEFAULT_PAENT_DCTOR_MAP_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return patientDoctorMapAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientDoctorMapAuditLog createUpdatedEntity(EntityManager em) {
        PatientDoctorMapAuditLog patientDoctorMapAuditLog = new PatientDoctorMapAuditLog()
            .paentDctorMapId(UPDATED_PAENT_DCTOR_MAP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return patientDoctorMapAuditLog;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PatientDoctorMapAuditLog.class).block();
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
        patientDoctorMapAuditLog = createEntity(em);
    }

    @Test
    void createPatientDoctorMapAuditLog() throws Exception {
        int databaseSizeBeforeCreate = patientDoctorMapAuditLogRepository.findAll().collectList().block().size();
        // Create the PatientDoctorMapAuditLog
        PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO = patientDoctorMapAuditLogMapper.toDto(patientDoctorMapAuditLog);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDoctorMapAuditLogDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PatientDoctorMapAuditLog in the database
        List<PatientDoctorMapAuditLog> patientDoctorMapAuditLogList = patientDoctorMapAuditLogRepository.findAll().collectList().block();
        assertThat(patientDoctorMapAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        PatientDoctorMapAuditLog testPatientDoctorMapAuditLog = patientDoctorMapAuditLogList.get(patientDoctorMapAuditLogList.size() - 1);
        assertThat(testPatientDoctorMapAuditLog.getPaentDctorMapId()).isEqualTo(DEFAULT_PAENT_DCTOR_MAP_ID);
        assertThat(testPatientDoctorMapAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPatientDoctorMapAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testPatientDoctorMapAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPatientDoctorMapAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testPatientDoctorMapAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void createPatientDoctorMapAuditLogWithExistingId() throws Exception {
        // Create the PatientDoctorMapAuditLog with an existing ID
        patientDoctorMapAuditLog.setId(1L);
        PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO = patientDoctorMapAuditLogMapper.toDto(patientDoctorMapAuditLog);

        int databaseSizeBeforeCreate = patientDoctorMapAuditLogRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDoctorMapAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDoctorMapAuditLog in the database
        List<PatientDoctorMapAuditLog> patientDoctorMapAuditLogList = patientDoctorMapAuditLogRepository.findAll().collectList().block();
        assertThat(patientDoctorMapAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPatientDoctorMapAuditLogs() {
        // Initialize the database
        patientDoctorMapAuditLogRepository.save(patientDoctorMapAuditLog).block();

        // Get all the patientDoctorMapAuditLogList
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
            .value(hasItem(patientDoctorMapAuditLog.getId().intValue()))
            .jsonPath("$.[*].paentDctorMapId")
            .value(hasItem(DEFAULT_PAENT_DCTOR_MAP_ID.intValue()))
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
    void getPatientDoctorMapAuditLog() {
        // Initialize the database
        patientDoctorMapAuditLogRepository.save(patientDoctorMapAuditLog).block();

        // Get the patientDoctorMapAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, patientDoctorMapAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(patientDoctorMapAuditLog.getId().intValue()))
            .jsonPath("$.paentDctorMapId")
            .value(is(DEFAULT_PAENT_DCTOR_MAP_ID.intValue()))
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
    void getNonExistingPatientDoctorMapAuditLog() {
        // Get the patientDoctorMapAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPatientDoctorMapAuditLog() throws Exception {
        // Initialize the database
        patientDoctorMapAuditLogRepository.save(patientDoctorMapAuditLog).block();

        int databaseSizeBeforeUpdate = patientDoctorMapAuditLogRepository.findAll().collectList().block().size();

        // Update the patientDoctorMapAuditLog
        PatientDoctorMapAuditLog updatedPatientDoctorMapAuditLog = patientDoctorMapAuditLogRepository
            .findById(patientDoctorMapAuditLog.getId())
            .block();
        updatedPatientDoctorMapAuditLog
            .paentDctorMapId(UPDATED_PAENT_DCTOR_MAP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO = patientDoctorMapAuditLogMapper.toDto(updatedPatientDoctorMapAuditLog);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientDoctorMapAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDoctorMapAuditLogDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDoctorMapAuditLog in the database
        List<PatientDoctorMapAuditLog> patientDoctorMapAuditLogList = patientDoctorMapAuditLogRepository.findAll().collectList().block();
        assertThat(patientDoctorMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientDoctorMapAuditLog testPatientDoctorMapAuditLog = patientDoctorMapAuditLogList.get(patientDoctorMapAuditLogList.size() - 1);
        assertThat(testPatientDoctorMapAuditLog.getPaentDctorMapId()).isEqualTo(UPDATED_PAENT_DCTOR_MAP_ID);
        assertThat(testPatientDoctorMapAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPatientDoctorMapAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPatientDoctorMapAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPatientDoctorMapAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPatientDoctorMapAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void putNonExistingPatientDoctorMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientDoctorMapAuditLogRepository.findAll().collectList().block().size();
        patientDoctorMapAuditLog.setId(count.incrementAndGet());

        // Create the PatientDoctorMapAuditLog
        PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO = patientDoctorMapAuditLogMapper.toDto(patientDoctorMapAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientDoctorMapAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDoctorMapAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDoctorMapAuditLog in the database
        List<PatientDoctorMapAuditLog> patientDoctorMapAuditLogList = patientDoctorMapAuditLogRepository.findAll().collectList().block();
        assertThat(patientDoctorMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPatientDoctorMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientDoctorMapAuditLogRepository.findAll().collectList().block().size();
        patientDoctorMapAuditLog.setId(count.incrementAndGet());

        // Create the PatientDoctorMapAuditLog
        PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO = patientDoctorMapAuditLogMapper.toDto(patientDoctorMapAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDoctorMapAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDoctorMapAuditLog in the database
        List<PatientDoctorMapAuditLog> patientDoctorMapAuditLogList = patientDoctorMapAuditLogRepository.findAll().collectList().block();
        assertThat(patientDoctorMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPatientDoctorMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientDoctorMapAuditLogRepository.findAll().collectList().block().size();
        patientDoctorMapAuditLog.setId(count.incrementAndGet());

        // Create the PatientDoctorMapAuditLog
        PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO = patientDoctorMapAuditLogMapper.toDto(patientDoctorMapAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDoctorMapAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientDoctorMapAuditLog in the database
        List<PatientDoctorMapAuditLog> patientDoctorMapAuditLogList = patientDoctorMapAuditLogRepository.findAll().collectList().block();
        assertThat(patientDoctorMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePatientDoctorMapAuditLogWithPatch() throws Exception {
        // Initialize the database
        patientDoctorMapAuditLogRepository.save(patientDoctorMapAuditLog).block();

        int databaseSizeBeforeUpdate = patientDoctorMapAuditLogRepository.findAll().collectList().block().size();

        // Update the patientDoctorMapAuditLog using partial update
        PatientDoctorMapAuditLog partialUpdatedPatientDoctorMapAuditLog = new PatientDoctorMapAuditLog();
        partialUpdatedPatientDoctorMapAuditLog.setId(patientDoctorMapAuditLog.getId());

        partialUpdatedPatientDoctorMapAuditLog
            .paentDctorMapId(UPDATED_PAENT_DCTOR_MAP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientDoctorMapAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientDoctorMapAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDoctorMapAuditLog in the database
        List<PatientDoctorMapAuditLog> patientDoctorMapAuditLogList = patientDoctorMapAuditLogRepository.findAll().collectList().block();
        assertThat(patientDoctorMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientDoctorMapAuditLog testPatientDoctorMapAuditLog = patientDoctorMapAuditLogList.get(patientDoctorMapAuditLogList.size() - 1);
        assertThat(testPatientDoctorMapAuditLog.getPaentDctorMapId()).isEqualTo(UPDATED_PAENT_DCTOR_MAP_ID);
        assertThat(testPatientDoctorMapAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPatientDoctorMapAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPatientDoctorMapAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPatientDoctorMapAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPatientDoctorMapAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void fullUpdatePatientDoctorMapAuditLogWithPatch() throws Exception {
        // Initialize the database
        patientDoctorMapAuditLogRepository.save(patientDoctorMapAuditLog).block();

        int databaseSizeBeforeUpdate = patientDoctorMapAuditLogRepository.findAll().collectList().block().size();

        // Update the patientDoctorMapAuditLog using partial update
        PatientDoctorMapAuditLog partialUpdatedPatientDoctorMapAuditLog = new PatientDoctorMapAuditLog();
        partialUpdatedPatientDoctorMapAuditLog.setId(patientDoctorMapAuditLog.getId());

        partialUpdatedPatientDoctorMapAuditLog
            .paentDctorMapId(UPDATED_PAENT_DCTOR_MAP_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientDoctorMapAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientDoctorMapAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientDoctorMapAuditLog in the database
        List<PatientDoctorMapAuditLog> patientDoctorMapAuditLogList = patientDoctorMapAuditLogRepository.findAll().collectList().block();
        assertThat(patientDoctorMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientDoctorMapAuditLog testPatientDoctorMapAuditLog = patientDoctorMapAuditLogList.get(patientDoctorMapAuditLogList.size() - 1);
        assertThat(testPatientDoctorMapAuditLog.getPaentDctorMapId()).isEqualTo(UPDATED_PAENT_DCTOR_MAP_ID);
        assertThat(testPatientDoctorMapAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPatientDoctorMapAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPatientDoctorMapAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPatientDoctorMapAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPatientDoctorMapAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void patchNonExistingPatientDoctorMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientDoctorMapAuditLogRepository.findAll().collectList().block().size();
        patientDoctorMapAuditLog.setId(count.incrementAndGet());

        // Create the PatientDoctorMapAuditLog
        PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO = patientDoctorMapAuditLogMapper.toDto(patientDoctorMapAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, patientDoctorMapAuditLogDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDoctorMapAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDoctorMapAuditLog in the database
        List<PatientDoctorMapAuditLog> patientDoctorMapAuditLogList = patientDoctorMapAuditLogRepository.findAll().collectList().block();
        assertThat(patientDoctorMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPatientDoctorMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientDoctorMapAuditLogRepository.findAll().collectList().block().size();
        patientDoctorMapAuditLog.setId(count.incrementAndGet());

        // Create the PatientDoctorMapAuditLog
        PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO = patientDoctorMapAuditLogMapper.toDto(patientDoctorMapAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDoctorMapAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientDoctorMapAuditLog in the database
        List<PatientDoctorMapAuditLog> patientDoctorMapAuditLogList = patientDoctorMapAuditLogRepository.findAll().collectList().block();
        assertThat(patientDoctorMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPatientDoctorMapAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientDoctorMapAuditLogRepository.findAll().collectList().block().size();
        patientDoctorMapAuditLog.setId(count.incrementAndGet());

        // Create the PatientDoctorMapAuditLog
        PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO = patientDoctorMapAuditLogMapper.toDto(patientDoctorMapAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientDoctorMapAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientDoctorMapAuditLog in the database
        List<PatientDoctorMapAuditLog> patientDoctorMapAuditLogList = patientDoctorMapAuditLogRepository.findAll().collectList().block();
        assertThat(patientDoctorMapAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePatientDoctorMapAuditLog() {
        // Initialize the database
        patientDoctorMapAuditLogRepository.save(patientDoctorMapAuditLog).block();

        int databaseSizeBeforeDelete = patientDoctorMapAuditLogRepository.findAll().collectList().block().size();

        // Delete the patientDoctorMapAuditLog
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, patientDoctorMapAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PatientDoctorMapAuditLog> patientDoctorMapAuditLogList = patientDoctorMapAuditLogRepository.findAll().collectList().block();
        assertThat(patientDoctorMapAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
