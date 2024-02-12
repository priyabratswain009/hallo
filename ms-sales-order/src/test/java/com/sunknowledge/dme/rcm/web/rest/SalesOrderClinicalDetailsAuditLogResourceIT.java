package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.SalesOrderClinicalDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClinicalDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderClinicalDetailsAuditLogMapper;
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
 * Integration tests for the {@link SalesOrderClinicalDetailsAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SalesOrderClinicalDetailsAuditLogResourceIT {

    private static final Long DEFAULT_SALS_ODR_CLINCAL_DETILS_ID = 1L;
    private static final Long UPDATED_SALS_ODR_CLINCAL_DETILS_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/sales-order-clinical-details-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SalesOrderClinicalDetailsAuditLogRepository salesOrderClinicalDetailsAuditLogRepository;

    @Autowired
    private SalesOrderClinicalDetailsAuditLogMapper salesOrderClinicalDetailsAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SalesOrderClinicalDetailsAuditLog salesOrderClinicalDetailsAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderClinicalDetailsAuditLog createEntity(EntityManager em) {
        SalesOrderClinicalDetailsAuditLog salesOrderClinicalDetailsAuditLog = new SalesOrderClinicalDetailsAuditLog()
            .salsOdrClincalDetilsId(DEFAULT_SALS_ODR_CLINCAL_DETILS_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return salesOrderClinicalDetailsAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderClinicalDetailsAuditLog createUpdatedEntity(EntityManager em) {
        SalesOrderClinicalDetailsAuditLog salesOrderClinicalDetailsAuditLog = new SalesOrderClinicalDetailsAuditLog()
            .salsOdrClincalDetilsId(UPDATED_SALS_ODR_CLINCAL_DETILS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return salesOrderClinicalDetailsAuditLog;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SalesOrderClinicalDetailsAuditLog.class).block();
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
        salesOrderClinicalDetailsAuditLog = createEntity(em);
    }

    @Test
    void createSalesOrderClinicalDetailsAuditLog() throws Exception {
        int databaseSizeBeforeCreate = salesOrderClinicalDetailsAuditLogRepository.findAll().collectList().block().size();
        // Create the SalesOrderClinicalDetailsAuditLog
        SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO = salesOrderClinicalDetailsAuditLogMapper.toDto(
            salesOrderClinicalDetailsAuditLog
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderClinicalDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SalesOrderClinicalDetailsAuditLog in the database
        List<SalesOrderClinicalDetailsAuditLog> salesOrderClinicalDetailsAuditLogList = salesOrderClinicalDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderClinicalDetailsAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        SalesOrderClinicalDetailsAuditLog testSalesOrderClinicalDetailsAuditLog = salesOrderClinicalDetailsAuditLogList.get(
            salesOrderClinicalDetailsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderClinicalDetailsAuditLog.getSalsOdrClincalDetilsId()).isEqualTo(DEFAULT_SALS_ODR_CLINCAL_DETILS_ID);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void createSalesOrderClinicalDetailsAuditLogWithExistingId() throws Exception {
        // Create the SalesOrderClinicalDetailsAuditLog with an existing ID
        salesOrderClinicalDetailsAuditLog.setId(1L);
        SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO = salesOrderClinicalDetailsAuditLogMapper.toDto(
            salesOrderClinicalDetailsAuditLog
        );

        int databaseSizeBeforeCreate = salesOrderClinicalDetailsAuditLogRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderClinicalDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderClinicalDetailsAuditLog in the database
        List<SalesOrderClinicalDetailsAuditLog> salesOrderClinicalDetailsAuditLogList = salesOrderClinicalDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderClinicalDetailsAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSalesOrderClinicalDetailsAuditLogs() {
        // Initialize the database
        salesOrderClinicalDetailsAuditLogRepository.save(salesOrderClinicalDetailsAuditLog).block();

        // Get all the salesOrderClinicalDetailsAuditLogList
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
            .value(hasItem(salesOrderClinicalDetailsAuditLog.getId().intValue()))
            .jsonPath("$.[*].salsOdrClincalDetilsId")
            .value(hasItem(DEFAULT_SALS_ODR_CLINCAL_DETILS_ID.intValue()))
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
    void getSalesOrderClinicalDetailsAuditLog() {
        // Initialize the database
        salesOrderClinicalDetailsAuditLogRepository.save(salesOrderClinicalDetailsAuditLog).block();

        // Get the salesOrderClinicalDetailsAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, salesOrderClinicalDetailsAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(salesOrderClinicalDetailsAuditLog.getId().intValue()))
            .jsonPath("$.salsOdrClincalDetilsId")
            .value(is(DEFAULT_SALS_ODR_CLINCAL_DETILS_ID.intValue()))
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
    void getNonExistingSalesOrderClinicalDetailsAuditLog() {
        // Get the salesOrderClinicalDetailsAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingSalesOrderClinicalDetailsAuditLog() throws Exception {
        // Initialize the database
        salesOrderClinicalDetailsAuditLogRepository.save(salesOrderClinicalDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = salesOrderClinicalDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the salesOrderClinicalDetailsAuditLog
        SalesOrderClinicalDetailsAuditLog updatedSalesOrderClinicalDetailsAuditLog = salesOrderClinicalDetailsAuditLogRepository
            .findById(salesOrderClinicalDetailsAuditLog.getId())
            .block();
        updatedSalesOrderClinicalDetailsAuditLog
            .salsOdrClincalDetilsId(UPDATED_SALS_ODR_CLINCAL_DETILS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO = salesOrderClinicalDetailsAuditLogMapper.toDto(
            updatedSalesOrderClinicalDetailsAuditLog
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderClinicalDetailsAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderClinicalDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderClinicalDetailsAuditLog in the database
        List<SalesOrderClinicalDetailsAuditLog> salesOrderClinicalDetailsAuditLogList = salesOrderClinicalDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderClinicalDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderClinicalDetailsAuditLog testSalesOrderClinicalDetailsAuditLog = salesOrderClinicalDetailsAuditLogList.get(
            salesOrderClinicalDetailsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderClinicalDetailsAuditLog.getSalsOdrClincalDetilsId()).isEqualTo(UPDATED_SALS_ODR_CLINCAL_DETILS_ID);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void putNonExistingSalesOrderClinicalDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClinicalDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderClinicalDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderClinicalDetailsAuditLog
        SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO = salesOrderClinicalDetailsAuditLogMapper.toDto(
            salesOrderClinicalDetailsAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderClinicalDetailsAuditLogDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderClinicalDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderClinicalDetailsAuditLog in the database
        List<SalesOrderClinicalDetailsAuditLog> salesOrderClinicalDetailsAuditLogList = salesOrderClinicalDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderClinicalDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSalesOrderClinicalDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClinicalDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderClinicalDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderClinicalDetailsAuditLog
        SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO = salesOrderClinicalDetailsAuditLogMapper.toDto(
            salesOrderClinicalDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderClinicalDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderClinicalDetailsAuditLog in the database
        List<SalesOrderClinicalDetailsAuditLog> salesOrderClinicalDetailsAuditLogList = salesOrderClinicalDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderClinicalDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSalesOrderClinicalDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClinicalDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderClinicalDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderClinicalDetailsAuditLog
        SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO = salesOrderClinicalDetailsAuditLogMapper.toDto(
            salesOrderClinicalDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderClinicalDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderClinicalDetailsAuditLog in the database
        List<SalesOrderClinicalDetailsAuditLog> salesOrderClinicalDetailsAuditLogList = salesOrderClinicalDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderClinicalDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSalesOrderClinicalDetailsAuditLogWithPatch() throws Exception {
        // Initialize the database
        salesOrderClinicalDetailsAuditLogRepository.save(salesOrderClinicalDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = salesOrderClinicalDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the salesOrderClinicalDetailsAuditLog using partial update
        SalesOrderClinicalDetailsAuditLog partialUpdatedSalesOrderClinicalDetailsAuditLog = new SalesOrderClinicalDetailsAuditLog();
        partialUpdatedSalesOrderClinicalDetailsAuditLog.setId(salesOrderClinicalDetailsAuditLog.getId());

        partialUpdatedSalesOrderClinicalDetailsAuditLog
            .salsOdrClincalDetilsId(UPDATED_SALS_ODR_CLINCAL_DETILS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderClinicalDetailsAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderClinicalDetailsAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderClinicalDetailsAuditLog in the database
        List<SalesOrderClinicalDetailsAuditLog> salesOrderClinicalDetailsAuditLogList = salesOrderClinicalDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderClinicalDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderClinicalDetailsAuditLog testSalesOrderClinicalDetailsAuditLog = salesOrderClinicalDetailsAuditLogList.get(
            salesOrderClinicalDetailsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderClinicalDetailsAuditLog.getSalsOdrClincalDetilsId()).isEqualTo(UPDATED_SALS_ODR_CLINCAL_DETILS_ID);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    void fullUpdateSalesOrderClinicalDetailsAuditLogWithPatch() throws Exception {
        // Initialize the database
        salesOrderClinicalDetailsAuditLogRepository.save(salesOrderClinicalDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = salesOrderClinicalDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the salesOrderClinicalDetailsAuditLog using partial update
        SalesOrderClinicalDetailsAuditLog partialUpdatedSalesOrderClinicalDetailsAuditLog = new SalesOrderClinicalDetailsAuditLog();
        partialUpdatedSalesOrderClinicalDetailsAuditLog.setId(salesOrderClinicalDetailsAuditLog.getId());

        partialUpdatedSalesOrderClinicalDetailsAuditLog
            .salsOdrClincalDetilsId(UPDATED_SALS_ODR_CLINCAL_DETILS_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderClinicalDetailsAuditLog.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderClinicalDetailsAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderClinicalDetailsAuditLog in the database
        List<SalesOrderClinicalDetailsAuditLog> salesOrderClinicalDetailsAuditLogList = salesOrderClinicalDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderClinicalDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderClinicalDetailsAuditLog testSalesOrderClinicalDetailsAuditLog = salesOrderClinicalDetailsAuditLogList.get(
            salesOrderClinicalDetailsAuditLogList.size() - 1
        );
        assertThat(testSalesOrderClinicalDetailsAuditLog.getSalsOdrClincalDetilsId()).isEqualTo(UPDATED_SALS_ODR_CLINCAL_DETILS_ID);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testSalesOrderClinicalDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    void patchNonExistingSalesOrderClinicalDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClinicalDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderClinicalDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderClinicalDetailsAuditLog
        SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO = salesOrderClinicalDetailsAuditLogMapper.toDto(
            salesOrderClinicalDetailsAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, salesOrderClinicalDetailsAuditLogDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderClinicalDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderClinicalDetailsAuditLog in the database
        List<SalesOrderClinicalDetailsAuditLog> salesOrderClinicalDetailsAuditLogList = salesOrderClinicalDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderClinicalDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSalesOrderClinicalDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClinicalDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderClinicalDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderClinicalDetailsAuditLog
        SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO = salesOrderClinicalDetailsAuditLogMapper.toDto(
            salesOrderClinicalDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderClinicalDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderClinicalDetailsAuditLog in the database
        List<SalesOrderClinicalDetailsAuditLog> salesOrderClinicalDetailsAuditLogList = salesOrderClinicalDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderClinicalDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSalesOrderClinicalDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClinicalDetailsAuditLogRepository.findAll().collectList().block().size();
        salesOrderClinicalDetailsAuditLog.setId(count.incrementAndGet());

        // Create the SalesOrderClinicalDetailsAuditLog
        SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO = salesOrderClinicalDetailsAuditLogMapper.toDto(
            salesOrderClinicalDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderClinicalDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderClinicalDetailsAuditLog in the database
        List<SalesOrderClinicalDetailsAuditLog> salesOrderClinicalDetailsAuditLogList = salesOrderClinicalDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderClinicalDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSalesOrderClinicalDetailsAuditLog() {
        // Initialize the database
        salesOrderClinicalDetailsAuditLogRepository.save(salesOrderClinicalDetailsAuditLog).block();

        int databaseSizeBeforeDelete = salesOrderClinicalDetailsAuditLogRepository.findAll().collectList().block().size();

        // Delete the salesOrderClinicalDetailsAuditLog
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, salesOrderClinicalDetailsAuditLog.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SalesOrderClinicalDetailsAuditLog> salesOrderClinicalDetailsAuditLogList = salesOrderClinicalDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(salesOrderClinicalDetailsAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
