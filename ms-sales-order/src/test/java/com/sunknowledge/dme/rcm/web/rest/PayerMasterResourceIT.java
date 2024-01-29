package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PayerMaster;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.PayerMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.PayerMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PayerMasterMapper;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.UUID;
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
 * Integration tests for the {@link PayerMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PayerMasterResourceIT {

    private static final String DEFAULT_PAYER_ID = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_PAYER_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PAYER_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/payer-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{payerMasterId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PayerMasterRepository payerMasterRepository;

    @Autowired
    private PayerMasterMapper payerMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PayerMaster payerMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PayerMaster createEntity(EntityManager em) {
        PayerMaster payerMaster = new PayerMaster()
            .payerId(DEFAULT_PAYER_ID)
            .payerName(DEFAULT_PAYER_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .payerMasterUuid(DEFAULT_PAYER_MASTER_UUID);
        return payerMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PayerMaster createUpdatedEntity(EntityManager em) {
        PayerMaster payerMaster = new PayerMaster()
            .payerId(UPDATED_PAYER_ID)
            .payerName(UPDATED_PAYER_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .payerMasterUuid(UPDATED_PAYER_MASTER_UUID);
        return payerMaster;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PayerMaster.class).block();
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
        payerMaster = createEntity(em);
    }

    @Test
    void createPayerMaster() throws Exception {
        int databaseSizeBeforeCreate = payerMasterRepository.findAll().collectList().block().size();
        // Create the PayerMaster
        PayerMasterDTO payerMasterDTO = payerMasterMapper.toDto(payerMaster);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(payerMasterDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll().collectList().block();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeCreate + 1);
        PayerMaster testPayerMaster = payerMasterList.get(payerMasterList.size() - 1);
        assertThat(testPayerMaster.getPayerId()).isEqualTo(DEFAULT_PAYER_ID);
        assertThat(testPayerMaster.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testPayerMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPayerMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPayerMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPayerMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPayerMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPayerMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPayerMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPayerMaster.getPayerMasterUuid()).isEqualTo(DEFAULT_PAYER_MASTER_UUID);
    }

    @Test
    void createPayerMasterWithExistingId() throws Exception {
        // Create the PayerMaster with an existing ID
        payerMaster.setPayerMasterId(1L);
        PayerMasterDTO payerMasterDTO = payerMasterMapper.toDto(payerMaster);

        int databaseSizeBeforeCreate = payerMasterRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(payerMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll().collectList().block();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPayerMasters() {
        // Initialize the database
        payerMasterRepository.save(payerMaster).block();

        // Get all the payerMasterList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=payerMasterId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].payerMasterId")
            .value(hasItem(payerMaster.getPayerMasterId().intValue()))
            .jsonPath("$.[*].payerId")
            .value(hasItem(DEFAULT_PAYER_ID))
            .jsonPath("$.[*].payerName")
            .value(hasItem(DEFAULT_PAYER_NAME))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].payerMasterUuid")
            .value(hasItem(DEFAULT_PAYER_MASTER_UUID.toString()));
    }

    @Test
    void getPayerMaster() {
        // Initialize the database
        payerMasterRepository.save(payerMaster).block();

        // Get the payerMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, payerMaster.getPayerMasterId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.payerMasterId")
            .value(is(payerMaster.getPayerMasterId().intValue()))
            .jsonPath("$.payerId")
            .value(is(DEFAULT_PAYER_ID))
            .jsonPath("$.payerName")
            .value(is(DEFAULT_PAYER_NAME))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.payerMasterUuid")
            .value(is(DEFAULT_PAYER_MASTER_UUID.toString()));
    }

    @Test
    void getNonExistingPayerMaster() {
        // Get the payerMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPayerMaster() throws Exception {
        // Initialize the database
        payerMasterRepository.save(payerMaster).block();

        int databaseSizeBeforeUpdate = payerMasterRepository.findAll().collectList().block().size();

        // Update the payerMaster
        PayerMaster updatedPayerMaster = payerMasterRepository.findById(payerMaster.getPayerMasterId()).block();
        updatedPayerMaster
            .payerId(UPDATED_PAYER_ID)
            .payerName(UPDATED_PAYER_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .payerMasterUuid(UPDATED_PAYER_MASTER_UUID);
        PayerMasterDTO payerMasterDTO = payerMasterMapper.toDto(updatedPayerMaster);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, payerMasterDTO.getPayerMasterId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(payerMasterDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll().collectList().block();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeUpdate);
        PayerMaster testPayerMaster = payerMasterList.get(payerMasterList.size() - 1);
        assertThat(testPayerMaster.getPayerId()).isEqualTo(UPDATED_PAYER_ID);
        assertThat(testPayerMaster.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testPayerMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPayerMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPayerMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPayerMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPayerMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPayerMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPayerMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPayerMaster.getPayerMasterUuid()).isEqualTo(UPDATED_PAYER_MASTER_UUID);
    }

    @Test
    void putNonExistingPayerMaster() throws Exception {
        int databaseSizeBeforeUpdate = payerMasterRepository.findAll().collectList().block().size();
        payerMaster.setPayerMasterId(count.incrementAndGet());

        // Create the PayerMaster
        PayerMasterDTO payerMasterDTO = payerMasterMapper.toDto(payerMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, payerMasterDTO.getPayerMasterId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(payerMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll().collectList().block();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPayerMaster() throws Exception {
        int databaseSizeBeforeUpdate = payerMasterRepository.findAll().collectList().block().size();
        payerMaster.setPayerMasterId(count.incrementAndGet());

        // Create the PayerMaster
        PayerMasterDTO payerMasterDTO = payerMasterMapper.toDto(payerMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(payerMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll().collectList().block();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPayerMaster() throws Exception {
        int databaseSizeBeforeUpdate = payerMasterRepository.findAll().collectList().block().size();
        payerMaster.setPayerMasterId(count.incrementAndGet());

        // Create the PayerMaster
        PayerMasterDTO payerMasterDTO = payerMasterMapper.toDto(payerMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(payerMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll().collectList().block();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePayerMasterWithPatch() throws Exception {
        // Initialize the database
        payerMasterRepository.save(payerMaster).block();

        int databaseSizeBeforeUpdate = payerMasterRepository.findAll().collectList().block().size();

        // Update the payerMaster using partial update
        PayerMaster partialUpdatedPayerMaster = new PayerMaster();
        partialUpdatedPayerMaster.setPayerMasterId(payerMaster.getPayerMasterId());

        partialUpdatedPayerMaster
            .payerId(UPDATED_PAYER_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .payerMasterUuid(UPDATED_PAYER_MASTER_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPayerMaster.getPayerMasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPayerMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll().collectList().block();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeUpdate);
        PayerMaster testPayerMaster = payerMasterList.get(payerMasterList.size() - 1);
        assertThat(testPayerMaster.getPayerId()).isEqualTo(UPDATED_PAYER_ID);
        assertThat(testPayerMaster.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testPayerMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPayerMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPayerMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPayerMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPayerMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPayerMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPayerMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPayerMaster.getPayerMasterUuid()).isEqualTo(UPDATED_PAYER_MASTER_UUID);
    }

    @Test
    void fullUpdatePayerMasterWithPatch() throws Exception {
        // Initialize the database
        payerMasterRepository.save(payerMaster).block();

        int databaseSizeBeforeUpdate = payerMasterRepository.findAll().collectList().block().size();

        // Update the payerMaster using partial update
        PayerMaster partialUpdatedPayerMaster = new PayerMaster();
        partialUpdatedPayerMaster.setPayerMasterId(payerMaster.getPayerMasterId());

        partialUpdatedPayerMaster
            .payerId(UPDATED_PAYER_ID)
            .payerName(UPDATED_PAYER_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .payerMasterUuid(UPDATED_PAYER_MASTER_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPayerMaster.getPayerMasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPayerMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll().collectList().block();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeUpdate);
        PayerMaster testPayerMaster = payerMasterList.get(payerMasterList.size() - 1);
        assertThat(testPayerMaster.getPayerId()).isEqualTo(UPDATED_PAYER_ID);
        assertThat(testPayerMaster.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testPayerMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPayerMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPayerMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPayerMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPayerMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPayerMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPayerMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPayerMaster.getPayerMasterUuid()).isEqualTo(UPDATED_PAYER_MASTER_UUID);
    }

    @Test
    void patchNonExistingPayerMaster() throws Exception {
        int databaseSizeBeforeUpdate = payerMasterRepository.findAll().collectList().block().size();
        payerMaster.setPayerMasterId(count.incrementAndGet());

        // Create the PayerMaster
        PayerMasterDTO payerMasterDTO = payerMasterMapper.toDto(payerMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, payerMasterDTO.getPayerMasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(payerMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll().collectList().block();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPayerMaster() throws Exception {
        int databaseSizeBeforeUpdate = payerMasterRepository.findAll().collectList().block().size();
        payerMaster.setPayerMasterId(count.incrementAndGet());

        // Create the PayerMaster
        PayerMasterDTO payerMasterDTO = payerMasterMapper.toDto(payerMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(payerMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll().collectList().block();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPayerMaster() throws Exception {
        int databaseSizeBeforeUpdate = payerMasterRepository.findAll().collectList().block().size();
        payerMaster.setPayerMasterId(count.incrementAndGet());

        // Create the PayerMaster
        PayerMasterDTO payerMasterDTO = payerMasterMapper.toDto(payerMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(payerMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll().collectList().block();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePayerMaster() {
        // Initialize the database
        payerMasterRepository.save(payerMaster).block();

        int databaseSizeBeforeDelete = payerMasterRepository.findAll().collectList().block().size();

        // Delete the payerMaster
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, payerMaster.getPayerMasterId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll().collectList().block();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
