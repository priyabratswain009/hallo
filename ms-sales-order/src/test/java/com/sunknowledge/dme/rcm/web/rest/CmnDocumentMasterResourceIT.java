package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.CmnDocumentMaster;
import com.sunknowledge.dme.rcm.repository.CmnDocumentMasterRepository;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.service.dto.CmnDocumentMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.CmnDocumentMasterMapper;
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
 * Integration tests for the {@link CmnDocumentMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CmnDocumentMasterResourceIT {

    private static final Long DEFAULT_CMN_ID = 1L;
    private static final Long UPDATED_CMN_ID = 2L;

    private static final String DEFAULT_CMN_NO = "AAAAAAAAAA";
    private static final String UPDATED_CMN_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_GENERATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_GENERATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_INITIAL_DOCUMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INITIAL_DOCUMENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FAX_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_FAX_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_OUT_BOUND_FAX_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_OUT_BOUND_FAX_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_OUT_BOUND_FAX_NO = "AAAAAAAAAA";
    private static final String UPDATED_OUT_BOUND_FAX_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_OUT_BOUND_FAX_DATE_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OUT_BOUND_FAX_DATE_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_IN_BOUND_FAX_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_IN_BOUND_FAX_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_IN_BOUND_FAX_NO = "AAAAAAAAAA";
    private static final String UPDATED_IN_BOUND_FAX_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_IN_BOUND_FAX_DATE_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_IN_BOUND_FAX_DATE_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_CMN_DOCUMENT_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_CMN_DOCUMENT_MASTER_UUID = UUID.randomUUID();

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RETURN_DOCUMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RETURN_DOCUMENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CMN_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_CMN_COMMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/cmn-document-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{cmnDocumentId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CmnDocumentMasterRepository cmnDocumentMasterRepository;

    @Autowired
    private CmnDocumentMasterMapper cmnDocumentMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CmnDocumentMaster cmnDocumentMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CmnDocumentMaster createEntity(EntityManager em) {
        CmnDocumentMaster cmnDocumentMaster = new CmnDocumentMaster()
            .cmnId(DEFAULT_CMN_ID)
            .cmnNo(DEFAULT_CMN_NO)
            .generationDate(DEFAULT_GENERATION_DATE)
            .initialDocumentName(DEFAULT_INITIAL_DOCUMENT_NAME)
            .faxStatus(DEFAULT_FAX_STATUS)
            .outBoundFaxStatus(DEFAULT_OUT_BOUND_FAX_STATUS)
            .outBoundFaxNo(DEFAULT_OUT_BOUND_FAX_NO)
            .outBoundFaxDateTime(DEFAULT_OUT_BOUND_FAX_DATE_TIME)
            .inBoundFaxStatus(DEFAULT_IN_BOUND_FAX_STATUS)
            .inBoundFaxNo(DEFAULT_IN_BOUND_FAX_NO)
            .inBoundFaxDateTime(DEFAULT_IN_BOUND_FAX_DATE_TIME)
            .cmnDocumentMasterUuid(DEFAULT_CMN_DOCUMENT_MASTER_UUID)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .returnDocumentName(DEFAULT_RETURN_DOCUMENT_NAME)
            .cmnComments(DEFAULT_CMN_COMMENTS)
            .status(DEFAULT_STATUS);
        return cmnDocumentMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CmnDocumentMaster createUpdatedEntity(EntityManager em) {
        CmnDocumentMaster cmnDocumentMaster = new CmnDocumentMaster()
            .cmnId(UPDATED_CMN_ID)
            .cmnNo(UPDATED_CMN_NO)
            .generationDate(UPDATED_GENERATION_DATE)
            .initialDocumentName(UPDATED_INITIAL_DOCUMENT_NAME)
            .faxStatus(UPDATED_FAX_STATUS)
            .outBoundFaxStatus(UPDATED_OUT_BOUND_FAX_STATUS)
            .outBoundFaxNo(UPDATED_OUT_BOUND_FAX_NO)
            .outBoundFaxDateTime(UPDATED_OUT_BOUND_FAX_DATE_TIME)
            .inBoundFaxStatus(UPDATED_IN_BOUND_FAX_STATUS)
            .inBoundFaxNo(UPDATED_IN_BOUND_FAX_NO)
            .inBoundFaxDateTime(UPDATED_IN_BOUND_FAX_DATE_TIME)
            .cmnDocumentMasterUuid(UPDATED_CMN_DOCUMENT_MASTER_UUID)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .returnDocumentName(UPDATED_RETURN_DOCUMENT_NAME)
            .cmnComments(UPDATED_CMN_COMMENTS)
            .status(UPDATED_STATUS);
        return cmnDocumentMaster;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CmnDocumentMaster.class).block();
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
        cmnDocumentMaster = createEntity(em);
    }

    @Test
    void createCmnDocumentMaster() throws Exception {
        int databaseSizeBeforeCreate = cmnDocumentMasterRepository.findAll().collectList().block().size();
        // Create the CmnDocumentMaster
        CmnDocumentMasterDTO cmnDocumentMasterDTO = cmnDocumentMasterMapper.toDto(cmnDocumentMaster);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cmnDocumentMasterDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the CmnDocumentMaster in the database
        List<CmnDocumentMaster> cmnDocumentMasterList = cmnDocumentMasterRepository.findAll().collectList().block();
        assertThat(cmnDocumentMasterList).hasSize(databaseSizeBeforeCreate + 1);
        CmnDocumentMaster testCmnDocumentMaster = cmnDocumentMasterList.get(cmnDocumentMasterList.size() - 1);
        assertThat(testCmnDocumentMaster.getCmnId()).isEqualTo(DEFAULT_CMN_ID);
        assertThat(testCmnDocumentMaster.getCmnNo()).isEqualTo(DEFAULT_CMN_NO);
        assertThat(testCmnDocumentMaster.getGenerationDate()).isEqualTo(DEFAULT_GENERATION_DATE);
        assertThat(testCmnDocumentMaster.getInitialDocumentName()).isEqualTo(DEFAULT_INITIAL_DOCUMENT_NAME);
        assertThat(testCmnDocumentMaster.getFaxStatus()).isEqualTo(DEFAULT_FAX_STATUS);
        assertThat(testCmnDocumentMaster.getOutBoundFaxStatus()).isEqualTo(DEFAULT_OUT_BOUND_FAX_STATUS);
        assertThat(testCmnDocumentMaster.getOutBoundFaxNo()).isEqualTo(DEFAULT_OUT_BOUND_FAX_NO);
        assertThat(testCmnDocumentMaster.getOutBoundFaxDateTime()).isEqualTo(DEFAULT_OUT_BOUND_FAX_DATE_TIME);
        assertThat(testCmnDocumentMaster.getInBoundFaxStatus()).isEqualTo(DEFAULT_IN_BOUND_FAX_STATUS);
        assertThat(testCmnDocumentMaster.getInBoundFaxNo()).isEqualTo(DEFAULT_IN_BOUND_FAX_NO);
        assertThat(testCmnDocumentMaster.getInBoundFaxDateTime()).isEqualTo(DEFAULT_IN_BOUND_FAX_DATE_TIME);
        assertThat(testCmnDocumentMaster.getCmnDocumentMasterUuid()).isEqualTo(DEFAULT_CMN_DOCUMENT_MASTER_UUID);
        assertThat(testCmnDocumentMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testCmnDocumentMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCmnDocumentMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testCmnDocumentMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testCmnDocumentMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testCmnDocumentMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testCmnDocumentMaster.getReturnDocumentName()).isEqualTo(DEFAULT_RETURN_DOCUMENT_NAME);
        assertThat(testCmnDocumentMaster.getCmnComments()).isEqualTo(DEFAULT_CMN_COMMENTS);
        assertThat(testCmnDocumentMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    void createCmnDocumentMasterWithExistingId() throws Exception {
        // Create the CmnDocumentMaster with an existing ID
        cmnDocumentMaster.setCmnDocumentId(1L);
        CmnDocumentMasterDTO cmnDocumentMasterDTO = cmnDocumentMasterMapper.toDto(cmnDocumentMaster);

        int databaseSizeBeforeCreate = cmnDocumentMasterRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cmnDocumentMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CmnDocumentMaster in the database
        List<CmnDocumentMaster> cmnDocumentMasterList = cmnDocumentMasterRepository.findAll().collectList().block();
        assertThat(cmnDocumentMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllCmnDocumentMasters() {
        // Initialize the database
        cmnDocumentMasterRepository.save(cmnDocumentMaster).block();

        // Get all the cmnDocumentMasterList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=cmnDocumentId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].cmnDocumentId")
            .value(hasItem(cmnDocumentMaster.getCmnDocumentId().intValue()))
            .jsonPath("$.[*].cmnId")
            .value(hasItem(DEFAULT_CMN_ID.intValue()))
            .jsonPath("$.[*].cmnNo")
            .value(hasItem(DEFAULT_CMN_NO))
            .jsonPath("$.[*].generationDate")
            .value(hasItem(DEFAULT_GENERATION_DATE.toString()))
            .jsonPath("$.[*].initialDocumentName")
            .value(hasItem(DEFAULT_INITIAL_DOCUMENT_NAME))
            .jsonPath("$.[*].faxStatus")
            .value(hasItem(DEFAULT_FAX_STATUS))
            .jsonPath("$.[*].outBoundFaxStatus")
            .value(hasItem(DEFAULT_OUT_BOUND_FAX_STATUS))
            .jsonPath("$.[*].outBoundFaxNo")
            .value(hasItem(DEFAULT_OUT_BOUND_FAX_NO))
            .jsonPath("$.[*].outBoundFaxDateTime")
            .value(hasItem(DEFAULT_OUT_BOUND_FAX_DATE_TIME.toString()))
            .jsonPath("$.[*].inBoundFaxStatus")
            .value(hasItem(DEFAULT_IN_BOUND_FAX_STATUS))
            .jsonPath("$.[*].inBoundFaxNo")
            .value(hasItem(DEFAULT_IN_BOUND_FAX_NO))
            .jsonPath("$.[*].inBoundFaxDateTime")
            .value(hasItem(DEFAULT_IN_BOUND_FAX_DATE_TIME.toString()))
            .jsonPath("$.[*].cmnDocumentMasterUuid")
            .value(hasItem(DEFAULT_CMN_DOCUMENT_MASTER_UUID.toString()))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].returnDocumentName")
            .value(hasItem(DEFAULT_RETURN_DOCUMENT_NAME))
            .jsonPath("$.[*].cmnComments")
            .value(hasItem(DEFAULT_CMN_COMMENTS))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS));
    }

    @Test
    void getCmnDocumentMaster() {
        // Initialize the database
        cmnDocumentMasterRepository.save(cmnDocumentMaster).block();

        // Get the cmnDocumentMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, cmnDocumentMaster.getCmnDocumentId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.cmnDocumentId")
            .value(is(cmnDocumentMaster.getCmnDocumentId().intValue()))
            .jsonPath("$.cmnId")
            .value(is(DEFAULT_CMN_ID.intValue()))
            .jsonPath("$.cmnNo")
            .value(is(DEFAULT_CMN_NO))
            .jsonPath("$.generationDate")
            .value(is(DEFAULT_GENERATION_DATE.toString()))
            .jsonPath("$.initialDocumentName")
            .value(is(DEFAULT_INITIAL_DOCUMENT_NAME))
            .jsonPath("$.faxStatus")
            .value(is(DEFAULT_FAX_STATUS))
            .jsonPath("$.outBoundFaxStatus")
            .value(is(DEFAULT_OUT_BOUND_FAX_STATUS))
            .jsonPath("$.outBoundFaxNo")
            .value(is(DEFAULT_OUT_BOUND_FAX_NO))
            .jsonPath("$.outBoundFaxDateTime")
            .value(is(DEFAULT_OUT_BOUND_FAX_DATE_TIME.toString()))
            .jsonPath("$.inBoundFaxStatus")
            .value(is(DEFAULT_IN_BOUND_FAX_STATUS))
            .jsonPath("$.inBoundFaxNo")
            .value(is(DEFAULT_IN_BOUND_FAX_NO))
            .jsonPath("$.inBoundFaxDateTime")
            .value(is(DEFAULT_IN_BOUND_FAX_DATE_TIME.toString()))
            .jsonPath("$.cmnDocumentMasterUuid")
            .value(is(DEFAULT_CMN_DOCUMENT_MASTER_UUID.toString()))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.returnDocumentName")
            .value(is(DEFAULT_RETURN_DOCUMENT_NAME))
            .jsonPath("$.cmnComments")
            .value(is(DEFAULT_CMN_COMMENTS))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS));
    }

    @Test
    void getNonExistingCmnDocumentMaster() {
        // Get the cmnDocumentMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCmnDocumentMaster() throws Exception {
        // Initialize the database
        cmnDocumentMasterRepository.save(cmnDocumentMaster).block();

        int databaseSizeBeforeUpdate = cmnDocumentMasterRepository.findAll().collectList().block().size();

        // Update the cmnDocumentMaster
        CmnDocumentMaster updatedCmnDocumentMaster = cmnDocumentMasterRepository.findById(cmnDocumentMaster.getCmnDocumentId()).block();
        updatedCmnDocumentMaster
            .cmnId(UPDATED_CMN_ID)
            .cmnNo(UPDATED_CMN_NO)
            .generationDate(UPDATED_GENERATION_DATE)
            .initialDocumentName(UPDATED_INITIAL_DOCUMENT_NAME)
            .faxStatus(UPDATED_FAX_STATUS)
            .outBoundFaxStatus(UPDATED_OUT_BOUND_FAX_STATUS)
            .outBoundFaxNo(UPDATED_OUT_BOUND_FAX_NO)
            .outBoundFaxDateTime(UPDATED_OUT_BOUND_FAX_DATE_TIME)
            .inBoundFaxStatus(UPDATED_IN_BOUND_FAX_STATUS)
            .inBoundFaxNo(UPDATED_IN_BOUND_FAX_NO)
            .inBoundFaxDateTime(UPDATED_IN_BOUND_FAX_DATE_TIME)
            .cmnDocumentMasterUuid(UPDATED_CMN_DOCUMENT_MASTER_UUID)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .returnDocumentName(UPDATED_RETURN_DOCUMENT_NAME)
            .cmnComments(UPDATED_CMN_COMMENTS)
            .status(UPDATED_STATUS);
        CmnDocumentMasterDTO cmnDocumentMasterDTO = cmnDocumentMasterMapper.toDto(updatedCmnDocumentMaster);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, cmnDocumentMasterDTO.getCmnDocumentId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cmnDocumentMasterDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CmnDocumentMaster in the database
        List<CmnDocumentMaster> cmnDocumentMasterList = cmnDocumentMasterRepository.findAll().collectList().block();
        assertThat(cmnDocumentMasterList).hasSize(databaseSizeBeforeUpdate);
        CmnDocumentMaster testCmnDocumentMaster = cmnDocumentMasterList.get(cmnDocumentMasterList.size() - 1);
        assertThat(testCmnDocumentMaster.getCmnId()).isEqualTo(UPDATED_CMN_ID);
        assertThat(testCmnDocumentMaster.getCmnNo()).isEqualTo(UPDATED_CMN_NO);
        assertThat(testCmnDocumentMaster.getGenerationDate()).isEqualTo(UPDATED_GENERATION_DATE);
        assertThat(testCmnDocumentMaster.getInitialDocumentName()).isEqualTo(UPDATED_INITIAL_DOCUMENT_NAME);
        assertThat(testCmnDocumentMaster.getFaxStatus()).isEqualTo(UPDATED_FAX_STATUS);
        assertThat(testCmnDocumentMaster.getOutBoundFaxStatus()).isEqualTo(UPDATED_OUT_BOUND_FAX_STATUS);
        assertThat(testCmnDocumentMaster.getOutBoundFaxNo()).isEqualTo(UPDATED_OUT_BOUND_FAX_NO);
        assertThat(testCmnDocumentMaster.getOutBoundFaxDateTime()).isEqualTo(UPDATED_OUT_BOUND_FAX_DATE_TIME);
        assertThat(testCmnDocumentMaster.getInBoundFaxStatus()).isEqualTo(UPDATED_IN_BOUND_FAX_STATUS);
        assertThat(testCmnDocumentMaster.getInBoundFaxNo()).isEqualTo(UPDATED_IN_BOUND_FAX_NO);
        assertThat(testCmnDocumentMaster.getInBoundFaxDateTime()).isEqualTo(UPDATED_IN_BOUND_FAX_DATE_TIME);
        assertThat(testCmnDocumentMaster.getCmnDocumentMasterUuid()).isEqualTo(UPDATED_CMN_DOCUMENT_MASTER_UUID);
        assertThat(testCmnDocumentMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testCmnDocumentMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCmnDocumentMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testCmnDocumentMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testCmnDocumentMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testCmnDocumentMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testCmnDocumentMaster.getReturnDocumentName()).isEqualTo(UPDATED_RETURN_DOCUMENT_NAME);
        assertThat(testCmnDocumentMaster.getCmnComments()).isEqualTo(UPDATED_CMN_COMMENTS);
        assertThat(testCmnDocumentMaster.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    void putNonExistingCmnDocumentMaster() throws Exception {
        int databaseSizeBeforeUpdate = cmnDocumentMasterRepository.findAll().collectList().block().size();
        cmnDocumentMaster.setCmnDocumentId(count.incrementAndGet());

        // Create the CmnDocumentMaster
        CmnDocumentMasterDTO cmnDocumentMasterDTO = cmnDocumentMasterMapper.toDto(cmnDocumentMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, cmnDocumentMasterDTO.getCmnDocumentId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cmnDocumentMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CmnDocumentMaster in the database
        List<CmnDocumentMaster> cmnDocumentMasterList = cmnDocumentMasterRepository.findAll().collectList().block();
        assertThat(cmnDocumentMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCmnDocumentMaster() throws Exception {
        int databaseSizeBeforeUpdate = cmnDocumentMasterRepository.findAll().collectList().block().size();
        cmnDocumentMaster.setCmnDocumentId(count.incrementAndGet());

        // Create the CmnDocumentMaster
        CmnDocumentMasterDTO cmnDocumentMasterDTO = cmnDocumentMasterMapper.toDto(cmnDocumentMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cmnDocumentMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CmnDocumentMaster in the database
        List<CmnDocumentMaster> cmnDocumentMasterList = cmnDocumentMasterRepository.findAll().collectList().block();
        assertThat(cmnDocumentMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCmnDocumentMaster() throws Exception {
        int databaseSizeBeforeUpdate = cmnDocumentMasterRepository.findAll().collectList().block().size();
        cmnDocumentMaster.setCmnDocumentId(count.incrementAndGet());

        // Create the CmnDocumentMaster
        CmnDocumentMasterDTO cmnDocumentMasterDTO = cmnDocumentMasterMapper.toDto(cmnDocumentMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cmnDocumentMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CmnDocumentMaster in the database
        List<CmnDocumentMaster> cmnDocumentMasterList = cmnDocumentMasterRepository.findAll().collectList().block();
        assertThat(cmnDocumentMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCmnDocumentMasterWithPatch() throws Exception {
        // Initialize the database
        cmnDocumentMasterRepository.save(cmnDocumentMaster).block();

        int databaseSizeBeforeUpdate = cmnDocumentMasterRepository.findAll().collectList().block().size();

        // Update the cmnDocumentMaster using partial update
        CmnDocumentMaster partialUpdatedCmnDocumentMaster = new CmnDocumentMaster();
        partialUpdatedCmnDocumentMaster.setCmnDocumentId(cmnDocumentMaster.getCmnDocumentId());

        partialUpdatedCmnDocumentMaster
            .cmnNo(UPDATED_CMN_NO)
            .faxStatus(UPDATED_FAX_STATUS)
            .outBoundFaxNo(UPDATED_OUT_BOUND_FAX_NO)
            .outBoundFaxDateTime(UPDATED_OUT_BOUND_FAX_DATE_TIME)
            .inBoundFaxStatus(UPDATED_IN_BOUND_FAX_STATUS)
            .cmnDocumentMasterUuid(UPDATED_CMN_DOCUMENT_MASTER_UUID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .returnDocumentName(UPDATED_RETURN_DOCUMENT_NAME)
            .status(UPDATED_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCmnDocumentMaster.getCmnDocumentId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCmnDocumentMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CmnDocumentMaster in the database
        List<CmnDocumentMaster> cmnDocumentMasterList = cmnDocumentMasterRepository.findAll().collectList().block();
        assertThat(cmnDocumentMasterList).hasSize(databaseSizeBeforeUpdate);
        CmnDocumentMaster testCmnDocumentMaster = cmnDocumentMasterList.get(cmnDocumentMasterList.size() - 1);
        assertThat(testCmnDocumentMaster.getCmnId()).isEqualTo(DEFAULT_CMN_ID);
        assertThat(testCmnDocumentMaster.getCmnNo()).isEqualTo(UPDATED_CMN_NO);
        assertThat(testCmnDocumentMaster.getGenerationDate()).isEqualTo(DEFAULT_GENERATION_DATE);
        assertThat(testCmnDocumentMaster.getInitialDocumentName()).isEqualTo(DEFAULT_INITIAL_DOCUMENT_NAME);
        assertThat(testCmnDocumentMaster.getFaxStatus()).isEqualTo(UPDATED_FAX_STATUS);
        assertThat(testCmnDocumentMaster.getOutBoundFaxStatus()).isEqualTo(DEFAULT_OUT_BOUND_FAX_STATUS);
        assertThat(testCmnDocumentMaster.getOutBoundFaxNo()).isEqualTo(UPDATED_OUT_BOUND_FAX_NO);
        assertThat(testCmnDocumentMaster.getOutBoundFaxDateTime()).isEqualTo(UPDATED_OUT_BOUND_FAX_DATE_TIME);
        assertThat(testCmnDocumentMaster.getInBoundFaxStatus()).isEqualTo(UPDATED_IN_BOUND_FAX_STATUS);
        assertThat(testCmnDocumentMaster.getInBoundFaxNo()).isEqualTo(DEFAULT_IN_BOUND_FAX_NO);
        assertThat(testCmnDocumentMaster.getInBoundFaxDateTime()).isEqualTo(DEFAULT_IN_BOUND_FAX_DATE_TIME);
        assertThat(testCmnDocumentMaster.getCmnDocumentMasterUuid()).isEqualTo(UPDATED_CMN_DOCUMENT_MASTER_UUID);
        assertThat(testCmnDocumentMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testCmnDocumentMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCmnDocumentMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testCmnDocumentMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testCmnDocumentMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testCmnDocumentMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testCmnDocumentMaster.getReturnDocumentName()).isEqualTo(UPDATED_RETURN_DOCUMENT_NAME);
        assertThat(testCmnDocumentMaster.getCmnComments()).isEqualTo(DEFAULT_CMN_COMMENTS);
        assertThat(testCmnDocumentMaster.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    void fullUpdateCmnDocumentMasterWithPatch() throws Exception {
        // Initialize the database
        cmnDocumentMasterRepository.save(cmnDocumentMaster).block();

        int databaseSizeBeforeUpdate = cmnDocumentMasterRepository.findAll().collectList().block().size();

        // Update the cmnDocumentMaster using partial update
        CmnDocumentMaster partialUpdatedCmnDocumentMaster = new CmnDocumentMaster();
        partialUpdatedCmnDocumentMaster.setCmnDocumentId(cmnDocumentMaster.getCmnDocumentId());

        partialUpdatedCmnDocumentMaster
            .cmnId(UPDATED_CMN_ID)
            .cmnNo(UPDATED_CMN_NO)
            .generationDate(UPDATED_GENERATION_DATE)
            .initialDocumentName(UPDATED_INITIAL_DOCUMENT_NAME)
            .faxStatus(UPDATED_FAX_STATUS)
            .outBoundFaxStatus(UPDATED_OUT_BOUND_FAX_STATUS)
            .outBoundFaxNo(UPDATED_OUT_BOUND_FAX_NO)
            .outBoundFaxDateTime(UPDATED_OUT_BOUND_FAX_DATE_TIME)
            .inBoundFaxStatus(UPDATED_IN_BOUND_FAX_STATUS)
            .inBoundFaxNo(UPDATED_IN_BOUND_FAX_NO)
            .inBoundFaxDateTime(UPDATED_IN_BOUND_FAX_DATE_TIME)
            .cmnDocumentMasterUuid(UPDATED_CMN_DOCUMENT_MASTER_UUID)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .returnDocumentName(UPDATED_RETURN_DOCUMENT_NAME)
            .cmnComments(UPDATED_CMN_COMMENTS)
            .status(UPDATED_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCmnDocumentMaster.getCmnDocumentId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCmnDocumentMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CmnDocumentMaster in the database
        List<CmnDocumentMaster> cmnDocumentMasterList = cmnDocumentMasterRepository.findAll().collectList().block();
        assertThat(cmnDocumentMasterList).hasSize(databaseSizeBeforeUpdate);
        CmnDocumentMaster testCmnDocumentMaster = cmnDocumentMasterList.get(cmnDocumentMasterList.size() - 1);
        assertThat(testCmnDocumentMaster.getCmnId()).isEqualTo(UPDATED_CMN_ID);
        assertThat(testCmnDocumentMaster.getCmnNo()).isEqualTo(UPDATED_CMN_NO);
        assertThat(testCmnDocumentMaster.getGenerationDate()).isEqualTo(UPDATED_GENERATION_DATE);
        assertThat(testCmnDocumentMaster.getInitialDocumentName()).isEqualTo(UPDATED_INITIAL_DOCUMENT_NAME);
        assertThat(testCmnDocumentMaster.getFaxStatus()).isEqualTo(UPDATED_FAX_STATUS);
        assertThat(testCmnDocumentMaster.getOutBoundFaxStatus()).isEqualTo(UPDATED_OUT_BOUND_FAX_STATUS);
        assertThat(testCmnDocumentMaster.getOutBoundFaxNo()).isEqualTo(UPDATED_OUT_BOUND_FAX_NO);
        assertThat(testCmnDocumentMaster.getOutBoundFaxDateTime()).isEqualTo(UPDATED_OUT_BOUND_FAX_DATE_TIME);
        assertThat(testCmnDocumentMaster.getInBoundFaxStatus()).isEqualTo(UPDATED_IN_BOUND_FAX_STATUS);
        assertThat(testCmnDocumentMaster.getInBoundFaxNo()).isEqualTo(UPDATED_IN_BOUND_FAX_NO);
        assertThat(testCmnDocumentMaster.getInBoundFaxDateTime()).isEqualTo(UPDATED_IN_BOUND_FAX_DATE_TIME);
        assertThat(testCmnDocumentMaster.getCmnDocumentMasterUuid()).isEqualTo(UPDATED_CMN_DOCUMENT_MASTER_UUID);
        assertThat(testCmnDocumentMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testCmnDocumentMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCmnDocumentMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testCmnDocumentMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testCmnDocumentMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testCmnDocumentMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testCmnDocumentMaster.getReturnDocumentName()).isEqualTo(UPDATED_RETURN_DOCUMENT_NAME);
        assertThat(testCmnDocumentMaster.getCmnComments()).isEqualTo(UPDATED_CMN_COMMENTS);
        assertThat(testCmnDocumentMaster.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    void patchNonExistingCmnDocumentMaster() throws Exception {
        int databaseSizeBeforeUpdate = cmnDocumentMasterRepository.findAll().collectList().block().size();
        cmnDocumentMaster.setCmnDocumentId(count.incrementAndGet());

        // Create the CmnDocumentMaster
        CmnDocumentMasterDTO cmnDocumentMasterDTO = cmnDocumentMasterMapper.toDto(cmnDocumentMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, cmnDocumentMasterDTO.getCmnDocumentId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(cmnDocumentMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CmnDocumentMaster in the database
        List<CmnDocumentMaster> cmnDocumentMasterList = cmnDocumentMasterRepository.findAll().collectList().block();
        assertThat(cmnDocumentMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCmnDocumentMaster() throws Exception {
        int databaseSizeBeforeUpdate = cmnDocumentMasterRepository.findAll().collectList().block().size();
        cmnDocumentMaster.setCmnDocumentId(count.incrementAndGet());

        // Create the CmnDocumentMaster
        CmnDocumentMasterDTO cmnDocumentMasterDTO = cmnDocumentMasterMapper.toDto(cmnDocumentMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(cmnDocumentMasterDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CmnDocumentMaster in the database
        List<CmnDocumentMaster> cmnDocumentMasterList = cmnDocumentMasterRepository.findAll().collectList().block();
        assertThat(cmnDocumentMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCmnDocumentMaster() throws Exception {
        int databaseSizeBeforeUpdate = cmnDocumentMasterRepository.findAll().collectList().block().size();
        cmnDocumentMaster.setCmnDocumentId(count.incrementAndGet());

        // Create the CmnDocumentMaster
        CmnDocumentMasterDTO cmnDocumentMasterDTO = cmnDocumentMasterMapper.toDto(cmnDocumentMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(cmnDocumentMasterDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CmnDocumentMaster in the database
        List<CmnDocumentMaster> cmnDocumentMasterList = cmnDocumentMasterRepository.findAll().collectList().block();
        assertThat(cmnDocumentMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCmnDocumentMaster() {
        // Initialize the database
        cmnDocumentMasterRepository.save(cmnDocumentMaster).block();

        int databaseSizeBeforeDelete = cmnDocumentMasterRepository.findAll().collectList().block().size();

        // Delete the cmnDocumentMaster
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, cmnDocumentMaster.getCmnDocumentId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<CmnDocumentMaster> cmnDocumentMasterList = cmnDocumentMasterRepository.findAll().collectList().block();
        assertThat(cmnDocumentMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
