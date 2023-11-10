package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PatientInsVerifStatAuditLog;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PatientInsVerifStatAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientInsVerifStatAuditLogMapper;
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
 * Integration tests for the {@link PatientInsVerifStatAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PatientInsVerifStatAuditLogResourceIT {

    private static final Long DEFAULT_INSRNCE_VRIF_ID = 1L;
    private static final Long UPDATED_INSRNCE_VRIF_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/patient-ins-verif-stat-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PatientInsVerifStatAuditLogRepository patientInsVerifStatAuditLogRepository;

    @Autowired
    private PatientInsVerifStatAuditLogMapper patientInsVerifStatAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PatientInsVerifStatAuditLog patientInsVerifStatAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientInsVerifStatAuditLog createEntity(EntityManager em) {
        PatientInsVerifStatAuditLog patientInsVerifStatAuditLog = new PatientInsVerifStatAuditLog()
            .insrnceVrifId(DEFAULT_INSRNCE_VRIF_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return patientInsVerifStatAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientInsVerifStatAuditLog createUpdatedEntity(EntityManager em) {
        PatientInsVerifStatAuditLog patientInsVerifStatAuditLog = new PatientInsVerifStatAuditLog()
            .insrnceVrifId(UPDATED_INSRNCE_VRIF_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return patientInsVerifStatAuditLog;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PatientInsVerifStatAuditLog.class).block();
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
        patientInsVerifStatAuditLog = createEntity(em);
    }

    @Test
    void createPatientInsVerifStatAuditLog() throws Exception {
        int databaseSizeBeforeCreate = patientInsVerifStatAuditLogRepository.findAll().collectList().block().size();
        // Create the PatientInsVerifStatAuditLog
        PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO = patientInsVerifStatAuditLogMapper.toDto(
            patientInsVerifStatAuditLog
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsVerifStatAuditLogDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PatientInsVerifStatAuditLog in the database
        List<PatientInsVerifStatAuditLog> patientInsVerifStatAuditLogList = patientInsVerifStatAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientInsVerifStatAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        PatientInsVerifStatAuditLog testPatientInsVerifStatAuditLog = patientInsVerifStatAuditLogList.get(
            patientInsVerifStatAuditLogList.size() - 1
        );
        assertThat(testPatientInsVerifStatAuditLog.getInsrnceVrifId()).isEqualTo(DEFAULT_INSRNCE_VRIF_ID);
        assertThat(testPatientInsVerifStatAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testPatientInsVerifStatAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testPatientInsVerifStatAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testPatientInsVerifStatAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testPatientInsVerifStatAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void createPatientInsVerifStatAuditLogWithExistingId() throws Exception {
        // Create the PatientInsVerifStatAuditLog with an existing ID
        patientInsVerifStatAuditLog.setId(1L);
        PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO = patientInsVerifStatAuditLogMapper.toDto(
            patientInsVerifStatAuditLog
        );

        int databaseSizeBeforeCreate = patientInsVerifStatAuditLogRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsVerifStatAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsVerifStatAuditLog in the database
        List<PatientInsVerifStatAuditLog> patientInsVerifStatAuditLogList = patientInsVerifStatAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientInsVerifStatAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPatientInsVerifStatAuditLogs() {
        // Initialize the database
        patientInsVerifStatAuditLogRepository.save(patientInsVerifStatAuditLog).block();

        // Get all the patientInsVerifStatAuditLogList
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
            .value(hasItem(patientInsVerifStatAuditLog.getId().intValue()))
            .jsonPath("$.[*].insrnceVrifId")
            .value(hasItem(DEFAULT_INSRNCE_VRIF_ID.intValue()))
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
    void getPatientInsVerifStatAuditLog() {
        // Initialize the database
        patientInsVerifStatAuditLogRepository.save(patientInsVerifStatAuditLog).block();

        // Get the patientInsVerifStatAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, patientInsVerifStatAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(patientInsVerifStatAuditLog.getId().intValue()))
            .jsonPath("$.insrnceVrifId")
            .value(is(DEFAULT_INSRNCE_VRIF_ID.intValue()))
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
    void getNonExistingPatientInsVerifStatAuditLog() {
        // Get the patientInsVerifStatAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPatientInsVerifStatAuditLog() throws Exception {
        // Initialize the database
        patientInsVerifStatAuditLogRepository.save(patientInsVerifStatAuditLog).block();

        int databaseSizeBeforeUpdate = patientInsVerifStatAuditLogRepository.findAll().collectList().block().size();

        // Update the patientInsVerifStatAuditLog
        PatientInsVerifStatAuditLog updatedPatientInsVerifStatAuditLog = patientInsVerifStatAuditLogRepository
            .findById(patientInsVerifStatAuditLog.getId())
            .block();
        updatedPatientInsVerifStatAuditLog
            .insrnceVrifId(UPDATED_INSRNCE_VRIF_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO = patientInsVerifStatAuditLogMapper.toDto(
            updatedPatientInsVerifStatAuditLog
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientInsVerifStatAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsVerifStatAuditLogDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientInsVerifStatAuditLog in the database
        List<PatientInsVerifStatAuditLog> patientInsVerifStatAuditLogList = patientInsVerifStatAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientInsVerifStatAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientInsVerifStatAuditLog testPatientInsVerifStatAuditLog = patientInsVerifStatAuditLogList.get(
            patientInsVerifStatAuditLogList.size() - 1
        );
        assertThat(testPatientInsVerifStatAuditLog.getInsrnceVrifId()).isEqualTo(UPDATED_INSRNCE_VRIF_ID);
        assertThat(testPatientInsVerifStatAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPatientInsVerifStatAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPatientInsVerifStatAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPatientInsVerifStatAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPatientInsVerifStatAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void putNonExistingPatientInsVerifStatAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientInsVerifStatAuditLogRepository.findAll().collectList().block().size();
        patientInsVerifStatAuditLog.setId(count.incrementAndGet());

        // Create the PatientInsVerifStatAuditLog
        PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO = patientInsVerifStatAuditLogMapper.toDto(
            patientInsVerifStatAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patientInsVerifStatAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsVerifStatAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsVerifStatAuditLog in the database
        List<PatientInsVerifStatAuditLog> patientInsVerifStatAuditLogList = patientInsVerifStatAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientInsVerifStatAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPatientInsVerifStatAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientInsVerifStatAuditLogRepository.findAll().collectList().block().size();
        patientInsVerifStatAuditLog.setId(count.incrementAndGet());

        // Create the PatientInsVerifStatAuditLog
        PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO = patientInsVerifStatAuditLogMapper.toDto(
            patientInsVerifStatAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsVerifStatAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsVerifStatAuditLog in the database
        List<PatientInsVerifStatAuditLog> patientInsVerifStatAuditLogList = patientInsVerifStatAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientInsVerifStatAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPatientInsVerifStatAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientInsVerifStatAuditLogRepository.findAll().collectList().block().size();
        patientInsVerifStatAuditLog.setId(count.incrementAndGet());

        // Create the PatientInsVerifStatAuditLog
        PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO = patientInsVerifStatAuditLogMapper.toDto(
            patientInsVerifStatAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsVerifStatAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientInsVerifStatAuditLog in the database
        List<PatientInsVerifStatAuditLog> patientInsVerifStatAuditLogList = patientInsVerifStatAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientInsVerifStatAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePatientInsVerifStatAuditLogWithPatch() throws Exception {
        // Initialize the database
        patientInsVerifStatAuditLogRepository.save(patientInsVerifStatAuditLog).block();

        int databaseSizeBeforeUpdate = patientInsVerifStatAuditLogRepository.findAll().collectList().block().size();

        // Update the patientInsVerifStatAuditLog using partial update
        PatientInsVerifStatAuditLog partialUpdatedPatientInsVerifStatAuditLog = new PatientInsVerifStatAuditLog();
        partialUpdatedPatientInsVerifStatAuditLog.setId(patientInsVerifStatAuditLog.getId());

        partialUpdatedPatientInsVerifStatAuditLog
            .insrnceVrifId(UPDATED_INSRNCE_VRIF_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientInsVerifStatAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientInsVerifStatAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientInsVerifStatAuditLog in the database
        List<PatientInsVerifStatAuditLog> patientInsVerifStatAuditLogList = patientInsVerifStatAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientInsVerifStatAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientInsVerifStatAuditLog testPatientInsVerifStatAuditLog = patientInsVerifStatAuditLogList.get(
            patientInsVerifStatAuditLogList.size() - 1
        );
        assertThat(testPatientInsVerifStatAuditLog.getInsrnceVrifId()).isEqualTo(UPDATED_INSRNCE_VRIF_ID);
        assertThat(testPatientInsVerifStatAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPatientInsVerifStatAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPatientInsVerifStatAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPatientInsVerifStatAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testPatientInsVerifStatAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void fullUpdatePatientInsVerifStatAuditLogWithPatch() throws Exception {
        // Initialize the database
        patientInsVerifStatAuditLogRepository.save(patientInsVerifStatAuditLog).block();

        int databaseSizeBeforeUpdate = patientInsVerifStatAuditLogRepository.findAll().collectList().block().size();

        // Update the patientInsVerifStatAuditLog using partial update
        PatientInsVerifStatAuditLog partialUpdatedPatientInsVerifStatAuditLog = new PatientInsVerifStatAuditLog();
        partialUpdatedPatientInsVerifStatAuditLog.setId(patientInsVerifStatAuditLog.getId());

        partialUpdatedPatientInsVerifStatAuditLog
            .insrnceVrifId(UPDATED_INSRNCE_VRIF_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatientInsVerifStatAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatientInsVerifStatAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PatientInsVerifStatAuditLog in the database
        List<PatientInsVerifStatAuditLog> patientInsVerifStatAuditLogList = patientInsVerifStatAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientInsVerifStatAuditLogList).hasSize(databaseSizeBeforeUpdate);
        PatientInsVerifStatAuditLog testPatientInsVerifStatAuditLog = patientInsVerifStatAuditLogList.get(
            patientInsVerifStatAuditLogList.size() - 1
        );
        assertThat(testPatientInsVerifStatAuditLog.getInsrnceVrifId()).isEqualTo(UPDATED_INSRNCE_VRIF_ID);
        assertThat(testPatientInsVerifStatAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testPatientInsVerifStatAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testPatientInsVerifStatAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testPatientInsVerifStatAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testPatientInsVerifStatAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void patchNonExistingPatientInsVerifStatAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientInsVerifStatAuditLogRepository.findAll().collectList().block().size();
        patientInsVerifStatAuditLog.setId(count.incrementAndGet());

        // Create the PatientInsVerifStatAuditLog
        PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO = patientInsVerifStatAuditLogMapper.toDto(
            patientInsVerifStatAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, patientInsVerifStatAuditLogDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsVerifStatAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsVerifStatAuditLog in the database
        List<PatientInsVerifStatAuditLog> patientInsVerifStatAuditLogList = patientInsVerifStatAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientInsVerifStatAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPatientInsVerifStatAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientInsVerifStatAuditLogRepository.findAll().collectList().block().size();
        patientInsVerifStatAuditLog.setId(count.incrementAndGet());

        // Create the PatientInsVerifStatAuditLog
        PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO = patientInsVerifStatAuditLogMapper.toDto(
            patientInsVerifStatAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsVerifStatAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PatientInsVerifStatAuditLog in the database
        List<PatientInsVerifStatAuditLog> patientInsVerifStatAuditLogList = patientInsVerifStatAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientInsVerifStatAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPatientInsVerifStatAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = patientInsVerifStatAuditLogRepository.findAll().collectList().block().size();
        patientInsVerifStatAuditLog.setId(count.incrementAndGet());

        // Create the PatientInsVerifStatAuditLog
        PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO = patientInsVerifStatAuditLogMapper.toDto(
            patientInsVerifStatAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patientInsVerifStatAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PatientInsVerifStatAuditLog in the database
        List<PatientInsVerifStatAuditLog> patientInsVerifStatAuditLogList = patientInsVerifStatAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientInsVerifStatAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePatientInsVerifStatAuditLog() {
        // Initialize the database
        patientInsVerifStatAuditLogRepository.save(patientInsVerifStatAuditLog).block();

        int databaseSizeBeforeDelete = patientInsVerifStatAuditLogRepository.findAll().collectList().block().size();

        // Delete the patientInsVerifStatAuditLog
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, patientInsVerifStatAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PatientInsVerifStatAuditLog> patientInsVerifStatAuditLogList = patientInsVerifStatAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(patientInsVerifStatAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
