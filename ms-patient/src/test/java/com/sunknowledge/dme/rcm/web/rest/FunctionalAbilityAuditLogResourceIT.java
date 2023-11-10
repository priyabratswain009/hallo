package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.FunctionalAbilityAuditLog;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.FunctionalAbilityAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.FunctionalAbilityAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.FunctionalAbilityAuditLogMapper;
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
 * Integration tests for the {@link FunctionalAbilityAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class FunctionalAbilityAuditLogResourceIT {

    private static final Long DEFAULT_FUNAL_ABITY_ID = 1L;
    private static final Long UPDATED_FUNAL_ABITY_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/functional-ability-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FunctionalAbilityAuditLogRepository functionalAbilityAuditLogRepository;

    @Autowired
    private FunctionalAbilityAuditLogMapper functionalAbilityAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private FunctionalAbilityAuditLog functionalAbilityAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FunctionalAbilityAuditLog createEntity(EntityManager em) {
        FunctionalAbilityAuditLog functionalAbilityAuditLog = new FunctionalAbilityAuditLog()
            .funalAbityId(DEFAULT_FUNAL_ABITY_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return functionalAbilityAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FunctionalAbilityAuditLog createUpdatedEntity(EntityManager em) {
        FunctionalAbilityAuditLog functionalAbilityAuditLog = new FunctionalAbilityAuditLog()
            .funalAbityId(UPDATED_FUNAL_ABITY_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return functionalAbilityAuditLog;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(FunctionalAbilityAuditLog.class).block();
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
        functionalAbilityAuditLog = createEntity(em);
    }

    @Test
    void createFunctionalAbilityAuditLog() throws Exception {
        int databaseSizeBeforeCreate = functionalAbilityAuditLogRepository.findAll().collectList().block().size();
        // Create the FunctionalAbilityAuditLog
        FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO = functionalAbilityAuditLogMapper.toDto(functionalAbilityAuditLog);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(functionalAbilityAuditLogDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the FunctionalAbilityAuditLog in the database
        List<FunctionalAbilityAuditLog> functionalAbilityAuditLogList = functionalAbilityAuditLogRepository.findAll().collectList().block();
        assertThat(functionalAbilityAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        FunctionalAbilityAuditLog testFunctionalAbilityAuditLog = functionalAbilityAuditLogList.get(
            functionalAbilityAuditLogList.size() - 1
        );
        assertThat(testFunctionalAbilityAuditLog.getFunalAbityId()).isEqualTo(DEFAULT_FUNAL_ABITY_ID);
        assertThat(testFunctionalAbilityAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testFunctionalAbilityAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testFunctionalAbilityAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testFunctionalAbilityAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testFunctionalAbilityAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void createFunctionalAbilityAuditLogWithExistingId() throws Exception {
        // Create the FunctionalAbilityAuditLog with an existing ID
        functionalAbilityAuditLog.setId(1L);
        FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO = functionalAbilityAuditLogMapper.toDto(functionalAbilityAuditLog);

        int databaseSizeBeforeCreate = functionalAbilityAuditLogRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(functionalAbilityAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FunctionalAbilityAuditLog in the database
        List<FunctionalAbilityAuditLog> functionalAbilityAuditLogList = functionalAbilityAuditLogRepository.findAll().collectList().block();
        assertThat(functionalAbilityAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllFunctionalAbilityAuditLogs() {
        // Initialize the database
        functionalAbilityAuditLogRepository.save(functionalAbilityAuditLog).block();

        // Get all the functionalAbilityAuditLogList
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
            .value(hasItem(functionalAbilityAuditLog.getId().intValue()))
            .jsonPath("$.[*].funalAbityId")
            .value(hasItem(DEFAULT_FUNAL_ABITY_ID.intValue()))
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
    void getFunctionalAbilityAuditLog() {
        // Initialize the database
        functionalAbilityAuditLogRepository.save(functionalAbilityAuditLog).block();

        // Get the functionalAbilityAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, functionalAbilityAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(functionalAbilityAuditLog.getId().intValue()))
            .jsonPath("$.funalAbityId")
            .value(is(DEFAULT_FUNAL_ABITY_ID.intValue()))
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
    void getNonExistingFunctionalAbilityAuditLog() {
        // Get the functionalAbilityAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingFunctionalAbilityAuditLog() throws Exception {
        // Initialize the database
        functionalAbilityAuditLogRepository.save(functionalAbilityAuditLog).block();

        int databaseSizeBeforeUpdate = functionalAbilityAuditLogRepository.findAll().collectList().block().size();

        // Update the functionalAbilityAuditLog
        FunctionalAbilityAuditLog updatedFunctionalAbilityAuditLog = functionalAbilityAuditLogRepository
            .findById(functionalAbilityAuditLog.getId())
            .block();
        updatedFunctionalAbilityAuditLog
            .funalAbityId(UPDATED_FUNAL_ABITY_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO = functionalAbilityAuditLogMapper.toDto(updatedFunctionalAbilityAuditLog);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, functionalAbilityAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(functionalAbilityAuditLogDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FunctionalAbilityAuditLog in the database
        List<FunctionalAbilityAuditLog> functionalAbilityAuditLogList = functionalAbilityAuditLogRepository.findAll().collectList().block();
        assertThat(functionalAbilityAuditLogList).hasSize(databaseSizeBeforeUpdate);
        FunctionalAbilityAuditLog testFunctionalAbilityAuditLog = functionalAbilityAuditLogList.get(
            functionalAbilityAuditLogList.size() - 1
        );
        assertThat(testFunctionalAbilityAuditLog.getFunalAbityId()).isEqualTo(UPDATED_FUNAL_ABITY_ID);
        assertThat(testFunctionalAbilityAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testFunctionalAbilityAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testFunctionalAbilityAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testFunctionalAbilityAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testFunctionalAbilityAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void putNonExistingFunctionalAbilityAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = functionalAbilityAuditLogRepository.findAll().collectList().block().size();
        functionalAbilityAuditLog.setId(count.incrementAndGet());

        // Create the FunctionalAbilityAuditLog
        FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO = functionalAbilityAuditLogMapper.toDto(functionalAbilityAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, functionalAbilityAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(functionalAbilityAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FunctionalAbilityAuditLog in the database
        List<FunctionalAbilityAuditLog> functionalAbilityAuditLogList = functionalAbilityAuditLogRepository.findAll().collectList().block();
        assertThat(functionalAbilityAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchFunctionalAbilityAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = functionalAbilityAuditLogRepository.findAll().collectList().block().size();
        functionalAbilityAuditLog.setId(count.incrementAndGet());

        // Create the FunctionalAbilityAuditLog
        FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO = functionalAbilityAuditLogMapper.toDto(functionalAbilityAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(functionalAbilityAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FunctionalAbilityAuditLog in the database
        List<FunctionalAbilityAuditLog> functionalAbilityAuditLogList = functionalAbilityAuditLogRepository.findAll().collectList().block();
        assertThat(functionalAbilityAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamFunctionalAbilityAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = functionalAbilityAuditLogRepository.findAll().collectList().block().size();
        functionalAbilityAuditLog.setId(count.incrementAndGet());

        // Create the FunctionalAbilityAuditLog
        FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO = functionalAbilityAuditLogMapper.toDto(functionalAbilityAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(functionalAbilityAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the FunctionalAbilityAuditLog in the database
        List<FunctionalAbilityAuditLog> functionalAbilityAuditLogList = functionalAbilityAuditLogRepository.findAll().collectList().block();
        assertThat(functionalAbilityAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateFunctionalAbilityAuditLogWithPatch() throws Exception {
        // Initialize the database
        functionalAbilityAuditLogRepository.save(functionalAbilityAuditLog).block();

        int databaseSizeBeforeUpdate = functionalAbilityAuditLogRepository.findAll().collectList().block().size();

        // Update the functionalAbilityAuditLog using partial update
        FunctionalAbilityAuditLog partialUpdatedFunctionalAbilityAuditLog = new FunctionalAbilityAuditLog();
        partialUpdatedFunctionalAbilityAuditLog.setId(functionalAbilityAuditLog.getId());

        partialUpdatedFunctionalAbilityAuditLog
            .funalAbityId(UPDATED_FUNAL_ABITY_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedFunctionalAbilityAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedFunctionalAbilityAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FunctionalAbilityAuditLog in the database
        List<FunctionalAbilityAuditLog> functionalAbilityAuditLogList = functionalAbilityAuditLogRepository.findAll().collectList().block();
        assertThat(functionalAbilityAuditLogList).hasSize(databaseSizeBeforeUpdate);
        FunctionalAbilityAuditLog testFunctionalAbilityAuditLog = functionalAbilityAuditLogList.get(
            functionalAbilityAuditLogList.size() - 1
        );
        assertThat(testFunctionalAbilityAuditLog.getFunalAbityId()).isEqualTo(UPDATED_FUNAL_ABITY_ID);
        assertThat(testFunctionalAbilityAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testFunctionalAbilityAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testFunctionalAbilityAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testFunctionalAbilityAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testFunctionalAbilityAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void fullUpdateFunctionalAbilityAuditLogWithPatch() throws Exception {
        // Initialize the database
        functionalAbilityAuditLogRepository.save(functionalAbilityAuditLog).block();

        int databaseSizeBeforeUpdate = functionalAbilityAuditLogRepository.findAll().collectList().block().size();

        // Update the functionalAbilityAuditLog using partial update
        FunctionalAbilityAuditLog partialUpdatedFunctionalAbilityAuditLog = new FunctionalAbilityAuditLog();
        partialUpdatedFunctionalAbilityAuditLog.setId(functionalAbilityAuditLog.getId());

        partialUpdatedFunctionalAbilityAuditLog
            .funalAbityId(UPDATED_FUNAL_ABITY_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedFunctionalAbilityAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedFunctionalAbilityAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FunctionalAbilityAuditLog in the database
        List<FunctionalAbilityAuditLog> functionalAbilityAuditLogList = functionalAbilityAuditLogRepository.findAll().collectList().block();
        assertThat(functionalAbilityAuditLogList).hasSize(databaseSizeBeforeUpdate);
        FunctionalAbilityAuditLog testFunctionalAbilityAuditLog = functionalAbilityAuditLogList.get(
            functionalAbilityAuditLogList.size() - 1
        );
        assertThat(testFunctionalAbilityAuditLog.getFunalAbityId()).isEqualTo(UPDATED_FUNAL_ABITY_ID);
        assertThat(testFunctionalAbilityAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testFunctionalAbilityAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testFunctionalAbilityAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testFunctionalAbilityAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testFunctionalAbilityAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void patchNonExistingFunctionalAbilityAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = functionalAbilityAuditLogRepository.findAll().collectList().block().size();
        functionalAbilityAuditLog.setId(count.incrementAndGet());

        // Create the FunctionalAbilityAuditLog
        FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO = functionalAbilityAuditLogMapper.toDto(functionalAbilityAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, functionalAbilityAuditLogDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(functionalAbilityAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FunctionalAbilityAuditLog in the database
        List<FunctionalAbilityAuditLog> functionalAbilityAuditLogList = functionalAbilityAuditLogRepository.findAll().collectList().block();
        assertThat(functionalAbilityAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchFunctionalAbilityAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = functionalAbilityAuditLogRepository.findAll().collectList().block().size();
        functionalAbilityAuditLog.setId(count.incrementAndGet());

        // Create the FunctionalAbilityAuditLog
        FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO = functionalAbilityAuditLogMapper.toDto(functionalAbilityAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(functionalAbilityAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FunctionalAbilityAuditLog in the database
        List<FunctionalAbilityAuditLog> functionalAbilityAuditLogList = functionalAbilityAuditLogRepository.findAll().collectList().block();
        assertThat(functionalAbilityAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamFunctionalAbilityAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = functionalAbilityAuditLogRepository.findAll().collectList().block().size();
        functionalAbilityAuditLog.setId(count.incrementAndGet());

        // Create the FunctionalAbilityAuditLog
        FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO = functionalAbilityAuditLogMapper.toDto(functionalAbilityAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(functionalAbilityAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the FunctionalAbilityAuditLog in the database
        List<FunctionalAbilityAuditLog> functionalAbilityAuditLogList = functionalAbilityAuditLogRepository.findAll().collectList().block();
        assertThat(functionalAbilityAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteFunctionalAbilityAuditLog() {
        // Initialize the database
        functionalAbilityAuditLogRepository.save(functionalAbilityAuditLog).block();

        int databaseSizeBeforeDelete = functionalAbilityAuditLogRepository.findAll().collectList().block().size();

        // Delete the functionalAbilityAuditLog
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, functionalAbilityAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<FunctionalAbilityAuditLog> functionalAbilityAuditLogList = functionalAbilityAuditLogRepository.findAll().collectList().block();
        assertThat(functionalAbilityAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
