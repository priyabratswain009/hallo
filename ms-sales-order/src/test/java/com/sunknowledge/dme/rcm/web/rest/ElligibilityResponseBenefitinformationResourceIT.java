package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ElligibilityResponseBenefitinformation;
import com.sunknowledge.dme.rcm.repository.ElligibilityResponseBenefitinformationRepository;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.service.dto.ElligibilityResponseBenefitinformationDTO;
import com.sunknowledge.dme.rcm.service.mapper.ElligibilityResponseBenefitinformationMapper;
import java.time.Duration;
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
 * Integration tests for the {@link ElligibilityResponseBenefitinformationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ElligibilityResponseBenefitinformationResourceIT {

    private static final Long DEFAULT_ELLIGIBILITY_RESPONSE_STATUS_ID = 1L;
    private static final Long UPDATED_ELLIGIBILITY_RESPONSE_STATUS_ID = 2L;

    private static final String DEFAULT_BENEFITSINFORMATION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BENEFITSINFORMATION_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFITSINFORMATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BENEFITSINFORMATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COVERAGE_LEVEL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COVERAGE_LEVEL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_TYPE_CODES = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_TYPE_CODES = "BBBBBBBBBB";

    private static final String DEFAULT_INSURANCE_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_TYPE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PLAN_COVERAGE = "AAAAAAAAAA";
    private static final String UPDATED_PLAN_COVERAGE = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFITS_DATE_INFORMATION = "AAAAAAAAAA";
    private static final String UPDATED_BENEFITS_DATE_INFORMATION = "BBBBBBBBBB";

    private static final UUID DEFAULT_ELLIGIBILITY_RESPONSE_BENEFIT_INFORMATION_UUID = UUID.randomUUID();
    private static final UUID UPDATED_ELLIGIBILITY_RESPONSE_BENEFIT_INFORMATION_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/elligibility-response-benefitinformations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{elligibilityResponseBenefitinformationId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ElligibilityResponseBenefitinformationRepository elligibilityResponseBenefitinformationRepository;

    @Autowired
    private ElligibilityResponseBenefitinformationMapper elligibilityResponseBenefitinformationMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ElligibilityResponseBenefitinformation elligibilityResponseBenefitinformation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElligibilityResponseBenefitinformation createEntity(EntityManager em) {
        ElligibilityResponseBenefitinformation elligibilityResponseBenefitinformation = new ElligibilityResponseBenefitinformation()
            .elligibilityResponseStatusId(DEFAULT_ELLIGIBILITY_RESPONSE_STATUS_ID)
            .benefitsinformationCode(DEFAULT_BENEFITSINFORMATION_CODE)
            .benefitsinformationName(DEFAULT_BENEFITSINFORMATION_NAME)
            .coverageLevelCode(DEFAULT_COVERAGE_LEVEL_CODE)
            .serviceTypeCodes(DEFAULT_SERVICE_TYPE_CODES)
            .insuranceTypeCode(DEFAULT_INSURANCE_TYPE_CODE)
            .planCoverage(DEFAULT_PLAN_COVERAGE)
            .benefitsDateInformation(DEFAULT_BENEFITS_DATE_INFORMATION)
            .elligibilityResponseBenefitInformationUuid(DEFAULT_ELLIGIBILITY_RESPONSE_BENEFIT_INFORMATION_UUID);
        return elligibilityResponseBenefitinformation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElligibilityResponseBenefitinformation createUpdatedEntity(EntityManager em) {
        ElligibilityResponseBenefitinformation elligibilityResponseBenefitinformation = new ElligibilityResponseBenefitinformation()
            .elligibilityResponseStatusId(UPDATED_ELLIGIBILITY_RESPONSE_STATUS_ID)
            .benefitsinformationCode(UPDATED_BENEFITSINFORMATION_CODE)
            .benefitsinformationName(UPDATED_BENEFITSINFORMATION_NAME)
            .coverageLevelCode(UPDATED_COVERAGE_LEVEL_CODE)
            .serviceTypeCodes(UPDATED_SERVICE_TYPE_CODES)
            .insuranceTypeCode(UPDATED_INSURANCE_TYPE_CODE)
            .planCoverage(UPDATED_PLAN_COVERAGE)
            .benefitsDateInformation(UPDATED_BENEFITS_DATE_INFORMATION)
            .elligibilityResponseBenefitInformationUuid(UPDATED_ELLIGIBILITY_RESPONSE_BENEFIT_INFORMATION_UUID);
        return elligibilityResponseBenefitinformation;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ElligibilityResponseBenefitinformation.class).block();
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
        elligibilityResponseBenefitinformation = createEntity(em);
    }

    @Test
    void createElligibilityResponseBenefitinformation() throws Exception {
        int databaseSizeBeforeCreate = elligibilityResponseBenefitinformationRepository.findAll().collectList().block().size();
        // Create the ElligibilityResponseBenefitinformation
        ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO = elligibilityResponseBenefitinformationMapper.toDto(
            elligibilityResponseBenefitinformation
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseBenefitinformationDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ElligibilityResponseBenefitinformation in the database
        List<ElligibilityResponseBenefitinformation> elligibilityResponseBenefitinformationList = elligibilityResponseBenefitinformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseBenefitinformationList).hasSize(databaseSizeBeforeCreate + 1);
        ElligibilityResponseBenefitinformation testElligibilityResponseBenefitinformation = elligibilityResponseBenefitinformationList.get(
            elligibilityResponseBenefitinformationList.size() - 1
        );
        assertThat(testElligibilityResponseBenefitinformation.getElligibilityResponseStatusId())
            .isEqualTo(DEFAULT_ELLIGIBILITY_RESPONSE_STATUS_ID);
        assertThat(testElligibilityResponseBenefitinformation.getBenefitsinformationCode()).isEqualTo(DEFAULT_BENEFITSINFORMATION_CODE);
        assertThat(testElligibilityResponseBenefitinformation.getBenefitsinformationName()).isEqualTo(DEFAULT_BENEFITSINFORMATION_NAME);
        assertThat(testElligibilityResponseBenefitinformation.getCoverageLevelCode()).isEqualTo(DEFAULT_COVERAGE_LEVEL_CODE);
        assertThat(testElligibilityResponseBenefitinformation.getServiceTypeCodes()).isEqualTo(DEFAULT_SERVICE_TYPE_CODES);
        assertThat(testElligibilityResponseBenefitinformation.getInsuranceTypeCode()).isEqualTo(DEFAULT_INSURANCE_TYPE_CODE);
        assertThat(testElligibilityResponseBenefitinformation.getPlanCoverage()).isEqualTo(DEFAULT_PLAN_COVERAGE);
        assertThat(testElligibilityResponseBenefitinformation.getBenefitsDateInformation()).isEqualTo(DEFAULT_BENEFITS_DATE_INFORMATION);
        assertThat(testElligibilityResponseBenefitinformation.getElligibilityResponseBenefitInformationUuid())
            .isEqualTo(DEFAULT_ELLIGIBILITY_RESPONSE_BENEFIT_INFORMATION_UUID);
    }

    @Test
    void createElligibilityResponseBenefitinformationWithExistingId() throws Exception {
        // Create the ElligibilityResponseBenefitinformation with an existing ID
        elligibilityResponseBenefitinformation.setElligibilityResponseBenefitinformationId(1L);
        ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO = elligibilityResponseBenefitinformationMapper.toDto(
            elligibilityResponseBenefitinformation
        );

        int databaseSizeBeforeCreate = elligibilityResponseBenefitinformationRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseBenefitinformationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ElligibilityResponseBenefitinformation in the database
        List<ElligibilityResponseBenefitinformation> elligibilityResponseBenefitinformationList = elligibilityResponseBenefitinformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseBenefitinformationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllElligibilityResponseBenefitinformations() {
        // Initialize the database
        elligibilityResponseBenefitinformationRepository.save(elligibilityResponseBenefitinformation).block();

        // Get all the elligibilityResponseBenefitinformationList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=elligibilityResponseBenefitinformationId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].elligibilityResponseBenefitinformationId")
            .value(hasItem(elligibilityResponseBenefitinformation.getElligibilityResponseBenefitinformationId().intValue()))
            .jsonPath("$.[*].elligibilityResponseStatusId")
            .value(hasItem(DEFAULT_ELLIGIBILITY_RESPONSE_STATUS_ID.intValue()))
            .jsonPath("$.[*].benefitsinformationCode")
            .value(hasItem(DEFAULT_BENEFITSINFORMATION_CODE))
            .jsonPath("$.[*].benefitsinformationName")
            .value(hasItem(DEFAULT_BENEFITSINFORMATION_NAME))
            .jsonPath("$.[*].coverageLevelCode")
            .value(hasItem(DEFAULT_COVERAGE_LEVEL_CODE))
            .jsonPath("$.[*].serviceTypeCodes")
            .value(hasItem(DEFAULT_SERVICE_TYPE_CODES))
            .jsonPath("$.[*].insuranceTypeCode")
            .value(hasItem(DEFAULT_INSURANCE_TYPE_CODE))
            .jsonPath("$.[*].planCoverage")
            .value(hasItem(DEFAULT_PLAN_COVERAGE))
            .jsonPath("$.[*].benefitsDateInformation")
            .value(hasItem(DEFAULT_BENEFITS_DATE_INFORMATION))
            .jsonPath("$.[*].elligibilityResponseBenefitInformationUuid")
            .value(hasItem(DEFAULT_ELLIGIBILITY_RESPONSE_BENEFIT_INFORMATION_UUID.toString()));
    }

    @Test
    void getElligibilityResponseBenefitinformation() {
        // Initialize the database
        elligibilityResponseBenefitinformationRepository.save(elligibilityResponseBenefitinformation).block();

        // Get the elligibilityResponseBenefitinformation
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, elligibilityResponseBenefitinformation.getElligibilityResponseBenefitinformationId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.elligibilityResponseBenefitinformationId")
            .value(is(elligibilityResponseBenefitinformation.getElligibilityResponseBenefitinformationId().intValue()))
            .jsonPath("$.elligibilityResponseStatusId")
            .value(is(DEFAULT_ELLIGIBILITY_RESPONSE_STATUS_ID.intValue()))
            .jsonPath("$.benefitsinformationCode")
            .value(is(DEFAULT_BENEFITSINFORMATION_CODE))
            .jsonPath("$.benefitsinformationName")
            .value(is(DEFAULT_BENEFITSINFORMATION_NAME))
            .jsonPath("$.coverageLevelCode")
            .value(is(DEFAULT_COVERAGE_LEVEL_CODE))
            .jsonPath("$.serviceTypeCodes")
            .value(is(DEFAULT_SERVICE_TYPE_CODES))
            .jsonPath("$.insuranceTypeCode")
            .value(is(DEFAULT_INSURANCE_TYPE_CODE))
            .jsonPath("$.planCoverage")
            .value(is(DEFAULT_PLAN_COVERAGE))
            .jsonPath("$.benefitsDateInformation")
            .value(is(DEFAULT_BENEFITS_DATE_INFORMATION))
            .jsonPath("$.elligibilityResponseBenefitInformationUuid")
            .value(is(DEFAULT_ELLIGIBILITY_RESPONSE_BENEFIT_INFORMATION_UUID.toString()));
    }

    @Test
    void getNonExistingElligibilityResponseBenefitinformation() {
        // Get the elligibilityResponseBenefitinformation
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingElligibilityResponseBenefitinformation() throws Exception {
        // Initialize the database
        elligibilityResponseBenefitinformationRepository.save(elligibilityResponseBenefitinformation).block();

        int databaseSizeBeforeUpdate = elligibilityResponseBenefitinformationRepository.findAll().collectList().block().size();

        // Update the elligibilityResponseBenefitinformation
        ElligibilityResponseBenefitinformation updatedElligibilityResponseBenefitinformation = elligibilityResponseBenefitinformationRepository
            .findById(elligibilityResponseBenefitinformation.getElligibilityResponseBenefitinformationId())
            .block();
        updatedElligibilityResponseBenefitinformation
            .elligibilityResponseStatusId(UPDATED_ELLIGIBILITY_RESPONSE_STATUS_ID)
            .benefitsinformationCode(UPDATED_BENEFITSINFORMATION_CODE)
            .benefitsinformationName(UPDATED_BENEFITSINFORMATION_NAME)
            .coverageLevelCode(UPDATED_COVERAGE_LEVEL_CODE)
            .serviceTypeCodes(UPDATED_SERVICE_TYPE_CODES)
            .insuranceTypeCode(UPDATED_INSURANCE_TYPE_CODE)
            .planCoverage(UPDATED_PLAN_COVERAGE)
            .benefitsDateInformation(UPDATED_BENEFITS_DATE_INFORMATION)
            .elligibilityResponseBenefitInformationUuid(UPDATED_ELLIGIBILITY_RESPONSE_BENEFIT_INFORMATION_UUID);
        ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO = elligibilityResponseBenefitinformationMapper.toDto(
            updatedElligibilityResponseBenefitinformation
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, elligibilityResponseBenefitinformationDTO.getElligibilityResponseBenefitinformationId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseBenefitinformationDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ElligibilityResponseBenefitinformation in the database
        List<ElligibilityResponseBenefitinformation> elligibilityResponseBenefitinformationList = elligibilityResponseBenefitinformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseBenefitinformationList).hasSize(databaseSizeBeforeUpdate);
        ElligibilityResponseBenefitinformation testElligibilityResponseBenefitinformation = elligibilityResponseBenefitinformationList.get(
            elligibilityResponseBenefitinformationList.size() - 1
        );
        assertThat(testElligibilityResponseBenefitinformation.getElligibilityResponseStatusId())
            .isEqualTo(UPDATED_ELLIGIBILITY_RESPONSE_STATUS_ID);
        assertThat(testElligibilityResponseBenefitinformation.getBenefitsinformationCode()).isEqualTo(UPDATED_BENEFITSINFORMATION_CODE);
        assertThat(testElligibilityResponseBenefitinformation.getBenefitsinformationName()).isEqualTo(UPDATED_BENEFITSINFORMATION_NAME);
        assertThat(testElligibilityResponseBenefitinformation.getCoverageLevelCode()).isEqualTo(UPDATED_COVERAGE_LEVEL_CODE);
        assertThat(testElligibilityResponseBenefitinformation.getServiceTypeCodes()).isEqualTo(UPDATED_SERVICE_TYPE_CODES);
        assertThat(testElligibilityResponseBenefitinformation.getInsuranceTypeCode()).isEqualTo(UPDATED_INSURANCE_TYPE_CODE);
        assertThat(testElligibilityResponseBenefitinformation.getPlanCoverage()).isEqualTo(UPDATED_PLAN_COVERAGE);
        assertThat(testElligibilityResponseBenefitinformation.getBenefitsDateInformation()).isEqualTo(UPDATED_BENEFITS_DATE_INFORMATION);
        assertThat(testElligibilityResponseBenefitinformation.getElligibilityResponseBenefitInformationUuid())
            .isEqualTo(UPDATED_ELLIGIBILITY_RESPONSE_BENEFIT_INFORMATION_UUID);
    }

    @Test
    void putNonExistingElligibilityResponseBenefitinformation() throws Exception {
        int databaseSizeBeforeUpdate = elligibilityResponseBenefitinformationRepository.findAll().collectList().block().size();
        elligibilityResponseBenefitinformation.setElligibilityResponseBenefitinformationId(count.incrementAndGet());

        // Create the ElligibilityResponseBenefitinformation
        ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO = elligibilityResponseBenefitinformationMapper.toDto(
            elligibilityResponseBenefitinformation
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, elligibilityResponseBenefitinformationDTO.getElligibilityResponseBenefitinformationId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseBenefitinformationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ElligibilityResponseBenefitinformation in the database
        List<ElligibilityResponseBenefitinformation> elligibilityResponseBenefitinformationList = elligibilityResponseBenefitinformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseBenefitinformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchElligibilityResponseBenefitinformation() throws Exception {
        int databaseSizeBeforeUpdate = elligibilityResponseBenefitinformationRepository.findAll().collectList().block().size();
        elligibilityResponseBenefitinformation.setElligibilityResponseBenefitinformationId(count.incrementAndGet());

        // Create the ElligibilityResponseBenefitinformation
        ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO = elligibilityResponseBenefitinformationMapper.toDto(
            elligibilityResponseBenefitinformation
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseBenefitinformationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ElligibilityResponseBenefitinformation in the database
        List<ElligibilityResponseBenefitinformation> elligibilityResponseBenefitinformationList = elligibilityResponseBenefitinformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseBenefitinformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamElligibilityResponseBenefitinformation() throws Exception {
        int databaseSizeBeforeUpdate = elligibilityResponseBenefitinformationRepository.findAll().collectList().block().size();
        elligibilityResponseBenefitinformation.setElligibilityResponseBenefitinformationId(count.incrementAndGet());

        // Create the ElligibilityResponseBenefitinformation
        ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO = elligibilityResponseBenefitinformationMapper.toDto(
            elligibilityResponseBenefitinformation
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseBenefitinformationDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ElligibilityResponseBenefitinformation in the database
        List<ElligibilityResponseBenefitinformation> elligibilityResponseBenefitinformationList = elligibilityResponseBenefitinformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseBenefitinformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateElligibilityResponseBenefitinformationWithPatch() throws Exception {
        // Initialize the database
        elligibilityResponseBenefitinformationRepository.save(elligibilityResponseBenefitinformation).block();

        int databaseSizeBeforeUpdate = elligibilityResponseBenefitinformationRepository.findAll().collectList().block().size();

        // Update the elligibilityResponseBenefitinformation using partial update
        ElligibilityResponseBenefitinformation partialUpdatedElligibilityResponseBenefitinformation = new ElligibilityResponseBenefitinformation();
        partialUpdatedElligibilityResponseBenefitinformation.setElligibilityResponseBenefitinformationId(
            elligibilityResponseBenefitinformation.getElligibilityResponseBenefitinformationId()
        );

        partialUpdatedElligibilityResponseBenefitinformation
            .elligibilityResponseStatusId(UPDATED_ELLIGIBILITY_RESPONSE_STATUS_ID)
            .benefitsinformationCode(UPDATED_BENEFITSINFORMATION_CODE)
            .insuranceTypeCode(UPDATED_INSURANCE_TYPE_CODE)
            .elligibilityResponseBenefitInformationUuid(UPDATED_ELLIGIBILITY_RESPONSE_BENEFIT_INFORMATION_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedElligibilityResponseBenefitinformation.getElligibilityResponseBenefitinformationId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedElligibilityResponseBenefitinformation))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ElligibilityResponseBenefitinformation in the database
        List<ElligibilityResponseBenefitinformation> elligibilityResponseBenefitinformationList = elligibilityResponseBenefitinformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseBenefitinformationList).hasSize(databaseSizeBeforeUpdate);
        ElligibilityResponseBenefitinformation testElligibilityResponseBenefitinformation = elligibilityResponseBenefitinformationList.get(
            elligibilityResponseBenefitinformationList.size() - 1
        );
        assertThat(testElligibilityResponseBenefitinformation.getElligibilityResponseStatusId())
            .isEqualTo(UPDATED_ELLIGIBILITY_RESPONSE_STATUS_ID);
        assertThat(testElligibilityResponseBenefitinformation.getBenefitsinformationCode()).isEqualTo(UPDATED_BENEFITSINFORMATION_CODE);
        assertThat(testElligibilityResponseBenefitinformation.getBenefitsinformationName()).isEqualTo(DEFAULT_BENEFITSINFORMATION_NAME);
        assertThat(testElligibilityResponseBenefitinformation.getCoverageLevelCode()).isEqualTo(DEFAULT_COVERAGE_LEVEL_CODE);
        assertThat(testElligibilityResponseBenefitinformation.getServiceTypeCodes()).isEqualTo(DEFAULT_SERVICE_TYPE_CODES);
        assertThat(testElligibilityResponseBenefitinformation.getInsuranceTypeCode()).isEqualTo(UPDATED_INSURANCE_TYPE_CODE);
        assertThat(testElligibilityResponseBenefitinformation.getPlanCoverage()).isEqualTo(DEFAULT_PLAN_COVERAGE);
        assertThat(testElligibilityResponseBenefitinformation.getBenefitsDateInformation()).isEqualTo(DEFAULT_BENEFITS_DATE_INFORMATION);
        assertThat(testElligibilityResponseBenefitinformation.getElligibilityResponseBenefitInformationUuid())
            .isEqualTo(UPDATED_ELLIGIBILITY_RESPONSE_BENEFIT_INFORMATION_UUID);
    }

    @Test
    void fullUpdateElligibilityResponseBenefitinformationWithPatch() throws Exception {
        // Initialize the database
        elligibilityResponseBenefitinformationRepository.save(elligibilityResponseBenefitinformation).block();

        int databaseSizeBeforeUpdate = elligibilityResponseBenefitinformationRepository.findAll().collectList().block().size();

        // Update the elligibilityResponseBenefitinformation using partial update
        ElligibilityResponseBenefitinformation partialUpdatedElligibilityResponseBenefitinformation = new ElligibilityResponseBenefitinformation();
        partialUpdatedElligibilityResponseBenefitinformation.setElligibilityResponseBenefitinformationId(
            elligibilityResponseBenefitinformation.getElligibilityResponseBenefitinformationId()
        );

        partialUpdatedElligibilityResponseBenefitinformation
            .elligibilityResponseStatusId(UPDATED_ELLIGIBILITY_RESPONSE_STATUS_ID)
            .benefitsinformationCode(UPDATED_BENEFITSINFORMATION_CODE)
            .benefitsinformationName(UPDATED_BENEFITSINFORMATION_NAME)
            .coverageLevelCode(UPDATED_COVERAGE_LEVEL_CODE)
            .serviceTypeCodes(UPDATED_SERVICE_TYPE_CODES)
            .insuranceTypeCode(UPDATED_INSURANCE_TYPE_CODE)
            .planCoverage(UPDATED_PLAN_COVERAGE)
            .benefitsDateInformation(UPDATED_BENEFITS_DATE_INFORMATION)
            .elligibilityResponseBenefitInformationUuid(UPDATED_ELLIGIBILITY_RESPONSE_BENEFIT_INFORMATION_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedElligibilityResponseBenefitinformation.getElligibilityResponseBenefitinformationId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedElligibilityResponseBenefitinformation))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ElligibilityResponseBenefitinformation in the database
        List<ElligibilityResponseBenefitinformation> elligibilityResponseBenefitinformationList = elligibilityResponseBenefitinformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseBenefitinformationList).hasSize(databaseSizeBeforeUpdate);
        ElligibilityResponseBenefitinformation testElligibilityResponseBenefitinformation = elligibilityResponseBenefitinformationList.get(
            elligibilityResponseBenefitinformationList.size() - 1
        );
        assertThat(testElligibilityResponseBenefitinformation.getElligibilityResponseStatusId())
            .isEqualTo(UPDATED_ELLIGIBILITY_RESPONSE_STATUS_ID);
        assertThat(testElligibilityResponseBenefitinformation.getBenefitsinformationCode()).isEqualTo(UPDATED_BENEFITSINFORMATION_CODE);
        assertThat(testElligibilityResponseBenefitinformation.getBenefitsinformationName()).isEqualTo(UPDATED_BENEFITSINFORMATION_NAME);
        assertThat(testElligibilityResponseBenefitinformation.getCoverageLevelCode()).isEqualTo(UPDATED_COVERAGE_LEVEL_CODE);
        assertThat(testElligibilityResponseBenefitinformation.getServiceTypeCodes()).isEqualTo(UPDATED_SERVICE_TYPE_CODES);
        assertThat(testElligibilityResponseBenefitinformation.getInsuranceTypeCode()).isEqualTo(UPDATED_INSURANCE_TYPE_CODE);
        assertThat(testElligibilityResponseBenefitinformation.getPlanCoverage()).isEqualTo(UPDATED_PLAN_COVERAGE);
        assertThat(testElligibilityResponseBenefitinformation.getBenefitsDateInformation()).isEqualTo(UPDATED_BENEFITS_DATE_INFORMATION);
        assertThat(testElligibilityResponseBenefitinformation.getElligibilityResponseBenefitInformationUuid())
            .isEqualTo(UPDATED_ELLIGIBILITY_RESPONSE_BENEFIT_INFORMATION_UUID);
    }

    @Test
    void patchNonExistingElligibilityResponseBenefitinformation() throws Exception {
        int databaseSizeBeforeUpdate = elligibilityResponseBenefitinformationRepository.findAll().collectList().block().size();
        elligibilityResponseBenefitinformation.setElligibilityResponseBenefitinformationId(count.incrementAndGet());

        // Create the ElligibilityResponseBenefitinformation
        ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO = elligibilityResponseBenefitinformationMapper.toDto(
            elligibilityResponseBenefitinformation
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, elligibilityResponseBenefitinformationDTO.getElligibilityResponseBenefitinformationId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseBenefitinformationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ElligibilityResponseBenefitinformation in the database
        List<ElligibilityResponseBenefitinformation> elligibilityResponseBenefitinformationList = elligibilityResponseBenefitinformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseBenefitinformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchElligibilityResponseBenefitinformation() throws Exception {
        int databaseSizeBeforeUpdate = elligibilityResponseBenefitinformationRepository.findAll().collectList().block().size();
        elligibilityResponseBenefitinformation.setElligibilityResponseBenefitinformationId(count.incrementAndGet());

        // Create the ElligibilityResponseBenefitinformation
        ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO = elligibilityResponseBenefitinformationMapper.toDto(
            elligibilityResponseBenefitinformation
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseBenefitinformationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ElligibilityResponseBenefitinformation in the database
        List<ElligibilityResponseBenefitinformation> elligibilityResponseBenefitinformationList = elligibilityResponseBenefitinformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseBenefitinformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamElligibilityResponseBenefitinformation() throws Exception {
        int databaseSizeBeforeUpdate = elligibilityResponseBenefitinformationRepository.findAll().collectList().block().size();
        elligibilityResponseBenefitinformation.setElligibilityResponseBenefitinformationId(count.incrementAndGet());

        // Create the ElligibilityResponseBenefitinformation
        ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO = elligibilityResponseBenefitinformationMapper.toDto(
            elligibilityResponseBenefitinformation
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(elligibilityResponseBenefitinformationDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ElligibilityResponseBenefitinformation in the database
        List<ElligibilityResponseBenefitinformation> elligibilityResponseBenefitinformationList = elligibilityResponseBenefitinformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseBenefitinformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteElligibilityResponseBenefitinformation() {
        // Initialize the database
        elligibilityResponseBenefitinformationRepository.save(elligibilityResponseBenefitinformation).block();

        int databaseSizeBeforeDelete = elligibilityResponseBenefitinformationRepository.findAll().collectList().block().size();

        // Delete the elligibilityResponseBenefitinformation
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, elligibilityResponseBenefitinformation.getElligibilityResponseBenefitinformationId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ElligibilityResponseBenefitinformation> elligibilityResponseBenefitinformationList = elligibilityResponseBenefitinformationRepository
            .findAll()
            .collectList()
            .block();
        assertThat(elligibilityResponseBenefitinformationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
