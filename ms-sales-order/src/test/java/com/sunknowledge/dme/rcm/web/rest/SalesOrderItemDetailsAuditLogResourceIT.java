package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.SalesOrderItemDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderItemDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderItemDetailsAuditLogMapper;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link SalesOrderItemDetailsAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SalesOrderItemDetailsAuditLogResourceIT {

    private static final Long DEFAULT_SALS_ORDR_ITEM_DETAIL_ID = 1L;
    private static final Long UPDATED_SALS_ORDR_ITEM_DETAIL_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/sales-order-item-details-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SalesOrderItemDetailsAuditLogRepository salesOrderItemDetailsAuditLogRepository;

    @Autowired
    private SalesOrderItemDetailsAuditLogMapper salesOrderItemDetailsAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SalesOrderItemDetailsAuditLog salesOrderItemDetailsAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderItemDetailsAuditLog createEntity(EntityManager em) {
        SalesOrderItemDetailsAuditLog salesOrderItemDetailsAuditLog = new SalesOrderItemDetailsAuditLog()
            .salsOrdrItemDetailId(DEFAULT_SALS_ORDR_ITEM_DETAIL_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return salesOrderItemDetailsAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderItemDetailsAuditLog createUpdatedEntity(EntityManager em) {
        SalesOrderItemDetailsAuditLog salesOrderItemDetailsAuditLog = new SalesOrderItemDetailsAuditLog()
            .salsOrdrItemDetailId(UPDATED_SALS_ORDR_ITEM_DETAIL_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return salesOrderItemDetailsAuditLog;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SalesOrderItemDetailsAuditLog.class).block();
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
        salesOrderItemDetailsAuditLog = createEntity(em);
    }

    @Test
    void createSalesOrderItemDetailsAuditLog() throws Exception {
        int databaseSizeBeforeCreate = salesOrderItemDetailsAuditLogRepository.findAll().collectList().block().size();
        // Create the SalesOrderItemDetailsAuditLog
        SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO = salesOrderItemDetailsAuditLogMapper.toDto(
            salesOrderItemDetailsAuditLog
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderItemDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SalesOrderItemDetailsAuditLog in the database
        List<SalesOrderItemDetailsAuditLog> salesOrderItemDetailsAuditLogList = salesOrderItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderItemDetailsAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        SalesOrderItemDetailsAuditLog testSalesOrderItemDetailsAuditLog = salesOrderItemDetailsAuditLogList.get(
            salesOrderItemDetailsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderItemDetailsAuditLog.getSalsOrdrItemDetailId()).isEqualTo(DEFAULT_SALS_ORDR_ITEM_DETAIL_ID);
        assertThat(testSalesOrderItemDetailsAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testSalesOrderItemDetailsAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testSalesOrderItemDetailsAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testSalesOrderItemDetailsAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testSalesOrderItemDetailsAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void createSalesOrderItemDetailsAuditLogWithExistingId() throws Exception {
        // Create the SalesOrderItemDetailsAuditLog with an existing ID
        salesOrderItemDetailsAuditLog.setId(1L);
        SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO = salesOrderItemDetailsAuditLogMapper.toDto(
            salesOrderItemDetailsAuditLog
        );

        int databaseSizeBeforeCreate = salesOrderItemDetailsAuditLogRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderItemDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderItemDetailsAuditLog in the database
        List<SalesOrderItemDetailsAuditLog> salesOrderItemDetailsAuditLogList = salesOrderItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderItemDetailsAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSalesOrderItemDetailsAuditLogs() {
        // Initialize the database
        salesOrderItemDetailsAuditLogRepository.save(salesOrderItemDetailsAuditLog).block();

        // Get all the salesOrderItemDetailsAuditLogList
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
            .value(hasItem(salesOrderItemDetailsAuditLog.getId().intValue()))
            .jsonPath("$.[*].salsOrdrItemDetailId")
            .value(hasItem(DEFAULT_SALS_ORDR_ITEM_DETAIL_ID.intValue()))
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
    void getSalesOrderItemDetailsAuditLog() {
        // Initialize the database
        salesOrderItemDetailsAuditLogRepository.save(salesOrderItemDetailsAuditLog).block();

        // Get the salesOrderItemDetailsAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, salesOrderItemDetailsAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(salesOrderItemDetailsAuditLog.getId().intValue()))
            .jsonPath("$.salsOrdrItemDetailId")
            .value(is(DEFAULT_SALS_ORDR_ITEM_DETAIL_ID.intValue()))
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
    void getNonExistingSalesOrderItemDetailsAuditLog() {
        // Get the salesOrderItemDetailsAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewSalesOrderItemDetailsAuditLog() throws Exception {
        // Initialize the database
        salesOrderItemDetailsAuditLogRepository.save(salesOrderItemDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = salesOrderItemDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the salesOrderItemDetailsAuditLog
        SalesOrderItemDetailsAuditLog updatedSalesOrderItemDetailsAuditLog = salesOrderItemDetailsAuditLogRepository
            .findById(salesOrderItemDetailsAuditLog.getId())
            .block();
        updatedSalesOrderItemDetailsAuditLog
            .salsOrdrItemDetailId(UPDATED_SALS_ORDR_ITEM_DETAIL_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO = salesOrderItemDetailsAuditLogMapper.toDto(
            updatedSalesOrderItemDetailsAuditLog
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderItemDetailsAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderItemDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderItemDetailsAuditLog in the database
        List<SalesOrderItemDetailsAuditLog> salesOrderItemDetailsAuditLogList = salesOrderItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderItemDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderItemDetailsAuditLog testSalesOrderItemDetailsAuditLog = salesOrderItemDetailsAuditLogList.get(
            salesOrderItemDetailsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderItemDetailsAuditLog.getSalsOrdrItemDetailId()).isEqualTo(UPDATED_SALS_ORDR_ITEM_DETAIL_ID);
        assertThat(testSalesOrderItemDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testSalesOrderItemDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderItemDetailsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testSalesOrderItemDetailsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testSalesOrderItemDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void putNonExistingSalesOrderItemDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderItemDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderItemDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderItemDetailsAuditLog
        SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO = salesOrderItemDetailsAuditLogMapper.toDto(
            salesOrderItemDetailsAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderItemDetailsAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderItemDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderItemDetailsAuditLog in the database
        List<SalesOrderItemDetailsAuditLog> salesOrderItemDetailsAuditLogList = salesOrderItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderItemDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSalesOrderItemDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderItemDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderItemDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderItemDetailsAuditLog
        SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO = salesOrderItemDetailsAuditLogMapper.toDto(
            salesOrderItemDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderItemDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderItemDetailsAuditLog in the database
        List<SalesOrderItemDetailsAuditLog> salesOrderItemDetailsAuditLogList = salesOrderItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderItemDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSalesOrderItemDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderItemDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderItemDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderItemDetailsAuditLog
        SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO = salesOrderItemDetailsAuditLogMapper.toDto(
            salesOrderItemDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderItemDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderItemDetailsAuditLog in the database
        List<SalesOrderItemDetailsAuditLog> salesOrderItemDetailsAuditLogList = salesOrderItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderItemDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSalesOrderItemDetailsAuditLogWithPatch() throws Exception {
        // Initialize the database
        salesOrderItemDetailsAuditLogRepository.save(salesOrderItemDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = salesOrderItemDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the salesOrderItemDetailsAuditLog using partial update
        SalesOrderItemDetailsAuditLog partialUpdatedSalesOrderItemDetailsAuditLog = new SalesOrderItemDetailsAuditLog();
        partialUpdatedSalesOrderItemDetailsAuditLog.setId(salesOrderItemDetailsAuditLog.getId());

        partialUpdatedSalesOrderItemDetailsAuditLog
            .salsOrdrItemDetailId(UPDATED_SALS_ORDR_ITEM_DETAIL_ID)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderItemDetailsAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderItemDetailsAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderItemDetailsAuditLog in the database
        List<SalesOrderItemDetailsAuditLog> salesOrderItemDetailsAuditLogList = salesOrderItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderItemDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderItemDetailsAuditLog testSalesOrderItemDetailsAuditLog = salesOrderItemDetailsAuditLogList.get(
            salesOrderItemDetailsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderItemDetailsAuditLog.getSalsOrdrItemDetailId()).isEqualTo(UPDATED_SALS_ORDR_ITEM_DETAIL_ID);
        assertThat(testSalesOrderItemDetailsAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testSalesOrderItemDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderItemDetailsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testSalesOrderItemDetailsAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testSalesOrderItemDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void fullUpdateSalesOrderItemDetailsAuditLogWithPatch() throws Exception {
        // Initialize the database
        salesOrderItemDetailsAuditLogRepository.save(salesOrderItemDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = salesOrderItemDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the salesOrderItemDetailsAuditLog using partial update
        SalesOrderItemDetailsAuditLog partialUpdatedSalesOrderItemDetailsAuditLog = new SalesOrderItemDetailsAuditLog();
        partialUpdatedSalesOrderItemDetailsAuditLog.setId(salesOrderItemDetailsAuditLog.getId());

        partialUpdatedSalesOrderItemDetailsAuditLog
            .salsOrdrItemDetailId(UPDATED_SALS_ORDR_ITEM_DETAIL_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderItemDetailsAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderItemDetailsAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderItemDetailsAuditLog in the database
        List<SalesOrderItemDetailsAuditLog> salesOrderItemDetailsAuditLogList = salesOrderItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderItemDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderItemDetailsAuditLog testSalesOrderItemDetailsAuditLog = salesOrderItemDetailsAuditLogList.get(
            salesOrderItemDetailsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderItemDetailsAuditLog.getSalsOrdrItemDetailId()).isEqualTo(UPDATED_SALS_ORDR_ITEM_DETAIL_ID);
        assertThat(testSalesOrderItemDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testSalesOrderItemDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderItemDetailsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testSalesOrderItemDetailsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testSalesOrderItemDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void patchNonExistingSalesOrderItemDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderItemDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderItemDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderItemDetailsAuditLog
        SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO = salesOrderItemDetailsAuditLogMapper.toDto(
            salesOrderItemDetailsAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, salesOrderItemDetailsAuditLogDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderItemDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderItemDetailsAuditLog in the database
        List<SalesOrderItemDetailsAuditLog> salesOrderItemDetailsAuditLogList = salesOrderItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderItemDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSalesOrderItemDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderItemDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderItemDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderItemDetailsAuditLog
        SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO = salesOrderItemDetailsAuditLogMapper.toDto(
            salesOrderItemDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderItemDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderItemDetailsAuditLog in the database
        List<SalesOrderItemDetailsAuditLog> salesOrderItemDetailsAuditLogList = salesOrderItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderItemDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSalesOrderItemDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderItemDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderItemDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderItemDetailsAuditLog
        SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO = salesOrderItemDetailsAuditLogMapper.toDto(
            salesOrderItemDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderItemDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderItemDetailsAuditLog in the database
        List<SalesOrderItemDetailsAuditLog> salesOrderItemDetailsAuditLogList = salesOrderItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderItemDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSalesOrderItemDetailsAuditLog() {
        // Initialize the database
        salesOrderItemDetailsAuditLogRepository.save(salesOrderItemDetailsAuditLog).block();

        int databaseSizeBeforeDelete = salesOrderItemDetailsAuditLogRepository.findAll().collectList().block().size();

        // Delete the salesOrderItemDetailsAuditLog
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, salesOrderItemDetailsAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SalesOrderItemDetailsAuditLog> salesOrderItemDetailsAuditLogList = salesOrderItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderItemDetailsAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
