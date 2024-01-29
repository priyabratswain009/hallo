package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SalesOrderMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.SalesOrderMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderMasterAuditLogMapper;
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
 * Integration tests for the {@link SalesOrderMasterAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SalesOrderMasterAuditLogResourceIT {

    private static final Long DEFAULT_SALS_ODR_ID = 1L;
    private static final Long UPDATED_SALS_ODR_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/sales-order-master-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SalesOrderMasterAuditLogRepository salesOrderMasterAuditLogRepository;

    @Autowired
    private SalesOrderMasterAuditLogMapper salesOrderMasterAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SalesOrderMasterAuditLog salesOrderMasterAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderMasterAuditLog createEntity(EntityManager em) {
        SalesOrderMasterAuditLog salesOrderMasterAuditLog = new SalesOrderMasterAuditLog()
            .salsOdrId(DEFAULT_SALS_ODR_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return salesOrderMasterAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderMasterAuditLog createUpdatedEntity(EntityManager em) {
        SalesOrderMasterAuditLog salesOrderMasterAuditLog = new SalesOrderMasterAuditLog()
            .salsOdrId(UPDATED_SALS_ODR_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return salesOrderMasterAuditLog;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SalesOrderMasterAuditLog.class).block();
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
        salesOrderMasterAuditLog = createEntity(em);
    }

    @Test
    void createSalesOrderMasterAuditLog() throws Exception {
        int databaseSizeBeforeCreate = salesOrderMasterAuditLogRepository.findAll().collectList().block().size();
        // Create the SalesOrderMasterAuditLog
        SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO = salesOrderMasterAuditLogMapper.toDto(salesOrderMasterAuditLog);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SalesOrderMasterAuditLog in the database
        List<SalesOrderMasterAuditLog> salesOrderMasterAuditLogList = salesOrderMasterAuditLogRepository.findAll().collectList().block();
        assertThat(salesOrderMasterAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        SalesOrderMasterAuditLog testSalesOrderMasterAuditLog = salesOrderMasterAuditLogList.get(salesOrderMasterAuditLogList.size() - 1);
        assertThat(testSalesOrderMasterAuditLog.getSalsOdrId()).isEqualTo(DEFAULT_SALS_ODR_ID);
        assertThat(testSalesOrderMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testSalesOrderMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testSalesOrderMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testSalesOrderMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testSalesOrderMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void createSalesOrderMasterAuditLogWithExistingId() throws Exception {
        // Create the SalesOrderMasterAuditLog with an existing ID
        salesOrderMasterAuditLog.setId(1L);
        SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO = salesOrderMasterAuditLogMapper.toDto(salesOrderMasterAuditLog);

        int databaseSizeBeforeCreate = salesOrderMasterAuditLogRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderMasterAuditLog in the database
        List<SalesOrderMasterAuditLog> salesOrderMasterAuditLogList = salesOrderMasterAuditLogRepository.findAll().collectList().block();
        assertThat(salesOrderMasterAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSalesOrderMasterAuditLogs() {
        // Initialize the database
        salesOrderMasterAuditLogRepository.save(salesOrderMasterAuditLog).block();

        // Get all the salesOrderMasterAuditLogList
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
            .value(hasItem(salesOrderMasterAuditLog.getId().intValue()))
            .jsonPath("$.[*].salsOdrId")
            .value(hasItem(DEFAULT_SALS_ODR_ID.intValue()))
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
    void getSalesOrderMasterAuditLog() {
        // Initialize the database
        salesOrderMasterAuditLogRepository.save(salesOrderMasterAuditLog).block();

        // Get the salesOrderMasterAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, salesOrderMasterAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(salesOrderMasterAuditLog.getId().intValue()))
            .jsonPath("$.salsOdrId")
            .value(is(DEFAULT_SALS_ODR_ID.intValue()))
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
    void getNonExistingSalesOrderMasterAuditLog() {
        // Get the salesOrderMasterAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingSalesOrderMasterAuditLog() throws Exception {
        // Initialize the database
        salesOrderMasterAuditLogRepository.save(salesOrderMasterAuditLog).block();

        int databaseSizeBeforeUpdate = salesOrderMasterAuditLogRepository.findAll().collectList().block().size();

        // Update the salesOrderMasterAuditLog
        SalesOrderMasterAuditLog updatedSalesOrderMasterAuditLog = salesOrderMasterAuditLogRepository
            .findById(salesOrderMasterAuditLog.getId())
            .block();
        updatedSalesOrderMasterAuditLog
            .salsOdrId(UPDATED_SALS_ODR_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO = salesOrderMasterAuditLogMapper.toDto(updatedSalesOrderMasterAuditLog);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderMasterAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderMasterAuditLog in the database
        List<SalesOrderMasterAuditLog> salesOrderMasterAuditLogList = salesOrderMasterAuditLogRepository.findAll().collectList().block();
        assertThat(salesOrderMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderMasterAuditLog testSalesOrderMasterAuditLog = salesOrderMasterAuditLogList.get(salesOrderMasterAuditLogList.size() - 1);
        assertThat(testSalesOrderMasterAuditLog.getSalsOdrId()).isEqualTo(UPDATED_SALS_ODR_ID);
        assertThat(testSalesOrderMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testSalesOrderMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testSalesOrderMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testSalesOrderMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void putNonExistingSalesOrderMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderMasterAuditLogRepository.findAll().collectList().block().size();
        salesOrderMasterAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderMasterAuditLog
        SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO = salesOrderMasterAuditLogMapper.toDto(salesOrderMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderMasterAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderMasterAuditLog in the database
        List<SalesOrderMasterAuditLog> salesOrderMasterAuditLogList = salesOrderMasterAuditLogRepository.findAll().collectList().block();
        assertThat(salesOrderMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSalesOrderMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderMasterAuditLogRepository.findAll().collectList().block().size();
        salesOrderMasterAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderMasterAuditLog
        SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO = salesOrderMasterAuditLogMapper.toDto(salesOrderMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderMasterAuditLog in the database
        List<SalesOrderMasterAuditLog> salesOrderMasterAuditLogList = salesOrderMasterAuditLogRepository.findAll().collectList().block();
        assertThat(salesOrderMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSalesOrderMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderMasterAuditLogRepository.findAll().collectList().block().size();
        salesOrderMasterAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderMasterAuditLog
        SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO = salesOrderMasterAuditLogMapper.toDto(salesOrderMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderMasterAuditLog in the database
        List<SalesOrderMasterAuditLog> salesOrderMasterAuditLogList = salesOrderMasterAuditLogRepository.findAll().collectList().block();
        assertThat(salesOrderMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSalesOrderMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        salesOrderMasterAuditLogRepository.save(salesOrderMasterAuditLog).block();

        int databaseSizeBeforeUpdate = salesOrderMasterAuditLogRepository.findAll().collectList().block().size();

        // Update the salesOrderMasterAuditLog using partial update
        SalesOrderMasterAuditLog partialUpdatedSalesOrderMasterAuditLog = new SalesOrderMasterAuditLog();
        partialUpdatedSalesOrderMasterAuditLog.setId(salesOrderMasterAuditLog.getId());

        partialUpdatedSalesOrderMasterAuditLog.newRowData(UPDATED_NEW_ROW_DATA);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderMasterAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderMasterAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderMasterAuditLog in the database
        List<SalesOrderMasterAuditLog> salesOrderMasterAuditLogList = salesOrderMasterAuditLogRepository.findAll().collectList().block();
        assertThat(salesOrderMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderMasterAuditLog testSalesOrderMasterAuditLog = salesOrderMasterAuditLogList.get(salesOrderMasterAuditLogList.size() - 1);
        assertThat(testSalesOrderMasterAuditLog.getSalsOdrId()).isEqualTo(DEFAULT_SALS_ODR_ID);
        assertThat(testSalesOrderMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testSalesOrderMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testSalesOrderMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testSalesOrderMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void fullUpdateSalesOrderMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        salesOrderMasterAuditLogRepository.save(salesOrderMasterAuditLog).block();

        int databaseSizeBeforeUpdate = salesOrderMasterAuditLogRepository.findAll().collectList().block().size();

        // Update the salesOrderMasterAuditLog using partial update
        SalesOrderMasterAuditLog partialUpdatedSalesOrderMasterAuditLog = new SalesOrderMasterAuditLog();
        partialUpdatedSalesOrderMasterAuditLog.setId(salesOrderMasterAuditLog.getId());

        partialUpdatedSalesOrderMasterAuditLog
            .salsOdrId(UPDATED_SALS_ODR_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderMasterAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderMasterAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderMasterAuditLog in the database
        List<SalesOrderMasterAuditLog> salesOrderMasterAuditLogList = salesOrderMasterAuditLogRepository.findAll().collectList().block();
        assertThat(salesOrderMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderMasterAuditLog testSalesOrderMasterAuditLog = salesOrderMasterAuditLogList.get(salesOrderMasterAuditLogList.size() - 1);
        assertThat(testSalesOrderMasterAuditLog.getSalsOdrId()).isEqualTo(UPDATED_SALS_ODR_ID);
        assertThat(testSalesOrderMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testSalesOrderMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testSalesOrderMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testSalesOrderMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void patchNonExistingSalesOrderMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderMasterAuditLogRepository.findAll().collectList().block().size();
        salesOrderMasterAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderMasterAuditLog
        SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO = salesOrderMasterAuditLogMapper.toDto(salesOrderMasterAuditLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, salesOrderMasterAuditLogDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderMasterAuditLog in the database
        List<SalesOrderMasterAuditLog> salesOrderMasterAuditLogList = salesOrderMasterAuditLogRepository.findAll().collectList().block();
        assertThat(salesOrderMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSalesOrderMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderMasterAuditLogRepository.findAll().collectList().block().size();
        salesOrderMasterAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderMasterAuditLog
        SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO = salesOrderMasterAuditLogMapper.toDto(salesOrderMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderMasterAuditLog in the database
        List<SalesOrderMasterAuditLog> salesOrderMasterAuditLogList = salesOrderMasterAuditLogRepository.findAll().collectList().block();
        assertThat(salesOrderMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSalesOrderMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderMasterAuditLogRepository.findAll().collectList().block().size();
        salesOrderMasterAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderMasterAuditLog
        SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO = salesOrderMasterAuditLogMapper.toDto(salesOrderMasterAuditLog);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderMasterAuditLog in the database
        List<SalesOrderMasterAuditLog> salesOrderMasterAuditLogList = salesOrderMasterAuditLogRepository.findAll().collectList().block();
        assertThat(salesOrderMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSalesOrderMasterAuditLog() {
        // Initialize the database
        salesOrderMasterAuditLogRepository.save(salesOrderMasterAuditLog).block();

        int databaseSizeBeforeDelete = salesOrderMasterAuditLogRepository.findAll().collectList().block().size();

        // Delete the salesOrderMasterAuditLog
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, salesOrderMasterAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SalesOrderMasterAuditLog> salesOrderMasterAuditLogList = salesOrderMasterAuditLogRepository.findAll().collectList().block();
        assertThat(salesOrderMasterAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
