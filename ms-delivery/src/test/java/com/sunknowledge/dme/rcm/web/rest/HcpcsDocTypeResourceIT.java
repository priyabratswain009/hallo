package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.HcpcsDocType;
import com.sunknowledge.dme.rcm.repository.HcpcsDocTypeRepository;
import com.sunknowledge.dme.rcm.service.dto.HcpcsDocTypeDTO;
import com.sunknowledge.dme.rcm.service.mapper.HcpcsDocTypeMapper;
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
 * Integration tests for the {@link HcpcsDocTypeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HcpcsDocTypeResourceIT {

    private static final String DEFAULT_HCPCS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_HCPCS_CODE = "BBBBBBBBBB";

    private static final Long DEFAULT_DOCUMENT_ID = 1L;
    private static final Long UPDATED_DOCUMENT_ID = 2L;

    private static final String DEFAULT_DOCUMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENT_FILE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_FILE_PATH = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_HCPCS_DOC_TYPE_UUID = UUID.randomUUID();
    private static final UUID UPDATED_HCPCS_DOC_TYPE_UUID = UUID.randomUUID();

    private static final String DEFAULT_FILE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FILE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_FORM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FORM_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/hcpcs-doc-types";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{hcpcsDoctypeId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private HcpcsDocTypeRepository hcpcsDocTypeRepository;

    @Autowired
    private HcpcsDocTypeMapper hcpcsDocTypeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHcpcsDocTypeMockMvc;

    private HcpcsDocType hcpcsDocType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HcpcsDocType createEntity(EntityManager em) {
        HcpcsDocType hcpcsDocType = new HcpcsDocType()
            .hcpcsCode(DEFAULT_HCPCS_CODE)
            .documentId(DEFAULT_DOCUMENT_ID)
            .documentName(DEFAULT_DOCUMENT_NAME)
            .documentFilePath(DEFAULT_DOCUMENT_FILE_PATH)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .hcpcsDocTypeUuid(DEFAULT_HCPCS_DOC_TYPE_UUID)
            .fileType(DEFAULT_FILE_TYPE)
            .formName(DEFAULT_FORM_NAME);
        return hcpcsDocType;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HcpcsDocType createUpdatedEntity(EntityManager em) {
        HcpcsDocType hcpcsDocType = new HcpcsDocType()
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .documentId(UPDATED_DOCUMENT_ID)
            .documentName(UPDATED_DOCUMENT_NAME)
            .documentFilePath(UPDATED_DOCUMENT_FILE_PATH)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .hcpcsDocTypeUuid(UPDATED_HCPCS_DOC_TYPE_UUID)
            .fileType(UPDATED_FILE_TYPE)
            .formName(UPDATED_FORM_NAME);
        return hcpcsDocType;
    }

    @BeforeEach
    public void initTest() {
        hcpcsDocType = createEntity(em);
    }

    @Test
    @Transactional
    void createHcpcsDocType() throws Exception {
        int databaseSizeBeforeCreate = hcpcsDocTypeRepository.findAll().size();
        // Create the HcpcsDocType
        HcpcsDocTypeDTO hcpcsDocTypeDTO = hcpcsDocTypeMapper.toDto(hcpcsDocType);
        restHcpcsDocTypeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hcpcsDocTypeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the HcpcsDocType in the database
        List<HcpcsDocType> hcpcsDocTypeList = hcpcsDocTypeRepository.findAll();
        assertThat(hcpcsDocTypeList).hasSize(databaseSizeBeforeCreate + 1);
        HcpcsDocType testHcpcsDocType = hcpcsDocTypeList.get(hcpcsDocTypeList.size() - 1);
        assertThat(testHcpcsDocType.getHcpcsCode()).isEqualTo(DEFAULT_HCPCS_CODE);
        assertThat(testHcpcsDocType.getDocumentId()).isEqualTo(DEFAULT_DOCUMENT_ID);
        assertThat(testHcpcsDocType.getDocumentName()).isEqualTo(DEFAULT_DOCUMENT_NAME);
        assertThat(testHcpcsDocType.getDocumentFilePath()).isEqualTo(DEFAULT_DOCUMENT_FILE_PATH);
        assertThat(testHcpcsDocType.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testHcpcsDocType.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testHcpcsDocType.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testHcpcsDocType.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testHcpcsDocType.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testHcpcsDocType.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testHcpcsDocType.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testHcpcsDocType.getHcpcsDocTypeUuid()).isEqualTo(DEFAULT_HCPCS_DOC_TYPE_UUID);
        assertThat(testHcpcsDocType.getFileType()).isEqualTo(DEFAULT_FILE_TYPE);
        assertThat(testHcpcsDocType.getFormName()).isEqualTo(DEFAULT_FORM_NAME);
    }

    @Test
    @Transactional
    void createHcpcsDocTypeWithExistingId() throws Exception {
        // Create the HcpcsDocType with an existing ID
        hcpcsDocType.setHcpcsDoctypeId(1L);
        HcpcsDocTypeDTO hcpcsDocTypeDTO = hcpcsDocTypeMapper.toDto(hcpcsDocType);

        int databaseSizeBeforeCreate = hcpcsDocTypeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHcpcsDocTypeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hcpcsDocTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HcpcsDocType in the database
        List<HcpcsDocType> hcpcsDocTypeList = hcpcsDocTypeRepository.findAll();
        assertThat(hcpcsDocTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllHcpcsDocTypes() throws Exception {
        // Initialize the database
        hcpcsDocTypeRepository.saveAndFlush(hcpcsDocType);

        // Get all the hcpcsDocTypeList
        restHcpcsDocTypeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=hcpcsDoctypeId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].hcpcsDoctypeId").value(hasItem(hcpcsDocType.getHcpcsDoctypeId().intValue())))
            .andExpect(jsonPath("$.[*].hcpcsCode").value(hasItem(DEFAULT_HCPCS_CODE)))
            .andExpect(jsonPath("$.[*].documentId").value(hasItem(DEFAULT_DOCUMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].documentName").value(hasItem(DEFAULT_DOCUMENT_NAME)))
            .andExpect(jsonPath("$.[*].documentFilePath").value(hasItem(DEFAULT_DOCUMENT_FILE_PATH)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].hcpcsDocTypeUuid").value(hasItem(DEFAULT_HCPCS_DOC_TYPE_UUID.toString())))
            .andExpect(jsonPath("$.[*].fileType").value(hasItem(DEFAULT_FILE_TYPE)))
            .andExpect(jsonPath("$.[*].formName").value(hasItem(DEFAULT_FORM_NAME)));
    }

    @Test
    @Transactional
    void getHcpcsDocType() throws Exception {
        // Initialize the database
        hcpcsDocTypeRepository.saveAndFlush(hcpcsDocType);

        // Get the hcpcsDocType
        restHcpcsDocTypeMockMvc
            .perform(get(ENTITY_API_URL_ID, hcpcsDocType.getHcpcsDoctypeId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.hcpcsDoctypeId").value(hcpcsDocType.getHcpcsDoctypeId().intValue()))
            .andExpect(jsonPath("$.hcpcsCode").value(DEFAULT_HCPCS_CODE))
            .andExpect(jsonPath("$.documentId").value(DEFAULT_DOCUMENT_ID.intValue()))
            .andExpect(jsonPath("$.documentName").value(DEFAULT_DOCUMENT_NAME))
            .andExpect(jsonPath("$.documentFilePath").value(DEFAULT_DOCUMENT_FILE_PATH))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.hcpcsDocTypeUuid").value(DEFAULT_HCPCS_DOC_TYPE_UUID.toString()))
            .andExpect(jsonPath("$.fileType").value(DEFAULT_FILE_TYPE))
            .andExpect(jsonPath("$.formName").value(DEFAULT_FORM_NAME));
    }

    @Test
    @Transactional
    void getNonExistingHcpcsDocType() throws Exception {
        // Get the hcpcsDocType
        restHcpcsDocTypeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewHcpcsDocType() throws Exception {
        // Initialize the database
        hcpcsDocTypeRepository.saveAndFlush(hcpcsDocType);

        int databaseSizeBeforeUpdate = hcpcsDocTypeRepository.findAll().size();

        // Update the hcpcsDocType
        HcpcsDocType updatedHcpcsDocType = hcpcsDocTypeRepository.findById(hcpcsDocType.getHcpcsDoctypeId()).get();
        // Disconnect from session so that the updates on updatedHcpcsDocType are not directly saved in db
        em.detach(updatedHcpcsDocType);
        updatedHcpcsDocType
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .documentId(UPDATED_DOCUMENT_ID)
            .documentName(UPDATED_DOCUMENT_NAME)
            .documentFilePath(UPDATED_DOCUMENT_FILE_PATH)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .hcpcsDocTypeUuid(UPDATED_HCPCS_DOC_TYPE_UUID)
            .fileType(UPDATED_FILE_TYPE)
            .formName(UPDATED_FORM_NAME);
        HcpcsDocTypeDTO hcpcsDocTypeDTO = hcpcsDocTypeMapper.toDto(updatedHcpcsDocType);

        restHcpcsDocTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hcpcsDocTypeDTO.getHcpcsDoctypeId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hcpcsDocTypeDTO))
            )
            .andExpect(status().isOk());

        // Validate the HcpcsDocType in the database
        List<HcpcsDocType> hcpcsDocTypeList = hcpcsDocTypeRepository.findAll();
        assertThat(hcpcsDocTypeList).hasSize(databaseSizeBeforeUpdate);
        HcpcsDocType testHcpcsDocType = hcpcsDocTypeList.get(hcpcsDocTypeList.size() - 1);
        assertThat(testHcpcsDocType.getHcpcsCode()).isEqualTo(UPDATED_HCPCS_CODE);
        assertThat(testHcpcsDocType.getDocumentId()).isEqualTo(UPDATED_DOCUMENT_ID);
        assertThat(testHcpcsDocType.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testHcpcsDocType.getDocumentFilePath()).isEqualTo(UPDATED_DOCUMENT_FILE_PATH);
        assertThat(testHcpcsDocType.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testHcpcsDocType.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testHcpcsDocType.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testHcpcsDocType.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testHcpcsDocType.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testHcpcsDocType.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testHcpcsDocType.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testHcpcsDocType.getHcpcsDocTypeUuid()).isEqualTo(UPDATED_HCPCS_DOC_TYPE_UUID);
        assertThat(testHcpcsDocType.getFileType()).isEqualTo(UPDATED_FILE_TYPE);
        assertThat(testHcpcsDocType.getFormName()).isEqualTo(UPDATED_FORM_NAME);
    }

    @Test
    @Transactional
    void putNonExistingHcpcsDocType() throws Exception {
        int databaseSizeBeforeUpdate = hcpcsDocTypeRepository.findAll().size();
        hcpcsDocType.setHcpcsDoctypeId(count.incrementAndGet());

        // Create the HcpcsDocType
        HcpcsDocTypeDTO hcpcsDocTypeDTO = hcpcsDocTypeMapper.toDto(hcpcsDocType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHcpcsDocTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hcpcsDocTypeDTO.getHcpcsDoctypeId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hcpcsDocTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HcpcsDocType in the database
        List<HcpcsDocType> hcpcsDocTypeList = hcpcsDocTypeRepository.findAll();
        assertThat(hcpcsDocTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHcpcsDocType() throws Exception {
        int databaseSizeBeforeUpdate = hcpcsDocTypeRepository.findAll().size();
        hcpcsDocType.setHcpcsDoctypeId(count.incrementAndGet());

        // Create the HcpcsDocType
        HcpcsDocTypeDTO hcpcsDocTypeDTO = hcpcsDocTypeMapper.toDto(hcpcsDocType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHcpcsDocTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hcpcsDocTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HcpcsDocType in the database
        List<HcpcsDocType> hcpcsDocTypeList = hcpcsDocTypeRepository.findAll();
        assertThat(hcpcsDocTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHcpcsDocType() throws Exception {
        int databaseSizeBeforeUpdate = hcpcsDocTypeRepository.findAll().size();
        hcpcsDocType.setHcpcsDoctypeId(count.incrementAndGet());

        // Create the HcpcsDocType
        HcpcsDocTypeDTO hcpcsDocTypeDTO = hcpcsDocTypeMapper.toDto(hcpcsDocType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHcpcsDocTypeMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hcpcsDocTypeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HcpcsDocType in the database
        List<HcpcsDocType> hcpcsDocTypeList = hcpcsDocTypeRepository.findAll();
        assertThat(hcpcsDocTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHcpcsDocTypeWithPatch() throws Exception {
        // Initialize the database
        hcpcsDocTypeRepository.saveAndFlush(hcpcsDocType);

        int databaseSizeBeforeUpdate = hcpcsDocTypeRepository.findAll().size();

        // Update the hcpcsDocType using partial update
        HcpcsDocType partialUpdatedHcpcsDocType = new HcpcsDocType();
        partialUpdatedHcpcsDocType.setHcpcsDoctypeId(hcpcsDocType.getHcpcsDoctypeId());

        partialUpdatedHcpcsDocType
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .documentId(UPDATED_DOCUMENT_ID)
            .documentName(UPDATED_DOCUMENT_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .hcpcsDocTypeUuid(UPDATED_HCPCS_DOC_TYPE_UUID)
            .fileType(UPDATED_FILE_TYPE)
            .formName(UPDATED_FORM_NAME);

        restHcpcsDocTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHcpcsDocType.getHcpcsDoctypeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHcpcsDocType))
            )
            .andExpect(status().isOk());

        // Validate the HcpcsDocType in the database
        List<HcpcsDocType> hcpcsDocTypeList = hcpcsDocTypeRepository.findAll();
        assertThat(hcpcsDocTypeList).hasSize(databaseSizeBeforeUpdate);
        HcpcsDocType testHcpcsDocType = hcpcsDocTypeList.get(hcpcsDocTypeList.size() - 1);
        assertThat(testHcpcsDocType.getHcpcsCode()).isEqualTo(UPDATED_HCPCS_CODE);
        assertThat(testHcpcsDocType.getDocumentId()).isEqualTo(UPDATED_DOCUMENT_ID);
        assertThat(testHcpcsDocType.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testHcpcsDocType.getDocumentFilePath()).isEqualTo(DEFAULT_DOCUMENT_FILE_PATH);
        assertThat(testHcpcsDocType.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testHcpcsDocType.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testHcpcsDocType.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testHcpcsDocType.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testHcpcsDocType.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testHcpcsDocType.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testHcpcsDocType.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testHcpcsDocType.getHcpcsDocTypeUuid()).isEqualTo(UPDATED_HCPCS_DOC_TYPE_UUID);
        assertThat(testHcpcsDocType.getFileType()).isEqualTo(UPDATED_FILE_TYPE);
        assertThat(testHcpcsDocType.getFormName()).isEqualTo(UPDATED_FORM_NAME);
    }

    @Test
    @Transactional
    void fullUpdateHcpcsDocTypeWithPatch() throws Exception {
        // Initialize the database
        hcpcsDocTypeRepository.saveAndFlush(hcpcsDocType);

        int databaseSizeBeforeUpdate = hcpcsDocTypeRepository.findAll().size();

        // Update the hcpcsDocType using partial update
        HcpcsDocType partialUpdatedHcpcsDocType = new HcpcsDocType();
        partialUpdatedHcpcsDocType.setHcpcsDoctypeId(hcpcsDocType.getHcpcsDoctypeId());

        partialUpdatedHcpcsDocType
            .hcpcsCode(UPDATED_HCPCS_CODE)
            .documentId(UPDATED_DOCUMENT_ID)
            .documentName(UPDATED_DOCUMENT_NAME)
            .documentFilePath(UPDATED_DOCUMENT_FILE_PATH)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .hcpcsDocTypeUuid(UPDATED_HCPCS_DOC_TYPE_UUID)
            .fileType(UPDATED_FILE_TYPE)
            .formName(UPDATED_FORM_NAME);

        restHcpcsDocTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHcpcsDocType.getHcpcsDoctypeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHcpcsDocType))
            )
            .andExpect(status().isOk());

        // Validate the HcpcsDocType in the database
        List<HcpcsDocType> hcpcsDocTypeList = hcpcsDocTypeRepository.findAll();
        assertThat(hcpcsDocTypeList).hasSize(databaseSizeBeforeUpdate);
        HcpcsDocType testHcpcsDocType = hcpcsDocTypeList.get(hcpcsDocTypeList.size() - 1);
        assertThat(testHcpcsDocType.getHcpcsCode()).isEqualTo(UPDATED_HCPCS_CODE);
        assertThat(testHcpcsDocType.getDocumentId()).isEqualTo(UPDATED_DOCUMENT_ID);
        assertThat(testHcpcsDocType.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testHcpcsDocType.getDocumentFilePath()).isEqualTo(UPDATED_DOCUMENT_FILE_PATH);
        assertThat(testHcpcsDocType.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testHcpcsDocType.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testHcpcsDocType.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testHcpcsDocType.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testHcpcsDocType.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testHcpcsDocType.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testHcpcsDocType.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testHcpcsDocType.getHcpcsDocTypeUuid()).isEqualTo(UPDATED_HCPCS_DOC_TYPE_UUID);
        assertThat(testHcpcsDocType.getFileType()).isEqualTo(UPDATED_FILE_TYPE);
        assertThat(testHcpcsDocType.getFormName()).isEqualTo(UPDATED_FORM_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingHcpcsDocType() throws Exception {
        int databaseSizeBeforeUpdate = hcpcsDocTypeRepository.findAll().size();
        hcpcsDocType.setHcpcsDoctypeId(count.incrementAndGet());

        // Create the HcpcsDocType
        HcpcsDocTypeDTO hcpcsDocTypeDTO = hcpcsDocTypeMapper.toDto(hcpcsDocType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHcpcsDocTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, hcpcsDocTypeDTO.getHcpcsDoctypeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(hcpcsDocTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HcpcsDocType in the database
        List<HcpcsDocType> hcpcsDocTypeList = hcpcsDocTypeRepository.findAll();
        assertThat(hcpcsDocTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHcpcsDocType() throws Exception {
        int databaseSizeBeforeUpdate = hcpcsDocTypeRepository.findAll().size();
        hcpcsDocType.setHcpcsDoctypeId(count.incrementAndGet());

        // Create the HcpcsDocType
        HcpcsDocTypeDTO hcpcsDocTypeDTO = hcpcsDocTypeMapper.toDto(hcpcsDocType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHcpcsDocTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(hcpcsDocTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HcpcsDocType in the database
        List<HcpcsDocType> hcpcsDocTypeList = hcpcsDocTypeRepository.findAll();
        assertThat(hcpcsDocTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHcpcsDocType() throws Exception {
        int databaseSizeBeforeUpdate = hcpcsDocTypeRepository.findAll().size();
        hcpcsDocType.setHcpcsDoctypeId(count.incrementAndGet());

        // Create the HcpcsDocType
        HcpcsDocTypeDTO hcpcsDocTypeDTO = hcpcsDocTypeMapper.toDto(hcpcsDocType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHcpcsDocTypeMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(hcpcsDocTypeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HcpcsDocType in the database
        List<HcpcsDocType> hcpcsDocTypeList = hcpcsDocTypeRepository.findAll();
        assertThat(hcpcsDocTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHcpcsDocType() throws Exception {
        // Initialize the database
        hcpcsDocTypeRepository.saveAndFlush(hcpcsDocType);

        int databaseSizeBeforeDelete = hcpcsDocTypeRepository.findAll().size();

        // Delete the hcpcsDocType
        restHcpcsDocTypeMockMvc
            .perform(delete(ENTITY_API_URL_ID, hcpcsDocType.getHcpcsDoctypeId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HcpcsDocType> hcpcsDocTypeList = hcpcsDocTypeRepository.findAll();
        assertThat(hcpcsDocTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
