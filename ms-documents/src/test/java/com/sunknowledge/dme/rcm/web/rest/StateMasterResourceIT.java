package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.StateMaster;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.StateMasterRepository;
import java.time.Duration;
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
 * Integration tests for the {@link StateMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class StateMasterResourceIT {

    private static final String DEFAULT_STATE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_STATE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STATE_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/state-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{stateId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StateMasterRepository stateMasterRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private StateMaster stateMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StateMaster createEntity(EntityManager em) {
        StateMaster stateMaster = new StateMaster().stateCode(DEFAULT_STATE_CODE).stateName(DEFAULT_STATE_NAME);
        return stateMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StateMaster createUpdatedEntity(EntityManager em) {
        StateMaster stateMaster = new StateMaster().stateCode(UPDATED_STATE_CODE).stateName(UPDATED_STATE_NAME);
        return stateMaster;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(StateMaster.class).block();
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
        stateMaster = createEntity(em);
    }

    @Test
    void createStateMaster() throws Exception {
        int databaseSizeBeforeCreate = stateMasterRepository.findAll().collectList().block().size();
        // Create the StateMaster
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(stateMaster))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the StateMaster in the database
        List<StateMaster> stateMasterList = stateMasterRepository.findAll().collectList().block();
        assertThat(stateMasterList).hasSize(databaseSizeBeforeCreate + 1);
        StateMaster testStateMaster = stateMasterList.get(stateMasterList.size() - 1);
        assertThat(testStateMaster.getStateCode()).isEqualTo(DEFAULT_STATE_CODE);
        assertThat(testStateMaster.getStateName()).isEqualTo(DEFAULT_STATE_NAME);
    }

    @Test
    void createStateMasterWithExistingId() throws Exception {
        // Create the StateMaster with an existing ID
        stateMaster.setStateId(1L);

        int databaseSizeBeforeCreate = stateMasterRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(stateMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the StateMaster in the database
        List<StateMaster> stateMasterList = stateMasterRepository.findAll().collectList().block();
        assertThat(stateMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllStateMastersAsStream() {
        // Initialize the database
        stateMasterRepository.save(stateMaster).block();

        List<StateMaster> stateMasterList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(StateMaster.class)
            .getResponseBody()
            .filter(stateMaster::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(stateMasterList).isNotNull();
        assertThat(stateMasterList).hasSize(1);
        StateMaster testStateMaster = stateMasterList.get(0);
        assertThat(testStateMaster.getStateCode()).isEqualTo(DEFAULT_STATE_CODE);
        assertThat(testStateMaster.getStateName()).isEqualTo(DEFAULT_STATE_NAME);
    }

    @Test
    void getAllStateMasters() {
        // Initialize the database
        stateMasterRepository.save(stateMaster).block();

        // Get all the stateMasterList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=stateId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].stateId")
            .value(hasItem(stateMaster.getStateId().intValue()))
            .jsonPath("$.[*].stateCode")
            .value(hasItem(DEFAULT_STATE_CODE))
            .jsonPath("$.[*].stateName")
            .value(hasItem(DEFAULT_STATE_NAME));
    }

    @Test
    void getStateMaster() {
        // Initialize the database
        stateMasterRepository.save(stateMaster).block();

        // Get the stateMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, stateMaster.getStateId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.stateId")
            .value(is(stateMaster.getStateId().intValue()))
            .jsonPath("$.stateCode")
            .value(is(DEFAULT_STATE_CODE))
            .jsonPath("$.stateName")
            .value(is(DEFAULT_STATE_NAME));
    }

    @Test
    void getNonExistingStateMaster() {
        // Get the stateMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewStateMaster() throws Exception {
        // Initialize the database
        stateMasterRepository.save(stateMaster).block();

        int databaseSizeBeforeUpdate = stateMasterRepository.findAll().collectList().block().size();

        // Update the stateMaster
        StateMaster updatedStateMaster = stateMasterRepository.findById(stateMaster.getStateId()).block();
        updatedStateMaster.stateCode(UPDATED_STATE_CODE).stateName(UPDATED_STATE_NAME);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedStateMaster.getStateId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedStateMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the StateMaster in the database
        List<StateMaster> stateMasterList = stateMasterRepository.findAll().collectList().block();
        assertThat(stateMasterList).hasSize(databaseSizeBeforeUpdate);
        StateMaster testStateMaster = stateMasterList.get(stateMasterList.size() - 1);
        assertThat(testStateMaster.getStateCode()).isEqualTo(UPDATED_STATE_CODE);
        assertThat(testStateMaster.getStateName()).isEqualTo(UPDATED_STATE_NAME);
    }

    @Test
    void putNonExistingStateMaster() throws Exception {
        int databaseSizeBeforeUpdate = stateMasterRepository.findAll().collectList().block().size();
        stateMaster.setStateId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, stateMaster.getStateId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(stateMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the StateMaster in the database
        List<StateMaster> stateMasterList = stateMasterRepository.findAll().collectList().block();
        assertThat(stateMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchStateMaster() throws Exception {
        int databaseSizeBeforeUpdate = stateMasterRepository.findAll().collectList().block().size();
        stateMaster.setStateId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(stateMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the StateMaster in the database
        List<StateMaster> stateMasterList = stateMasterRepository.findAll().collectList().block();
        assertThat(stateMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamStateMaster() throws Exception {
        int databaseSizeBeforeUpdate = stateMasterRepository.findAll().collectList().block().size();
        stateMaster.setStateId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(stateMaster))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the StateMaster in the database
        List<StateMaster> stateMasterList = stateMasterRepository.findAll().collectList().block();
        assertThat(stateMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateStateMasterWithPatch() throws Exception {
        // Initialize the database
        stateMasterRepository.save(stateMaster).block();

        int databaseSizeBeforeUpdate = stateMasterRepository.findAll().collectList().block().size();

        // Update the stateMaster using partial update
        StateMaster partialUpdatedStateMaster = new StateMaster();
        partialUpdatedStateMaster.setStateId(stateMaster.getStateId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedStateMaster.getStateId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedStateMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the StateMaster in the database
        List<StateMaster> stateMasterList = stateMasterRepository.findAll().collectList().block();
        assertThat(stateMasterList).hasSize(databaseSizeBeforeUpdate);
        StateMaster testStateMaster = stateMasterList.get(stateMasterList.size() - 1);
        assertThat(testStateMaster.getStateCode()).isEqualTo(DEFAULT_STATE_CODE);
        assertThat(testStateMaster.getStateName()).isEqualTo(DEFAULT_STATE_NAME);
    }

    @Test
    void fullUpdateStateMasterWithPatch() throws Exception {
        // Initialize the database
        stateMasterRepository.save(stateMaster).block();

        int databaseSizeBeforeUpdate = stateMasterRepository.findAll().collectList().block().size();

        // Update the stateMaster using partial update
        StateMaster partialUpdatedStateMaster = new StateMaster();
        partialUpdatedStateMaster.setStateId(stateMaster.getStateId());

        partialUpdatedStateMaster.stateCode(UPDATED_STATE_CODE).stateName(UPDATED_STATE_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedStateMaster.getStateId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedStateMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the StateMaster in the database
        List<StateMaster> stateMasterList = stateMasterRepository.findAll().collectList().block();
        assertThat(stateMasterList).hasSize(databaseSizeBeforeUpdate);
        StateMaster testStateMaster = stateMasterList.get(stateMasterList.size() - 1);
        assertThat(testStateMaster.getStateCode()).isEqualTo(UPDATED_STATE_CODE);
        assertThat(testStateMaster.getStateName()).isEqualTo(UPDATED_STATE_NAME);
    }

    @Test
    void patchNonExistingStateMaster() throws Exception {
        int databaseSizeBeforeUpdate = stateMasterRepository.findAll().collectList().block().size();
        stateMaster.setStateId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, stateMaster.getStateId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(stateMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the StateMaster in the database
        List<StateMaster> stateMasterList = stateMasterRepository.findAll().collectList().block();
        assertThat(stateMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchStateMaster() throws Exception {
        int databaseSizeBeforeUpdate = stateMasterRepository.findAll().collectList().block().size();
        stateMaster.setStateId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(stateMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the StateMaster in the database
        List<StateMaster> stateMasterList = stateMasterRepository.findAll().collectList().block();
        assertThat(stateMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamStateMaster() throws Exception {
        int databaseSizeBeforeUpdate = stateMasterRepository.findAll().collectList().block().size();
        stateMaster.setStateId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(stateMaster))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the StateMaster in the database
        List<StateMaster> stateMasterList = stateMasterRepository.findAll().collectList().block();
        assertThat(stateMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteStateMaster() {
        // Initialize the database
        stateMasterRepository.save(stateMaster).block();

        int databaseSizeBeforeDelete = stateMasterRepository.findAll().collectList().block().size();

        // Delete the stateMaster
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, stateMaster.getStateId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<StateMaster> stateMasterList = stateMasterRepository.findAll().collectList().block();
        assertThat(stateMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
