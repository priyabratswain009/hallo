package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.FunctionalAbility;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.FunctionalAbilityRepository;
import com.sunknowledge.dme.rcm.service.dto.FunctionalAbilityDTO;
import com.sunknowledge.dme.rcm.service.mapper.FunctionalAbilityMapper;
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
 * Integration tests for the {@link FunctionalAbilityResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class FunctionalAbilityResourceIT {

    private static final String DEFAULT_FUNCTIONAL_ABILITY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTIONAL_ABILITY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCTIONAL_ABILITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTIONAL_ABILITY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_FUNCTIONAL_ABILITY_UUID = UUID.randomUUID();
    private static final UUID UPDATED_FUNCTIONAL_ABILITY_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/functional-abilities";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{functionalAbilityId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FunctionalAbilityRepository functionalAbilityRepository;

    @Autowired
    private FunctionalAbilityMapper functionalAbilityMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private FunctionalAbility functionalAbility;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FunctionalAbility createEntity(EntityManager em) {
        FunctionalAbility functionalAbility = new FunctionalAbility()
            .functionalAbilityCode(DEFAULT_FUNCTIONAL_ABILITY_CODE)
            .functionalAbilityName(DEFAULT_FUNCTIONAL_ABILITY_NAME)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .functionalAbilityUuid(DEFAULT_FUNCTIONAL_ABILITY_UUID);
        return functionalAbility;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FunctionalAbility createUpdatedEntity(EntityManager em) {
        FunctionalAbility functionalAbility = new FunctionalAbility()
            .functionalAbilityCode(UPDATED_FUNCTIONAL_ABILITY_CODE)
            .functionalAbilityName(UPDATED_FUNCTIONAL_ABILITY_NAME)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .functionalAbilityUuid(UPDATED_FUNCTIONAL_ABILITY_UUID);
        return functionalAbility;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(FunctionalAbility.class).block();
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
        functionalAbility = createEntity(em);
    }

    @Test
    void createFunctionalAbility() throws Exception {
        int databaseSizeBeforeCreate = functionalAbilityRepository.findAll().collectList().block().size();
        // Create the FunctionalAbility
        FunctionalAbilityDTO functionalAbilityDTO = functionalAbilityMapper.toDto(functionalAbility);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(functionalAbilityDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the FunctionalAbility in the database
        List<FunctionalAbility> functionalAbilityList = functionalAbilityRepository.findAll().collectList().block();
        assertThat(functionalAbilityList).hasSize(databaseSizeBeforeCreate + 1);
        FunctionalAbility testFunctionalAbility = functionalAbilityList.get(functionalAbilityList.size() - 1);
        assertThat(testFunctionalAbility.getFunctionalAbilityCode()).isEqualTo(DEFAULT_FUNCTIONAL_ABILITY_CODE);
        assertThat(testFunctionalAbility.getFunctionalAbilityName()).isEqualTo(DEFAULT_FUNCTIONAL_ABILITY_NAME);
        assertThat(testFunctionalAbility.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testFunctionalAbility.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFunctionalAbility.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testFunctionalAbility.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFunctionalAbility.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testFunctionalAbility.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testFunctionalAbility.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testFunctionalAbility.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testFunctionalAbility.getFunctionalAbilityUuid()).isEqualTo(DEFAULT_FUNCTIONAL_ABILITY_UUID);
    }

    @Test
    void createFunctionalAbilityWithExistingId() throws Exception {
        // Create the FunctionalAbility with an existing ID
        functionalAbility.setFunctionalAbilityId(1L);
        FunctionalAbilityDTO functionalAbilityDTO = functionalAbilityMapper.toDto(functionalAbility);

        int databaseSizeBeforeCreate = functionalAbilityRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(functionalAbilityDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FunctionalAbility in the database
        List<FunctionalAbility> functionalAbilityList = functionalAbilityRepository.findAll().collectList().block();
        assertThat(functionalAbilityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllFunctionalAbilities() {
        // Initialize the database
        functionalAbilityRepository.save(functionalAbility).block();

        // Get all the functionalAbilityList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=functionalAbilityId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].functionalAbilityId")
            .value(hasItem(functionalAbility.getFunctionalAbilityId().intValue()))
            .jsonPath("$.[*].functionalAbilityCode")
            .value(hasItem(DEFAULT_FUNCTIONAL_ABILITY_CODE))
            .jsonPath("$.[*].functionalAbilityName")
            .value(hasItem(DEFAULT_FUNCTIONAL_ABILITY_NAME))
            .jsonPath("$.[*].description")
            .value(hasItem(DEFAULT_DESCRIPTION))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].functionalAbilityUuid")
            .value(hasItem(DEFAULT_FUNCTIONAL_ABILITY_UUID.toString()));
    }

    @Test
    void getFunctionalAbility() {
        // Initialize the database
        functionalAbilityRepository.save(functionalAbility).block();

        // Get the functionalAbility
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, functionalAbility.getFunctionalAbilityId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.functionalAbilityId")
            .value(is(functionalAbility.getFunctionalAbilityId().intValue()))
            .jsonPath("$.functionalAbilityCode")
            .value(is(DEFAULT_FUNCTIONAL_ABILITY_CODE))
            .jsonPath("$.functionalAbilityName")
            .value(is(DEFAULT_FUNCTIONAL_ABILITY_NAME))
            .jsonPath("$.description")
            .value(is(DEFAULT_DESCRIPTION))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.functionalAbilityUuid")
            .value(is(DEFAULT_FUNCTIONAL_ABILITY_UUID.toString()));
    }

    @Test
    void getNonExistingFunctionalAbility() {
        // Get the functionalAbility
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingFunctionalAbility() throws Exception {
        // Initialize the database
        functionalAbilityRepository.save(functionalAbility).block();

        int databaseSizeBeforeUpdate = functionalAbilityRepository.findAll().collectList().block().size();

        // Update the functionalAbility
        FunctionalAbility updatedFunctionalAbility = functionalAbilityRepository
            .findById(functionalAbility.getFunctionalAbilityId())
            .block();
        updatedFunctionalAbility
            .functionalAbilityCode(UPDATED_FUNCTIONAL_ABILITY_CODE)
            .functionalAbilityName(UPDATED_FUNCTIONAL_ABILITY_NAME)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .functionalAbilityUuid(UPDATED_FUNCTIONAL_ABILITY_UUID);
        FunctionalAbilityDTO functionalAbilityDTO = functionalAbilityMapper.toDto(updatedFunctionalAbility);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, functionalAbilityDTO.getFunctionalAbilityId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(functionalAbilityDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FunctionalAbility in the database
        List<FunctionalAbility> functionalAbilityList = functionalAbilityRepository.findAll().collectList().block();
        assertThat(functionalAbilityList).hasSize(databaseSizeBeforeUpdate);
        FunctionalAbility testFunctionalAbility = functionalAbilityList.get(functionalAbilityList.size() - 1);
        assertThat(testFunctionalAbility.getFunctionalAbilityCode()).isEqualTo(UPDATED_FUNCTIONAL_ABILITY_CODE);
        assertThat(testFunctionalAbility.getFunctionalAbilityName()).isEqualTo(UPDATED_FUNCTIONAL_ABILITY_NAME);
        assertThat(testFunctionalAbility.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testFunctionalAbility.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFunctionalAbility.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testFunctionalAbility.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFunctionalAbility.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testFunctionalAbility.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testFunctionalAbility.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testFunctionalAbility.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testFunctionalAbility.getFunctionalAbilityUuid()).isEqualTo(UPDATED_FUNCTIONAL_ABILITY_UUID);
    }

    @Test
    void putNonExistingFunctionalAbility() throws Exception {
        int databaseSizeBeforeUpdate = functionalAbilityRepository.findAll().collectList().block().size();
        functionalAbility.setFunctionalAbilityId(count.incrementAndGet());

        // Create the FunctionalAbility
        FunctionalAbilityDTO functionalAbilityDTO = functionalAbilityMapper.toDto(functionalAbility);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, functionalAbilityDTO.getFunctionalAbilityId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(functionalAbilityDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FunctionalAbility in the database
        List<FunctionalAbility> functionalAbilityList = functionalAbilityRepository.findAll().collectList().block();
        assertThat(functionalAbilityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchFunctionalAbility() throws Exception {
        int databaseSizeBeforeUpdate = functionalAbilityRepository.findAll().collectList().block().size();
        functionalAbility.setFunctionalAbilityId(count.incrementAndGet());

        // Create the FunctionalAbility
        FunctionalAbilityDTO functionalAbilityDTO = functionalAbilityMapper.toDto(functionalAbility);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(functionalAbilityDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FunctionalAbility in the database
        List<FunctionalAbility> functionalAbilityList = functionalAbilityRepository.findAll().collectList().block();
        assertThat(functionalAbilityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamFunctionalAbility() throws Exception {
        int databaseSizeBeforeUpdate = functionalAbilityRepository.findAll().collectList().block().size();
        functionalAbility.setFunctionalAbilityId(count.incrementAndGet());

        // Create the FunctionalAbility
        FunctionalAbilityDTO functionalAbilityDTO = functionalAbilityMapper.toDto(functionalAbility);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(functionalAbilityDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the FunctionalAbility in the database
        List<FunctionalAbility> functionalAbilityList = functionalAbilityRepository.findAll().collectList().block();
        assertThat(functionalAbilityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateFunctionalAbilityWithPatch() throws Exception {
        // Initialize the database
        functionalAbilityRepository.save(functionalAbility).block();

        int databaseSizeBeforeUpdate = functionalAbilityRepository.findAll().collectList().block().size();

        // Update the functionalAbility using partial update
        FunctionalAbility partialUpdatedFunctionalAbility = new FunctionalAbility();
        partialUpdatedFunctionalAbility.setFunctionalAbilityId(functionalAbility.getFunctionalAbilityId());

        partialUpdatedFunctionalAbility
            .description(UPDATED_DESCRIPTION)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .functionalAbilityUuid(UPDATED_FUNCTIONAL_ABILITY_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedFunctionalAbility.getFunctionalAbilityId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedFunctionalAbility))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FunctionalAbility in the database
        List<FunctionalAbility> functionalAbilityList = functionalAbilityRepository.findAll().collectList().block();
        assertThat(functionalAbilityList).hasSize(databaseSizeBeforeUpdate);
        FunctionalAbility testFunctionalAbility = functionalAbilityList.get(functionalAbilityList.size() - 1);
        assertThat(testFunctionalAbility.getFunctionalAbilityCode()).isEqualTo(DEFAULT_FUNCTIONAL_ABILITY_CODE);
        assertThat(testFunctionalAbility.getFunctionalAbilityName()).isEqualTo(DEFAULT_FUNCTIONAL_ABILITY_NAME);
        assertThat(testFunctionalAbility.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testFunctionalAbility.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFunctionalAbility.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testFunctionalAbility.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFunctionalAbility.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testFunctionalAbility.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testFunctionalAbility.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testFunctionalAbility.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testFunctionalAbility.getFunctionalAbilityUuid()).isEqualTo(UPDATED_FUNCTIONAL_ABILITY_UUID);
    }

    @Test
    void fullUpdateFunctionalAbilityWithPatch() throws Exception {
        // Initialize the database
        functionalAbilityRepository.save(functionalAbility).block();

        int databaseSizeBeforeUpdate = functionalAbilityRepository.findAll().collectList().block().size();

        // Update the functionalAbility using partial update
        FunctionalAbility partialUpdatedFunctionalAbility = new FunctionalAbility();
        partialUpdatedFunctionalAbility.setFunctionalAbilityId(functionalAbility.getFunctionalAbilityId());

        partialUpdatedFunctionalAbility
            .functionalAbilityCode(UPDATED_FUNCTIONAL_ABILITY_CODE)
            .functionalAbilityName(UPDATED_FUNCTIONAL_ABILITY_NAME)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .functionalAbilityUuid(UPDATED_FUNCTIONAL_ABILITY_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedFunctionalAbility.getFunctionalAbilityId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedFunctionalAbility))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FunctionalAbility in the database
        List<FunctionalAbility> functionalAbilityList = functionalAbilityRepository.findAll().collectList().block();
        assertThat(functionalAbilityList).hasSize(databaseSizeBeforeUpdate);
        FunctionalAbility testFunctionalAbility = functionalAbilityList.get(functionalAbilityList.size() - 1);
        assertThat(testFunctionalAbility.getFunctionalAbilityCode()).isEqualTo(UPDATED_FUNCTIONAL_ABILITY_CODE);
        assertThat(testFunctionalAbility.getFunctionalAbilityName()).isEqualTo(UPDATED_FUNCTIONAL_ABILITY_NAME);
        assertThat(testFunctionalAbility.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testFunctionalAbility.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFunctionalAbility.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testFunctionalAbility.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFunctionalAbility.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testFunctionalAbility.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testFunctionalAbility.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testFunctionalAbility.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testFunctionalAbility.getFunctionalAbilityUuid()).isEqualTo(UPDATED_FUNCTIONAL_ABILITY_UUID);
    }

    @Test
    void patchNonExistingFunctionalAbility() throws Exception {
        int databaseSizeBeforeUpdate = functionalAbilityRepository.findAll().collectList().block().size();
        functionalAbility.setFunctionalAbilityId(count.incrementAndGet());

        // Create the FunctionalAbility
        FunctionalAbilityDTO functionalAbilityDTO = functionalAbilityMapper.toDto(functionalAbility);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, functionalAbilityDTO.getFunctionalAbilityId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(functionalAbilityDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FunctionalAbility in the database
        List<FunctionalAbility> functionalAbilityList = functionalAbilityRepository.findAll().collectList().block();
        assertThat(functionalAbilityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchFunctionalAbility() throws Exception {
        int databaseSizeBeforeUpdate = functionalAbilityRepository.findAll().collectList().block().size();
        functionalAbility.setFunctionalAbilityId(count.incrementAndGet());

        // Create the FunctionalAbility
        FunctionalAbilityDTO functionalAbilityDTO = functionalAbilityMapper.toDto(functionalAbility);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(functionalAbilityDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FunctionalAbility in the database
        List<FunctionalAbility> functionalAbilityList = functionalAbilityRepository.findAll().collectList().block();
        assertThat(functionalAbilityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamFunctionalAbility() throws Exception {
        int databaseSizeBeforeUpdate = functionalAbilityRepository.findAll().collectList().block().size();
        functionalAbility.setFunctionalAbilityId(count.incrementAndGet());

        // Create the FunctionalAbility
        FunctionalAbilityDTO functionalAbilityDTO = functionalAbilityMapper.toDto(functionalAbility);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(functionalAbilityDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the FunctionalAbility in the database
        List<FunctionalAbility> functionalAbilityList = functionalAbilityRepository.findAll().collectList().block();
        assertThat(functionalAbilityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteFunctionalAbility() {
        // Initialize the database
        functionalAbilityRepository.save(functionalAbility).block();

        int databaseSizeBeforeDelete = functionalAbilityRepository.findAll().collectList().block().size();

        // Delete the functionalAbility
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, functionalAbility.getFunctionalAbilityId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<FunctionalAbility> functionalAbilityList = functionalAbilityRepository.findAll().collectList().block();
        assertThat(functionalAbilityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
