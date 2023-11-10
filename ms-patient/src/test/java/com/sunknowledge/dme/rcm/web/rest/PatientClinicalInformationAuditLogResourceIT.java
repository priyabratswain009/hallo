package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PatientClinicalInformationAuditLog;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PatientClinicalInformationAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientClinicalInformationAuditLogMapper;
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
 * Integration tests for the {@link PatientClinicalInformationAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PatientClinicalInformationAuditLogResourceIT {

    private static final Long DEFAULT_PATNT_CLIICAL_INFOATION_ID = 1L;
    private static final Long UPDATED_PATNT_CLIICAL_INFOATION_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/patient-clinical-information-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PatientClinicalInformationAuditLogRepository patientClinicalInformationAuditLogRepository;

    @Autowired
    private PatientClinicalInformationAuditLogMapper patientClinicalInformationAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PatientClinicalInformationAuditLog patientClinicalInformationAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientClinicalInformationAuditLog createEntity(EntityManager em) {
        PatientClinicalInformationAuditLog patientClinicalInformationAuditLog = new PatientClinicalInformationAuditLog()
            .patntCliicalInfoationId(DEFAULT_PATNT_CLIICAL_INFOATION_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return patientClinicalInformationAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientClinicalInformationAuditLog createUpdatedEntity(EntityManager em) {
        PatientClinicalInformationAuditLog patientClinicalInformationAuditLog = new PatientClinicalInformationAuditLog()
            .patntCliicalInfoationId(UPDATED_PATNT_CLIICAL_INFOATION_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return patientClinicalInformationAuditLog;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PatientClinicalInformationAuditLog.class).block();
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
        patientClinicalInformationAuditLog = createEntity(em);
    }

    @Test
    void createPatientClinicalInformationAuditLog() throws Exception {
        int databaseSizeBeforeCreate = patientClinicalInformationAuditLogRepository.findAll().collectList().block().size();
        // Create the PatientClinicalInformationAuditLog
        PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO = patientClinicalInformationAuditLogMapper.toDto(
            patientClinicalInformationAuditLog
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientClinicalInformationAuditLogDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PatientClinicalInformationAuditLog in the database
        List<PatientClinicalInformationAuditLog> patientClinicalInformationAuditLogList = patientClinicalInformationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        PatientClinicalInformationAuditLog testPatientClinicalInformationAuditLog = patientClinicalInformationAuditLogList.get(
            patientClinicalInformationAuditLogList.size() - 1
        );
        assertThat(testPatientClinicalInformationAuditLog.getPatntCliicalInfoationId()).isEqualTo(DEFAULT_PATNT_CLIICAL_INFOATION_ID);
        assertThat(testPatientClinicalInformationAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPatientClinicalInformationAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testPatientClinicalInformationAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPatientClinicalInformationAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testPatientClinicalInformationAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void createPatientClinicalInformationAuditLogWithExistingId() throws Exception {
        // Create the PatientClinicalInformationAuditLog with an existing ID
        patientClinicalInformationAuditLog.setId(1L);
        PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO = patientClinicalInformationAuditLogMapper.toDto(
            patientClinicalInformationAuditLog
        );

        int databaseSizeBeforeCreate = patientClinicalInformationAuditLogRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientClinicalInformationAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientClinicalInformationAuditLog in the database
        List<PatientClinicalInformationAuditLog> patientClinicalInformationAuditLogList = patientClinicalInformationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPatientClinicalInformationAuditLogs() {
        // Initialize the database
        patientClinicalInformationAuditLogRepository.save(patientClinicalInformationAuditLog).block();

        // Get all the patientClinicalInformationAuditLogList
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
            .value(hasItem(patientClinicalInformationAuditLog.getId().intValue()))
            .jsonPath("$.[*].patntCliicalInfoationId")
            .value(hasItem(DEFAULT_PATNT_CLIICAL_INFOATION_ID.intValue()))
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
    void getPatientClinicalInformationAuditLog() {
        // Initialize the database
        patientClinicalInformationAuditLogRepository.save(patientClinicalInformationAuditLog).block();

        // Get the patientClinicalInformationAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, patientClinicalInformationAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(patientClinicalInformationAuditLog.getId().intValue()))
            .jsonPath("$.patntCliicalInfoationId")
            .value(is(DEFAULT_PATNT_CLIICAL_INFOATION_ID.intValue()))
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
    void getNonExistingPatientClinicalInformationAuditLog() {
        // Get the patientClinicalInformationAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPatientClinicalInformationAuditLog() throws Exception {
        // Initialize the database
        patientClinicalInformationAuditLogRepository.save(patientClinicalInformationAuditLog).block();

        int databaseSizeBeforeUpdate = patientClinicalInformationAuditLogRepository.findAll().collectList().block().size();

        // Update the patientClinicalInformationAuditLog
        PatientClinicalInformationAuditLog updatedPatientClinicalInformationAuditLog = patientClinicalInformationAuditLogRepository
            .findById(patientClinicalInformationAuditLog.getId())
            .block();
        updatedPatientClinicalInformationAuditLog
            .patntCliicalInfoationId(UPDATED_PATNT_CLIICAL_INFOATION_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO = patientClinicalInformationAuditLogMapper.toDto(
            updatedPatientClinicalInformationAuditLog
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientClinicalInformationAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientClinicalInformationAuditLogDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientClinicalInformationAuditLog in the database
        List<PatientClinicalInformationAuditLog> patientClinicalInformationAuditLogList = patientClinicalInformationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientClinicalInformationAuditLog testPatientClinicalInformationAuditLog = patientClinicalInformationAuditLogList.get(
            patientClinicalInformationAuditLogList.size() - 1
        );
        assertThat(testPatientClinicalInformationAuditLog.getPatntCliicalInfoationId()).isEqualTo(UPDATED_PATNT_CLIICAL_INFOATION_ID);
        assertThat(testPatientClinicalInformationAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPatientClinicalInformationAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPatientClinicalInformationAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPatientClinicalInformationAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPatientClinicalInformationAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void putNonExistingPatientClinicalInformationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientClinicalInformationAuditLogRepository.findAll().collectList().block().size();
        patientClinicalInformationAuditLog.setId(count.incrementAndGet());

        // Create the PatientClinicalInformationAuditLog
        PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO = patientClinicalInformationAuditLogMapper.toDto(
            patientClinicalInformationAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientClinicalInformationAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientClinicalInformationAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientClinicalInformationAuditLog in the database
        List<PatientClinicalInformationAuditLog> patientClinicalInformationAuditLogList = patientClinicalInformationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPatientClinicalInformationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientClinicalInformationAuditLogRepository.findAll().collectList().block().size();
        patientClinicalInformationAuditLog.setId(count.incrementAndGet());

        // Create the PatientClinicalInformationAuditLog
        PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO = patientClinicalInformationAuditLogMapper.toDto(
            patientClinicalInformationAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientClinicalInformationAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientClinicalInformationAuditLog in the database
        List<PatientClinicalInformationAuditLog> patientClinicalInformationAuditLogList = patientClinicalInformationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPatientClinicalInformationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientClinicalInformationAuditLogRepository.findAll().collectList().block().size();
        patientClinicalInformationAuditLog.setId(count.incrementAndGet());

        // Create the PatientClinicalInformationAuditLog
        PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO = patientClinicalInformationAuditLogMapper.toDto(
            patientClinicalInformationAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientClinicalInformationAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientClinicalInformationAuditLog in the database
        List<PatientClinicalInformationAuditLog> patientClinicalInformationAuditLogList = patientClinicalInformationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePatientClinicalInformationAuditLogWithPatch() throws Exception {
        // Initialize the database
        patientClinicalInformationAuditLogRepository.save(patientClinicalInformationAuditLog).block();

        int databaseSizeBeforeUpdate = patientClinicalInformationAuditLogRepository.findAll().collectList().block().size();

        // Update the patientClinicalInformationAuditLog using partial update
        PatientClinicalInformationAuditLog partialUpdatedPatientClinicalInformationAuditLog = new PatientClinicalInformationAuditLog();
        partialUpdatedPatientClinicalInformationAuditLog.setId(patientClinicalInformationAuditLog.getId());

        partialUpdatedPatientClinicalInformationAuditLog.dmlTimestamp(UPDATED_DML_TIMESTAMP).dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientClinicalInformationAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientClinicalInformationAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientClinicalInformationAuditLog in the database
        List<PatientClinicalInformationAuditLog> patientClinicalInformationAuditLogList = patientClinicalInformationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientClinicalInformationAuditLog testPatientClinicalInformationAuditLog = patientClinicalInformationAuditLogList.get(
            patientClinicalInformationAuditLogList.size() - 1
        );
        assertThat(testPatientClinicalInformationAuditLog.getPatntCliicalInfoationId()).isEqualTo(DEFAULT_PATNT_CLIICAL_INFOATION_ID);
        assertThat(testPatientClinicalInformationAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPatientClinicalInformationAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testPatientClinicalInformationAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPatientClinicalInformationAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPatientClinicalInformationAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void fullUpdatePatientClinicalInformationAuditLogWithPatch() throws Exception {
        // Initialize the database
        patientClinicalInformationAuditLogRepository.save(patientClinicalInformationAuditLog).block();

        int databaseSizeBeforeUpdate = patientClinicalInformationAuditLogRepository.findAll().collectList().block().size();

        // Update the patientClinicalInformationAuditLog using partial update
        PatientClinicalInformationAuditLog partialUpdatedPatientClinicalInformationAuditLog = new PatientClinicalInformationAuditLog();
        partialUpdatedPatientClinicalInformationAuditLog.setId(patientClinicalInformationAuditLog.getId());

        partialUpdatedPatientClinicalInformationAuditLog
            .patntCliicalInfoationId(UPDATED_PATNT_CLIICAL_INFOATION_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientClinicalInformationAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientClinicalInformationAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientClinicalInformationAuditLog in the database
        List<PatientClinicalInformationAuditLog> patientClinicalInformationAuditLogList = patientClinicalInformationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientClinicalInformationAuditLog testPatientClinicalInformationAuditLog = patientClinicalInformationAuditLogList.get(
            patientClinicalInformationAuditLogList.size() - 1
        );
        assertThat(testPatientClinicalInformationAuditLog.getPatntCliicalInfoationId()).isEqualTo(UPDATED_PATNT_CLIICAL_INFOATION_ID);
        assertThat(testPatientClinicalInformationAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPatientClinicalInformationAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPatientClinicalInformationAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPatientClinicalInformationAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPatientClinicalInformationAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void patchNonExistingPatientClinicalInformationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientClinicalInformationAuditLogRepository.findAll().collectList().block().size();
        patientClinicalInformationAuditLog.setId(count.incrementAndGet());

        // Create the PatientClinicalInformationAuditLog
        PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO = patientClinicalInformationAuditLogMapper.toDto(
            patientClinicalInformationAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, patientClinicalInformationAuditLogDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientClinicalInformationAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientClinicalInformationAuditLog in the database
        List<PatientClinicalInformationAuditLog> patientClinicalInformationAuditLogList = patientClinicalInformationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPatientClinicalInformationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientClinicalInformationAuditLogRepository.findAll().collectList().block().size();
        patientClinicalInformationAuditLog.setId(count.incrementAndGet());

        // Create the PatientClinicalInformationAuditLog
        PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO = patientClinicalInformationAuditLogMapper.toDto(
            patientClinicalInformationAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientClinicalInformationAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientClinicalInformationAuditLog in the database
        List<PatientClinicalInformationAuditLog> patientClinicalInformationAuditLogList = patientClinicalInformationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPatientClinicalInformationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientClinicalInformationAuditLogRepository.findAll().collectList().block().size();
        patientClinicalInformationAuditLog.setId(count.incrementAndGet());

        // Create the PatientClinicalInformationAuditLog
        PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO = patientClinicalInformationAuditLogMapper.toDto(
            patientClinicalInformationAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientClinicalInformationAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientClinicalInformationAuditLog in the database
        List<PatientClinicalInformationAuditLog> patientClinicalInformationAuditLogList = patientClinicalInformationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePatientClinicalInformationAuditLog() {
        // Initialize the database
        patientClinicalInformationAuditLogRepository.save(patientClinicalInformationAuditLog).block();

        int databaseSizeBeforeDelete = patientClinicalInformationAuditLogRepository.findAll().collectList().block().size();

        // Delete the patientClinicalInformationAuditLog
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, patientClinicalInformationAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PatientClinicalInformationAuditLog> patientClinicalInformationAuditLogList = patientClinicalInformationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientClinicalInformationAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
