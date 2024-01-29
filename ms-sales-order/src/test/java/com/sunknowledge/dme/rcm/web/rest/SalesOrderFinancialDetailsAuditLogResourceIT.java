package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.SalesOrderFinancialDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderFinancialDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderFinancialDetailsAuditLogMapper;
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
 * Integration tests for the {@link SalesOrderFinancialDetailsAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SalesOrderFinancialDetailsAuditLogResourceIT {

    private static final Long DEFAULT_SALS_ORDR_FINCIAL_ID = 1L;
    private static final Long UPDATED_SALS_ORDR_FINCIAL_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/sales-order-financial-details-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SalesOrderFinancialDetailsAuditLogRepository salesOrderFinancialDetailsAuditLogRepository;

    @Autowired
    private SalesOrderFinancialDetailsAuditLogMapper salesOrderFinancialDetailsAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SalesOrderFinancialDetailsAuditLog salesOrderFinancialDetailsAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderFinancialDetailsAuditLog createEntity(EntityManager em) {
        SalesOrderFinancialDetailsAuditLog salesOrderFinancialDetailsAuditLog = new SalesOrderFinancialDetailsAuditLog()
            .salsOrdrFincialId(DEFAULT_SALS_ORDR_FINCIAL_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return salesOrderFinancialDetailsAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderFinancialDetailsAuditLog createUpdatedEntity(EntityManager em) {
        SalesOrderFinancialDetailsAuditLog salesOrderFinancialDetailsAuditLog = new SalesOrderFinancialDetailsAuditLog()
            .salsOrdrFincialId(UPDATED_SALS_ORDR_FINCIAL_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return salesOrderFinancialDetailsAuditLog;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SalesOrderFinancialDetailsAuditLog.class).block();
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
        salesOrderFinancialDetailsAuditLog = createEntity(em);
    }

    @Test
    void createSalesOrderFinancialDetailsAuditLog() throws Exception {
        int databaseSizeBeforeCreate = salesOrderFinancialDetailsAuditLogRepository.findAll().collectList().block().size();
        // Create the SalesOrderFinancialDetailsAuditLog
        SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO = salesOrderFinancialDetailsAuditLogMapper.toDto(
            salesOrderFinancialDetailsAuditLog
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderFinancialDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SalesOrderFinancialDetailsAuditLog in the database
        List<SalesOrderFinancialDetailsAuditLog> salesOrderFinancialDetailsAuditLogList = salesOrderFinancialDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderFinancialDetailsAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        SalesOrderFinancialDetailsAuditLog testSalesOrderFinancialDetailsAuditLog = salesOrderFinancialDetailsAuditLogList.get(
            salesOrderFinancialDetailsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderFinancialDetailsAuditLog.getSalsOrdrFincialId()).isEqualTo(DEFAULT_SALS_ORDR_FINCIAL_ID);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void createSalesOrderFinancialDetailsAuditLogWithExistingId() throws Exception {
        // Create the SalesOrderFinancialDetailsAuditLog with an existing ID
        salesOrderFinancialDetailsAuditLog.setId(1L);
        SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO = salesOrderFinancialDetailsAuditLogMapper.toDto(
            salesOrderFinancialDetailsAuditLog
        );

        int databaseSizeBeforeCreate = salesOrderFinancialDetailsAuditLogRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderFinancialDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderFinancialDetailsAuditLog in the database
        List<SalesOrderFinancialDetailsAuditLog> salesOrderFinancialDetailsAuditLogList = salesOrderFinancialDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderFinancialDetailsAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSalesOrderFinancialDetailsAuditLogs() {
        // Initialize the database
        salesOrderFinancialDetailsAuditLogRepository.save(salesOrderFinancialDetailsAuditLog).block();

        // Get all the salesOrderFinancialDetailsAuditLogList
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
            .value(hasItem(salesOrderFinancialDetailsAuditLog.getId().intValue()))
            .jsonPath("$.[*].salsOrdrFincialId")
            .value(hasItem(DEFAULT_SALS_ORDR_FINCIAL_ID.intValue()))
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
    void getSalesOrderFinancialDetailsAuditLog() {
        // Initialize the database
        salesOrderFinancialDetailsAuditLogRepository.save(salesOrderFinancialDetailsAuditLog).block();

        // Get the salesOrderFinancialDetailsAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, salesOrderFinancialDetailsAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(salesOrderFinancialDetailsAuditLog.getId().intValue()))
            .jsonPath("$.salsOrdrFincialId")
            .value(is(DEFAULT_SALS_ORDR_FINCIAL_ID.intValue()))
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
    void getNonExistingSalesOrderFinancialDetailsAuditLog() {
        // Get the salesOrderFinancialDetailsAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingSalesOrderFinancialDetailsAuditLog() throws Exception {
        // Initialize the database
        salesOrderFinancialDetailsAuditLogRepository.save(salesOrderFinancialDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = salesOrderFinancialDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the salesOrderFinancialDetailsAuditLog
        SalesOrderFinancialDetailsAuditLog updatedSalesOrderFinancialDetailsAuditLog = salesOrderFinancialDetailsAuditLogRepository
            .findById(salesOrderFinancialDetailsAuditLog.getId())
            .block();
        updatedSalesOrderFinancialDetailsAuditLog
            .salsOrdrFincialId(UPDATED_SALS_ORDR_FINCIAL_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO = salesOrderFinancialDetailsAuditLogMapper.toDto(
            updatedSalesOrderFinancialDetailsAuditLog
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderFinancialDetailsAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderFinancialDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderFinancialDetailsAuditLog in the database
        List<SalesOrderFinancialDetailsAuditLog> salesOrderFinancialDetailsAuditLogList = salesOrderFinancialDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderFinancialDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderFinancialDetailsAuditLog testSalesOrderFinancialDetailsAuditLog = salesOrderFinancialDetailsAuditLogList.get(
            salesOrderFinancialDetailsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderFinancialDetailsAuditLog.getSalsOrdrFincialId()).isEqualTo(UPDATED_SALS_ORDR_FINCIAL_ID);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void putNonExistingSalesOrderFinancialDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderFinancialDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderFinancialDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderFinancialDetailsAuditLog
        SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO = salesOrderFinancialDetailsAuditLogMapper.toDto(
            salesOrderFinancialDetailsAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderFinancialDetailsAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderFinancialDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderFinancialDetailsAuditLog in the database
        List<SalesOrderFinancialDetailsAuditLog> salesOrderFinancialDetailsAuditLogList = salesOrderFinancialDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderFinancialDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSalesOrderFinancialDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderFinancialDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderFinancialDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderFinancialDetailsAuditLog
        SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO = salesOrderFinancialDetailsAuditLogMapper.toDto(
            salesOrderFinancialDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderFinancialDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderFinancialDetailsAuditLog in the database
        List<SalesOrderFinancialDetailsAuditLog> salesOrderFinancialDetailsAuditLogList = salesOrderFinancialDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderFinancialDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSalesOrderFinancialDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderFinancialDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderFinancialDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderFinancialDetailsAuditLog
        SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO = salesOrderFinancialDetailsAuditLogMapper.toDto(
            salesOrderFinancialDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderFinancialDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderFinancialDetailsAuditLog in the database
        List<SalesOrderFinancialDetailsAuditLog> salesOrderFinancialDetailsAuditLogList = salesOrderFinancialDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderFinancialDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSalesOrderFinancialDetailsAuditLogWithPatch() throws Exception {
        // Initialize the database
        salesOrderFinancialDetailsAuditLogRepository.save(salesOrderFinancialDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = salesOrderFinancialDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the salesOrderFinancialDetailsAuditLog using partial update
        SalesOrderFinancialDetailsAuditLog partialUpdatedSalesOrderFinancialDetailsAuditLog = new SalesOrderFinancialDetailsAuditLog();
        partialUpdatedSalesOrderFinancialDetailsAuditLog.setId(salesOrderFinancialDetailsAuditLog.getId());

        partialUpdatedSalesOrderFinancialDetailsAuditLog.dmlTimestamp(UPDATED_DML_TIMESTAMP).dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderFinancialDetailsAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderFinancialDetailsAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderFinancialDetailsAuditLog in the database
        List<SalesOrderFinancialDetailsAuditLog> salesOrderFinancialDetailsAuditLogList = salesOrderFinancialDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderFinancialDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderFinancialDetailsAuditLog testSalesOrderFinancialDetailsAuditLog = salesOrderFinancialDetailsAuditLogList.get(
            salesOrderFinancialDetailsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderFinancialDetailsAuditLog.getSalsOrdrFincialId()).isEqualTo(DEFAULT_SALS_ORDR_FINCIAL_ID);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void fullUpdateSalesOrderFinancialDetailsAuditLogWithPatch() throws Exception {
        // Initialize the database
        salesOrderFinancialDetailsAuditLogRepository.save(salesOrderFinancialDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = salesOrderFinancialDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the salesOrderFinancialDetailsAuditLog using partial update
        SalesOrderFinancialDetailsAuditLog partialUpdatedSalesOrderFinancialDetailsAuditLog = new SalesOrderFinancialDetailsAuditLog();
        partialUpdatedSalesOrderFinancialDetailsAuditLog.setId(salesOrderFinancialDetailsAuditLog.getId());

        partialUpdatedSalesOrderFinancialDetailsAuditLog
            .salsOrdrFincialId(UPDATED_SALS_ORDR_FINCIAL_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderFinancialDetailsAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderFinancialDetailsAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderFinancialDetailsAuditLog in the database
        List<SalesOrderFinancialDetailsAuditLog> salesOrderFinancialDetailsAuditLogList = salesOrderFinancialDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderFinancialDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderFinancialDetailsAuditLog testSalesOrderFinancialDetailsAuditLog = salesOrderFinancialDetailsAuditLogList.get(
            salesOrderFinancialDetailsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderFinancialDetailsAuditLog.getSalsOrdrFincialId()).isEqualTo(UPDATED_SALS_ORDR_FINCIAL_ID);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testSalesOrderFinancialDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void patchNonExistingSalesOrderFinancialDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderFinancialDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderFinancialDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderFinancialDetailsAuditLog
        SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO = salesOrderFinancialDetailsAuditLogMapper.toDto(
            salesOrderFinancialDetailsAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, salesOrderFinancialDetailsAuditLogDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderFinancialDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderFinancialDetailsAuditLog in the database
        List<SalesOrderFinancialDetailsAuditLog> salesOrderFinancialDetailsAuditLogList = salesOrderFinancialDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderFinancialDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSalesOrderFinancialDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderFinancialDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderFinancialDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderFinancialDetailsAuditLog
        SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO = salesOrderFinancialDetailsAuditLogMapper.toDto(
            salesOrderFinancialDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderFinancialDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderFinancialDetailsAuditLog in the database
        List<SalesOrderFinancialDetailsAuditLog> salesOrderFinancialDetailsAuditLogList = salesOrderFinancialDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderFinancialDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSalesOrderFinancialDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderFinancialDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderFinancialDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderFinancialDetailsAuditLog
        SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO = salesOrderFinancialDetailsAuditLogMapper.toDto(
            salesOrderFinancialDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderFinancialDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderFinancialDetailsAuditLog in the database
        List<SalesOrderFinancialDetailsAuditLog> salesOrderFinancialDetailsAuditLogList = salesOrderFinancialDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderFinancialDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSalesOrderFinancialDetailsAuditLog() {
        // Initialize the database
        salesOrderFinancialDetailsAuditLogRepository.save(salesOrderFinancialDetailsAuditLog).block();

        int databaseSizeBeforeDelete = salesOrderFinancialDetailsAuditLogRepository.findAll().collectList().block().size();

        // Delete the salesOrderFinancialDetailsAuditLog
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, salesOrderFinancialDetailsAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SalesOrderFinancialDetailsAuditLog> salesOrderFinancialDetailsAuditLogList = salesOrderFinancialDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderFinancialDetailsAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
