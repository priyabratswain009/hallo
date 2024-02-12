package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.InvoiceMasterDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.InvoiceMasterDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.InvoiceMasterDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.InvoiceMasterDetailsAuditLogMapper;
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
 * Integration tests for the {@link InvoiceMasterDetailsAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class InvoiceMasterDetailsAuditLogResourceIT {

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

    private static final Long DEFAULT_ID = 1L;
    private static final Long UPDATED_ID = 2L;

    private static final String ENTITY_API_URL = "/api/invoice-master-details-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{invceId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InvoiceMasterDetailsAuditLogRepository invoiceMasterDetailsAuditLogRepository;

    @Autowired
    private InvoiceMasterDetailsAuditLogMapper invoiceMasterDetailsAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private InvoiceMasterDetailsAuditLog invoiceMasterDetailsAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceMasterDetailsAuditLog createEntity(EntityManager em) {
        InvoiceMasterDetailsAuditLog invoiceMasterDetailsAuditLog = new InvoiceMasterDetailsAuditLog()
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY)
            .id(DEFAULT_ID);
        return invoiceMasterDetailsAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceMasterDetailsAuditLog createUpdatedEntity(EntityManager em) {
        InvoiceMasterDetailsAuditLog invoiceMasterDetailsAuditLog = new InvoiceMasterDetailsAuditLog()
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY)
            .id(UPDATED_ID);
        return invoiceMasterDetailsAuditLog;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(InvoiceMasterDetailsAuditLog.class).block();
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
        invoiceMasterDetailsAuditLog = createEntity(em);
    }

    @Test
    void createInvoiceMasterDetailsAuditLog() throws Exception {
        int databaseSizeBeforeCreate = invoiceMasterDetailsAuditLogRepository.findAll().collectList().block().size();
        // Create the InvoiceMasterDetailsAuditLog
        InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO = invoiceMasterDetailsAuditLogMapper.toDto(
            invoiceMasterDetailsAuditLog
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the InvoiceMasterDetailsAuditLog in the database
        List<InvoiceMasterDetailsAuditLog> invoiceMasterDetailsAuditLogList = invoiceMasterDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceMasterDetailsAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        InvoiceMasterDetailsAuditLog testInvoiceMasterDetailsAuditLog = invoiceMasterDetailsAuditLogList.get(
            invoiceMasterDetailsAuditLogList.size() - 1
        );
        assertThat(testInvoiceMasterDetailsAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testInvoiceMasterDetailsAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testInvoiceMasterDetailsAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testInvoiceMasterDetailsAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testInvoiceMasterDetailsAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
        assertThat(testInvoiceMasterDetailsAuditLog.getId()).isEqualTo(DEFAULT_ID);
    }

    @Test
    void createInvoiceMasterDetailsAuditLogWithExistingId() throws Exception {
        // Create the InvoiceMasterDetailsAuditLog with an existing ID
        invoiceMasterDetailsAuditLog.setInvceId(1L);
        InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO = invoiceMasterDetailsAuditLogMapper.toDto(
            invoiceMasterDetailsAuditLog
        );

        int databaseSizeBeforeCreate = invoiceMasterDetailsAuditLogRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceMasterDetailsAuditLog in the database
        List<InvoiceMasterDetailsAuditLog> invoiceMasterDetailsAuditLogList = invoiceMasterDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceMasterDetailsAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllInvoiceMasterDetailsAuditLogs() {
        // Initialize the database
        invoiceMasterDetailsAuditLogRepository.save(invoiceMasterDetailsAuditLog).block();

        // Get all the invoiceMasterDetailsAuditLogList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=invceId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].invceId")
            .value(hasItem(invoiceMasterDetailsAuditLog.getInvceId().intValue()))
            .jsonPath("$.[*].oldRowData")
            .value(hasItem(DEFAULT_OLD_ROW_DATA))
            .jsonPath("$.[*].newRowData")
            .value(hasItem(DEFAULT_NEW_ROW_DATA))
            .jsonPath("$.[*].dmlType")
            .value(hasItem(DEFAULT_DML_TYPE))
            .jsonPath("$.[*].dmlTimestamp")
            .value(hasItem(DEFAULT_DML_TIMESTAMP.toString()))
            .jsonPath("$.[*].dmlCreatedBy")
            .value(hasItem(DEFAULT_DML_CREATED_BY))
            .jsonPath("$.[*].id")
            .value(hasItem(DEFAULT_ID.intValue()));
    }

    @Test
    void getInvoiceMasterDetailsAuditLog() {
        // Initialize the database
        invoiceMasterDetailsAuditLogRepository.save(invoiceMasterDetailsAuditLog).block();

        // Get the invoiceMasterDetailsAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, invoiceMasterDetailsAuditLog.getInvceId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.invceId")
            .value(is(invoiceMasterDetailsAuditLog.getInvceId().intValue()))
            .jsonPath("$.oldRowData")
            .value(is(DEFAULT_OLD_ROW_DATA))
            .jsonPath("$.newRowData")
            .value(is(DEFAULT_NEW_ROW_DATA))
            .jsonPath("$.dmlType")
            .value(is(DEFAULT_DML_TYPE))
            .jsonPath("$.dmlTimestamp")
            .value(is(DEFAULT_DML_TIMESTAMP.toString()))
            .jsonPath("$.dmlCreatedBy")
            .value(is(DEFAULT_DML_CREATED_BY))
            .jsonPath("$.id")
            .value(is(DEFAULT_ID.intValue()));
    }

    @Test
    void getNonExistingInvoiceMasterDetailsAuditLog() {
        // Get the invoiceMasterDetailsAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingInvoiceMasterDetailsAuditLog() throws Exception {
        // Initialize the database
        invoiceMasterDetailsAuditLogRepository.save(invoiceMasterDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = invoiceMasterDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the invoiceMasterDetailsAuditLog
        InvoiceMasterDetailsAuditLog updatedInvoiceMasterDetailsAuditLog = invoiceMasterDetailsAuditLogRepository
            .findById(invoiceMasterDetailsAuditLog.getInvceId())
            .block();
        updatedInvoiceMasterDetailsAuditLog
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY)
            .id(UPDATED_ID);
        InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO = invoiceMasterDetailsAuditLogMapper.toDto(
            updatedInvoiceMasterDetailsAuditLog
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, invoiceMasterDetailsAuditLogDTO.getInvceId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InvoiceMasterDetailsAuditLog in the database
        List<InvoiceMasterDetailsAuditLog> invoiceMasterDetailsAuditLogList = invoiceMasterDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceMasterDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        InvoiceMasterDetailsAuditLog testInvoiceMasterDetailsAuditLog = invoiceMasterDetailsAuditLogList.get(
            invoiceMasterDetailsAuditLogList.size() - 1
        );
        assertThat(testInvoiceMasterDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testInvoiceMasterDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testInvoiceMasterDetailsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testInvoiceMasterDetailsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testInvoiceMasterDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
        assertThat(testInvoiceMasterDetailsAuditLog.getId()).isEqualTo(UPDATED_ID);
    }

    @Test
    void putNonExistingInvoiceMasterDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = invoiceMasterDetailsAuditLogRepository.findAll().collectList().block().size();
        invoiceMasterDetailsAuditLog.setInvceId(count.incrementAndGet());

        // Create the InvoiceMasterDetailsAuditLog
        InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO = invoiceMasterDetailsAuditLogMapper.toDto(
            invoiceMasterDetailsAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, invoiceMasterDetailsAuditLogDTO.getInvceId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceMasterDetailsAuditLog in the database
        List<InvoiceMasterDetailsAuditLog> invoiceMasterDetailsAuditLogList = invoiceMasterDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceMasterDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchInvoiceMasterDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = invoiceMasterDetailsAuditLogRepository.findAll().collectList().block().size();
        invoiceMasterDetailsAuditLog.setInvceId(count.incrementAndGet());

        // Create the InvoiceMasterDetailsAuditLog
        InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO = invoiceMasterDetailsAuditLogMapper.toDto(
            invoiceMasterDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceMasterDetailsAuditLog in the database
        List<InvoiceMasterDetailsAuditLog> invoiceMasterDetailsAuditLogList = invoiceMasterDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceMasterDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamInvoiceMasterDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = invoiceMasterDetailsAuditLogRepository.findAll().collectList().block().size();
        invoiceMasterDetailsAuditLog.setInvceId(count.incrementAndGet());

        // Create the InvoiceMasterDetailsAuditLog
        InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO = invoiceMasterDetailsAuditLogMapper.toDto(
            invoiceMasterDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the InvoiceMasterDetailsAuditLog in the database
        List<InvoiceMasterDetailsAuditLog> invoiceMasterDetailsAuditLogList = invoiceMasterDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceMasterDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateInvoiceMasterDetailsAuditLogWithPatch() throws Exception {
        // Initialize the database
        invoiceMasterDetailsAuditLogRepository.save(invoiceMasterDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = invoiceMasterDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the invoiceMasterDetailsAuditLog using partial update
        InvoiceMasterDetailsAuditLog partialUpdatedInvoiceMasterDetailsAuditLog = new InvoiceMasterDetailsAuditLog();
        partialUpdatedInvoiceMasterDetailsAuditLog.setInvceId(invoiceMasterDetailsAuditLog.getInvceId());

        partialUpdatedInvoiceMasterDetailsAuditLog.oldRowData(UPDATED_OLD_ROW_DATA).dmlCreatedBy(UPDATED_DML_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedInvoiceMasterDetailsAuditLog.getInvceId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoiceMasterDetailsAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InvoiceMasterDetailsAuditLog in the database
        List<InvoiceMasterDetailsAuditLog> invoiceMasterDetailsAuditLogList = invoiceMasterDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceMasterDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        InvoiceMasterDetailsAuditLog testInvoiceMasterDetailsAuditLog = invoiceMasterDetailsAuditLogList.get(
            invoiceMasterDetailsAuditLogList.size() - 1
        );
        assertThat(testInvoiceMasterDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testInvoiceMasterDetailsAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testInvoiceMasterDetailsAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testInvoiceMasterDetailsAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testInvoiceMasterDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
        assertThat(testInvoiceMasterDetailsAuditLog.getId()).isEqualTo(DEFAULT_ID);
    }

    @Test
    void fullUpdateInvoiceMasterDetailsAuditLogWithPatch() throws Exception {
        // Initialize the database
        invoiceMasterDetailsAuditLogRepository.save(invoiceMasterDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = invoiceMasterDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the invoiceMasterDetailsAuditLog using partial update
        InvoiceMasterDetailsAuditLog partialUpdatedInvoiceMasterDetailsAuditLog = new InvoiceMasterDetailsAuditLog();
        partialUpdatedInvoiceMasterDetailsAuditLog.setInvceId(invoiceMasterDetailsAuditLog.getInvceId());

        partialUpdatedInvoiceMasterDetailsAuditLog
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY)
            .id(UPDATED_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedInvoiceMasterDetailsAuditLog.getInvceId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoiceMasterDetailsAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InvoiceMasterDetailsAuditLog in the database
        List<InvoiceMasterDetailsAuditLog> invoiceMasterDetailsAuditLogList = invoiceMasterDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceMasterDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        InvoiceMasterDetailsAuditLog testInvoiceMasterDetailsAuditLog = invoiceMasterDetailsAuditLogList.get(
            invoiceMasterDetailsAuditLogList.size() - 1
        );
        assertThat(testInvoiceMasterDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testInvoiceMasterDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testInvoiceMasterDetailsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testInvoiceMasterDetailsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testInvoiceMasterDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
        assertThat(testInvoiceMasterDetailsAuditLog.getId()).isEqualTo(UPDATED_ID);
    }

    @Test
    void patchNonExistingInvoiceMasterDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = invoiceMasterDetailsAuditLogRepository.findAll().collectList().block().size();
        invoiceMasterDetailsAuditLog.setInvceId(count.incrementAndGet());

        // Create the InvoiceMasterDetailsAuditLog
        InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO = invoiceMasterDetailsAuditLogMapper.toDto(
            invoiceMasterDetailsAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, invoiceMasterDetailsAuditLogDTO.getInvceId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceMasterDetailsAuditLog in the database
        List<InvoiceMasterDetailsAuditLog> invoiceMasterDetailsAuditLogList = invoiceMasterDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceMasterDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchInvoiceMasterDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = invoiceMasterDetailsAuditLogRepository.findAll().collectList().block().size();
        invoiceMasterDetailsAuditLog.setInvceId(count.incrementAndGet());

        // Create the InvoiceMasterDetailsAuditLog
        InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO = invoiceMasterDetailsAuditLogMapper.toDto(
            invoiceMasterDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceMasterDetailsAuditLog in the database
        List<InvoiceMasterDetailsAuditLog> invoiceMasterDetailsAuditLogList = invoiceMasterDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceMasterDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamInvoiceMasterDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = invoiceMasterDetailsAuditLogRepository.findAll().collectList().block().size();
        invoiceMasterDetailsAuditLog.setInvceId(count.incrementAndGet());

        // Create the InvoiceMasterDetailsAuditLog
        InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO = invoiceMasterDetailsAuditLogMapper.toDto(
            invoiceMasterDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceMasterDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the InvoiceMasterDetailsAuditLog in the database
        List<InvoiceMasterDetailsAuditLog> invoiceMasterDetailsAuditLogList = invoiceMasterDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceMasterDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteInvoiceMasterDetailsAuditLog() {
        // Initialize the database
        invoiceMasterDetailsAuditLogRepository.save(invoiceMasterDetailsAuditLog).block();

        int databaseSizeBeforeDelete = invoiceMasterDetailsAuditLogRepository.findAll().collectList().block().size();

        // Delete the invoiceMasterDetailsAuditLog
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, invoiceMasterDetailsAuditLog.getInvceId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<InvoiceMasterDetailsAuditLog> invoiceMasterDetailsAuditLogList = invoiceMasterDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceMasterDetailsAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
