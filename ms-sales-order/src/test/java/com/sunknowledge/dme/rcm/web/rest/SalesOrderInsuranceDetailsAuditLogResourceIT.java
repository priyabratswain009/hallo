package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.SalesOrderInsuranceDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderInsuranceDetailsAuditLogMapper;
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
 * Integration tests for the {@link SalesOrderInsuranceDetailsAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SalesOrderInsuranceDetailsAuditLogResourceIT {

    private static final Long DEFAULT_SALS_ORD_INSRANCE_DETAILS_ID = 1L;
    private static final Long UPDATED_SALS_ORD_INSRANCE_DETAILS_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/sales-order-insurance-details-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SalesOrderInsuranceDetailsAuditLogRepository salesOrderInsuranceDetailsAuditLogRepository;

    @Autowired
    private SalesOrderInsuranceDetailsAuditLogMapper salesOrderInsuranceDetailsAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SalesOrderInsuranceDetailsAuditLog salesOrderInsuranceDetailsAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderInsuranceDetailsAuditLog createEntity(EntityManager em) {
        SalesOrderInsuranceDetailsAuditLog salesOrderInsuranceDetailsAuditLog = new SalesOrderInsuranceDetailsAuditLog()
            .salsOrdInsranceDetailsId(DEFAULT_SALS_ORD_INSRANCE_DETAILS_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return salesOrderInsuranceDetailsAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderInsuranceDetailsAuditLog createUpdatedEntity(EntityManager em) {
        SalesOrderInsuranceDetailsAuditLog salesOrderInsuranceDetailsAuditLog = new SalesOrderInsuranceDetailsAuditLog()
            .salsOrdInsranceDetailsId(UPDATED_SALS_ORD_INSRANCE_DETAILS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return salesOrderInsuranceDetailsAuditLog;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SalesOrderInsuranceDetailsAuditLog.class).block();
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
        salesOrderInsuranceDetailsAuditLog = createEntity(em);
    }

    @Test
    void createSalesOrderInsuranceDetailsAuditLog() throws Exception {
        int databaseSizeBeforeCreate = salesOrderInsuranceDetailsAuditLogRepository.findAll().collectList().block().size();
        // Create the SalesOrderInsuranceDetailsAuditLog
        SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO = salesOrderInsuranceDetailsAuditLogMapper.toDto(
            salesOrderInsuranceDetailsAuditLog
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderInsuranceDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SalesOrderInsuranceDetailsAuditLog in the database
        List<SalesOrderInsuranceDetailsAuditLog> salesOrderInsuranceDetailsAuditLogList = salesOrderInsuranceDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        SalesOrderInsuranceDetailsAuditLog testSalesOrderInsuranceDetailsAuditLog = salesOrderInsuranceDetailsAuditLogList.get(
            salesOrderInsuranceDetailsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getSalsOrdInsranceDetailsId()).isEqualTo(DEFAULT_SALS_ORD_INSRANCE_DETAILS_ID);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void createSalesOrderInsuranceDetailsAuditLogWithExistingId() throws Exception {
        // Create the SalesOrderInsuranceDetailsAuditLog with an existing ID
        salesOrderInsuranceDetailsAuditLog.setId(1L);
        SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO = salesOrderInsuranceDetailsAuditLogMapper.toDto(
            salesOrderInsuranceDetailsAuditLog
        );

        int databaseSizeBeforeCreate = salesOrderInsuranceDetailsAuditLogRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderInsuranceDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderInsuranceDetailsAuditLog in the database
        List<SalesOrderInsuranceDetailsAuditLog> salesOrderInsuranceDetailsAuditLogList = salesOrderInsuranceDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSalesOrderInsuranceDetailsAuditLogs() {
        // Initialize the database
        salesOrderInsuranceDetailsAuditLogRepository.save(salesOrderInsuranceDetailsAuditLog).block();

        // Get all the salesOrderInsuranceDetailsAuditLogList
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
            .value(hasItem(salesOrderInsuranceDetailsAuditLog.getId().intValue()))
            .jsonPath("$.[*].salsOrdInsranceDetailsId")
            .value(hasItem(DEFAULT_SALS_ORD_INSRANCE_DETAILS_ID.intValue()))
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
    void getSalesOrderInsuranceDetailsAuditLog() {
        // Initialize the database
        salesOrderInsuranceDetailsAuditLogRepository.save(salesOrderInsuranceDetailsAuditLog).block();

        // Get the salesOrderInsuranceDetailsAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, salesOrderInsuranceDetailsAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(salesOrderInsuranceDetailsAuditLog.getId().intValue()))
            .jsonPath("$.salsOrdInsranceDetailsId")
            .value(is(DEFAULT_SALS_ORD_INSRANCE_DETAILS_ID.intValue()))
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
    void getNonExistingSalesOrderInsuranceDetailsAuditLog() {
        // Get the salesOrderInsuranceDetailsAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingSalesOrderInsuranceDetailsAuditLog() throws Exception {
        // Initialize the database
        salesOrderInsuranceDetailsAuditLogRepository.save(salesOrderInsuranceDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = salesOrderInsuranceDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the salesOrderInsuranceDetailsAuditLog
        SalesOrderInsuranceDetailsAuditLog updatedSalesOrderInsuranceDetailsAuditLog = salesOrderInsuranceDetailsAuditLogRepository
            .findById(salesOrderInsuranceDetailsAuditLog.getId())
            .block();
        updatedSalesOrderInsuranceDetailsAuditLog
            .salsOrdInsranceDetailsId(UPDATED_SALS_ORD_INSRANCE_DETAILS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO = salesOrderInsuranceDetailsAuditLogMapper.toDto(
            updatedSalesOrderInsuranceDetailsAuditLog
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderInsuranceDetailsAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderInsuranceDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderInsuranceDetailsAuditLog in the database
        List<SalesOrderInsuranceDetailsAuditLog> salesOrderInsuranceDetailsAuditLogList = salesOrderInsuranceDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderInsuranceDetailsAuditLog testSalesOrderInsuranceDetailsAuditLog = salesOrderInsuranceDetailsAuditLogList.get(
            salesOrderInsuranceDetailsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getSalsOrdInsranceDetailsId()).isEqualTo(UPDATED_SALS_ORD_INSRANCE_DETAILS_ID);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void putNonExistingSalesOrderInsuranceDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderInsuranceDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderInsuranceDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderInsuranceDetailsAuditLog
        SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO = salesOrderInsuranceDetailsAuditLogMapper.toDto(
            salesOrderInsuranceDetailsAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderInsuranceDetailsAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderInsuranceDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderInsuranceDetailsAuditLog in the database
        List<SalesOrderInsuranceDetailsAuditLog> salesOrderInsuranceDetailsAuditLogList = salesOrderInsuranceDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSalesOrderInsuranceDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderInsuranceDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderInsuranceDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderInsuranceDetailsAuditLog
        SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO = salesOrderInsuranceDetailsAuditLogMapper.toDto(
            salesOrderInsuranceDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderInsuranceDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderInsuranceDetailsAuditLog in the database
        List<SalesOrderInsuranceDetailsAuditLog> salesOrderInsuranceDetailsAuditLogList = salesOrderInsuranceDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSalesOrderInsuranceDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderInsuranceDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderInsuranceDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderInsuranceDetailsAuditLog
        SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO = salesOrderInsuranceDetailsAuditLogMapper.toDto(
            salesOrderInsuranceDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderInsuranceDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderInsuranceDetailsAuditLog in the database
        List<SalesOrderInsuranceDetailsAuditLog> salesOrderInsuranceDetailsAuditLogList = salesOrderInsuranceDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSalesOrderInsuranceDetailsAuditLogWithPatch() throws Exception {
        // Initialize the database
        salesOrderInsuranceDetailsAuditLogRepository.save(salesOrderInsuranceDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = salesOrderInsuranceDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the salesOrderInsuranceDetailsAuditLog using partial update
        SalesOrderInsuranceDetailsAuditLog partialUpdatedSalesOrderInsuranceDetailsAuditLog = new SalesOrderInsuranceDetailsAuditLog();
        partialUpdatedSalesOrderInsuranceDetailsAuditLog.setId(salesOrderInsuranceDetailsAuditLog.getId());

        partialUpdatedSalesOrderInsuranceDetailsAuditLog
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderInsuranceDetailsAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderInsuranceDetailsAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderInsuranceDetailsAuditLog in the database
        List<SalesOrderInsuranceDetailsAuditLog> salesOrderInsuranceDetailsAuditLogList = salesOrderInsuranceDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderInsuranceDetailsAuditLog testSalesOrderInsuranceDetailsAuditLog = salesOrderInsuranceDetailsAuditLogList.get(
            salesOrderInsuranceDetailsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getSalsOrdInsranceDetailsId()).isEqualTo(DEFAULT_SALS_ORD_INSRANCE_DETAILS_ID);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void fullUpdateSalesOrderInsuranceDetailsAuditLogWithPatch() throws Exception {
        // Initialize the database
        salesOrderInsuranceDetailsAuditLogRepository.save(salesOrderInsuranceDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = salesOrderInsuranceDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the salesOrderInsuranceDetailsAuditLog using partial update
        SalesOrderInsuranceDetailsAuditLog partialUpdatedSalesOrderInsuranceDetailsAuditLog = new SalesOrderInsuranceDetailsAuditLog();
        partialUpdatedSalesOrderInsuranceDetailsAuditLog.setId(salesOrderInsuranceDetailsAuditLog.getId());

        partialUpdatedSalesOrderInsuranceDetailsAuditLog
            .salsOrdInsranceDetailsId(UPDATED_SALS_ORD_INSRANCE_DETAILS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderInsuranceDetailsAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderInsuranceDetailsAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderInsuranceDetailsAuditLog in the database
        List<SalesOrderInsuranceDetailsAuditLog> salesOrderInsuranceDetailsAuditLogList = salesOrderInsuranceDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderInsuranceDetailsAuditLog testSalesOrderInsuranceDetailsAuditLog = salesOrderInsuranceDetailsAuditLogList.get(
            salesOrderInsuranceDetailsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getSalsOrdInsranceDetailsId()).isEqualTo(UPDATED_SALS_ORD_INSRANCE_DETAILS_ID);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testSalesOrderInsuranceDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void patchNonExistingSalesOrderInsuranceDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderInsuranceDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderInsuranceDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderInsuranceDetailsAuditLog
        SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO = salesOrderInsuranceDetailsAuditLogMapper.toDto(
            salesOrderInsuranceDetailsAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, salesOrderInsuranceDetailsAuditLogDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderInsuranceDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderInsuranceDetailsAuditLog in the database
        List<SalesOrderInsuranceDetailsAuditLog> salesOrderInsuranceDetailsAuditLogList = salesOrderInsuranceDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSalesOrderInsuranceDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderInsuranceDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderInsuranceDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderInsuranceDetailsAuditLog
        SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO = salesOrderInsuranceDetailsAuditLogMapper.toDto(
            salesOrderInsuranceDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderInsuranceDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderInsuranceDetailsAuditLog in the database
        List<SalesOrderInsuranceDetailsAuditLog> salesOrderInsuranceDetailsAuditLogList = salesOrderInsuranceDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSalesOrderInsuranceDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderInsuranceDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderInsuranceDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderInsuranceDetailsAuditLog
        SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO = salesOrderInsuranceDetailsAuditLogMapper.toDto(
            salesOrderInsuranceDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderInsuranceDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderInsuranceDetailsAuditLog in the database
        List<SalesOrderInsuranceDetailsAuditLog> salesOrderInsuranceDetailsAuditLogList = salesOrderInsuranceDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSalesOrderInsuranceDetailsAuditLog() {
        // Initialize the database
        salesOrderInsuranceDetailsAuditLogRepository.save(salesOrderInsuranceDetailsAuditLog).block();

        int databaseSizeBeforeDelete = salesOrderInsuranceDetailsAuditLogRepository.findAll().collectList().block().size();

        // Delete the salesOrderInsuranceDetailsAuditLog
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, salesOrderInsuranceDetailsAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SalesOrderInsuranceDetailsAuditLog> salesOrderInsuranceDetailsAuditLogList = salesOrderInsuranceDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderInsuranceDetailsAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
