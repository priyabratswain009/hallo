package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ObjectTypeMaster;
import com.sunknowledge.dme.rcm.repository.ObjectTypeMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.ObjectTypeMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ObjectTypeMasterMapper;
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
 * Integration tests for the {@link ObjectTypeMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ObjectTypeMasterResourceIT {

    private static final String DEFAULT_OBJECT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OBJECT_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_OBJECT_TYPE_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_OBJECT_TYPE_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/object-type-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{objectId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectTypeMasterRepository objectTypeMasterRepository;

    @Autowired
    private ObjectTypeMasterMapper objectTypeMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restObjectTypeMasterMockMvc;

    private ObjectTypeMaster objectTypeMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ObjectTypeMaster createEntity(EntityManager em) {
        ObjectTypeMaster objectTypeMaster = new ObjectTypeMaster()
            .objectName(DEFAULT_OBJECT_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .objectTypeMasterUuid(DEFAULT_OBJECT_TYPE_MASTER_UUID);
        return objectTypeMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ObjectTypeMaster createUpdatedEntity(EntityManager em) {
        ObjectTypeMaster objectTypeMaster = new ObjectTypeMaster()
            .objectName(UPDATED_OBJECT_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .objectTypeMasterUuid(UPDATED_OBJECT_TYPE_MASTER_UUID);
        return objectTypeMaster;
    }

    @BeforeEach
    public void initTest() {
        objectTypeMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createObjectTypeMaster() throws Exception {
        int databaseSizeBeforeCreate = objectTypeMasterRepository.findAll().size();
        // Create the ObjectTypeMaster
        ObjectTypeMasterDTO objectTypeMasterDTO = objectTypeMasterMapper.toDto(objectTypeMaster);
        restObjectTypeMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objectTypeMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ObjectTypeMaster in the database
        List<ObjectTypeMaster> objectTypeMasterList = objectTypeMasterRepository.findAll();
        assertThat(objectTypeMasterList).hasSize(databaseSizeBeforeCreate + 1);
        ObjectTypeMaster testObjectTypeMaster = objectTypeMasterList.get(objectTypeMasterList.size() - 1);
        assertThat(testObjectTypeMaster.getObjectName()).isEqualTo(DEFAULT_OBJECT_NAME);
        assertThat(testObjectTypeMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testObjectTypeMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testObjectTypeMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testObjectTypeMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testObjectTypeMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testObjectTypeMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testObjectTypeMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testObjectTypeMaster.getObjectTypeMasterUuid()).isEqualTo(DEFAULT_OBJECT_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void createObjectTypeMasterWithExistingId() throws Exception {
        // Create the ObjectTypeMaster with an existing ID
        objectTypeMaster.setObjectId(1L);
        ObjectTypeMasterDTO objectTypeMasterDTO = objectTypeMasterMapper.toDto(objectTypeMaster);

        int databaseSizeBeforeCreate = objectTypeMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restObjectTypeMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objectTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjectTypeMaster in the database
        List<ObjectTypeMaster> objectTypeMasterList = objectTypeMasterRepository.findAll();
        assertThat(objectTypeMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllObjectTypeMasters() throws Exception {
        // Initialize the database
        objectTypeMasterRepository.saveAndFlush(objectTypeMaster);

        // Get all the objectTypeMasterList
        restObjectTypeMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=objectId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].objectId").value(hasItem(objectTypeMaster.getObjectId().intValue())))
            .andExpect(jsonPath("$.[*].objectName").value(hasItem(DEFAULT_OBJECT_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].objectTypeMasterUuid").value(hasItem(DEFAULT_OBJECT_TYPE_MASTER_UUID.toString())));
    }

    @Test
    @Transactional
    void getObjectTypeMaster() throws Exception {
        // Initialize the database
        objectTypeMasterRepository.saveAndFlush(objectTypeMaster);

        // Get the objectTypeMaster
        restObjectTypeMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, objectTypeMaster.getObjectId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.objectId").value(objectTypeMaster.getObjectId().intValue()))
            .andExpect(jsonPath("$.objectName").value(DEFAULT_OBJECT_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.objectTypeMasterUuid").value(DEFAULT_OBJECT_TYPE_MASTER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingObjectTypeMaster() throws Exception {
        // Get the objectTypeMaster
        restObjectTypeMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewObjectTypeMaster() throws Exception {
        // Initialize the database
        objectTypeMasterRepository.saveAndFlush(objectTypeMaster);

        int databaseSizeBeforeUpdate = objectTypeMasterRepository.findAll().size();

        // Update the objectTypeMaster
        ObjectTypeMaster updatedObjectTypeMaster = objectTypeMasterRepository.findById(objectTypeMaster.getObjectId()).get();
        // Disconnect from session so that the updates on updatedObjectTypeMaster are not directly saved in db
        em.detach(updatedObjectTypeMaster);
        updatedObjectTypeMaster
            .objectName(UPDATED_OBJECT_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .objectTypeMasterUuid(UPDATED_OBJECT_TYPE_MASTER_UUID);
        ObjectTypeMasterDTO objectTypeMasterDTO = objectTypeMasterMapper.toDto(updatedObjectTypeMaster);

        restObjectTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, objectTypeMasterDTO.getObjectId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objectTypeMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the ObjectTypeMaster in the database
        List<ObjectTypeMaster> objectTypeMasterList = objectTypeMasterRepository.findAll();
        assertThat(objectTypeMasterList).hasSize(databaseSizeBeforeUpdate);
        ObjectTypeMaster testObjectTypeMaster = objectTypeMasterList.get(objectTypeMasterList.size() - 1);
        assertThat(testObjectTypeMaster.getObjectName()).isEqualTo(UPDATED_OBJECT_NAME);
        assertThat(testObjectTypeMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testObjectTypeMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testObjectTypeMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testObjectTypeMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testObjectTypeMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testObjectTypeMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testObjectTypeMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testObjectTypeMaster.getObjectTypeMasterUuid()).isEqualTo(UPDATED_OBJECT_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingObjectTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = objectTypeMasterRepository.findAll().size();
        objectTypeMaster.setObjectId(count.incrementAndGet());

        // Create the ObjectTypeMaster
        ObjectTypeMasterDTO objectTypeMasterDTO = objectTypeMasterMapper.toDto(objectTypeMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restObjectTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, objectTypeMasterDTO.getObjectId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objectTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjectTypeMaster in the database
        List<ObjectTypeMaster> objectTypeMasterList = objectTypeMasterRepository.findAll();
        assertThat(objectTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchObjectTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = objectTypeMasterRepository.findAll().size();
        objectTypeMaster.setObjectId(count.incrementAndGet());

        // Create the ObjectTypeMaster
        ObjectTypeMasterDTO objectTypeMasterDTO = objectTypeMasterMapper.toDto(objectTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restObjectTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objectTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjectTypeMaster in the database
        List<ObjectTypeMaster> objectTypeMasterList = objectTypeMasterRepository.findAll();
        assertThat(objectTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamObjectTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = objectTypeMasterRepository.findAll().size();
        objectTypeMaster.setObjectId(count.incrementAndGet());

        // Create the ObjectTypeMaster
        ObjectTypeMasterDTO objectTypeMasterDTO = objectTypeMasterMapper.toDto(objectTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restObjectTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objectTypeMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ObjectTypeMaster in the database
        List<ObjectTypeMaster> objectTypeMasterList = objectTypeMasterRepository.findAll();
        assertThat(objectTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateObjectTypeMasterWithPatch() throws Exception {
        // Initialize the database
        objectTypeMasterRepository.saveAndFlush(objectTypeMaster);

        int databaseSizeBeforeUpdate = objectTypeMasterRepository.findAll().size();

        // Update the objectTypeMaster using partial update
        ObjectTypeMaster partialUpdatedObjectTypeMaster = new ObjectTypeMaster();
        partialUpdatedObjectTypeMaster.setObjectId(objectTypeMaster.getObjectId());

        partialUpdatedObjectTypeMaster
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE);

        restObjectTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedObjectTypeMaster.getObjectId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedObjectTypeMaster))
            )
            .andExpect(status().isOk());

        // Validate the ObjectTypeMaster in the database
        List<ObjectTypeMaster> objectTypeMasterList = objectTypeMasterRepository.findAll();
        assertThat(objectTypeMasterList).hasSize(databaseSizeBeforeUpdate);
        ObjectTypeMaster testObjectTypeMaster = objectTypeMasterList.get(objectTypeMasterList.size() - 1);
        assertThat(testObjectTypeMaster.getObjectName()).isEqualTo(DEFAULT_OBJECT_NAME);
        assertThat(testObjectTypeMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testObjectTypeMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testObjectTypeMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testObjectTypeMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testObjectTypeMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testObjectTypeMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testObjectTypeMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testObjectTypeMaster.getObjectTypeMasterUuid()).isEqualTo(DEFAULT_OBJECT_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void fullUpdateObjectTypeMasterWithPatch() throws Exception {
        // Initialize the database
        objectTypeMasterRepository.saveAndFlush(objectTypeMaster);

        int databaseSizeBeforeUpdate = objectTypeMasterRepository.findAll().size();

        // Update the objectTypeMaster using partial update
        ObjectTypeMaster partialUpdatedObjectTypeMaster = new ObjectTypeMaster();
        partialUpdatedObjectTypeMaster.setObjectId(objectTypeMaster.getObjectId());

        partialUpdatedObjectTypeMaster
            .objectName(UPDATED_OBJECT_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .objectTypeMasterUuid(UPDATED_OBJECT_TYPE_MASTER_UUID);

        restObjectTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedObjectTypeMaster.getObjectId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedObjectTypeMaster))
            )
            .andExpect(status().isOk());

        // Validate the ObjectTypeMaster in the database
        List<ObjectTypeMaster> objectTypeMasterList = objectTypeMasterRepository.findAll();
        assertThat(objectTypeMasterList).hasSize(databaseSizeBeforeUpdate);
        ObjectTypeMaster testObjectTypeMaster = objectTypeMasterList.get(objectTypeMasterList.size() - 1);
        assertThat(testObjectTypeMaster.getObjectName()).isEqualTo(UPDATED_OBJECT_NAME);
        assertThat(testObjectTypeMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testObjectTypeMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testObjectTypeMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testObjectTypeMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testObjectTypeMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testObjectTypeMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testObjectTypeMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testObjectTypeMaster.getObjectTypeMasterUuid()).isEqualTo(UPDATED_OBJECT_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingObjectTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = objectTypeMasterRepository.findAll().size();
        objectTypeMaster.setObjectId(count.incrementAndGet());

        // Create the ObjectTypeMaster
        ObjectTypeMasterDTO objectTypeMasterDTO = objectTypeMasterMapper.toDto(objectTypeMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restObjectTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, objectTypeMasterDTO.getObjectId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(objectTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjectTypeMaster in the database
        List<ObjectTypeMaster> objectTypeMasterList = objectTypeMasterRepository.findAll();
        assertThat(objectTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchObjectTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = objectTypeMasterRepository.findAll().size();
        objectTypeMaster.setObjectId(count.incrementAndGet());

        // Create the ObjectTypeMaster
        ObjectTypeMasterDTO objectTypeMasterDTO = objectTypeMasterMapper.toDto(objectTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restObjectTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(objectTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjectTypeMaster in the database
        List<ObjectTypeMaster> objectTypeMasterList = objectTypeMasterRepository.findAll();
        assertThat(objectTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamObjectTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = objectTypeMasterRepository.findAll().size();
        objectTypeMaster.setObjectId(count.incrementAndGet());

        // Create the ObjectTypeMaster
        ObjectTypeMasterDTO objectTypeMasterDTO = objectTypeMasterMapper.toDto(objectTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restObjectTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(objectTypeMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ObjectTypeMaster in the database
        List<ObjectTypeMaster> objectTypeMasterList = objectTypeMasterRepository.findAll();
        assertThat(objectTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteObjectTypeMaster() throws Exception {
        // Initialize the database
        objectTypeMasterRepository.saveAndFlush(objectTypeMaster);

        int databaseSizeBeforeDelete = objectTypeMasterRepository.findAll().size();

        // Delete the objectTypeMaster
        restObjectTypeMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, objectTypeMaster.getObjectId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ObjectTypeMaster> objectTypeMasterList = objectTypeMasterRepository.findAll();
        assertThat(objectTypeMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
