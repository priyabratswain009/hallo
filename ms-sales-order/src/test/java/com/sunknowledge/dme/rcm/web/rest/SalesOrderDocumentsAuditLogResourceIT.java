package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SalesOrderDocumentsAuditLog;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.SalesOrderDocumentsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderDocumentsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderDocumentsAuditLogMapper;
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
 * Integration tests for the {@link SalesOrderDocumentsAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SalesOrderDocumentsAuditLogResourceIT {

    private static final Long DEFAULT_SALES_ORDER_DOC_ID = 1L;
    private static final Long UPDATED_SALES_ORDER_DOC_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/sales-order-documents-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SalesOrderDocumentsAuditLogRepository salesOrderDocumentsAuditLogRepository;

    @Autowired
    private SalesOrderDocumentsAuditLogMapper salesOrderDocumentsAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SalesOrderDocumentsAuditLog salesOrderDocumentsAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderDocumentsAuditLog createEntity(EntityManager em) {
        SalesOrderDocumentsAuditLog salesOrderDocumentsAuditLog = new SalesOrderDocumentsAuditLog()
            .salesOrderDocId(DEFAULT_SALES_ORDER_DOC_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return salesOrderDocumentsAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderDocumentsAuditLog createUpdatedEntity(EntityManager em) {
        SalesOrderDocumentsAuditLog salesOrderDocumentsAuditLog = new SalesOrderDocumentsAuditLog()
            .salesOrderDocId(UPDATED_SALES_ORDER_DOC_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return salesOrderDocumentsAuditLog;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SalesOrderDocumentsAuditLog.class).block();
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
        salesOrderDocumentsAuditLog = createEntity(em);
    }

    @Test
    void createSalesOrderDocumentsAuditLog() throws Exception {
        int databaseSizeBeforeCreate = salesOrderDocumentsAuditLogRepository.findAll().collectList().block().size();
        // Create the SalesOrderDocumentsAuditLog
        SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO = salesOrderDocumentsAuditLogMapper.toDto(
            salesOrderDocumentsAuditLog
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderDocumentsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SalesOrderDocumentsAuditLog in the database
        List<SalesOrderDocumentsAuditLog> salesOrderDocumentsAuditLogList = salesOrderDocumentsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderDocumentsAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        SalesOrderDocumentsAuditLog testSalesOrderDocumentsAuditLog = salesOrderDocumentsAuditLogList.get(
            salesOrderDocumentsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderDocumentsAuditLog.getSalesOrderDocId()).isEqualTo(DEFAULT_SALES_ORDER_DOC_ID);
        assertThat(testSalesOrderDocumentsAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testSalesOrderDocumentsAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testSalesOrderDocumentsAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testSalesOrderDocumentsAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testSalesOrderDocumentsAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void createSalesOrderDocumentsAuditLogWithExistingId() throws Exception {
        // Create the SalesOrderDocumentsAuditLog with an existing ID
        salesOrderDocumentsAuditLog.setId(1L);
        SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO = salesOrderDocumentsAuditLogMapper.toDto(
            salesOrderDocumentsAuditLog
        );

        int databaseSizeBeforeCreate = salesOrderDocumentsAuditLogRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderDocumentsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderDocumentsAuditLog in the database
        List<SalesOrderDocumentsAuditLog> salesOrderDocumentsAuditLogList = salesOrderDocumentsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderDocumentsAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSalesOrderDocumentsAuditLogs() {
        // Initialize the database
        salesOrderDocumentsAuditLogRepository.save(salesOrderDocumentsAuditLog).block();

        // Get all the salesOrderDocumentsAuditLogList
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
            .value(hasItem(salesOrderDocumentsAuditLog.getId().intValue()))
            .jsonPath("$.[*].salesOrderDocId")
            .value(hasItem(DEFAULT_SALES_ORDER_DOC_ID.intValue()))
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
    void getSalesOrderDocumentsAuditLog() {
        // Initialize the database
        salesOrderDocumentsAuditLogRepository.save(salesOrderDocumentsAuditLog).block();

        // Get the salesOrderDocumentsAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, salesOrderDocumentsAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(salesOrderDocumentsAuditLog.getId().intValue()))
            .jsonPath("$.salesOrderDocId")
            .value(is(DEFAULT_SALES_ORDER_DOC_ID.intValue()))
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
    void getNonExistingSalesOrderDocumentsAuditLog() {
        // Get the salesOrderDocumentsAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingSalesOrderDocumentsAuditLog() throws Exception {
        // Initialize the database
        salesOrderDocumentsAuditLogRepository.save(salesOrderDocumentsAuditLog).block();

        int databaseSizeBeforeUpdate = salesOrderDocumentsAuditLogRepository.findAll().collectList().block().size();

        // Update the salesOrderDocumentsAuditLog
        SalesOrderDocumentsAuditLog updatedSalesOrderDocumentsAuditLog = salesOrderDocumentsAuditLogRepository
            .findById(salesOrderDocumentsAuditLog.getId())
            .block();
        updatedSalesOrderDocumentsAuditLog
            .salesOrderDocId(UPDATED_SALES_ORDER_DOC_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO = salesOrderDocumentsAuditLogMapper.toDto(
            updatedSalesOrderDocumentsAuditLog
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderDocumentsAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderDocumentsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderDocumentsAuditLog in the database
        List<SalesOrderDocumentsAuditLog> salesOrderDocumentsAuditLogList = salesOrderDocumentsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderDocumentsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderDocumentsAuditLog testSalesOrderDocumentsAuditLog = salesOrderDocumentsAuditLogList.get(
            salesOrderDocumentsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderDocumentsAuditLog.getSalesOrderDocId()).isEqualTo(UPDATED_SALES_ORDER_DOC_ID);
        assertThat(testSalesOrderDocumentsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testSalesOrderDocumentsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderDocumentsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testSalesOrderDocumentsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testSalesOrderDocumentsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void putNonExistingSalesOrderDocumentsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderDocumentsAuditLogRepository.findAll().collectList().block().size();
        salesOrderDocumentsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderDocumentsAuditLog
        SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO = salesOrderDocumentsAuditLogMapper.toDto(
            salesOrderDocumentsAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderDocumentsAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderDocumentsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderDocumentsAuditLog in the database
        List<SalesOrderDocumentsAuditLog> salesOrderDocumentsAuditLogList = salesOrderDocumentsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderDocumentsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSalesOrderDocumentsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderDocumentsAuditLogRepository.findAll().collectList().block().size();
        salesOrderDocumentsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderDocumentsAuditLog
        SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO = salesOrderDocumentsAuditLogMapper.toDto(
            salesOrderDocumentsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderDocumentsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderDocumentsAuditLog in the database
        List<SalesOrderDocumentsAuditLog> salesOrderDocumentsAuditLogList = salesOrderDocumentsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderDocumentsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSalesOrderDocumentsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderDocumentsAuditLogRepository.findAll().collectList().block().size();
        salesOrderDocumentsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderDocumentsAuditLog
        SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO = salesOrderDocumentsAuditLogMapper.toDto(
            salesOrderDocumentsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderDocumentsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderDocumentsAuditLog in the database
        List<SalesOrderDocumentsAuditLog> salesOrderDocumentsAuditLogList = salesOrderDocumentsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderDocumentsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSalesOrderDocumentsAuditLogWithPatch() throws Exception {
        // Initialize the database
        salesOrderDocumentsAuditLogRepository.save(salesOrderDocumentsAuditLog).block();

        int databaseSizeBeforeUpdate = salesOrderDocumentsAuditLogRepository.findAll().collectList().block().size();

        // Update the salesOrderDocumentsAuditLog using partial update
        SalesOrderDocumentsAuditLog partialUpdatedSalesOrderDocumentsAuditLog = new SalesOrderDocumentsAuditLog();
        partialUpdatedSalesOrderDocumentsAuditLog.setId(salesOrderDocumentsAuditLog.getId());

        partialUpdatedSalesOrderDocumentsAuditLog
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderDocumentsAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderDocumentsAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderDocumentsAuditLog in the database
        List<SalesOrderDocumentsAuditLog> salesOrderDocumentsAuditLogList = salesOrderDocumentsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderDocumentsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderDocumentsAuditLog testSalesOrderDocumentsAuditLog = salesOrderDocumentsAuditLogList.get(
            salesOrderDocumentsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderDocumentsAuditLog.getSalesOrderDocId()).isEqualTo(DEFAULT_SALES_ORDER_DOC_ID);
        assertThat(testSalesOrderDocumentsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testSalesOrderDocumentsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderDocumentsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testSalesOrderDocumentsAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testSalesOrderDocumentsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void fullUpdateSalesOrderDocumentsAuditLogWithPatch() throws Exception {
        // Initialize the database
        salesOrderDocumentsAuditLogRepository.save(salesOrderDocumentsAuditLog).block();

        int databaseSizeBeforeUpdate = salesOrderDocumentsAuditLogRepository.findAll().collectList().block().size();

        // Update the salesOrderDocumentsAuditLog using partial update
        SalesOrderDocumentsAuditLog partialUpdatedSalesOrderDocumentsAuditLog = new SalesOrderDocumentsAuditLog();
        partialUpdatedSalesOrderDocumentsAuditLog.setId(salesOrderDocumentsAuditLog.getId());

        partialUpdatedSalesOrderDocumentsAuditLog
            .salesOrderDocId(UPDATED_SALES_ORDER_DOC_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderDocumentsAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderDocumentsAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderDocumentsAuditLog in the database
        List<SalesOrderDocumentsAuditLog> salesOrderDocumentsAuditLogList = salesOrderDocumentsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderDocumentsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderDocumentsAuditLog testSalesOrderDocumentsAuditLog = salesOrderDocumentsAuditLogList.get(
            salesOrderDocumentsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderDocumentsAuditLog.getSalesOrderDocId()).isEqualTo(UPDATED_SALES_ORDER_DOC_ID);
        assertThat(testSalesOrderDocumentsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testSalesOrderDocumentsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderDocumentsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testSalesOrderDocumentsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testSalesOrderDocumentsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void patchNonExistingSalesOrderDocumentsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderDocumentsAuditLogRepository.findAll().collectList().block().size();
        salesOrderDocumentsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderDocumentsAuditLog
        SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO = salesOrderDocumentsAuditLogMapper.toDto(
            salesOrderDocumentsAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, salesOrderDocumentsAuditLogDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderDocumentsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderDocumentsAuditLog in the database
        List<SalesOrderDocumentsAuditLog> salesOrderDocumentsAuditLogList = salesOrderDocumentsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderDocumentsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSalesOrderDocumentsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderDocumentsAuditLogRepository.findAll().collectList().block().size();
        salesOrderDocumentsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderDocumentsAuditLog
        SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO = salesOrderDocumentsAuditLogMapper.toDto(
            salesOrderDocumentsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderDocumentsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderDocumentsAuditLog in the database
        List<SalesOrderDocumentsAuditLog> salesOrderDocumentsAuditLogList = salesOrderDocumentsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderDocumentsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSalesOrderDocumentsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderDocumentsAuditLogRepository.findAll().collectList().block().size();
        salesOrderDocumentsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderDocumentsAuditLog
        SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO = salesOrderDocumentsAuditLogMapper.toDto(
            salesOrderDocumentsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderDocumentsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderDocumentsAuditLog in the database
        List<SalesOrderDocumentsAuditLog> salesOrderDocumentsAuditLogList = salesOrderDocumentsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderDocumentsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSalesOrderDocumentsAuditLog() {
        // Initialize the database
        salesOrderDocumentsAuditLogRepository.save(salesOrderDocumentsAuditLog).block();

        int databaseSizeBeforeDelete = salesOrderDocumentsAuditLogRepository.findAll().collectList().block().size();

        // Delete the salesOrderDocumentsAuditLog
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, salesOrderDocumentsAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SalesOrderDocumentsAuditLog> salesOrderDocumentsAuditLogList = salesOrderDocumentsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderDocumentsAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
