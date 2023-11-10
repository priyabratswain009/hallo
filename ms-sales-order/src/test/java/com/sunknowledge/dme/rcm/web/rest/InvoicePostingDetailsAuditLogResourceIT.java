package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.InvoicePostingDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.InvoicePostingDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.InvoicePostingDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.InvoicePostingDetailsAuditLogMapper;
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
 * Integration tests for the {@link InvoicePostingDetailsAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class InvoicePostingDetailsAuditLogResourceIT {

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

    private static final String ENTITY_API_URL = "/api/invoice-posting-details-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{invLineItmPstingId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InvoicePostingDetailsAuditLogRepository invoicePostingDetailsAuditLogRepository;

    @Autowired
    private InvoicePostingDetailsAuditLogMapper invoicePostingDetailsAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private InvoicePostingDetailsAuditLog invoicePostingDetailsAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoicePostingDetailsAuditLog createEntity(EntityManager em) {
        InvoicePostingDetailsAuditLog invoicePostingDetailsAuditLog = new InvoicePostingDetailsAuditLog()
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY)
            .id(DEFAULT_ID);
        return invoicePostingDetailsAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoicePostingDetailsAuditLog createUpdatedEntity(EntityManager em) {
        InvoicePostingDetailsAuditLog invoicePostingDetailsAuditLog = new InvoicePostingDetailsAuditLog()
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY)
            .id(UPDATED_ID);
        return invoicePostingDetailsAuditLog;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(InvoicePostingDetailsAuditLog.class).block();
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
        invoicePostingDetailsAuditLog = createEntity(em);
    }

    @Test
    void createInvoicePostingDetailsAuditLog() throws Exception {
        int databaseSizeBeforeCreate = invoicePostingDetailsAuditLogRepository.findAll().collectList().block().size();
        // Create the InvoicePostingDetailsAuditLog
        InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO = invoicePostingDetailsAuditLogMapper.toDto(
            invoicePostingDetailsAuditLog
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the InvoicePostingDetailsAuditLog in the database
        List<InvoicePostingDetailsAuditLog> invoicePostingDetailsAuditLogList = invoicePostingDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoicePostingDetailsAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        InvoicePostingDetailsAuditLog testInvoicePostingDetailsAuditLog = invoicePostingDetailsAuditLogList.get(
            invoicePostingDetailsAuditLogList.size() - 1
        );
        assertThat(testInvoicePostingDetailsAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testInvoicePostingDetailsAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testInvoicePostingDetailsAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testInvoicePostingDetailsAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testInvoicePostingDetailsAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
        assertThat(testInvoicePostingDetailsAuditLog.getId()).isEqualTo(DEFAULT_ID);
    }

    @Test
    void createInvoicePostingDetailsAuditLogWithExistingId() throws Exception {
        // Create the InvoicePostingDetailsAuditLog with an existing ID
        invoicePostingDetailsAuditLog.setInvLineItmPstingId(1L);
        InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO = invoicePostingDetailsAuditLogMapper.toDto(
            invoicePostingDetailsAuditLog
        );

        int databaseSizeBeforeCreate = invoicePostingDetailsAuditLogRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoicePostingDetailsAuditLog in the database
        List<InvoicePostingDetailsAuditLog> invoicePostingDetailsAuditLogList = invoicePostingDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoicePostingDetailsAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllInvoicePostingDetailsAuditLogs() {
        // Initialize the database
        invoicePostingDetailsAuditLogRepository.save(invoicePostingDetailsAuditLog).block();

        // Get all the invoicePostingDetailsAuditLogList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=invLineItmPstingId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].invLineItmPstingId")
            .value(hasItem(invoicePostingDetailsAuditLog.getInvLineItmPstingId().intValue()))
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
    void getInvoicePostingDetailsAuditLog() {
        // Initialize the database
        invoicePostingDetailsAuditLogRepository.save(invoicePostingDetailsAuditLog).block();

        // Get the invoicePostingDetailsAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, invoicePostingDetailsAuditLog.getInvLineItmPstingId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.invLineItmPstingId")
            .value(is(invoicePostingDetailsAuditLog.getInvLineItmPstingId().intValue()))
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
    void getNonExistingInvoicePostingDetailsAuditLog() {
        // Get the invoicePostingDetailsAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewInvoicePostingDetailsAuditLog() throws Exception {
        // Initialize the database
        invoicePostingDetailsAuditLogRepository.save(invoicePostingDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = invoicePostingDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the invoicePostingDetailsAuditLog
        InvoicePostingDetailsAuditLog updatedInvoicePostingDetailsAuditLog = invoicePostingDetailsAuditLogRepository
            .findById(invoicePostingDetailsAuditLog.getInvLineItmPstingId())
            .block();
        updatedInvoicePostingDetailsAuditLog
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY)
            .id(UPDATED_ID);
        InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO = invoicePostingDetailsAuditLogMapper.toDto(
            updatedInvoicePostingDetailsAuditLog
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, invoicePostingDetailsAuditLogDTO.getInvLineItmPstingId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InvoicePostingDetailsAuditLog in the database
        List<InvoicePostingDetailsAuditLog> invoicePostingDetailsAuditLogList = invoicePostingDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoicePostingDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        InvoicePostingDetailsAuditLog testInvoicePostingDetailsAuditLog = invoicePostingDetailsAuditLogList.get(
            invoicePostingDetailsAuditLogList.size() - 1
        );
        assertThat(testInvoicePostingDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testInvoicePostingDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testInvoicePostingDetailsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testInvoicePostingDetailsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testInvoicePostingDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
        assertThat(testInvoicePostingDetailsAuditLog.getId()).isEqualTo(UPDATED_ID);
    }

    @Test
    void putNonExistingInvoicePostingDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = invoicePostingDetailsAuditLogRepository.findAll().collectList().block().size();
        invoicePostingDetailsAuditLog.setInvLineItmPstingId(count.incrementAndGet());

        // Create the InvoicePostingDetailsAuditLog
        InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO = invoicePostingDetailsAuditLogMapper.toDto(
            invoicePostingDetailsAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, invoicePostingDetailsAuditLogDTO.getInvLineItmPstingId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoicePostingDetailsAuditLog in the database
        List<InvoicePostingDetailsAuditLog> invoicePostingDetailsAuditLogList = invoicePostingDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoicePostingDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchInvoicePostingDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = invoicePostingDetailsAuditLogRepository.findAll().collectList().block().size();
        invoicePostingDetailsAuditLog.setInvLineItmPstingId(count.incrementAndGet());

        // Create the InvoicePostingDetailsAuditLog
        InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO = invoicePostingDetailsAuditLogMapper.toDto(
            invoicePostingDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoicePostingDetailsAuditLog in the database
        List<InvoicePostingDetailsAuditLog> invoicePostingDetailsAuditLogList = invoicePostingDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoicePostingDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamInvoicePostingDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = invoicePostingDetailsAuditLogRepository.findAll().collectList().block().size();
        invoicePostingDetailsAuditLog.setInvLineItmPstingId(count.incrementAndGet());

        // Create the InvoicePostingDetailsAuditLog
        InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO = invoicePostingDetailsAuditLogMapper.toDto(
            invoicePostingDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the InvoicePostingDetailsAuditLog in the database
        List<InvoicePostingDetailsAuditLog> invoicePostingDetailsAuditLogList = invoicePostingDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoicePostingDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateInvoicePostingDetailsAuditLogWithPatch() throws Exception {
        // Initialize the database
        invoicePostingDetailsAuditLogRepository.save(invoicePostingDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = invoicePostingDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the invoicePostingDetailsAuditLog using partial update
        InvoicePostingDetailsAuditLog partialUpdatedInvoicePostingDetailsAuditLog = new InvoicePostingDetailsAuditLog();
        partialUpdatedInvoicePostingDetailsAuditLog.setInvLineItmPstingId(invoicePostingDetailsAuditLog.getInvLineItmPstingId());

        partialUpdatedInvoicePostingDetailsAuditLog.oldRowData(UPDATED_OLD_ROW_DATA).dmlType(UPDATED_DML_TYPE).id(UPDATED_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedInvoicePostingDetailsAuditLog.getInvLineItmPstingId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoicePostingDetailsAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InvoicePostingDetailsAuditLog in the database
        List<InvoicePostingDetailsAuditLog> invoicePostingDetailsAuditLogList = invoicePostingDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoicePostingDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        InvoicePostingDetailsAuditLog testInvoicePostingDetailsAuditLog = invoicePostingDetailsAuditLogList.get(
            invoicePostingDetailsAuditLogList.size() - 1
        );
        assertThat(testInvoicePostingDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testInvoicePostingDetailsAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testInvoicePostingDetailsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testInvoicePostingDetailsAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testInvoicePostingDetailsAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
        assertThat(testInvoicePostingDetailsAuditLog.getId()).isEqualTo(UPDATED_ID);
    }

    @Test
    void fullUpdateInvoicePostingDetailsAuditLogWithPatch() throws Exception {
        // Initialize the database
        invoicePostingDetailsAuditLogRepository.save(invoicePostingDetailsAuditLog).block();

        int databaseSizeBeforeUpdate = invoicePostingDetailsAuditLogRepository.findAll().collectList().block().size();

        // Update the invoicePostingDetailsAuditLog using partial update
        InvoicePostingDetailsAuditLog partialUpdatedInvoicePostingDetailsAuditLog = new InvoicePostingDetailsAuditLog();
        partialUpdatedInvoicePostingDetailsAuditLog.setInvLineItmPstingId(invoicePostingDetailsAuditLog.getInvLineItmPstingId());

        partialUpdatedInvoicePostingDetailsAuditLog
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY)
            .id(UPDATED_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedInvoicePostingDetailsAuditLog.getInvLineItmPstingId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoicePostingDetailsAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InvoicePostingDetailsAuditLog in the database
        List<InvoicePostingDetailsAuditLog> invoicePostingDetailsAuditLogList = invoicePostingDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoicePostingDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
        InvoicePostingDetailsAuditLog testInvoicePostingDetailsAuditLog = invoicePostingDetailsAuditLogList.get(
            invoicePostingDetailsAuditLogList.size() - 1
        );
        assertThat(testInvoicePostingDetailsAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testInvoicePostingDetailsAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testInvoicePostingDetailsAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testInvoicePostingDetailsAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testInvoicePostingDetailsAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
        assertThat(testInvoicePostingDetailsAuditLog.getId()).isEqualTo(UPDATED_ID);
    }

    @Test
    void patchNonExistingInvoicePostingDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = invoicePostingDetailsAuditLogRepository.findAll().collectList().block().size();
        invoicePostingDetailsAuditLog.setInvLineItmPstingId(count.incrementAndGet());

        // Create the InvoicePostingDetailsAuditLog
        InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO = invoicePostingDetailsAuditLogMapper.toDto(
            invoicePostingDetailsAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, invoicePostingDetailsAuditLogDTO.getInvLineItmPstingId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoicePostingDetailsAuditLog in the database
        List<InvoicePostingDetailsAuditLog> invoicePostingDetailsAuditLogList = invoicePostingDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoicePostingDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchInvoicePostingDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = invoicePostingDetailsAuditLogRepository.findAll().collectList().block().size();
        invoicePostingDetailsAuditLog.setInvLineItmPstingId(count.incrementAndGet());

        // Create the InvoicePostingDetailsAuditLog
        InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO = invoicePostingDetailsAuditLogMapper.toDto(
            invoicePostingDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InvoicePostingDetailsAuditLog in the database
        List<InvoicePostingDetailsAuditLog> invoicePostingDetailsAuditLogList = invoicePostingDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoicePostingDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamInvoicePostingDetailsAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = invoicePostingDetailsAuditLogRepository.findAll().collectList().block().size();
        invoicePostingDetailsAuditLog.setInvLineItmPstingId(count.incrementAndGet());

        // Create the InvoicePostingDetailsAuditLog
        InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO = invoicePostingDetailsAuditLogMapper.toDto(
            invoicePostingDetailsAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(invoicePostingDetailsAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the InvoicePostingDetailsAuditLog in the database
        List<InvoicePostingDetailsAuditLog> invoicePostingDetailsAuditLogList = invoicePostingDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoicePostingDetailsAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteInvoicePostingDetailsAuditLog() {
        // Initialize the database
        invoicePostingDetailsAuditLogRepository.save(invoicePostingDetailsAuditLog).block();

        int databaseSizeBeforeDelete = invoicePostingDetailsAuditLogRepository.findAll().collectList().block().size();

        // Delete the invoicePostingDetailsAuditLog
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, invoicePostingDetailsAuditLog.getInvLineItmPstingId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<InvoicePostingDetailsAuditLog> invoicePostingDetailsAuditLogList = invoicePostingDetailsAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(invoicePostingDetailsAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
