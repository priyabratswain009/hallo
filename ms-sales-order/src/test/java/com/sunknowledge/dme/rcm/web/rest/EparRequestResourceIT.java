package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.EparRequest;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.EparRequestRepository;
import com.sunknowledge.dme.rcm.service.dto.EparRequestDTO;
import com.sunknowledge.dme.rcm.service.mapper.EparRequestMapper;
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
 * Integration tests for the {@link EparRequestResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class EparRequestResourceIT {

    private static final Long DEFAULT_SO_ID = 1L;
    private static final Long UPDATED_SO_ID = 2L;

    private static final String DEFAULT_SO_NO = "AAAAAAAAAA";
    private static final String UPDATED_SO_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_PAR_ID = 1L;
    private static final Long UPDATED_PAR_ID = 2L;

    private static final String DEFAULT_PAR_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAR_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_REQUEST_DATETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REQUEST_DATETIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RESPONSE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_URL = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_JSON = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_JSON = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_EPA_REQUEST_UUID = UUID.randomUUID();
    private static final UUID UPDATED_EPA_REQUEST_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/epar-requests";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{eparRequestId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EparRequestRepository eparRequestRepository;

    @Autowired
    private EparRequestMapper eparRequestMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private EparRequest eparRequest;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EparRequest createEntity(EntityManager em) {
        EparRequest eparRequest = new EparRequest()
            .soId(DEFAULT_SO_ID)
            .soNo(DEFAULT_SO_NO)
            .parId(DEFAULT_PAR_ID)
            .parNo(DEFAULT_PAR_NO)
            .requestDatetime(DEFAULT_REQUEST_DATETIME)
            .responseStatus(DEFAULT_RESPONSE_STATUS)
            .responseUrl(DEFAULT_RESPONSE_URL)
            .requestJson(DEFAULT_REQUEST_JSON)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .epaRequestUuid(DEFAULT_EPA_REQUEST_UUID);
        return eparRequest;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EparRequest createUpdatedEntity(EntityManager em) {
        EparRequest eparRequest = new EparRequest()
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .parId(UPDATED_PAR_ID)
            .parNo(UPDATED_PAR_NO)
            .requestDatetime(UPDATED_REQUEST_DATETIME)
            .responseStatus(UPDATED_RESPONSE_STATUS)
            .responseUrl(UPDATED_RESPONSE_URL)
            .requestJson(UPDATED_REQUEST_JSON)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .epaRequestUuid(UPDATED_EPA_REQUEST_UUID);
        return eparRequest;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(EparRequest.class).block();
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
        eparRequest = createEntity(em);
    }

    @Test
    void createEparRequest() throws Exception {
        int databaseSizeBeforeCreate = eparRequestRepository.findAll().collectList().block().size();
        // Create the EparRequest
        EparRequestDTO eparRequestDTO = eparRequestMapper.toDto(eparRequest);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(eparRequestDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the EparRequest in the database
        List<EparRequest> eparRequestList = eparRequestRepository.findAll().collectList().block();
        assertThat(eparRequestList).hasSize(databaseSizeBeforeCreate + 1);
        EparRequest testEparRequest = eparRequestList.get(eparRequestList.size() - 1);
        assertThat(testEparRequest.getSoId()).isEqualTo(DEFAULT_SO_ID);
        assertThat(testEparRequest.getSoNo()).isEqualTo(DEFAULT_SO_NO);
        assertThat(testEparRequest.getParId()).isEqualTo(DEFAULT_PAR_ID);
        assertThat(testEparRequest.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testEparRequest.getRequestDatetime()).isEqualTo(DEFAULT_REQUEST_DATETIME);
        assertThat(testEparRequest.getResponseStatus()).isEqualTo(DEFAULT_RESPONSE_STATUS);
        assertThat(testEparRequest.getResponseUrl()).isEqualTo(DEFAULT_RESPONSE_URL);
        assertThat(testEparRequest.getRequestJson()).isEqualTo(DEFAULT_REQUEST_JSON);
        assertThat(testEparRequest.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEparRequest.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testEparRequest.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testEparRequest.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testEparRequest.getEpaRequestUuid()).isEqualTo(DEFAULT_EPA_REQUEST_UUID);
    }

    @Test
    void createEparRequestWithExistingId() throws Exception {
        // Create the EparRequest with an existing ID
        eparRequest.setEparRequestId(1L);
        EparRequestDTO eparRequestDTO = eparRequestMapper.toDto(eparRequest);

        int databaseSizeBeforeCreate = eparRequestRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(eparRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EparRequest in the database
        List<EparRequest> eparRequestList = eparRequestRepository.findAll().collectList().block();
        assertThat(eparRequestList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllEparRequests() {
        // Initialize the database
        eparRequestRepository.save(eparRequest).block();

        // Get all the eparRequestList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=eparRequestId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].eparRequestId")
            .value(hasItem(eparRequest.getEparRequestId().intValue()))
            .jsonPath("$.[*].soId")
            .value(hasItem(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.[*].soNo")
            .value(hasItem(DEFAULT_SO_NO))
            .jsonPath("$.[*].parId")
            .value(hasItem(DEFAULT_PAR_ID.intValue()))
            .jsonPath("$.[*].parNo")
            .value(hasItem(DEFAULT_PAR_NO))
            .jsonPath("$.[*].requestDatetime")
            .value(hasItem(DEFAULT_REQUEST_DATETIME.toString()))
            .jsonPath("$.[*].responseStatus")
            .value(hasItem(DEFAULT_RESPONSE_STATUS))
            .jsonPath("$.[*].responseUrl")
            .value(hasItem(DEFAULT_RESPONSE_URL))
            .jsonPath("$.[*].requestJson")
            .value(hasItem(DEFAULT_REQUEST_JSON))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].epaRequestUuid")
            .value(hasItem(DEFAULT_EPA_REQUEST_UUID.toString()));
    }

    @Test
    void getEparRequest() {
        // Initialize the database
        eparRequestRepository.save(eparRequest).block();

        // Get the eparRequest
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, eparRequest.getEparRequestId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.eparRequestId")
            .value(is(eparRequest.getEparRequestId().intValue()))
            .jsonPath("$.soId")
            .value(is(DEFAULT_SO_ID.intValue()))
            .jsonPath("$.soNo")
            .value(is(DEFAULT_SO_NO))
            .jsonPath("$.parId")
            .value(is(DEFAULT_PAR_ID.intValue()))
            .jsonPath("$.parNo")
            .value(is(DEFAULT_PAR_NO))
            .jsonPath("$.requestDatetime")
            .value(is(DEFAULT_REQUEST_DATETIME.toString()))
            .jsonPath("$.responseStatus")
            .value(is(DEFAULT_RESPONSE_STATUS))
            .jsonPath("$.responseUrl")
            .value(is(DEFAULT_RESPONSE_URL))
            .jsonPath("$.requestJson")
            .value(is(DEFAULT_REQUEST_JSON))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.epaRequestUuid")
            .value(is(DEFAULT_EPA_REQUEST_UUID.toString()));
    }

    @Test
    void getNonExistingEparRequest() {
        // Get the eparRequest
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingEparRequest() throws Exception {
        // Initialize the database
        eparRequestRepository.save(eparRequest).block();

        int databaseSizeBeforeUpdate = eparRequestRepository.findAll().collectList().block().size();

        // Update the eparRequest
        EparRequest updatedEparRequest = eparRequestRepository.findById(eparRequest.getEparRequestId()).block();
        updatedEparRequest
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .parId(UPDATED_PAR_ID)
            .parNo(UPDATED_PAR_NO)
            .requestDatetime(UPDATED_REQUEST_DATETIME)
            .responseStatus(UPDATED_RESPONSE_STATUS)
            .responseUrl(UPDATED_RESPONSE_URL)
            .requestJson(UPDATED_REQUEST_JSON)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .epaRequestUuid(UPDATED_EPA_REQUEST_UUID);
        EparRequestDTO eparRequestDTO = eparRequestMapper.toDto(updatedEparRequest);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, eparRequestDTO.getEparRequestId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(eparRequestDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the EparRequest in the database
        List<EparRequest> eparRequestList = eparRequestRepository.findAll().collectList().block();
        assertThat(eparRequestList).hasSize(databaseSizeBeforeUpdate);
        EparRequest testEparRequest = eparRequestList.get(eparRequestList.size() - 1);
        assertThat(testEparRequest.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testEparRequest.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testEparRequest.getParId()).isEqualTo(UPDATED_PAR_ID);
        assertThat(testEparRequest.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testEparRequest.getRequestDatetime()).isEqualTo(UPDATED_REQUEST_DATETIME);
        assertThat(testEparRequest.getResponseStatus()).isEqualTo(UPDATED_RESPONSE_STATUS);
        assertThat(testEparRequest.getResponseUrl()).isEqualTo(UPDATED_RESPONSE_URL);
        assertThat(testEparRequest.getRequestJson()).isEqualTo(UPDATED_REQUEST_JSON);
        assertThat(testEparRequest.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEparRequest.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testEparRequest.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testEparRequest.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testEparRequest.getEpaRequestUuid()).isEqualTo(UPDATED_EPA_REQUEST_UUID);
    }

    @Test
    void putNonExistingEparRequest() throws Exception {
        int databaseSizeBeforeUpdate = eparRequestRepository.findAll().collectList().block().size();
        eparRequest.setEparRequestId(count.incrementAndGet());

        // Create the EparRequest
        EparRequestDTO eparRequestDTO = eparRequestMapper.toDto(eparRequest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, eparRequestDTO.getEparRequestId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(eparRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EparRequest in the database
        List<EparRequest> eparRequestList = eparRequestRepository.findAll().collectList().block();
        assertThat(eparRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchEparRequest() throws Exception {
        int databaseSizeBeforeUpdate = eparRequestRepository.findAll().collectList().block().size();
        eparRequest.setEparRequestId(count.incrementAndGet());

        // Create the EparRequest
        EparRequestDTO eparRequestDTO = eparRequestMapper.toDto(eparRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(eparRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EparRequest in the database
        List<EparRequest> eparRequestList = eparRequestRepository.findAll().collectList().block();
        assertThat(eparRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamEparRequest() throws Exception {
        int databaseSizeBeforeUpdate = eparRequestRepository.findAll().collectList().block().size();
        eparRequest.setEparRequestId(count.incrementAndGet());

        // Create the EparRequest
        EparRequestDTO eparRequestDTO = eparRequestMapper.toDto(eparRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(eparRequestDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the EparRequest in the database
        List<EparRequest> eparRequestList = eparRequestRepository.findAll().collectList().block();
        assertThat(eparRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateEparRequestWithPatch() throws Exception {
        // Initialize the database
        eparRequestRepository.save(eparRequest).block();

        int databaseSizeBeforeUpdate = eparRequestRepository.findAll().collectList().block().size();

        // Update the eparRequest using partial update
        EparRequest partialUpdatedEparRequest = new EparRequest();
        partialUpdatedEparRequest.setEparRequestId(eparRequest.getEparRequestId());

        partialUpdatedEparRequest
            .soId(UPDATED_SO_ID)
            .parId(UPDATED_PAR_ID)
            .responseUrl(UPDATED_RESPONSE_URL)
            .requestJson(UPDATED_REQUEST_JSON)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedEparRequest.getEparRequestId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedEparRequest))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the EparRequest in the database
        List<EparRequest> eparRequestList = eparRequestRepository.findAll().collectList().block();
        assertThat(eparRequestList).hasSize(databaseSizeBeforeUpdate);
        EparRequest testEparRequest = eparRequestList.get(eparRequestList.size() - 1);
        assertThat(testEparRequest.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testEparRequest.getSoNo()).isEqualTo(DEFAULT_SO_NO);
        assertThat(testEparRequest.getParId()).isEqualTo(UPDATED_PAR_ID);
        assertThat(testEparRequest.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testEparRequest.getRequestDatetime()).isEqualTo(DEFAULT_REQUEST_DATETIME);
        assertThat(testEparRequest.getResponseStatus()).isEqualTo(DEFAULT_RESPONSE_STATUS);
        assertThat(testEparRequest.getResponseUrl()).isEqualTo(UPDATED_RESPONSE_URL);
        assertThat(testEparRequest.getRequestJson()).isEqualTo(UPDATED_REQUEST_JSON);
        assertThat(testEparRequest.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEparRequest.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testEparRequest.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testEparRequest.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testEparRequest.getEpaRequestUuid()).isEqualTo(DEFAULT_EPA_REQUEST_UUID);
    }

    @Test
    void fullUpdateEparRequestWithPatch() throws Exception {
        // Initialize the database
        eparRequestRepository.save(eparRequest).block();

        int databaseSizeBeforeUpdate = eparRequestRepository.findAll().collectList().block().size();

        // Update the eparRequest using partial update
        EparRequest partialUpdatedEparRequest = new EparRequest();
        partialUpdatedEparRequest.setEparRequestId(eparRequest.getEparRequestId());

        partialUpdatedEparRequest
            .soId(UPDATED_SO_ID)
            .soNo(UPDATED_SO_NO)
            .parId(UPDATED_PAR_ID)
            .parNo(UPDATED_PAR_NO)
            .requestDatetime(UPDATED_REQUEST_DATETIME)
            .responseStatus(UPDATED_RESPONSE_STATUS)
            .responseUrl(UPDATED_RESPONSE_URL)
            .requestJson(UPDATED_REQUEST_JSON)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .epaRequestUuid(UPDATED_EPA_REQUEST_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedEparRequest.getEparRequestId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedEparRequest))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the EparRequest in the database
        List<EparRequest> eparRequestList = eparRequestRepository.findAll().collectList().block();
        assertThat(eparRequestList).hasSize(databaseSizeBeforeUpdate);
        EparRequest testEparRequest = eparRequestList.get(eparRequestList.size() - 1);
        assertThat(testEparRequest.getSoId()).isEqualTo(UPDATED_SO_ID);
        assertThat(testEparRequest.getSoNo()).isEqualTo(UPDATED_SO_NO);
        assertThat(testEparRequest.getParId()).isEqualTo(UPDATED_PAR_ID);
        assertThat(testEparRequest.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testEparRequest.getRequestDatetime()).isEqualTo(UPDATED_REQUEST_DATETIME);
        assertThat(testEparRequest.getResponseStatus()).isEqualTo(UPDATED_RESPONSE_STATUS);
        assertThat(testEparRequest.getResponseUrl()).isEqualTo(UPDATED_RESPONSE_URL);
        assertThat(testEparRequest.getRequestJson()).isEqualTo(UPDATED_REQUEST_JSON);
        assertThat(testEparRequest.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEparRequest.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testEparRequest.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testEparRequest.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testEparRequest.getEpaRequestUuid()).isEqualTo(UPDATED_EPA_REQUEST_UUID);
    }

    @Test
    void patchNonExistingEparRequest() throws Exception {
        int databaseSizeBeforeUpdate = eparRequestRepository.findAll().collectList().block().size();
        eparRequest.setEparRequestId(count.incrementAndGet());

        // Create the EparRequest
        EparRequestDTO eparRequestDTO = eparRequestMapper.toDto(eparRequest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, eparRequestDTO.getEparRequestId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(eparRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EparRequest in the database
        List<EparRequest> eparRequestList = eparRequestRepository.findAll().collectList().block();
        assertThat(eparRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchEparRequest() throws Exception {
        int databaseSizeBeforeUpdate = eparRequestRepository.findAll().collectList().block().size();
        eparRequest.setEparRequestId(count.incrementAndGet());

        // Create the EparRequest
        EparRequestDTO eparRequestDTO = eparRequestMapper.toDto(eparRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(eparRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EparRequest in the database
        List<EparRequest> eparRequestList = eparRequestRepository.findAll().collectList().block();
        assertThat(eparRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamEparRequest() throws Exception {
        int databaseSizeBeforeUpdate = eparRequestRepository.findAll().collectList().block().size();
        eparRequest.setEparRequestId(count.incrementAndGet());

        // Create the EparRequest
        EparRequestDTO eparRequestDTO = eparRequestMapper.toDto(eparRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(eparRequestDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the EparRequest in the database
        List<EparRequest> eparRequestList = eparRequestRepository.findAll().collectList().block();
        assertThat(eparRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteEparRequest() {
        // Initialize the database
        eparRequestRepository.save(eparRequest).block();

        int databaseSizeBeforeDelete = eparRequestRepository.findAll().collectList().block().size();

        // Delete the eparRequest
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, eparRequest.getEparRequestId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<EparRequest> eparRequestList = eparRequestRepository.findAll().collectList().block();
        assertThat(eparRequestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
