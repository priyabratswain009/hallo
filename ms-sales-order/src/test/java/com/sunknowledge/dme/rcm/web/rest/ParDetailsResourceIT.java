package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ParDetails;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.ParDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.ParDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.ParDetailsMapper;
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
 * Integration tests for the {@link ParDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ParDetailsResourceIT {

    private static final Long DEFAULT_PAR_ID = 1L;
    private static final Long UPDATED_PAR_ID = 2L;

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final String DEFAULT_ITEM_NO = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_UOM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_UOM = "BBBBBBBBBB";

    private static final Double DEFAULT_ITEM_QUANTITY = 1D;
    private static final Double UPDATED_ITEM_QUANTITY = 2D;

    private static final String DEFAULT_HCPCS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_HCPCS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_PAR_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PAR_DETAILS_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/par-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{parDetailsId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ParDetailsRepository parDetailsRepository;

    @Autowired
    private ParDetailsMapper parDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ParDetails parDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParDetails createEntity(EntityManager em) {
        ParDetails parDetails = new ParDetails()
            .parId(DEFAULT_PAR_ID)
            .itemId(DEFAULT_ITEM_ID)
            .itemNo(DEFAULT_ITEM_NO)
            .itemUom(DEFAULT_ITEM_UOM)
            .itemQuantity(DEFAULT_ITEM_QUANTITY)
            .hcpcsCode(DEFAULT_HCPCS_CODE)
            .description(DEFAULT_DESCRIPTION)
            .itemName(DEFAULT_ITEM_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .parDetailsUuid(DEFAULT_PAR_DETAILS_UUID);
        return parDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParDetails createUpdatedEntity(EntityManager em) {
        ParDetails parDetails = new ParDetails()
            .parId(UPDATED_PAR_ID)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemUom(UPDATED_ITEM_UOM)
            .itemQuantity(UPDATED_ITEM_QUANTITY)
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .description(UPDATED_DESCRIPTION)
            .itemName(UPDATED_ITEM_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .parDetailsUuid(UPDATED_PAR_DETAILS_UUID);
        return parDetails;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ParDetails.class).block();
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
        parDetails = createEntity(em);
    }

    @Test
    void createParDetails() throws Exception {
        int databaseSizeBeforeCreate = parDetailsRepository.findAll().collectList().block().size();
        // Create the ParDetails
        ParDetailsDTO parDetailsDTO = parDetailsMapper.toDto(parDetails);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parDetailsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ParDetails in the database
        List<ParDetails> parDetailsList = parDetailsRepository.findAll().collectList().block();
        assertThat(parDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        ParDetails testParDetails = parDetailsList.get(parDetailsList.size() - 1);
        assertThat(testParDetails.getParId()).isEqualTo(DEFAULT_PAR_ID);
        assertThat(testParDetails.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testParDetails.getItemNo()).isEqualTo(DEFAULT_ITEM_NO);
        assertThat(testParDetails.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testParDetails.getItemQuantity()).isEqualTo(DEFAULT_ITEM_QUANTITY);
        assertThat(testParDetails.getHcpcsCode()).isEqualTo(DEFAULT_HCPCS_CODE);
        assertThat(testParDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testParDetails.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testParDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testParDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testParDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testParDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testParDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testParDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testParDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testParDetails.getParDetailsUuid()).isEqualTo(DEFAULT_PAR_DETAILS_UUID);
    }

    @Test
    void createParDetailsWithExistingId() throws Exception {
        // Create the ParDetails with an existing ID
        parDetails.setParDetailsId(1L);
        ParDetailsDTO parDetailsDTO = parDetailsMapper.toDto(parDetails);

        int databaseSizeBeforeCreate = parDetailsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParDetails in the database
        List<ParDetails> parDetailsList = parDetailsRepository.findAll().collectList().block();
        assertThat(parDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllParDetails() {
        // Initialize the database
        parDetailsRepository.save(parDetails).block();

        // Get all the parDetailsList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=parDetailsId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].parDetailsId")
            .value(hasItem(parDetails.getParDetailsId().intValue()))
            .jsonPath("$.[*].parId")
            .value(hasItem(DEFAULT_PAR_ID.intValue()))
            .jsonPath("$.[*].itemId")
            .value(hasItem(DEFAULT_ITEM_ID.intValue()))
            .jsonPath("$.[*].itemNo")
            .value(hasItem(DEFAULT_ITEM_NO))
            .jsonPath("$.[*].itemUom")
            .value(hasItem(DEFAULT_ITEM_UOM))
            .jsonPath("$.[*].itemQuantity")
            .value(hasItem(DEFAULT_ITEM_QUANTITY.doubleValue()))
            .jsonPath("$.[*].hcpcsCode")
            .value(hasItem(DEFAULT_HCPCS_CODE))
            .jsonPath("$.[*].description")
            .value(hasItem(DEFAULT_DESCRIPTION))
            .jsonPath("$.[*].itemName")
            .value(hasItem(DEFAULT_ITEM_NAME))
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
            .jsonPath("$.[*].parDetailsUuid")
            .value(hasItem(DEFAULT_PAR_DETAILS_UUID.toString()));
    }

    @Test
    void getParDetails() {
        // Initialize the database
        parDetailsRepository.save(parDetails).block();

        // Get the parDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, parDetails.getParDetailsId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.parDetailsId")
            .value(is(parDetails.getParDetailsId().intValue()))
            .jsonPath("$.parId")
            .value(is(DEFAULT_PAR_ID.intValue()))
            .jsonPath("$.itemId")
            .value(is(DEFAULT_ITEM_ID.intValue()))
            .jsonPath("$.itemNo")
            .value(is(DEFAULT_ITEM_NO))
            .jsonPath("$.itemUom")
            .value(is(DEFAULT_ITEM_UOM))
            .jsonPath("$.itemQuantity")
            .value(is(DEFAULT_ITEM_QUANTITY.doubleValue()))
            .jsonPath("$.hcpcsCode")
            .value(is(DEFAULT_HCPCS_CODE))
            .jsonPath("$.description")
            .value(is(DEFAULT_DESCRIPTION))
            .jsonPath("$.itemName")
            .value(is(DEFAULT_ITEM_NAME))
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
            .jsonPath("$.parDetailsUuid")
            .value(is(DEFAULT_PAR_DETAILS_UUID.toString()));
    }

    @Test
    void getNonExistingParDetails() {
        // Get the parDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingParDetails() throws Exception {
        // Initialize the database
        parDetailsRepository.save(parDetails).block();

        int databaseSizeBeforeUpdate = parDetailsRepository.findAll().collectList().block().size();

        // Update the parDetails
        ParDetails updatedParDetails = parDetailsRepository.findById(parDetails.getParDetailsId()).block();
        updatedParDetails
            .parId(UPDATED_PAR_ID)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemUom(UPDATED_ITEM_UOM)
            .itemQuantity(UPDATED_ITEM_QUANTITY)
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .description(UPDATED_DESCRIPTION)
            .itemName(UPDATED_ITEM_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .parDetailsUuid(UPDATED_PAR_DETAILS_UUID);
        ParDetailsDTO parDetailsDTO = parDetailsMapper.toDto(updatedParDetails);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, parDetailsDTO.getParDetailsId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parDetailsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ParDetails in the database
        List<ParDetails> parDetailsList = parDetailsRepository.findAll().collectList().block();
        assertThat(parDetailsList).hasSize(databaseSizeBeforeUpdate);
        ParDetails testParDetails = parDetailsList.get(parDetailsList.size() - 1);
        assertThat(testParDetails.getParId()).isEqualTo(UPDATED_PAR_ID);
        assertThat(testParDetails.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testParDetails.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testParDetails.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testParDetails.getItemQuantity()).isEqualTo(UPDATED_ITEM_QUANTITY);
        assertThat(testParDetails.getHcpcsCode()).isEqualTo(UPDATED_HCPCS_CODE);
        assertThat(testParDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testParDetails.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testParDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testParDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testParDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testParDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testParDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testParDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testParDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testParDetails.getParDetailsUuid()).isEqualTo(UPDATED_PAR_DETAILS_UUID);
    }

    @Test
    void putNonExistingParDetails() throws Exception {
        int databaseSizeBeforeUpdate = parDetailsRepository.findAll().collectList().block().size();
        parDetails.setParDetailsId(count.incrementAndGet());

        // Create the ParDetails
        ParDetailsDTO parDetailsDTO = parDetailsMapper.toDto(parDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, parDetailsDTO.getParDetailsId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParDetails in the database
        List<ParDetails> parDetailsList = parDetailsRepository.findAll().collectList().block();
        assertThat(parDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchParDetails() throws Exception {
        int databaseSizeBeforeUpdate = parDetailsRepository.findAll().collectList().block().size();
        parDetails.setParDetailsId(count.incrementAndGet());

        // Create the ParDetails
        ParDetailsDTO parDetailsDTO = parDetailsMapper.toDto(parDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParDetails in the database
        List<ParDetails> parDetailsList = parDetailsRepository.findAll().collectList().block();
        assertThat(parDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamParDetails() throws Exception {
        int databaseSizeBeforeUpdate = parDetailsRepository.findAll().collectList().block().size();
        parDetails.setParDetailsId(count.incrementAndGet());

        // Create the ParDetails
        ParDetailsDTO parDetailsDTO = parDetailsMapper.toDto(parDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(parDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ParDetails in the database
        List<ParDetails> parDetailsList = parDetailsRepository.findAll().collectList().block();
        assertThat(parDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateParDetailsWithPatch() throws Exception {
        // Initialize the database
        parDetailsRepository.save(parDetails).block();

        int databaseSizeBeforeUpdate = parDetailsRepository.findAll().collectList().block().size();

        // Update the parDetails using partial update
        ParDetails partialUpdatedParDetails = new ParDetails();
        partialUpdatedParDetails.setParDetailsId(parDetails.getParDetailsId());

        partialUpdatedParDetails
            .parId(UPDATED_PAR_ID)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedParDetails.getParDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedParDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ParDetails in the database
        List<ParDetails> parDetailsList = parDetailsRepository.findAll().collectList().block();
        assertThat(parDetailsList).hasSize(databaseSizeBeforeUpdate);
        ParDetails testParDetails = parDetailsList.get(parDetailsList.size() - 1);
        assertThat(testParDetails.getParId()).isEqualTo(UPDATED_PAR_ID);
        assertThat(testParDetails.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testParDetails.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testParDetails.getItemUom()).isEqualTo(DEFAULT_ITEM_UOM);
        assertThat(testParDetails.getItemQuantity()).isEqualTo(DEFAULT_ITEM_QUANTITY);
        assertThat(testParDetails.getHcpcsCode()).isEqualTo(UPDATED_HCPCS_CODE);
        assertThat(testParDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testParDetails.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testParDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testParDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testParDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testParDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testParDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testParDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testParDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testParDetails.getParDetailsUuid()).isEqualTo(DEFAULT_PAR_DETAILS_UUID);
    }

    @Test
    void fullUpdateParDetailsWithPatch() throws Exception {
        // Initialize the database
        parDetailsRepository.save(parDetails).block();

        int databaseSizeBeforeUpdate = parDetailsRepository.findAll().collectList().block().size();

        // Update the parDetails using partial update
        ParDetails partialUpdatedParDetails = new ParDetails();
        partialUpdatedParDetails.setParDetailsId(parDetails.getParDetailsId());

        partialUpdatedParDetails
            .parId(UPDATED_PAR_ID)
            .itemId(UPDATED_ITEM_ID)
            .itemNo(UPDATED_ITEM_NO)
            .itemUom(UPDATED_ITEM_UOM)
            .itemQuantity(UPDATED_ITEM_QUANTITY)
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .description(UPDATED_DESCRIPTION)
            .itemName(UPDATED_ITEM_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .parDetailsUuid(UPDATED_PAR_DETAILS_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedParDetails.getParDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedParDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ParDetails in the database
        List<ParDetails> parDetailsList = parDetailsRepository.findAll().collectList().block();
        assertThat(parDetailsList).hasSize(databaseSizeBeforeUpdate);
        ParDetails testParDetails = parDetailsList.get(parDetailsList.size() - 1);
        assertThat(testParDetails.getParId()).isEqualTo(UPDATED_PAR_ID);
        assertThat(testParDetails.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testParDetails.getItemNo()).isEqualTo(UPDATED_ITEM_NO);
        assertThat(testParDetails.getItemUom()).isEqualTo(UPDATED_ITEM_UOM);
        assertThat(testParDetails.getItemQuantity()).isEqualTo(UPDATED_ITEM_QUANTITY);
        assertThat(testParDetails.getHcpcsCode()).isEqualTo(UPDATED_HCPCS_CODE);
        assertThat(testParDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testParDetails.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testParDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testParDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testParDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testParDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testParDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testParDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testParDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testParDetails.getParDetailsUuid()).isEqualTo(UPDATED_PAR_DETAILS_UUID);
    }

    @Test
    void patchNonExistingParDetails() throws Exception {
        int databaseSizeBeforeUpdate = parDetailsRepository.findAll().collectList().block().size();
        parDetails.setParDetailsId(count.incrementAndGet());

        // Create the ParDetails
        ParDetailsDTO parDetailsDTO = parDetailsMapper.toDto(parDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, parDetailsDTO.getParDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(parDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParDetails in the database
        List<ParDetails> parDetailsList = parDetailsRepository.findAll().collectList().block();
        assertThat(parDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchParDetails() throws Exception {
        int databaseSizeBeforeUpdate = parDetailsRepository.findAll().collectList().block().size();
        parDetails.setParDetailsId(count.incrementAndGet());

        // Create the ParDetails
        ParDetailsDTO parDetailsDTO = parDetailsMapper.toDto(parDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(parDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParDetails in the database
        List<ParDetails> parDetailsList = parDetailsRepository.findAll().collectList().block();
        assertThat(parDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamParDetails() throws Exception {
        int databaseSizeBeforeUpdate = parDetailsRepository.findAll().collectList().block().size();
        parDetails.setParDetailsId(count.incrementAndGet());

        // Create the ParDetails
        ParDetailsDTO parDetailsDTO = parDetailsMapper.toDto(parDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(parDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ParDetails in the database
        List<ParDetails> parDetailsList = parDetailsRepository.findAll().collectList().block();
        assertThat(parDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteParDetails() {
        // Initialize the database
        parDetailsRepository.save(parDetails).block();

        int databaseSizeBeforeDelete = parDetailsRepository.findAll().collectList().block().size();

        // Delete the parDetails
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, parDetails.getParDetailsId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ParDetails> parDetailsList = parDetailsRepository.findAll().collectList().block();
        assertThat(parDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
