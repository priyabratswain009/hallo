package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.WorkersCompensationAuditLog;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.WorkersCompensationAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.WorkersCompensationAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.WorkersCompensationAuditLogMapper;
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
 * Integration tests for the {@link WorkersCompensationAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class WorkersCompensationAuditLogResourceIT {

    private static final Long DEFAULT_WORERS_COMENATION_ID = 1L;
    private static final Long UPDATED_WORERS_COMENATION_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/workers-compensation-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private WorkersCompensationAuditLogRepository workersCompensationAuditLogRepository;

    @Autowired
    private WorkersCompensationAuditLogMapper workersCompensationAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private WorkersCompensationAuditLog workersCompensationAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkersCompensationAuditLog createEntity(EntityManager em) {
        WorkersCompensationAuditLog workersCompensationAuditLog = new WorkersCompensationAuditLog()
            .worersComenationId(DEFAULT_WORERS_COMENATION_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return workersCompensationAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkersCompensationAuditLog createUpdatedEntity(EntityManager em) {
        WorkersCompensationAuditLog workersCompensationAuditLog = new WorkersCompensationAuditLog()
            .worersComenationId(UPDATED_WORERS_COMENATION_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return workersCompensationAuditLog;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(WorkersCompensationAuditLog.class).block();
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
        workersCompensationAuditLog = createEntity(em);
    }

    @Test
    void createWorkersCompensationAuditLog() throws Exception {
        int databaseSizeBeforeCreate = workersCompensationAuditLogRepository.findAll().collectList().block().size();
        // Create the WorkersCompensationAuditLog
        WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO = workersCompensationAuditLogMapper.toDto(
            workersCompensationAuditLog
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(workersCompensationAuditLogDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the WorkersCompensationAuditLog in the database
        List<WorkersCompensationAuditLog> workersCompensationAuditLogList = workersCompensationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(workersCompensationAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        WorkersCompensationAuditLog testWorkersCompensationAuditLog = workersCompensationAuditLogList.get(
            workersCompensationAuditLogList.size() - 1
        );
        assertThat(testWorkersCompensationAuditLog.getWorersComenationId()).isEqualTo(DEFAULT_WORERS_COMENATION_ID);
        assertThat(testWorkersCompensationAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testWorkersCompensationAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testWorkersCompensationAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testWorkersCompensationAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testWorkersCompensationAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void createWorkersCompensationAuditLogWithExistingId() throws Exception {
        // Create the WorkersCompensationAuditLog with an existing ID
        workersCompensationAuditLog.setId(1L);
        WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO = workersCompensationAuditLogMapper.toDto(
            workersCompensationAuditLog
        );

        int databaseSizeBeforeCreate = workersCompensationAuditLogRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(workersCompensationAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the WorkersCompensationAuditLog in the database
        List<WorkersCompensationAuditLog> workersCompensationAuditLogList = workersCompensationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(workersCompensationAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllWorkersCompensationAuditLogs() {
        // Initialize the database
        workersCompensationAuditLogRepository.save(workersCompensationAuditLog).block();

        // Get all the workersCompensationAuditLogList
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
            .value(hasItem(workersCompensationAuditLog.getId().intValue()))
            .jsonPath("$.[*].worersComenationId")
            .value(hasItem(DEFAULT_WORERS_COMENATION_ID.intValue()))
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
    void getWorkersCompensationAuditLog() {
        // Initialize the database
        workersCompensationAuditLogRepository.save(workersCompensationAuditLog).block();

        // Get the workersCompensationAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, workersCompensationAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(workersCompensationAuditLog.getId().intValue()))
            .jsonPath("$.worersComenationId")
            .value(is(DEFAULT_WORERS_COMENATION_ID.intValue()))
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
    void getNonExistingWorkersCompensationAuditLog() {
        // Get the workersCompensationAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingWorkersCompensationAuditLog() throws Exception {
        // Initialize the database
        workersCompensationAuditLogRepository.save(workersCompensationAuditLog).block();

        int databaseSizeBeforeUpdate = workersCompensationAuditLogRepository.findAll().collectList().block().size();

        // Update the workersCompensationAuditLog
        WorkersCompensationAuditLog updatedWorkersCompensationAuditLog = workersCompensationAuditLogRepository
            .findById(workersCompensationAuditLog.getId())
            .block();
        updatedWorkersCompensationAuditLog
            .worersComenationId(UPDATED_WORERS_COMENATION_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO = workersCompensationAuditLogMapper.toDto(
            updatedWorkersCompensationAuditLog
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, workersCompensationAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(workersCompensationAuditLogDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the WorkersCompensationAuditLog in the database
        List<WorkersCompensationAuditLog> workersCompensationAuditLogList = workersCompensationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(workersCompensationAuditLogList).hasSize(databaseSizeBeforeUpdate);
        WorkersCompensationAuditLog testWorkersCompensationAuditLog = workersCompensationAuditLogList.get(
            workersCompensationAuditLogList.size() - 1
        );
        assertThat(testWorkersCompensationAuditLog.getWorersComenationId()).isEqualTo(UPDATED_WORERS_COMENATION_ID);
        assertThat(testWorkersCompensationAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testWorkersCompensationAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testWorkersCompensationAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testWorkersCompensationAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testWorkersCompensationAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void putNonExistingWorkersCompensationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = workersCompensationAuditLogRepository.findAll().collectList().block().size();
        workersCompensationAuditLog.setId(count.incrementAndGet());

        // Create the WorkersCompensationAuditLog
        WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO = workersCompensationAuditLogMapper.toDto(
            workersCompensationAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, workersCompensationAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(workersCompensationAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the WorkersCompensationAuditLog in the database
        List<WorkersCompensationAuditLog> workersCompensationAuditLogList = workersCompensationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(workersCompensationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchWorkersCompensationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = workersCompensationAuditLogRepository.findAll().collectList().block().size();
        workersCompensationAuditLog.setId(count.incrementAndGet());

        // Create the WorkersCompensationAuditLog
        WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO = workersCompensationAuditLogMapper.toDto(
            workersCompensationAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(workersCompensationAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the WorkersCompensationAuditLog in the database
        List<WorkersCompensationAuditLog> workersCompensationAuditLogList = workersCompensationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(workersCompensationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamWorkersCompensationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = workersCompensationAuditLogRepository.findAll().collectList().block().size();
        workersCompensationAuditLog.setId(count.incrementAndGet());

        // Create the WorkersCompensationAuditLog
        WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO = workersCompensationAuditLogMapper.toDto(
            workersCompensationAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(workersCompensationAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the WorkersCompensationAuditLog in the database
        List<WorkersCompensationAuditLog> workersCompensationAuditLogList = workersCompensationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(workersCompensationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateWorkersCompensationAuditLogWithPatch() throws Exception {
        // Initialize the database
        workersCompensationAuditLogRepository.save(workersCompensationAuditLog).block();

        int databaseSizeBeforeUpdate = workersCompensationAuditLogRepository.findAll().collectList().block().size();

        // Update the workersCompensationAuditLog using partial update
        WorkersCompensationAuditLog partialUpdatedWorkersCompensationAuditLog = new WorkersCompensationAuditLog();
        partialUpdatedWorkersCompensationAuditLog.setId(workersCompensationAuditLog.getId());

        partialUpdatedWorkersCompensationAuditLog
            .worersComenationId(UPDATED_WORERS_COMENATION_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedWorkersCompensationAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedWorkersCompensationAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the WorkersCompensationAuditLog in the database
        List<WorkersCompensationAuditLog> workersCompensationAuditLogList = workersCompensationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(workersCompensationAuditLogList).hasSize(databaseSizeBeforeUpdate);
        WorkersCompensationAuditLog testWorkersCompensationAuditLog = workersCompensationAuditLogList.get(
            workersCompensationAuditLogList.size() - 1
        );
        assertThat(testWorkersCompensationAuditLog.getWorersComenationId()).isEqualTo(UPDATED_WORERS_COMENATION_ID);
        assertThat(testWorkersCompensationAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testWorkersCompensationAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testWorkersCompensationAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testWorkersCompensationAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testWorkersCompensationAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void fullUpdateWorkersCompensationAuditLogWithPatch() throws Exception {
        // Initialize the database
        workersCompensationAuditLogRepository.save(workersCompensationAuditLog).block();

        int databaseSizeBeforeUpdate = workersCompensationAuditLogRepository.findAll().collectList().block().size();

        // Update the workersCompensationAuditLog using partial update
        WorkersCompensationAuditLog partialUpdatedWorkersCompensationAuditLog = new WorkersCompensationAuditLog();
        partialUpdatedWorkersCompensationAuditLog.setId(workersCompensationAuditLog.getId());

        partialUpdatedWorkersCompensationAuditLog
            .worersComenationId(UPDATED_WORERS_COMENATION_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedWorkersCompensationAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedWorkersCompensationAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the WorkersCompensationAuditLog in the database
        List<WorkersCompensationAuditLog> workersCompensationAuditLogList = workersCompensationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(workersCompensationAuditLogList).hasSize(databaseSizeBeforeUpdate);
        WorkersCompensationAuditLog testWorkersCompensationAuditLog = workersCompensationAuditLogList.get(
            workersCompensationAuditLogList.size() - 1
        );
        assertThat(testWorkersCompensationAuditLog.getWorersComenationId()).isEqualTo(UPDATED_WORERS_COMENATION_ID);
        assertThat(testWorkersCompensationAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testWorkersCompensationAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testWorkersCompensationAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testWorkersCompensationAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testWorkersCompensationAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void patchNonExistingWorkersCompensationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = workersCompensationAuditLogRepository.findAll().collectList().block().size();
        workersCompensationAuditLog.setId(count.incrementAndGet());

        // Create the WorkersCompensationAuditLog
        WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO = workersCompensationAuditLogMapper.toDto(
            workersCompensationAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, workersCompensationAuditLogDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(workersCompensationAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the WorkersCompensationAuditLog in the database
        List<WorkersCompensationAuditLog> workersCompensationAuditLogList = workersCompensationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(workersCompensationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchWorkersCompensationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = workersCompensationAuditLogRepository.findAll().collectList().block().size();
        workersCompensationAuditLog.setId(count.incrementAndGet());

        // Create the WorkersCompensationAuditLog
        WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO = workersCompensationAuditLogMapper.toDto(
            workersCompensationAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(workersCompensationAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the WorkersCompensationAuditLog in the database
        List<WorkersCompensationAuditLog> workersCompensationAuditLogList = workersCompensationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(workersCompensationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamWorkersCompensationAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = workersCompensationAuditLogRepository.findAll().collectList().block().size();
        workersCompensationAuditLog.setId(count.incrementAndGet());

        // Create the WorkersCompensationAuditLog
        WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO = workersCompensationAuditLogMapper.toDto(
            workersCompensationAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(workersCompensationAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the WorkersCompensationAuditLog in the database
        List<WorkersCompensationAuditLog> workersCompensationAuditLogList = workersCompensationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(workersCompensationAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteWorkersCompensationAuditLog() {
        // Initialize the database
        workersCompensationAuditLogRepository.save(workersCompensationAuditLog).block();

        int databaseSizeBeforeDelete = workersCompensationAuditLogRepository.findAll().collectList().block().size();

        // Delete the workersCompensationAuditLog
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, workersCompensationAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<WorkersCompensationAuditLog> workersCompensationAuditLogList = workersCompensationAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(workersCompensationAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
