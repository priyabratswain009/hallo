package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.DepreciationMethod;
import com.sunknowledge.dme.rcm.repository.DepreciationMethodRepository;
import com.sunknowledge.dme.rcm.service.dto.DepreciationMethodDTO;
import com.sunknowledge.dme.rcm.service.mapper.DepreciationMethodMapper;
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
 * Integration tests for the {@link DepreciationMethodResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DepreciationMethodResourceIT {

    private static final String DEFAULT_DEPRECIATION_METHOD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEPRECIATION_METHOD_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_DEPRECIATION_METHOD_UUID = UUID.randomUUID();
    private static final UUID UPDATED_DEPRECIATION_METHOD_UUID = UUID.randomUUID();

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/depreciation-methods";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{depreciationMethodId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DepreciationMethodRepository depreciationMethodRepository;

    @Autowired
    private DepreciationMethodMapper depreciationMethodMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDepreciationMethodMockMvc;

    private DepreciationMethod depreciationMethod;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DepreciationMethod createEntity(EntityManager em) {
        DepreciationMethod depreciationMethod = new DepreciationMethod()
            .depreciationMethodName(DEFAULT_DEPRECIATION_METHOD_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .depreciationMethodUuid(DEFAULT_DEPRECIATION_METHOD_UUID)
            .updatedDate(DEFAULT_UPDATED_DATE);
        return depreciationMethod;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DepreciationMethod createUpdatedEntity(EntityManager em) {
        DepreciationMethod depreciationMethod = new DepreciationMethod()
            .depreciationMethodName(UPDATED_DEPRECIATION_METHOD_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .depreciationMethodUuid(UPDATED_DEPRECIATION_METHOD_UUID)
            .updatedDate(UPDATED_UPDATED_DATE);
        return depreciationMethod;
    }

    @BeforeEach
    public void initTest() {
        depreciationMethod = createEntity(em);
    }

    @Test
    @Transactional
    void createDepreciationMethod() throws Exception {
        int databaseSizeBeforeCreate = depreciationMethodRepository.findAll().size();
        // Create the DepreciationMethod
        DepreciationMethodDTO depreciationMethodDTO = depreciationMethodMapper.toDto(depreciationMethod);
        restDepreciationMethodMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(depreciationMethodDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DepreciationMethod in the database
        List<DepreciationMethod> depreciationMethodList = depreciationMethodRepository.findAll();
        assertThat(depreciationMethodList).hasSize(databaseSizeBeforeCreate + 1);
        DepreciationMethod testDepreciationMethod = depreciationMethodList.get(depreciationMethodList.size() - 1);
        assertThat(testDepreciationMethod.getDepreciationMethodName()).isEqualTo(DEFAULT_DEPRECIATION_METHOD_NAME);
        assertThat(testDepreciationMethod.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDepreciationMethod.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDepreciationMethod.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDepreciationMethod.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testDepreciationMethod.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDepreciationMethod.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDepreciationMethod.getDepreciationMethodUuid()).isEqualTo(DEFAULT_DEPRECIATION_METHOD_UUID);
        assertThat(testDepreciationMethod.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
    }

    @Test
    @Transactional
    void createDepreciationMethodWithExistingId() throws Exception {
        // Create the DepreciationMethod with an existing ID
        depreciationMethod.setDepreciationMethodId(1L);
        DepreciationMethodDTO depreciationMethodDTO = depreciationMethodMapper.toDto(depreciationMethod);

        int databaseSizeBeforeCreate = depreciationMethodRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepreciationMethodMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(depreciationMethodDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DepreciationMethod in the database
        List<DepreciationMethod> depreciationMethodList = depreciationMethodRepository.findAll();
        assertThat(depreciationMethodList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDepreciationMethods() throws Exception {
        // Initialize the database
        depreciationMethodRepository.saveAndFlush(depreciationMethod);

        // Get all the depreciationMethodList
        restDepreciationMethodMockMvc
            .perform(get(ENTITY_API_URL + "?sort=depreciationMethodId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].depreciationMethodId").value(hasItem(depreciationMethod.getDepreciationMethodId().intValue())))
            .andExpect(jsonPath("$.[*].depreciationMethodName").value(hasItem(DEFAULT_DEPRECIATION_METHOD_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].depreciationMethodUuid").value(hasItem(DEFAULT_DEPRECIATION_METHOD_UUID.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())));
    }

    @Test
    @Transactional
    void getDepreciationMethod() throws Exception {
        // Initialize the database
        depreciationMethodRepository.saveAndFlush(depreciationMethod);

        // Get the depreciationMethod
        restDepreciationMethodMockMvc
            .perform(get(ENTITY_API_URL_ID, depreciationMethod.getDepreciationMethodId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.depreciationMethodId").value(depreciationMethod.getDepreciationMethodId().intValue()))
            .andExpect(jsonPath("$.depreciationMethodName").value(DEFAULT_DEPRECIATION_METHOD_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.depreciationMethodUuid").value(DEFAULT_DEPRECIATION_METHOD_UUID.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingDepreciationMethod() throws Exception {
        // Get the depreciationMethod
        restDepreciationMethodMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDepreciationMethod() throws Exception {
        // Initialize the database
        depreciationMethodRepository.saveAndFlush(depreciationMethod);

        int databaseSizeBeforeUpdate = depreciationMethodRepository.findAll().size();

        // Update the depreciationMethod
        DepreciationMethod updatedDepreciationMethod = depreciationMethodRepository
            .findById(depreciationMethod.getDepreciationMethodId())
            .get();
        // Disconnect from session so that the updates on updatedDepreciationMethod are not directly saved in db
        em.detach(updatedDepreciationMethod);
        updatedDepreciationMethod
            .depreciationMethodName(UPDATED_DEPRECIATION_METHOD_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .depreciationMethodUuid(UPDATED_DEPRECIATION_METHOD_UUID)
            .updatedDate(UPDATED_UPDATED_DATE);
        DepreciationMethodDTO depreciationMethodDTO = depreciationMethodMapper.toDto(updatedDepreciationMethod);

        restDepreciationMethodMockMvc
            .perform(
                put(ENTITY_API_URL_ID, depreciationMethodDTO.getDepreciationMethodId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(depreciationMethodDTO))
            )
            .andExpect(status().isOk());

        // Validate the DepreciationMethod in the database
        List<DepreciationMethod> depreciationMethodList = depreciationMethodRepository.findAll();
        assertThat(depreciationMethodList).hasSize(databaseSizeBeforeUpdate);
        DepreciationMethod testDepreciationMethod = depreciationMethodList.get(depreciationMethodList.size() - 1);
        assertThat(testDepreciationMethod.getDepreciationMethodName()).isEqualTo(UPDATED_DEPRECIATION_METHOD_NAME);
        assertThat(testDepreciationMethod.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDepreciationMethod.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDepreciationMethod.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDepreciationMethod.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDepreciationMethod.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDepreciationMethod.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDepreciationMethod.getDepreciationMethodUuid()).isEqualTo(UPDATED_DEPRECIATION_METHOD_UUID);
        assertThat(testDepreciationMethod.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingDepreciationMethod() throws Exception {
        int databaseSizeBeforeUpdate = depreciationMethodRepository.findAll().size();
        depreciationMethod.setDepreciationMethodId(count.incrementAndGet());

        // Create the DepreciationMethod
        DepreciationMethodDTO depreciationMethodDTO = depreciationMethodMapper.toDto(depreciationMethod);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDepreciationMethodMockMvc
            .perform(
                put(ENTITY_API_URL_ID, depreciationMethodDTO.getDepreciationMethodId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(depreciationMethodDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DepreciationMethod in the database
        List<DepreciationMethod> depreciationMethodList = depreciationMethodRepository.findAll();
        assertThat(depreciationMethodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDepreciationMethod() throws Exception {
        int databaseSizeBeforeUpdate = depreciationMethodRepository.findAll().size();
        depreciationMethod.setDepreciationMethodId(count.incrementAndGet());

        // Create the DepreciationMethod
        DepreciationMethodDTO depreciationMethodDTO = depreciationMethodMapper.toDto(depreciationMethod);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepreciationMethodMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(depreciationMethodDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DepreciationMethod in the database
        List<DepreciationMethod> depreciationMethodList = depreciationMethodRepository.findAll();
        assertThat(depreciationMethodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDepreciationMethod() throws Exception {
        int databaseSizeBeforeUpdate = depreciationMethodRepository.findAll().size();
        depreciationMethod.setDepreciationMethodId(count.incrementAndGet());

        // Create the DepreciationMethod
        DepreciationMethodDTO depreciationMethodDTO = depreciationMethodMapper.toDto(depreciationMethod);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepreciationMethodMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(depreciationMethodDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DepreciationMethod in the database
        List<DepreciationMethod> depreciationMethodList = depreciationMethodRepository.findAll();
        assertThat(depreciationMethodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDepreciationMethodWithPatch() throws Exception {
        // Initialize the database
        depreciationMethodRepository.saveAndFlush(depreciationMethod);

        int databaseSizeBeforeUpdate = depreciationMethodRepository.findAll().size();

        // Update the depreciationMethod using partial update
        DepreciationMethod partialUpdatedDepreciationMethod = new DepreciationMethod();
        partialUpdatedDepreciationMethod.setDepreciationMethodId(depreciationMethod.getDepreciationMethodId());

        partialUpdatedDepreciationMethod
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE);

        restDepreciationMethodMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDepreciationMethod.getDepreciationMethodId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDepreciationMethod))
            )
            .andExpect(status().isOk());

        // Validate the DepreciationMethod in the database
        List<DepreciationMethod> depreciationMethodList = depreciationMethodRepository.findAll();
        assertThat(depreciationMethodList).hasSize(databaseSizeBeforeUpdate);
        DepreciationMethod testDepreciationMethod = depreciationMethodList.get(depreciationMethodList.size() - 1);
        assertThat(testDepreciationMethod.getDepreciationMethodName()).isEqualTo(DEFAULT_DEPRECIATION_METHOD_NAME);
        assertThat(testDepreciationMethod.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDepreciationMethod.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDepreciationMethod.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDepreciationMethod.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testDepreciationMethod.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDepreciationMethod.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDepreciationMethod.getDepreciationMethodUuid()).isEqualTo(DEFAULT_DEPRECIATION_METHOD_UUID);
        assertThat(testDepreciationMethod.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateDepreciationMethodWithPatch() throws Exception {
        // Initialize the database
        depreciationMethodRepository.saveAndFlush(depreciationMethod);

        int databaseSizeBeforeUpdate = depreciationMethodRepository.findAll().size();

        // Update the depreciationMethod using partial update
        DepreciationMethod partialUpdatedDepreciationMethod = new DepreciationMethod();
        partialUpdatedDepreciationMethod.setDepreciationMethodId(depreciationMethod.getDepreciationMethodId());

        partialUpdatedDepreciationMethod
            .depreciationMethodName(UPDATED_DEPRECIATION_METHOD_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .depreciationMethodUuid(UPDATED_DEPRECIATION_METHOD_UUID)
            .updatedDate(UPDATED_UPDATED_DATE);

        restDepreciationMethodMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDepreciationMethod.getDepreciationMethodId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDepreciationMethod))
            )
            .andExpect(status().isOk());

        // Validate the DepreciationMethod in the database
        List<DepreciationMethod> depreciationMethodList = depreciationMethodRepository.findAll();
        assertThat(depreciationMethodList).hasSize(databaseSizeBeforeUpdate);
        DepreciationMethod testDepreciationMethod = depreciationMethodList.get(depreciationMethodList.size() - 1);
        assertThat(testDepreciationMethod.getDepreciationMethodName()).isEqualTo(UPDATED_DEPRECIATION_METHOD_NAME);
        assertThat(testDepreciationMethod.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDepreciationMethod.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDepreciationMethod.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDepreciationMethod.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDepreciationMethod.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDepreciationMethod.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDepreciationMethod.getDepreciationMethodUuid()).isEqualTo(UPDATED_DEPRECIATION_METHOD_UUID);
        assertThat(testDepreciationMethod.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingDepreciationMethod() throws Exception {
        int databaseSizeBeforeUpdate = depreciationMethodRepository.findAll().size();
        depreciationMethod.setDepreciationMethodId(count.incrementAndGet());

        // Create the DepreciationMethod
        DepreciationMethodDTO depreciationMethodDTO = depreciationMethodMapper.toDto(depreciationMethod);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDepreciationMethodMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, depreciationMethodDTO.getDepreciationMethodId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(depreciationMethodDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DepreciationMethod in the database
        List<DepreciationMethod> depreciationMethodList = depreciationMethodRepository.findAll();
        assertThat(depreciationMethodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDepreciationMethod() throws Exception {
        int databaseSizeBeforeUpdate = depreciationMethodRepository.findAll().size();
        depreciationMethod.setDepreciationMethodId(count.incrementAndGet());

        // Create the DepreciationMethod
        DepreciationMethodDTO depreciationMethodDTO = depreciationMethodMapper.toDto(depreciationMethod);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepreciationMethodMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(depreciationMethodDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DepreciationMethod in the database
        List<DepreciationMethod> depreciationMethodList = depreciationMethodRepository.findAll();
        assertThat(depreciationMethodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDepreciationMethod() throws Exception {
        int databaseSizeBeforeUpdate = depreciationMethodRepository.findAll().size();
        depreciationMethod.setDepreciationMethodId(count.incrementAndGet());

        // Create the DepreciationMethod
        DepreciationMethodDTO depreciationMethodDTO = depreciationMethodMapper.toDto(depreciationMethod);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepreciationMethodMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(depreciationMethodDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DepreciationMethod in the database
        List<DepreciationMethod> depreciationMethodList = depreciationMethodRepository.findAll();
        assertThat(depreciationMethodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDepreciationMethod() throws Exception {
        // Initialize the database
        depreciationMethodRepository.saveAndFlush(depreciationMethod);

        int databaseSizeBeforeDelete = depreciationMethodRepository.findAll().size();

        // Delete the depreciationMethod
        restDepreciationMethodMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, depreciationMethod.getDepreciationMethodId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DepreciationMethod> depreciationMethodList = depreciationMethodRepository.findAll();
        assertThat(depreciationMethodList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
