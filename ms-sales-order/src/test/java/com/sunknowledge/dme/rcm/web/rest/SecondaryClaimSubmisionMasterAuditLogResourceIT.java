package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.SecondaryClaimSubmisionMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimSubmisionMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.SecondaryClaimSubmisionMasterAuditLogMapper;
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
 * Integration tests for the {@link SecondaryClaimSubmisionMasterAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SecondaryClaimSubmisionMasterAuditLogResourceIT {

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

    private static final String ENTITY_API_URL = "/api/secondary-claim-submision-master-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{chgHealthSconarySubmnMsterId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SecondaryClaimSubmisionMasterAuditLogRepository secondaryClaimSubmisionMasterAuditLogRepository;

    @Autowired
    private SecondaryClaimSubmisionMasterAuditLogMapper secondaryClaimSubmisionMasterAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SecondaryClaimSubmisionMasterAuditLog secondaryClaimSubmisionMasterAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SecondaryClaimSubmisionMasterAuditLog createEntity(EntityManager em) {
        SecondaryClaimSubmisionMasterAuditLog secondaryClaimSubmisionMasterAuditLog = new SecondaryClaimSubmisionMasterAuditLog()
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY)
            .id(DEFAULT_ID);
        return secondaryClaimSubmisionMasterAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SecondaryClaimSubmisionMasterAuditLog createUpdatedEntity(EntityManager em) {
        SecondaryClaimSubmisionMasterAuditLog secondaryClaimSubmisionMasterAuditLog = new SecondaryClaimSubmisionMasterAuditLog()
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY)
            .id(UPDATED_ID);
        return secondaryClaimSubmisionMasterAuditLog;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SecondaryClaimSubmisionMasterAuditLog.class).block();
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
        secondaryClaimSubmisionMasterAuditLog = createEntity(em);
    }

    @Test
    void createSecondaryClaimSubmisionMasterAuditLog() throws Exception {
        int databaseSizeBeforeCreate = secondaryClaimSubmisionMasterAuditLogRepository.findAll().collectList().block().size();
        // Create the SecondaryClaimSubmisionMasterAuditLog
        SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO = secondaryClaimSubmisionMasterAuditLogMapper.toDto(
            secondaryClaimSubmisionMasterAuditLog
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SecondaryClaimSubmisionMasterAuditLog in the database
        List<SecondaryClaimSubmisionMasterAuditLog> secondaryClaimSubmisionMasterAuditLogList = secondaryClaimSubmisionMasterAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        SecondaryClaimSubmisionMasterAuditLog testSecondaryClaimSubmisionMasterAuditLog = secondaryClaimSubmisionMasterAuditLogList.get(
            secondaryClaimSubmisionMasterAuditLogList.size() - 1
        );
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getId()).isEqualTo(DEFAULT_ID);
    }

    @Test
    void createSecondaryClaimSubmisionMasterAuditLogWithExistingId() throws Exception {
        // Create the SecondaryClaimSubmisionMasterAuditLog with an existing ID
        secondaryClaimSubmisionMasterAuditLog.setChgHealthSconarySubmnMsterId(1L);
        SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO = secondaryClaimSubmisionMasterAuditLogMapper.toDto(
            secondaryClaimSubmisionMasterAuditLog
        );

        int databaseSizeBeforeCreate = secondaryClaimSubmisionMasterAuditLogRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SecondaryClaimSubmisionMasterAuditLog in the database
        List<SecondaryClaimSubmisionMasterAuditLog> secondaryClaimSubmisionMasterAuditLogList = secondaryClaimSubmisionMasterAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSecondaryClaimSubmisionMasterAuditLogs() {
        // Initialize the database
        secondaryClaimSubmisionMasterAuditLogRepository.save(secondaryClaimSubmisionMasterAuditLog).block();

        // Get all the secondaryClaimSubmisionMasterAuditLogList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=chgHealthSconarySubmnMsterId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].chgHealthSconarySubmnMsterId")
            .value(hasItem(secondaryClaimSubmisionMasterAuditLog.getChgHealthSconarySubmnMsterId().intValue()))
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
    void getSecondaryClaimSubmisionMasterAuditLog() {
        // Initialize the database
        secondaryClaimSubmisionMasterAuditLogRepository.save(secondaryClaimSubmisionMasterAuditLog).block();

        // Get the secondaryClaimSubmisionMasterAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, secondaryClaimSubmisionMasterAuditLog.getChgHealthSconarySubmnMsterId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.chgHealthSconarySubmnMsterId")
            .value(is(secondaryClaimSubmisionMasterAuditLog.getChgHealthSconarySubmnMsterId().intValue()))
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
    void getNonExistingSecondaryClaimSubmisionMasterAuditLog() {
        // Get the secondaryClaimSubmisionMasterAuditLog
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingSecondaryClaimSubmisionMasterAuditLog() throws Exception {
        // Initialize the database
        secondaryClaimSubmisionMasterAuditLogRepository.save(secondaryClaimSubmisionMasterAuditLog).block();

        int databaseSizeBeforeUpdate = secondaryClaimSubmisionMasterAuditLogRepository.findAll().collectList().block().size();

        // Update the secondaryClaimSubmisionMasterAuditLog
        SecondaryClaimSubmisionMasterAuditLog updatedSecondaryClaimSubmisionMasterAuditLog = secondaryClaimSubmisionMasterAuditLogRepository
            .findById(secondaryClaimSubmisionMasterAuditLog.getChgHealthSconarySubmnMsterId())
            .block();
        updatedSecondaryClaimSubmisionMasterAuditLog
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY)
            .id(UPDATED_ID);
        SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO = secondaryClaimSubmisionMasterAuditLogMapper.toDto(
            updatedSecondaryClaimSubmisionMasterAuditLog
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, secondaryClaimSubmisionMasterAuditLogDTO.getChgHealthSconarySubmnMsterId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SecondaryClaimSubmisionMasterAuditLog in the database
        List<SecondaryClaimSubmisionMasterAuditLog> secondaryClaimSubmisionMasterAuditLogList = secondaryClaimSubmisionMasterAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SecondaryClaimSubmisionMasterAuditLog testSecondaryClaimSubmisionMasterAuditLog = secondaryClaimSubmisionMasterAuditLogList.get(
            secondaryClaimSubmisionMasterAuditLogList.size() - 1
        );
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getId()).isEqualTo(UPDATED_ID);
    }

    @Test
    void putNonExistingSecondaryClaimSubmisionMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimSubmisionMasterAuditLogRepository.findAll().collectList().block().size();
        secondaryClaimSubmisionMasterAuditLog.setChgHealthSconarySubmnMsterId(count.incrementAndGet());

        // Create the SecondaryClaimSubmisionMasterAuditLog
        SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO = secondaryClaimSubmisionMasterAuditLogMapper.toDto(
            secondaryClaimSubmisionMasterAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, secondaryClaimSubmisionMasterAuditLogDTO.getChgHealthSconarySubmnMsterId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SecondaryClaimSubmisionMasterAuditLog in the database
        List<SecondaryClaimSubmisionMasterAuditLog> secondaryClaimSubmisionMasterAuditLogList = secondaryClaimSubmisionMasterAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSecondaryClaimSubmisionMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimSubmisionMasterAuditLogRepository.findAll().collectList().block().size();
        secondaryClaimSubmisionMasterAuditLog.setChgHealthSconarySubmnMsterId(count.incrementAndGet());

        // Create the SecondaryClaimSubmisionMasterAuditLog
        SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO = secondaryClaimSubmisionMasterAuditLogMapper.toDto(
            secondaryClaimSubmisionMasterAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SecondaryClaimSubmisionMasterAuditLog in the database
        List<SecondaryClaimSubmisionMasterAuditLog> secondaryClaimSubmisionMasterAuditLogList = secondaryClaimSubmisionMasterAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSecondaryClaimSubmisionMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimSubmisionMasterAuditLogRepository.findAll().collectList().block().size();
        secondaryClaimSubmisionMasterAuditLog.setChgHealthSconarySubmnMsterId(count.incrementAndGet());

        // Create the SecondaryClaimSubmisionMasterAuditLog
        SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO = secondaryClaimSubmisionMasterAuditLogMapper.toDto(
            secondaryClaimSubmisionMasterAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SecondaryClaimSubmisionMasterAuditLog in the database
        List<SecondaryClaimSubmisionMasterAuditLog> secondaryClaimSubmisionMasterAuditLogList = secondaryClaimSubmisionMasterAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSecondaryClaimSubmisionMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        secondaryClaimSubmisionMasterAuditLogRepository.save(secondaryClaimSubmisionMasterAuditLog).block();

        int databaseSizeBeforeUpdate = secondaryClaimSubmisionMasterAuditLogRepository.findAll().collectList().block().size();

        // Update the secondaryClaimSubmisionMasterAuditLog using partial update
        SecondaryClaimSubmisionMasterAuditLog partialUpdatedSecondaryClaimSubmisionMasterAuditLog = new SecondaryClaimSubmisionMasterAuditLog();
        partialUpdatedSecondaryClaimSubmisionMasterAuditLog.setChgHealthSconarySubmnMsterId(
            secondaryClaimSubmisionMasterAuditLog.getChgHealthSconarySubmnMsterId()
        );

        partialUpdatedSecondaryClaimSubmisionMasterAuditLog.id(UPDATED_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSecondaryClaimSubmisionMasterAuditLog.getChgHealthSconarySubmnMsterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSecondaryClaimSubmisionMasterAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SecondaryClaimSubmisionMasterAuditLog in the database
        List<SecondaryClaimSubmisionMasterAuditLog> secondaryClaimSubmisionMasterAuditLogList = secondaryClaimSubmisionMasterAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SecondaryClaimSubmisionMasterAuditLog testSecondaryClaimSubmisionMasterAuditLog = secondaryClaimSubmisionMasterAuditLogList.get(
            secondaryClaimSubmisionMasterAuditLogList.size() - 1
        );
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getId()).isEqualTo(UPDATED_ID);
    }

    @Test
    void fullUpdateSecondaryClaimSubmisionMasterAuditLogWithPatch() throws Exception {
        // Initialize the database
        secondaryClaimSubmisionMasterAuditLogRepository.save(secondaryClaimSubmisionMasterAuditLog).block();

        int databaseSizeBeforeUpdate = secondaryClaimSubmisionMasterAuditLogRepository.findAll().collectList().block().size();

        // Update the secondaryClaimSubmisionMasterAuditLog using partial update
        SecondaryClaimSubmisionMasterAuditLog partialUpdatedSecondaryClaimSubmisionMasterAuditLog = new SecondaryClaimSubmisionMasterAuditLog();
        partialUpdatedSecondaryClaimSubmisionMasterAuditLog.setChgHealthSconarySubmnMsterId(
            secondaryClaimSubmisionMasterAuditLog.getChgHealthSconarySubmnMsterId()
        );

        partialUpdatedSecondaryClaimSubmisionMasterAuditLog
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY)
            .id(UPDATED_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSecondaryClaimSubmisionMasterAuditLog.getChgHealthSconarySubmnMsterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSecondaryClaimSubmisionMasterAuditLog))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SecondaryClaimSubmisionMasterAuditLog in the database
        List<SecondaryClaimSubmisionMasterAuditLog> secondaryClaimSubmisionMasterAuditLogList = secondaryClaimSubmisionMasterAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
        SecondaryClaimSubmisionMasterAuditLog testSecondaryClaimSubmisionMasterAuditLog = secondaryClaimSubmisionMasterAuditLogList.get(
            secondaryClaimSubmisionMasterAuditLogList.size() - 1
        );
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
        assertThat(testSecondaryClaimSubmisionMasterAuditLog.getId()).isEqualTo(UPDATED_ID);
    }

    @Test
    void patchNonExistingSecondaryClaimSubmisionMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimSubmisionMasterAuditLogRepository.findAll().collectList().block().size();
        secondaryClaimSubmisionMasterAuditLog.setChgHealthSconarySubmnMsterId(count.incrementAndGet());

        // Create the SecondaryClaimSubmisionMasterAuditLog
        SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO = secondaryClaimSubmisionMasterAuditLogMapper.toDto(
            secondaryClaimSubmisionMasterAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, secondaryClaimSubmisionMasterAuditLogDTO.getChgHealthSconarySubmnMsterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SecondaryClaimSubmisionMasterAuditLog in the database
        List<SecondaryClaimSubmisionMasterAuditLog> secondaryClaimSubmisionMasterAuditLogList = secondaryClaimSubmisionMasterAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSecondaryClaimSubmisionMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimSubmisionMasterAuditLogRepository.findAll().collectList().block().size();
        secondaryClaimSubmisionMasterAuditLog.setChgHealthSconarySubmnMsterId(count.incrementAndGet());

        // Create the SecondaryClaimSubmisionMasterAuditLog
        SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO = secondaryClaimSubmisionMasterAuditLogMapper.toDto(
            secondaryClaimSubmisionMasterAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SecondaryClaimSubmisionMasterAuditLog in the database
        List<SecondaryClaimSubmisionMasterAuditLog> secondaryClaimSubmisionMasterAuditLogList = secondaryClaimSubmisionMasterAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSecondaryClaimSubmisionMasterAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = secondaryClaimSubmisionMasterAuditLogRepository.findAll().collectList().block().size();
        secondaryClaimSubmisionMasterAuditLog.setChgHealthSconarySubmnMsterId(count.incrementAndGet());

        // Create the SecondaryClaimSubmisionMasterAuditLog
        SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO = secondaryClaimSubmisionMasterAuditLogMapper.toDto(
            secondaryClaimSubmisionMasterAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(secondaryClaimSubmisionMasterAuditLogDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SecondaryClaimSubmisionMasterAuditLog in the database
        List<SecondaryClaimSubmisionMasterAuditLog> secondaryClaimSubmisionMasterAuditLogList = secondaryClaimSubmisionMasterAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSecondaryClaimSubmisionMasterAuditLog() {
        // Initialize the database
        secondaryClaimSubmisionMasterAuditLogRepository.save(secondaryClaimSubmisionMasterAuditLog).block();

        int databaseSizeBeforeDelete = secondaryClaimSubmisionMasterAuditLogRepository.findAll().collectList().block().size();

        // Delete the secondaryClaimSubmisionMasterAuditLog
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, secondaryClaimSubmisionMasterAuditLog.getChgHealthSconarySubmnMsterId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SecondaryClaimSubmisionMasterAuditLog> secondaryClaimSubmisionMasterAuditLogList = secondaryClaimSubmisionMasterAuditLogRepository
            .findAll()
            .collectList()
            .block();
        assertThat(secondaryClaimSubmisionMasterAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
