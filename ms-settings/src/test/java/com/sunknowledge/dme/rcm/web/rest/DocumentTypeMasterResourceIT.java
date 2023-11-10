package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.DocumentTypeMaster;
import com.sunknowledge.dme.rcm.repository.DocumentTypeMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.DocumentTypeMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.DocumentTypeMasterMapper;
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
 * Integration tests for the {@link DocumentTypeMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DocumentTypeMasterResourceIT {

    private static final String DEFAULT_DOCUMENT_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_TYPE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY_ID = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_ID = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_DOCUMENT_TYPE_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_DOCUMENT_TYPE_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/document-type-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{documentTypeId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DocumentTypeMasterRepository documentTypeMasterRepository;

    @Autowired
    private DocumentTypeMasterMapper documentTypeMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDocumentTypeMasterMockMvc;

    private DocumentTypeMaster documentTypeMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DocumentTypeMaster createEntity(EntityManager em) {
        DocumentTypeMaster documentTypeMaster = new DocumentTypeMaster()
            .documentTypeName(DEFAULT_DOCUMENT_TYPE_NAME)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .status(DEFAULT_STATUS)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .documentTypeMasterUuid(DEFAULT_DOCUMENT_TYPE_MASTER_UUID);
        return documentTypeMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DocumentTypeMaster createUpdatedEntity(EntityManager em) {
        DocumentTypeMaster documentTypeMaster = new DocumentTypeMaster()
            .documentTypeName(UPDATED_DOCUMENT_TYPE_NAME)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .documentTypeMasterUuid(UPDATED_DOCUMENT_TYPE_MASTER_UUID);
        return documentTypeMaster;
    }

    @BeforeEach
    public void initTest() {
        documentTypeMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createDocumentTypeMaster() throws Exception {
        int databaseSizeBeforeCreate = documentTypeMasterRepository.findAll().size();
        // Create the DocumentTypeMaster
        DocumentTypeMasterDTO documentTypeMasterDTO = documentTypeMasterMapper.toDto(documentTypeMaster);
        restDocumentTypeMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentTypeMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DocumentTypeMaster in the database
        List<DocumentTypeMaster> documentTypeMasterList = documentTypeMasterRepository.findAll();
        assertThat(documentTypeMasterList).hasSize(databaseSizeBeforeCreate + 1);
        DocumentTypeMaster testDocumentTypeMaster = documentTypeMasterList.get(documentTypeMasterList.size() - 1);
        assertThat(testDocumentTypeMaster.getDocumentTypeName()).isEqualTo(DEFAULT_DOCUMENT_TYPE_NAME);
        assertThat(testDocumentTypeMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDocumentTypeMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDocumentTypeMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDocumentTypeMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDocumentTypeMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testDocumentTypeMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testDocumentTypeMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDocumentTypeMaster.getDocumentTypeMasterUuid()).isEqualTo(DEFAULT_DOCUMENT_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void createDocumentTypeMasterWithExistingId() throws Exception {
        // Create the DocumentTypeMaster with an existing ID
        documentTypeMaster.setDocumentTypeId(1L);
        DocumentTypeMasterDTO documentTypeMasterDTO = documentTypeMasterMapper.toDto(documentTypeMaster);

        int databaseSizeBeforeCreate = documentTypeMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDocumentTypeMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentTypeMaster in the database
        List<DocumentTypeMaster> documentTypeMasterList = documentTypeMasterRepository.findAll();
        assertThat(documentTypeMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDocumentTypeMasters() throws Exception {
        // Initialize the database
        documentTypeMasterRepository.saveAndFlush(documentTypeMaster);

        // Get all the documentTypeMasterList
        restDocumentTypeMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=documentTypeId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].documentTypeId").value(hasItem(documentTypeMaster.getDocumentTypeId().intValue())))
            .andExpect(jsonPath("$.[*].documentTypeName").value(hasItem(DEFAULT_DOCUMENT_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].documentTypeMasterUuid").value(hasItem(DEFAULT_DOCUMENT_TYPE_MASTER_UUID.toString())));
    }

    @Test
    @Transactional
    void getDocumentTypeMaster() throws Exception {
        // Initialize the database
        documentTypeMasterRepository.saveAndFlush(documentTypeMaster);

        // Get the documentTypeMaster
        restDocumentTypeMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, documentTypeMaster.getDocumentTypeId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.documentTypeId").value(documentTypeMaster.getDocumentTypeId().intValue()))
            .andExpect(jsonPath("$.documentTypeName").value(DEFAULT_DOCUMENT_TYPE_NAME))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.documentTypeMasterUuid").value(DEFAULT_DOCUMENT_TYPE_MASTER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingDocumentTypeMaster() throws Exception {
        // Get the documentTypeMaster
        restDocumentTypeMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDocumentTypeMaster() throws Exception {
        // Initialize the database
        documentTypeMasterRepository.saveAndFlush(documentTypeMaster);

        int databaseSizeBeforeUpdate = documentTypeMasterRepository.findAll().size();

        // Update the documentTypeMaster
        DocumentTypeMaster updatedDocumentTypeMaster = documentTypeMasterRepository.findById(documentTypeMaster.getDocumentTypeId()).get();
        // Disconnect from session so that the updates on updatedDocumentTypeMaster are not directly saved in db
        em.detach(updatedDocumentTypeMaster);
        updatedDocumentTypeMaster
            .documentTypeName(UPDATED_DOCUMENT_TYPE_NAME)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .documentTypeMasterUuid(UPDATED_DOCUMENT_TYPE_MASTER_UUID);
        DocumentTypeMasterDTO documentTypeMasterDTO = documentTypeMasterMapper.toDto(updatedDocumentTypeMaster);

        restDocumentTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, documentTypeMasterDTO.getDocumentTypeId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentTypeMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the DocumentTypeMaster in the database
        List<DocumentTypeMaster> documentTypeMasterList = documentTypeMasterRepository.findAll();
        assertThat(documentTypeMasterList).hasSize(databaseSizeBeforeUpdate);
        DocumentTypeMaster testDocumentTypeMaster = documentTypeMasterList.get(documentTypeMasterList.size() - 1);
        assertThat(testDocumentTypeMaster.getDocumentTypeName()).isEqualTo(UPDATED_DOCUMENT_TYPE_NAME);
        assertThat(testDocumentTypeMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDocumentTypeMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDocumentTypeMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDocumentTypeMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDocumentTypeMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDocumentTypeMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDocumentTypeMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDocumentTypeMaster.getDocumentTypeMasterUuid()).isEqualTo(UPDATED_DOCUMENT_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingDocumentTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = documentTypeMasterRepository.findAll().size();
        documentTypeMaster.setDocumentTypeId(count.incrementAndGet());

        // Create the DocumentTypeMaster
        DocumentTypeMasterDTO documentTypeMasterDTO = documentTypeMasterMapper.toDto(documentTypeMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, documentTypeMasterDTO.getDocumentTypeId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentTypeMaster in the database
        List<DocumentTypeMaster> documentTypeMasterList = documentTypeMasterRepository.findAll();
        assertThat(documentTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDocumentTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = documentTypeMasterRepository.findAll().size();
        documentTypeMaster.setDocumentTypeId(count.incrementAndGet());

        // Create the DocumentTypeMaster
        DocumentTypeMasterDTO documentTypeMasterDTO = documentTypeMasterMapper.toDto(documentTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentTypeMaster in the database
        List<DocumentTypeMaster> documentTypeMasterList = documentTypeMasterRepository.findAll();
        assertThat(documentTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDocumentTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = documentTypeMasterRepository.findAll().size();
        documentTypeMaster.setDocumentTypeId(count.incrementAndGet());

        // Create the DocumentTypeMaster
        DocumentTypeMasterDTO documentTypeMasterDTO = documentTypeMasterMapper.toDto(documentTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentTypeMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DocumentTypeMaster in the database
        List<DocumentTypeMaster> documentTypeMasterList = documentTypeMasterRepository.findAll();
        assertThat(documentTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDocumentTypeMasterWithPatch() throws Exception {
        // Initialize the database
        documentTypeMasterRepository.saveAndFlush(documentTypeMaster);

        int databaseSizeBeforeUpdate = documentTypeMasterRepository.findAll().size();

        // Update the documentTypeMaster using partial update
        DocumentTypeMaster partialUpdatedDocumentTypeMaster = new DocumentTypeMaster();
        partialUpdatedDocumentTypeMaster.setDocumentTypeId(documentTypeMaster.getDocumentTypeId());

        partialUpdatedDocumentTypeMaster
            .status(UPDATED_STATUS)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .documentTypeMasterUuid(UPDATED_DOCUMENT_TYPE_MASTER_UUID);

        restDocumentTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDocumentTypeMaster.getDocumentTypeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDocumentTypeMaster))
            )
            .andExpect(status().isOk());

        // Validate the DocumentTypeMaster in the database
        List<DocumentTypeMaster> documentTypeMasterList = documentTypeMasterRepository.findAll();
        assertThat(documentTypeMasterList).hasSize(databaseSizeBeforeUpdate);
        DocumentTypeMaster testDocumentTypeMaster = documentTypeMasterList.get(documentTypeMasterList.size() - 1);
        assertThat(testDocumentTypeMaster.getDocumentTypeName()).isEqualTo(DEFAULT_DOCUMENT_TYPE_NAME);
        assertThat(testDocumentTypeMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDocumentTypeMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDocumentTypeMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDocumentTypeMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDocumentTypeMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDocumentTypeMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDocumentTypeMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDocumentTypeMaster.getDocumentTypeMasterUuid()).isEqualTo(UPDATED_DOCUMENT_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void fullUpdateDocumentTypeMasterWithPatch() throws Exception {
        // Initialize the database
        documentTypeMasterRepository.saveAndFlush(documentTypeMaster);

        int databaseSizeBeforeUpdate = documentTypeMasterRepository.findAll().size();

        // Update the documentTypeMaster using partial update
        DocumentTypeMaster partialUpdatedDocumentTypeMaster = new DocumentTypeMaster();
        partialUpdatedDocumentTypeMaster.setDocumentTypeId(documentTypeMaster.getDocumentTypeId());

        partialUpdatedDocumentTypeMaster
            .documentTypeName(UPDATED_DOCUMENT_TYPE_NAME)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .documentTypeMasterUuid(UPDATED_DOCUMENT_TYPE_MASTER_UUID);

        restDocumentTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDocumentTypeMaster.getDocumentTypeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDocumentTypeMaster))
            )
            .andExpect(status().isOk());

        // Validate the DocumentTypeMaster in the database
        List<DocumentTypeMaster> documentTypeMasterList = documentTypeMasterRepository.findAll();
        assertThat(documentTypeMasterList).hasSize(databaseSizeBeforeUpdate);
        DocumentTypeMaster testDocumentTypeMaster = documentTypeMasterList.get(documentTypeMasterList.size() - 1);
        assertThat(testDocumentTypeMaster.getDocumentTypeName()).isEqualTo(UPDATED_DOCUMENT_TYPE_NAME);
        assertThat(testDocumentTypeMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDocumentTypeMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDocumentTypeMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDocumentTypeMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDocumentTypeMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDocumentTypeMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDocumentTypeMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDocumentTypeMaster.getDocumentTypeMasterUuid()).isEqualTo(UPDATED_DOCUMENT_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingDocumentTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = documentTypeMasterRepository.findAll().size();
        documentTypeMaster.setDocumentTypeId(count.incrementAndGet());

        // Create the DocumentTypeMaster
        DocumentTypeMasterDTO documentTypeMasterDTO = documentTypeMasterMapper.toDto(documentTypeMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, documentTypeMasterDTO.getDocumentTypeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(documentTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentTypeMaster in the database
        List<DocumentTypeMaster> documentTypeMasterList = documentTypeMasterRepository.findAll();
        assertThat(documentTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDocumentTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = documentTypeMasterRepository.findAll().size();
        documentTypeMaster.setDocumentTypeId(count.incrementAndGet());

        // Create the DocumentTypeMaster
        DocumentTypeMasterDTO documentTypeMasterDTO = documentTypeMasterMapper.toDto(documentTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(documentTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentTypeMaster in the database
        List<DocumentTypeMaster> documentTypeMasterList = documentTypeMasterRepository.findAll();
        assertThat(documentTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDocumentTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = documentTypeMasterRepository.findAll().size();
        documentTypeMaster.setDocumentTypeId(count.incrementAndGet());

        // Create the DocumentTypeMaster
        DocumentTypeMasterDTO documentTypeMasterDTO = documentTypeMasterMapper.toDto(documentTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(documentTypeMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DocumentTypeMaster in the database
        List<DocumentTypeMaster> documentTypeMasterList = documentTypeMasterRepository.findAll();
        assertThat(documentTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDocumentTypeMaster() throws Exception {
        // Initialize the database
        documentTypeMasterRepository.saveAndFlush(documentTypeMaster);

        int databaseSizeBeforeDelete = documentTypeMasterRepository.findAll().size();

        // Delete the documentTypeMaster
        restDocumentTypeMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, documentTypeMaster.getDocumentTypeId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DocumentTypeMaster> documentTypeMasterList = documentTypeMasterRepository.findAll();
        assertThat(documentTypeMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
