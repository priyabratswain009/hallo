package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.InvoiceLineItemDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.InvoiceLineItemDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.InvoiceLineItemDetailsAuditLogMapper;
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
 * Integration tests for the {@link InvoiceLineItemDetailsAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class InvoiceLineItemDetailsAuditLogResourceIT {

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

    private static final String ENTITY_API_URL = "/api/invoice-line-item-details-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{invceLinItmDetilId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InvoiceLineItemDetailsAuditLogRepository invoiceLineItemDetailsAuditLogRepository;

    @Autowired
    private InvoiceLineItemDetailsAuditLogMapper invoiceLineItemDetailsAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private InvoiceLineItemDetailsAuditLog invoiceLineItemDetailsAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceLineItemDetailsAuditLog createEntity(EntityManager em) {
        InvoiceLineItemDetailsAuditLog invoiceLineItemDetailsAuditLog = new InvoiceLineItemDetailsAuditLog()
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY)
            .id(DEFAULT_ID);
        return invoiceLineItemDetailsAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceLineItemDetailsAuditLog createUpdatedEntity(EntityManager em) {
        InvoiceLineItemDetailsAuditLog invoiceLineItemDetailsAuditLog = new InvoiceLineItemDetailsAuditLog()
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY)
            .id(UPDATED_ID);
        return invoiceLineItemDetailsAuditLog;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(InvoiceLineItemDetailsAuditLog.class).block();
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
        invoiceLineItemDetailsAuditLog = createEntity(em);
    }

    @Test
    void createInvoiceLineItemDetailsAuditLog() throws Exception {
        int databaseSizeBeforeCreate = invoiceLineItemDetailsAuditLogRepository.findAll().collectList().block().size();
        // Create the InvoiceLineItemDetailsAuditLog
        InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO = invoiceLineItemDetailsAuditLogMapper.toDto(
            invoiceLineItemDetailsAuditLog
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the InvoiceLineItemDetailsAuditLog in the database
        List<InvoiceLineItemDetailsAuditLog> invoiceLineItemDetailsAuditLogList = invoiceLineItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceLineItemDetailsAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        InvoiceLineItemDetailsAuditLog testInvoiceLineItemDetailsAuditLog = invoiceLineItemDetailsAuditLogList.get(
            invoiceLineItemDetailsAuditLogList.size() - 1
        );
        assertThat(testInvoiceLineItemDetailsAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testInvoiceLineItemDetailsAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testInvoiceLineItemDetailsAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testInvoiceLineItemDetailsAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testInvoiceLineItemDetailsAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
        assertThat(testInvoiceLineItemDetailsAuditLog.getId()).isEqualTo(DEFAULT_ID);
    }

    @Test
    void createInvoiceLineItemDetailsAuditLogWithExistingId() throws Exception {
        // Create the InvoiceLineItemDetailsAuditLog with an existing ID
        invoiceLineItemDetailsAuditLog.setInvceLinItmDetilId(1L);
        InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO = invoiceLineItemDetailsAuditLogMapper.toDto(
            invoiceLineItemDetailsAuditLog
        );

        int databaseSizeBeforeCreate = invoiceLineItemDetailsAuditLogRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceLineItemDetailsAuditLog in the database
        List<InvoiceLineItemDetailsAuditLog> invoiceLineItemDetailsAuditLogList = invoiceLineItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceLineItemDetailsAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllInvoiceLineItemDetailsAuditLogs() {
        // Initialize the database
        invoiceLineItemDetailsAuditLogRepository.save(invoiceLineItemDetailsAuditLog).block();

        // Get all the invoiceLineItemDetailsAuditLogList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=invceLinItmDetilId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].invceLinItmDetilId")
            .value(hasItem(invoiceLineItemDetailsAuditLog.getInvceLinItmDetilId().intValue()))
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
    void getInvoiceLineItemDetailsAuditLog() {
        // Initialize the database
        invoiceLineItemDetailsAuditLogRepository.save(invoiceLineItemDetailsAuditLog).block();

        // Get the invoiceLineItemDetailsAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, invoiceLineItemDetailsAuditLog.getInvceLinItmDetilId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.invceLinItmDetilId")
            .value(is(invoiceLineItemDetailsAuditLog.getInvceLinItmDetilId().intValue()))
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
    void getNonExistingInvoiceLineItemDetailsAuditLog() {
        // Get the invoiceLineItemDetailsAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingInvoiceLineItemDetailsAuditLog() throws Exception {
        // Initialize the database
        invoiceLineItemDetailsAuditLogRepository.save(invoiceLineItemDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = invoiceLineItemDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the invoiceLineItemDetailsAuditLog
        InvoiceLineItemDetailsAuditLog updatedInvoiceLineItemDetailsAuditLog = invoiceLineItemDetailsAuditLogRepository
            .findById(invoiceLineItemDetailsAuditLog.getInvceLinItmDetilId())
            .block();
        updatedInvoiceLineItemDetailsAuditLog
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY)
            .id(UPDATED_ID);
        InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO = invoiceLineItemDetailsAuditLogMapper.toDto(
            updatedInvoiceLineItemDetailsAuditLog
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, invoiceLineItemDetailsAuditLogDTO.getInvceLinItmDetilId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InvoiceLineItemDetailsAuditLog in the database
        List<InvoiceLineItemDetailsAuditLog> invoiceLineItemDetailsAuditLogList = invoiceLineItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceLineItemDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        InvoiceLineItemDetailsAuditLog testInvoiceLineItemDetailsAuditLog = invoiceLineItemDetailsAuditLogList.get(
            invoiceLineItemDetailsAuditLogList.size() - 1
        );
        assertThat(testInvoiceLineItemDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testInvoiceLineItemDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testInvoiceLineItemDetailsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testInvoiceLineItemDetailsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testInvoiceLineItemDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
        assertThat(testInvoiceLineItemDetailsAuditLog.getId()).isEqualTo(UPDATED_ID);
    }

    @Test
    void putNonExistingInvoiceLineItemDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineItemDetailsAuditLogRepository.findAll().collectList().block().size();
        invoiceLineItemDetailsAuditLog.setInvceLinItmDetilId(count.incrementAndGet());

        // Create the InvoiceLineItemDetailsAuditLog
        InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO = invoiceLineItemDetailsAuditLogMapper.toDto(
            invoiceLineItemDetailsAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, invoiceLineItemDetailsAuditLogDTO.getInvceLinItmDetilId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceLineItemDetailsAuditLog in the database
        List<InvoiceLineItemDetailsAuditLog> invoiceLineItemDetailsAuditLogList = invoiceLineItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceLineItemDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchInvoiceLineItemDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineItemDetailsAuditLogRepository.findAll().collectList().block().size();
        invoiceLineItemDetailsAuditLog.setInvceLinItmDetilId(count.incrementAndGet());

        // Create the InvoiceLineItemDetailsAuditLog
        InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO = invoiceLineItemDetailsAuditLogMapper.toDto(
            invoiceLineItemDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceLineItemDetailsAuditLog in the database
        List<InvoiceLineItemDetailsAuditLog> invoiceLineItemDetailsAuditLogList = invoiceLineItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceLineItemDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamInvoiceLineItemDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineItemDetailsAuditLogRepository.findAll().collectList().block().size();
        invoiceLineItemDetailsAuditLog.setInvceLinItmDetilId(count.incrementAndGet());

        // Create the InvoiceLineItemDetailsAuditLog
        InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO = invoiceLineItemDetailsAuditLogMapper.toDto(
            invoiceLineItemDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the InvoiceLineItemDetailsAuditLog in the database
        List<InvoiceLineItemDetailsAuditLog> invoiceLineItemDetailsAuditLogList = invoiceLineItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceLineItemDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateInvoiceLineItemDetailsAuditLogWithPatch() throws Exception {
        // Initialize the database
        invoiceLineItemDetailsAuditLogRepository.save(invoiceLineItemDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = invoiceLineItemDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the invoiceLineItemDetailsAuditLog using partial update
        InvoiceLineItemDetailsAuditLog partialUpdatedInvoiceLineItemDetailsAuditLog = new InvoiceLineItemDetailsAuditLog();
        partialUpdatedInvoiceLineItemDetailsAuditLog.setInvceLinItmDetilId(invoiceLineItemDetailsAuditLog.getInvceLinItmDetilId());

        partialUpdatedInvoiceLineItemDetailsAuditLog.oldRowData(UPDATED_OLD_ROW_DATA);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedInvoiceLineItemDetailsAuditLog.getInvceLinItmDetilId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoiceLineItemDetailsAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InvoiceLineItemDetailsAuditLog in the database
        List<InvoiceLineItemDetailsAuditLog> invoiceLineItemDetailsAuditLogList = invoiceLineItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceLineItemDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        InvoiceLineItemDetailsAuditLog testInvoiceLineItemDetailsAuditLog = invoiceLineItemDetailsAuditLogList.get(
            invoiceLineItemDetailsAuditLogList.size() - 1
        );
        assertThat(testInvoiceLineItemDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testInvoiceLineItemDetailsAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testInvoiceLineItemDetailsAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testInvoiceLineItemDetailsAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testInvoiceLineItemDetailsAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
        assertThat(testInvoiceLineItemDetailsAuditLog.getId()).isEqualTo(DEFAULT_ID);
    }

    @Test
    void fullUpdateInvoiceLineItemDetailsAuditLogWithPatch() throws Exception {
        // Initialize the database
        invoiceLineItemDetailsAuditLogRepository.save(invoiceLineItemDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = invoiceLineItemDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the invoiceLineItemDetailsAuditLog using partial update
        InvoiceLineItemDetailsAuditLog partialUpdatedInvoiceLineItemDetailsAuditLog = new InvoiceLineItemDetailsAuditLog();
        partialUpdatedInvoiceLineItemDetailsAuditLog.setInvceLinItmDetilId(invoiceLineItemDetailsAuditLog.getInvceLinItmDetilId());

        partialUpdatedInvoiceLineItemDetailsAuditLog
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY)
            .id(UPDATED_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedInvoiceLineItemDetailsAuditLog.getInvceLinItmDetilId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoiceLineItemDetailsAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InvoiceLineItemDetailsAuditLog in the database
        List<InvoiceLineItemDetailsAuditLog> invoiceLineItemDetailsAuditLogList = invoiceLineItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceLineItemDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        InvoiceLineItemDetailsAuditLog testInvoiceLineItemDetailsAuditLog = invoiceLineItemDetailsAuditLogList.get(
            invoiceLineItemDetailsAuditLogList.size() - 1
        );
        assertThat(testInvoiceLineItemDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testInvoiceLineItemDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testInvoiceLineItemDetailsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testInvoiceLineItemDetailsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testInvoiceLineItemDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
        assertThat(testInvoiceLineItemDetailsAuditLog.getId()).isEqualTo(UPDATED_ID);
    }

    @Test
    void patchNonExistingInvoiceLineItemDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineItemDetailsAuditLogRepository.findAll().collectList().block().size();
        invoiceLineItemDetailsAuditLog.setInvceLinItmDetilId(count.incrementAndGet());

        // Create the InvoiceLineItemDetailsAuditLog
        InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO = invoiceLineItemDetailsAuditLogMapper.toDto(
            invoiceLineItemDetailsAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, invoiceLineItemDetailsAuditLogDTO.getInvceLinItmDetilId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceLineItemDetailsAuditLog in the database
        List<InvoiceLineItemDetailsAuditLog> invoiceLineItemDetailsAuditLogList = invoiceLineItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceLineItemDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchInvoiceLineItemDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineItemDetailsAuditLogRepository.findAll().collectList().block().size();
        invoiceLineItemDetailsAuditLog.setInvceLinItmDetilId(count.incrementAndGet());

        // Create the InvoiceLineItemDetailsAuditLog
        InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO = invoiceLineItemDetailsAuditLogMapper.toDto(
            invoiceLineItemDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoiceLineItemDetailsAuditLog in the database
        List<InvoiceLineItemDetailsAuditLog> invoiceLineItemDetailsAuditLogList = invoiceLineItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceLineItemDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamInvoiceLineItemDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineItemDetailsAuditLogRepository.findAll().collectList().block().size();
        invoiceLineItemDetailsAuditLog.setInvceLinItmDetilId(count.incrementAndGet());

        // Create the InvoiceLineItemDetailsAuditLog
        InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO = invoiceLineItemDetailsAuditLogMapper.toDto(
            invoiceLineItemDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoiceLineItemDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the InvoiceLineItemDetailsAuditLog in the database
        List<InvoiceLineItemDetailsAuditLog> invoiceLineItemDetailsAuditLogList = invoiceLineItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceLineItemDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteInvoiceLineItemDetailsAuditLog() {
        // Initialize the database
        invoiceLineItemDetailsAuditLogRepository.save(invoiceLineItemDetailsAuditLog).block();

        int databaseSizeBeforeDelete = invoiceLineItemDetailsAuditLogRepository.findAll().collectList().block().size();

        // Delete the invoiceLineItemDetailsAuditLog
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, invoiceLineItemDetailsAuditLog.getInvceLinItmDetilId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<InvoiceLineItemDetailsAuditLog> invoiceLineItemDetailsAuditLogList = invoiceLineItemDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoiceLineItemDetailsAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
