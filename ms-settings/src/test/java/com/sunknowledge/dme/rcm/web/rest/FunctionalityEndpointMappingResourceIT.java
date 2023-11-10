package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.FunctionalityEndpointMapping;
import com.sunknowledge.dme.rcm.repository.FunctionalityEndpointMappingRepository;
import com.sunknowledge.dme.rcm.service.dto.FunctionalityEndpointMappingDTO;
import com.sunknowledge.dme.rcm.service.mapper.FunctionalityEndpointMappingMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link FunctionalityEndpointMappingResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FunctionalityEndpointMappingResourceIT {

    private static final Long DEFAULT_ENDPOINT_ID = 1L;
    private static final Long UPDATED_ENDPOINT_ID = 2L;

    private static final Long DEFAULT_FUNCTIONALITY_ID = 1L;
    private static final Long UPDATED_FUNCTIONALITY_ID = 2L;

    private static final String DEFAULT_MAPPING_DESC = "AAAAAAAAAA";
    private static final String UPDATED_MAPPING_DESC = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_FUNCTIONALITY_ENDPOINT_MAPPING_UUID = UUID.randomUUID();
    private static final UUID UPDATED_FUNCTIONALITY_ENDPOINT_MAPPING_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/functionality-endpoint-mappings";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{functionalityEndpointMappingId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FunctionalityEndpointMappingRepository functionalityEndpointMappingRepository;

    @Autowired
    private FunctionalityEndpointMappingMapper functionalityEndpointMappingMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFunctionalityEndpointMappingMockMvc;

    private FunctionalityEndpointMapping functionalityEndpointMapping;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FunctionalityEndpointMapping createEntity(EntityManager em) {
        FunctionalityEndpointMapping functionalityEndpointMapping = new FunctionalityEndpointMapping()
            .endpointId(DEFAULT_ENDPOINT_ID)
            .functionalityId(DEFAULT_FUNCTIONALITY_ID)
            .mappingDesc(DEFAULT_MAPPING_DESC)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .functionalityEndpointMappingUuid(DEFAULT_FUNCTIONALITY_ENDPOINT_MAPPING_UUID);
        return functionalityEndpointMapping;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FunctionalityEndpointMapping createUpdatedEntity(EntityManager em) {
        FunctionalityEndpointMapping functionalityEndpointMapping = new FunctionalityEndpointMapping()
            .endpointId(UPDATED_ENDPOINT_ID)
            .functionalityId(UPDATED_FUNCTIONALITY_ID)
            .mappingDesc(UPDATED_MAPPING_DESC)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .functionalityEndpointMappingUuid(UPDATED_FUNCTIONALITY_ENDPOINT_MAPPING_UUID);
        return functionalityEndpointMapping;
    }

    @BeforeEach
    public void initTest() {
        functionalityEndpointMapping = createEntity(em);
    }

    @Test
    @Transactional
    void createFunctionalityEndpointMapping() throws Exception {
        int databaseSizeBeforeCreate = functionalityEndpointMappingRepository.findAll().size();
        // Create the FunctionalityEndpointMapping
        FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO = functionalityEndpointMappingMapper.toDto(
            functionalityEndpointMapping
        );
        restFunctionalityEndpointMappingMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(functionalityEndpointMappingDTO))
            )
            .andExpect(status().isCreated());

        // Validate the FunctionalityEndpointMapping in the database
        List<FunctionalityEndpointMapping> functionalityEndpointMappingList = functionalityEndpointMappingRepository.findAll();
        assertThat(functionalityEndpointMappingList).hasSize(databaseSizeBeforeCreate + 1);
        FunctionalityEndpointMapping testFunctionalityEndpointMapping = functionalityEndpointMappingList.get(
            functionalityEndpointMappingList.size() - 1
        );
        assertThat(testFunctionalityEndpointMapping.getEndpointId()).isEqualTo(DEFAULT_ENDPOINT_ID);
        assertThat(testFunctionalityEndpointMapping.getFunctionalityId()).isEqualTo(DEFAULT_FUNCTIONALITY_ID);
        assertThat(testFunctionalityEndpointMapping.getMappingDesc()).isEqualTo(DEFAULT_MAPPING_DESC);
        assertThat(testFunctionalityEndpointMapping.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFunctionalityEndpointMapping.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testFunctionalityEndpointMapping.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testFunctionalityEndpointMapping.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFunctionalityEndpointMapping.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testFunctionalityEndpointMapping.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testFunctionalityEndpointMapping.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testFunctionalityEndpointMapping.getFunctionalityEndpointMappingUuid())
            .isEqualTo(DEFAULT_FUNCTIONALITY_ENDPOINT_MAPPING_UUID);
    }

    @Test
    @Transactional
    void createFunctionalityEndpointMappingWithExistingId() throws Exception {
        // Create the FunctionalityEndpointMapping with an existing ID
        functionalityEndpointMapping.setFunctionalityEndpointMappingId(1L);
        FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO = functionalityEndpointMappingMapper.toDto(
            functionalityEndpointMapping
        );

        int databaseSizeBeforeCreate = functionalityEndpointMappingRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFunctionalityEndpointMappingMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(functionalityEndpointMappingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FunctionalityEndpointMapping in the database
        List<FunctionalityEndpointMapping> functionalityEndpointMappingList = functionalityEndpointMappingRepository.findAll();
        assertThat(functionalityEndpointMappingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFunctionalityEndpointMappings() throws Exception {
        // Initialize the database
        functionalityEndpointMappingRepository.saveAndFlush(functionalityEndpointMapping);

        // Get all the functionalityEndpointMappingList
        restFunctionalityEndpointMappingMockMvc
            .perform(get(ENTITY_API_URL + "?sort=functionalityEndpointMappingId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].functionalityEndpointMappingId")
                    .value(hasItem(functionalityEndpointMapping.getFunctionalityEndpointMappingId().intValue()))
            )
            .andExpect(jsonPath("$.[*].endpointId").value(hasItem(DEFAULT_ENDPOINT_ID.intValue())))
            .andExpect(jsonPath("$.[*].functionalityId").value(hasItem(DEFAULT_FUNCTIONALITY_ID.intValue())))
            .andExpect(jsonPath("$.[*].mappingDesc").value(hasItem(DEFAULT_MAPPING_DESC)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(
                jsonPath("$.[*].functionalityEndpointMappingUuid").value(hasItem(DEFAULT_FUNCTIONALITY_ENDPOINT_MAPPING_UUID.toString()))
            );
    }

    @Test
    @Transactional
    void getFunctionalityEndpointMapping() throws Exception {
        // Initialize the database
        functionalityEndpointMappingRepository.saveAndFlush(functionalityEndpointMapping);

        // Get the functionalityEndpointMapping
        restFunctionalityEndpointMappingMockMvc
            .perform(get(ENTITY_API_URL_ID, functionalityEndpointMapping.getFunctionalityEndpointMappingId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.functionalityEndpointMappingId")
                    .value(functionalityEndpointMapping.getFunctionalityEndpointMappingId().intValue())
            )
            .andExpect(jsonPath("$.endpointId").value(DEFAULT_ENDPOINT_ID.intValue()))
            .andExpect(jsonPath("$.functionalityId").value(DEFAULT_FUNCTIONALITY_ID.intValue()))
            .andExpect(jsonPath("$.mappingDesc").value(DEFAULT_MAPPING_DESC))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.functionalityEndpointMappingUuid").value(DEFAULT_FUNCTIONALITY_ENDPOINT_MAPPING_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingFunctionalityEndpointMapping() throws Exception {
        // Get the functionalityEndpointMapping
        restFunctionalityEndpointMappingMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewFunctionalityEndpointMapping() throws Exception {
        // Initialize the database
        functionalityEndpointMappingRepository.saveAndFlush(functionalityEndpointMapping);

        int databaseSizeBeforeUpdate = functionalityEndpointMappingRepository.findAll().size();

        // Update the functionalityEndpointMapping
        FunctionalityEndpointMapping updatedFunctionalityEndpointMapping = functionalityEndpointMappingRepository
            .findById(functionalityEndpointMapping.getFunctionalityEndpointMappingId())
            .get();
        // Disconnect from session so that the updates on updatedFunctionalityEndpointMapping are not directly saved in db
        em.detach(updatedFunctionalityEndpointMapping);
        updatedFunctionalityEndpointMapping
            .endpointId(UPDATED_ENDPOINT_ID)
            .functionalityId(UPDATED_FUNCTIONALITY_ID)
            .mappingDesc(UPDATED_MAPPING_DESC)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .functionalityEndpointMappingUuid(UPDATED_FUNCTIONALITY_ENDPOINT_MAPPING_UUID);
        FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO = functionalityEndpointMappingMapper.toDto(
            updatedFunctionalityEndpointMapping
        );

        restFunctionalityEndpointMappingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, functionalityEndpointMappingDTO.getFunctionalityEndpointMappingId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(functionalityEndpointMappingDTO))
            )
            .andExpect(status().isOk());

        // Validate the FunctionalityEndpointMapping in the database
        List<FunctionalityEndpointMapping> functionalityEndpointMappingList = functionalityEndpointMappingRepository.findAll();
        assertThat(functionalityEndpointMappingList).hasSize(databaseSizeBeforeUpdate);
        FunctionalityEndpointMapping testFunctionalityEndpointMapping = functionalityEndpointMappingList.get(
            functionalityEndpointMappingList.size() - 1
        );
        assertThat(testFunctionalityEndpointMapping.getEndpointId()).isEqualTo(UPDATED_ENDPOINT_ID);
        assertThat(testFunctionalityEndpointMapping.getFunctionalityId()).isEqualTo(UPDATED_FUNCTIONALITY_ID);
        assertThat(testFunctionalityEndpointMapping.getMappingDesc()).isEqualTo(UPDATED_MAPPING_DESC);
        assertThat(testFunctionalityEndpointMapping.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFunctionalityEndpointMapping.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testFunctionalityEndpointMapping.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testFunctionalityEndpointMapping.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFunctionalityEndpointMapping.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testFunctionalityEndpointMapping.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testFunctionalityEndpointMapping.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testFunctionalityEndpointMapping.getFunctionalityEndpointMappingUuid())
            .isEqualTo(UPDATED_FUNCTIONALITY_ENDPOINT_MAPPING_UUID);
    }

    @Test
    @Transactional
    void putNonExistingFunctionalityEndpointMapping() throws Exception {
        int databaseSizeBeforeUpdate = functionalityEndpointMappingRepository.findAll().size();
        functionalityEndpointMapping.setFunctionalityEndpointMappingId(count.incrementAndGet());

        // Create the FunctionalityEndpointMapping
        FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO = functionalityEndpointMappingMapper.toDto(
            functionalityEndpointMapping
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFunctionalityEndpointMappingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, functionalityEndpointMappingDTO.getFunctionalityEndpointMappingId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(functionalityEndpointMappingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FunctionalityEndpointMapping in the database
        List<FunctionalityEndpointMapping> functionalityEndpointMappingList = functionalityEndpointMappingRepository.findAll();
        assertThat(functionalityEndpointMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFunctionalityEndpointMapping() throws Exception {
        int databaseSizeBeforeUpdate = functionalityEndpointMappingRepository.findAll().size();
        functionalityEndpointMapping.setFunctionalityEndpointMappingId(count.incrementAndGet());

        // Create the FunctionalityEndpointMapping
        FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO = functionalityEndpointMappingMapper.toDto(
            functionalityEndpointMapping
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFunctionalityEndpointMappingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(functionalityEndpointMappingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FunctionalityEndpointMapping in the database
        List<FunctionalityEndpointMapping> functionalityEndpointMappingList = functionalityEndpointMappingRepository.findAll();
        assertThat(functionalityEndpointMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFunctionalityEndpointMapping() throws Exception {
        int databaseSizeBeforeUpdate = functionalityEndpointMappingRepository.findAll().size();
        functionalityEndpointMapping.setFunctionalityEndpointMappingId(count.incrementAndGet());

        // Create the FunctionalityEndpointMapping
        FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO = functionalityEndpointMappingMapper.toDto(
            functionalityEndpointMapping
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFunctionalityEndpointMappingMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(functionalityEndpointMappingDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FunctionalityEndpointMapping in the database
        List<FunctionalityEndpointMapping> functionalityEndpointMappingList = functionalityEndpointMappingRepository.findAll();
        assertThat(functionalityEndpointMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFunctionalityEndpointMappingWithPatch() throws Exception {
        // Initialize the database
        functionalityEndpointMappingRepository.saveAndFlush(functionalityEndpointMapping);

        int databaseSizeBeforeUpdate = functionalityEndpointMappingRepository.findAll().size();

        // Update the functionalityEndpointMapping using partial update
        FunctionalityEndpointMapping partialUpdatedFunctionalityEndpointMapping = new FunctionalityEndpointMapping();
        partialUpdatedFunctionalityEndpointMapping.setFunctionalityEndpointMappingId(
            functionalityEndpointMapping.getFunctionalityEndpointMappingId()
        );

        partialUpdatedFunctionalityEndpointMapping
            .endpointId(UPDATED_ENDPOINT_ID)
            .functionalityId(UPDATED_FUNCTIONALITY_ID)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME);

        restFunctionalityEndpointMappingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFunctionalityEndpointMapping.getFunctionalityEndpointMappingId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFunctionalityEndpointMapping))
            )
            .andExpect(status().isOk());

        // Validate the FunctionalityEndpointMapping in the database
        List<FunctionalityEndpointMapping> functionalityEndpointMappingList = functionalityEndpointMappingRepository.findAll();
        assertThat(functionalityEndpointMappingList).hasSize(databaseSizeBeforeUpdate);
        FunctionalityEndpointMapping testFunctionalityEndpointMapping = functionalityEndpointMappingList.get(
            functionalityEndpointMappingList.size() - 1
        );
        assertThat(testFunctionalityEndpointMapping.getEndpointId()).isEqualTo(UPDATED_ENDPOINT_ID);
        assertThat(testFunctionalityEndpointMapping.getFunctionalityId()).isEqualTo(UPDATED_FUNCTIONALITY_ID);
        assertThat(testFunctionalityEndpointMapping.getMappingDesc()).isEqualTo(DEFAULT_MAPPING_DESC);
        assertThat(testFunctionalityEndpointMapping.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFunctionalityEndpointMapping.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testFunctionalityEndpointMapping.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testFunctionalityEndpointMapping.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFunctionalityEndpointMapping.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testFunctionalityEndpointMapping.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testFunctionalityEndpointMapping.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testFunctionalityEndpointMapping.getFunctionalityEndpointMappingUuid())
            .isEqualTo(DEFAULT_FUNCTIONALITY_ENDPOINT_MAPPING_UUID);
    }

    @Test
    @Transactional
    void fullUpdateFunctionalityEndpointMappingWithPatch() throws Exception {
        // Initialize the database
        functionalityEndpointMappingRepository.saveAndFlush(functionalityEndpointMapping);

        int databaseSizeBeforeUpdate = functionalityEndpointMappingRepository.findAll().size();

        // Update the functionalityEndpointMapping using partial update
        FunctionalityEndpointMapping partialUpdatedFunctionalityEndpointMapping = new FunctionalityEndpointMapping();
        partialUpdatedFunctionalityEndpointMapping.setFunctionalityEndpointMappingId(
            functionalityEndpointMapping.getFunctionalityEndpointMappingId()
        );

        partialUpdatedFunctionalityEndpointMapping
            .endpointId(UPDATED_ENDPOINT_ID)
            .functionalityId(UPDATED_FUNCTIONALITY_ID)
            .mappingDesc(UPDATED_MAPPING_DESC)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .functionalityEndpointMappingUuid(UPDATED_FUNCTIONALITY_ENDPOINT_MAPPING_UUID);

        restFunctionalityEndpointMappingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFunctionalityEndpointMapping.getFunctionalityEndpointMappingId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFunctionalityEndpointMapping))
            )
            .andExpect(status().isOk());

        // Validate the FunctionalityEndpointMapping in the database
        List<FunctionalityEndpointMapping> functionalityEndpointMappingList = functionalityEndpointMappingRepository.findAll();
        assertThat(functionalityEndpointMappingList).hasSize(databaseSizeBeforeUpdate);
        FunctionalityEndpointMapping testFunctionalityEndpointMapping = functionalityEndpointMappingList.get(
            functionalityEndpointMappingList.size() - 1
        );
        assertThat(testFunctionalityEndpointMapping.getEndpointId()).isEqualTo(UPDATED_ENDPOINT_ID);
        assertThat(testFunctionalityEndpointMapping.getFunctionalityId()).isEqualTo(UPDATED_FUNCTIONALITY_ID);
        assertThat(testFunctionalityEndpointMapping.getMappingDesc()).isEqualTo(UPDATED_MAPPING_DESC);
        assertThat(testFunctionalityEndpointMapping.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFunctionalityEndpointMapping.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testFunctionalityEndpointMapping.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testFunctionalityEndpointMapping.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFunctionalityEndpointMapping.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testFunctionalityEndpointMapping.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testFunctionalityEndpointMapping.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testFunctionalityEndpointMapping.getFunctionalityEndpointMappingUuid())
            .isEqualTo(UPDATED_FUNCTIONALITY_ENDPOINT_MAPPING_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingFunctionalityEndpointMapping() throws Exception {
        int databaseSizeBeforeUpdate = functionalityEndpointMappingRepository.findAll().size();
        functionalityEndpointMapping.setFunctionalityEndpointMappingId(count.incrementAndGet());

        // Create the FunctionalityEndpointMapping
        FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO = functionalityEndpointMappingMapper.toDto(
            functionalityEndpointMapping
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFunctionalityEndpointMappingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, functionalityEndpointMappingDTO.getFunctionalityEndpointMappingId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(functionalityEndpointMappingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FunctionalityEndpointMapping in the database
        List<FunctionalityEndpointMapping> functionalityEndpointMappingList = functionalityEndpointMappingRepository.findAll();
        assertThat(functionalityEndpointMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFunctionalityEndpointMapping() throws Exception {
        int databaseSizeBeforeUpdate = functionalityEndpointMappingRepository.findAll().size();
        functionalityEndpointMapping.setFunctionalityEndpointMappingId(count.incrementAndGet());

        // Create the FunctionalityEndpointMapping
        FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO = functionalityEndpointMappingMapper.toDto(
            functionalityEndpointMapping
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFunctionalityEndpointMappingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(functionalityEndpointMappingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FunctionalityEndpointMapping in the database
        List<FunctionalityEndpointMapping> functionalityEndpointMappingList = functionalityEndpointMappingRepository.findAll();
        assertThat(functionalityEndpointMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFunctionalityEndpointMapping() throws Exception {
        int databaseSizeBeforeUpdate = functionalityEndpointMappingRepository.findAll().size();
        functionalityEndpointMapping.setFunctionalityEndpointMappingId(count.incrementAndGet());

        // Create the FunctionalityEndpointMapping
        FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO = functionalityEndpointMappingMapper.toDto(
            functionalityEndpointMapping
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFunctionalityEndpointMappingMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(functionalityEndpointMappingDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FunctionalityEndpointMapping in the database
        List<FunctionalityEndpointMapping> functionalityEndpointMappingList = functionalityEndpointMappingRepository.findAll();
        assertThat(functionalityEndpointMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFunctionalityEndpointMapping() throws Exception {
        // Initialize the database
        functionalityEndpointMappingRepository.saveAndFlush(functionalityEndpointMapping);

        int databaseSizeBeforeDelete = functionalityEndpointMappingRepository.findAll().size();

        // Delete the functionalityEndpointMapping
        restFunctionalityEndpointMappingMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, functionalityEndpointMapping.getFunctionalityEndpointMappingId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FunctionalityEndpointMapping> functionalityEndpointMappingList = functionalityEndpointMappingRepository.findAll();
        assertThat(functionalityEndpointMappingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
