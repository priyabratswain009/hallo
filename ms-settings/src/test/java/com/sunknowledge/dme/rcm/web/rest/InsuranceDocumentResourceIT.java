package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.InsuranceDocument;
import com.sunknowledge.dme.rcm.repository.InsuranceDocumentRepository;
import com.sunknowledge.dme.rcm.service.dto.InsuranceDocumentDTO;
import com.sunknowledge.dme.rcm.service.mapper.InsuranceDocumentMapper;
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
 * Integration tests for the {@link InsuranceDocumentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InsuranceDocumentResourceIT {

    private static final Long DEFAULT_INSURANCE_ID = 1L;
    private static final Long UPDATED_INSURANCE_ID = 2L;

    private static final String DEFAULT_INSURANCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_BRANCH_ID = 1L;
    private static final Long UPDATED_BRANCH_ID = 2L;

    private static final String DEFAULT_BRANCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_DOCUMENT_TYPE_ID = 1L;
    private static final Long UPDATED_DOCUMENT_TYPE_ID = 2L;

    private static final String DEFAULT_DOCUMENT_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_TYPE_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DOCUMENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOCUMENT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DOCUMENT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_NOTE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY_ID = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DOCUMENT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SCAN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SCAN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_SCAN_BY = 1L;
    private static final Long UPDATED_SCAN_BY = 2L;

    private static final String DEFAULT_FILE_UPLOAD_PATH = "AAAAAAAAAA";
    private static final String UPDATED_FILE_UPLOAD_PATH = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPLOAD_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPLOAD_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPLOAD_BY = 1L;
    private static final Long UPDATED_UPLOAD_BY = 2L;

    private static final String DEFAULT_REVIEW_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_REVIEW_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_REVIEW_REASON = "AAAAAAAAAA";
    private static final String UPDATED_REVIEW_REASON = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_REVIEW_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REVIEW_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_REVIEW_BY = 1L;
    private static final Long UPDATED_REVIEW_BY = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_INSURANCE_DOCUMENT_UUID = UUID.randomUUID();
    private static final UUID UPDATED_INSURANCE_DOCUMENT_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/insurance-documents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{insuranceDocumentId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InsuranceDocumentRepository insuranceDocumentRepository;

    @Autowired
    private InsuranceDocumentMapper insuranceDocumentMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInsuranceDocumentMockMvc;

    private InsuranceDocument insuranceDocument;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceDocument createEntity(EntityManager em) {
        InsuranceDocument insuranceDocument = new InsuranceDocument()
            .insuranceId(DEFAULT_INSURANCE_ID)
            .insuranceName(DEFAULT_INSURANCE_NAME)
            .branchId(DEFAULT_BRANCH_ID)
            .branchName(DEFAULT_BRANCH_NAME)
            .documentTypeId(DEFAULT_DOCUMENT_TYPE_ID)
            .documentTypeName(DEFAULT_DOCUMENT_TYPE_NAME)
            .documentDate(DEFAULT_DOCUMENT_DATE)
            .documentNote(DEFAULT_DOCUMENT_NOTE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdById(DEFAULT_CREATED_BY_ID)
            .status(DEFAULT_STATUS)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .documentTitle(DEFAULT_DOCUMENT_TITLE)
            .documentName(DEFAULT_DOCUMENT_NAME)
            .scanDate(DEFAULT_SCAN_DATE)
            .scanBy(DEFAULT_SCAN_BY)
            .fileUploadPath(DEFAULT_FILE_UPLOAD_PATH)
            .uploadDate(DEFAULT_UPLOAD_DATE)
            .uploadBy(DEFAULT_UPLOAD_BY)
            .reviewStatus(DEFAULT_REVIEW_STATUS)
            .reviewReason(DEFAULT_REVIEW_REASON)
            .reviewDate(DEFAULT_REVIEW_DATE)
            .reviewBy(DEFAULT_REVIEW_BY)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .insuranceDocumentUuid(DEFAULT_INSURANCE_DOCUMENT_UUID);
        return insuranceDocument;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceDocument createUpdatedEntity(EntityManager em) {
        InsuranceDocument insuranceDocument = new InsuranceDocument()
            .insuranceId(UPDATED_INSURANCE_ID)
            .insuranceName(UPDATED_INSURANCE_NAME)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .documentTypeId(UPDATED_DOCUMENT_TYPE_ID)
            .documentTypeName(UPDATED_DOCUMENT_TYPE_NAME)
            .documentDate(UPDATED_DOCUMENT_DATE)
            .documentNote(UPDATED_DOCUMENT_NOTE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .documentName(UPDATED_DOCUMENT_NAME)
            .scanDate(UPDATED_SCAN_DATE)
            .scanBy(UPDATED_SCAN_BY)
            .fileUploadPath(UPDATED_FILE_UPLOAD_PATH)
            .uploadDate(UPDATED_UPLOAD_DATE)
            .uploadBy(UPDATED_UPLOAD_BY)
            .reviewStatus(UPDATED_REVIEW_STATUS)
            .reviewReason(UPDATED_REVIEW_REASON)
            .reviewDate(UPDATED_REVIEW_DATE)
            .reviewBy(UPDATED_REVIEW_BY)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .insuranceDocumentUuid(UPDATED_INSURANCE_DOCUMENT_UUID);
        return insuranceDocument;
    }

    @BeforeEach
    public void initTest() {
        insuranceDocument = createEntity(em);
    }

    @Test
    @Transactional
    void createInsuranceDocument() throws Exception {
        int databaseSizeBeforeCreate = insuranceDocumentRepository.findAll().size();
        // Create the InsuranceDocument
        InsuranceDocumentDTO insuranceDocumentDTO = insuranceDocumentMapper.toDto(insuranceDocument);
        restInsuranceDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceDocumentDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InsuranceDocument in the database
        List<InsuranceDocument> insuranceDocumentList = insuranceDocumentRepository.findAll();
        assertThat(insuranceDocumentList).hasSize(databaseSizeBeforeCreate + 1);
        InsuranceDocument testInsuranceDocument = insuranceDocumentList.get(insuranceDocumentList.size() - 1);
        assertThat(testInsuranceDocument.getInsuranceId()).isEqualTo(DEFAULT_INSURANCE_ID);
        assertThat(testInsuranceDocument.getInsuranceName()).isEqualTo(DEFAULT_INSURANCE_NAME);
        assertThat(testInsuranceDocument.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testInsuranceDocument.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testInsuranceDocument.getDocumentTypeId()).isEqualTo(DEFAULT_DOCUMENT_TYPE_ID);
        assertThat(testInsuranceDocument.getDocumentTypeName()).isEqualTo(DEFAULT_DOCUMENT_TYPE_NAME);
        assertThat(testInsuranceDocument.getDocumentDate()).isEqualTo(DEFAULT_DOCUMENT_DATE);
        assertThat(testInsuranceDocument.getDocumentNote()).isEqualTo(DEFAULT_DOCUMENT_NOTE);
        assertThat(testInsuranceDocument.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testInsuranceDocument.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testInsuranceDocument.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testInsuranceDocument.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testInsuranceDocument.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testInsuranceDocument.getDocumentTitle()).isEqualTo(DEFAULT_DOCUMENT_TITLE);
        assertThat(testInsuranceDocument.getDocumentName()).isEqualTo(DEFAULT_DOCUMENT_NAME);
        assertThat(testInsuranceDocument.getScanDate()).isEqualTo(DEFAULT_SCAN_DATE);
        assertThat(testInsuranceDocument.getScanBy()).isEqualTo(DEFAULT_SCAN_BY);
        assertThat(testInsuranceDocument.getFileUploadPath()).isEqualTo(DEFAULT_FILE_UPLOAD_PATH);
        assertThat(testInsuranceDocument.getUploadDate()).isEqualTo(DEFAULT_UPLOAD_DATE);
        assertThat(testInsuranceDocument.getUploadBy()).isEqualTo(DEFAULT_UPLOAD_BY);
        assertThat(testInsuranceDocument.getReviewStatus()).isEqualTo(DEFAULT_REVIEW_STATUS);
        assertThat(testInsuranceDocument.getReviewReason()).isEqualTo(DEFAULT_REVIEW_REASON);
        assertThat(testInsuranceDocument.getReviewDate()).isEqualTo(DEFAULT_REVIEW_DATE);
        assertThat(testInsuranceDocument.getReviewBy()).isEqualTo(DEFAULT_REVIEW_BY);
        assertThat(testInsuranceDocument.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testInsuranceDocument.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testInsuranceDocument.getInsuranceDocumentUuid()).isEqualTo(DEFAULT_INSURANCE_DOCUMENT_UUID);
    }

    @Test
    @Transactional
    void createInsuranceDocumentWithExistingId() throws Exception {
        // Create the InsuranceDocument with an existing ID
        insuranceDocument.setInsuranceDocumentId(1L);
        InsuranceDocumentDTO insuranceDocumentDTO = insuranceDocumentMapper.toDto(insuranceDocument);

        int databaseSizeBeforeCreate = insuranceDocumentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsuranceDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceDocument in the database
        List<InsuranceDocument> insuranceDocumentList = insuranceDocumentRepository.findAll();
        assertThat(insuranceDocumentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInsuranceDocuments() throws Exception {
        // Initialize the database
        insuranceDocumentRepository.saveAndFlush(insuranceDocument);

        // Get all the insuranceDocumentList
        restInsuranceDocumentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=insuranceDocumentId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].insuranceDocumentId").value(hasItem(insuranceDocument.getInsuranceDocumentId().intValue())))
            .andExpect(jsonPath("$.[*].insuranceId").value(hasItem(DEFAULT_INSURANCE_ID.intValue())))
            .andExpect(jsonPath("$.[*].insuranceName").value(hasItem(DEFAULT_INSURANCE_NAME)))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].branchName").value(hasItem(DEFAULT_BRANCH_NAME)))
            .andExpect(jsonPath("$.[*].documentTypeId").value(hasItem(DEFAULT_DOCUMENT_TYPE_ID.intValue())))
            .andExpect(jsonPath("$.[*].documentTypeName").value(hasItem(DEFAULT_DOCUMENT_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].documentDate").value(hasItem(DEFAULT_DOCUMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].documentNote").value(hasItem(DEFAULT_DOCUMENT_NOTE)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].documentTitle").value(hasItem(DEFAULT_DOCUMENT_TITLE)))
            .andExpect(jsonPath("$.[*].documentName").value(hasItem(DEFAULT_DOCUMENT_NAME)))
            .andExpect(jsonPath("$.[*].scanDate").value(hasItem(DEFAULT_SCAN_DATE.toString())))
            .andExpect(jsonPath("$.[*].scanBy").value(hasItem(DEFAULT_SCAN_BY.intValue())))
            .andExpect(jsonPath("$.[*].fileUploadPath").value(hasItem(DEFAULT_FILE_UPLOAD_PATH)))
            .andExpect(jsonPath("$.[*].uploadDate").value(hasItem(DEFAULT_UPLOAD_DATE.toString())))
            .andExpect(jsonPath("$.[*].uploadBy").value(hasItem(DEFAULT_UPLOAD_BY.intValue())))
            .andExpect(jsonPath("$.[*].reviewStatus").value(hasItem(DEFAULT_REVIEW_STATUS)))
            .andExpect(jsonPath("$.[*].reviewReason").value(hasItem(DEFAULT_REVIEW_REASON)))
            .andExpect(jsonPath("$.[*].reviewDate").value(hasItem(DEFAULT_REVIEW_DATE.toString())))
            .andExpect(jsonPath("$.[*].reviewBy").value(hasItem(DEFAULT_REVIEW_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].insuranceDocumentUuid").value(hasItem(DEFAULT_INSURANCE_DOCUMENT_UUID.toString())));
    }

    @Test
    @Transactional
    void getInsuranceDocument() throws Exception {
        // Initialize the database
        insuranceDocumentRepository.saveAndFlush(insuranceDocument);

        // Get the insuranceDocument
        restInsuranceDocumentMockMvc
            .perform(get(ENTITY_API_URL_ID, insuranceDocument.getInsuranceDocumentId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.insuranceDocumentId").value(insuranceDocument.getInsuranceDocumentId().intValue()))
            .andExpect(jsonPath("$.insuranceId").value(DEFAULT_INSURANCE_ID.intValue()))
            .andExpect(jsonPath("$.insuranceName").value(DEFAULT_INSURANCE_NAME))
            .andExpect(jsonPath("$.branchId").value(DEFAULT_BRANCH_ID.intValue()))
            .andExpect(jsonPath("$.branchName").value(DEFAULT_BRANCH_NAME))
            .andExpect(jsonPath("$.documentTypeId").value(DEFAULT_DOCUMENT_TYPE_ID.intValue()))
            .andExpect(jsonPath("$.documentTypeName").value(DEFAULT_DOCUMENT_TYPE_NAME))
            .andExpect(jsonPath("$.documentDate").value(DEFAULT_DOCUMENT_DATE.toString()))
            .andExpect(jsonPath("$.documentNote").value(DEFAULT_DOCUMENT_NOTE))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.documentTitle").value(DEFAULT_DOCUMENT_TITLE))
            .andExpect(jsonPath("$.documentName").value(DEFAULT_DOCUMENT_NAME))
            .andExpect(jsonPath("$.scanDate").value(DEFAULT_SCAN_DATE.toString()))
            .andExpect(jsonPath("$.scanBy").value(DEFAULT_SCAN_BY.intValue()))
            .andExpect(jsonPath("$.fileUploadPath").value(DEFAULT_FILE_UPLOAD_PATH))
            .andExpect(jsonPath("$.uploadDate").value(DEFAULT_UPLOAD_DATE.toString()))
            .andExpect(jsonPath("$.uploadBy").value(DEFAULT_UPLOAD_BY.intValue()))
            .andExpect(jsonPath("$.reviewStatus").value(DEFAULT_REVIEW_STATUS))
            .andExpect(jsonPath("$.reviewReason").value(DEFAULT_REVIEW_REASON))
            .andExpect(jsonPath("$.reviewDate").value(DEFAULT_REVIEW_DATE.toString()))
            .andExpect(jsonPath("$.reviewBy").value(DEFAULT_REVIEW_BY.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.insuranceDocumentUuid").value(DEFAULT_INSURANCE_DOCUMENT_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingInsuranceDocument() throws Exception {
        // Get the insuranceDocument
        restInsuranceDocumentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewInsuranceDocument() throws Exception {
        // Initialize the database
        insuranceDocumentRepository.saveAndFlush(insuranceDocument);

        int databaseSizeBeforeUpdate = insuranceDocumentRepository.findAll().size();

        // Update the insuranceDocument
        InsuranceDocument updatedInsuranceDocument = insuranceDocumentRepository.findById(insuranceDocument.getInsuranceDocumentId()).get();
        // Disconnect from session so that the updates on updatedInsuranceDocument are not directly saved in db
        em.detach(updatedInsuranceDocument);
        updatedInsuranceDocument
            .insuranceId(UPDATED_INSURANCE_ID)
            .insuranceName(UPDATED_INSURANCE_NAME)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .documentTypeId(UPDATED_DOCUMENT_TYPE_ID)
            .documentTypeName(UPDATED_DOCUMENT_TYPE_NAME)
            .documentDate(UPDATED_DOCUMENT_DATE)
            .documentNote(UPDATED_DOCUMENT_NOTE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .documentName(UPDATED_DOCUMENT_NAME)
            .scanDate(UPDATED_SCAN_DATE)
            .scanBy(UPDATED_SCAN_BY)
            .fileUploadPath(UPDATED_FILE_UPLOAD_PATH)
            .uploadDate(UPDATED_UPLOAD_DATE)
            .uploadBy(UPDATED_UPLOAD_BY)
            .reviewStatus(UPDATED_REVIEW_STATUS)
            .reviewReason(UPDATED_REVIEW_REASON)
            .reviewDate(UPDATED_REVIEW_DATE)
            .reviewBy(UPDATED_REVIEW_BY)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .insuranceDocumentUuid(UPDATED_INSURANCE_DOCUMENT_UUID);
        InsuranceDocumentDTO insuranceDocumentDTO = insuranceDocumentMapper.toDto(updatedInsuranceDocument);

        restInsuranceDocumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, insuranceDocumentDTO.getInsuranceDocumentId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceDocumentDTO))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceDocument in the database
        List<InsuranceDocument> insuranceDocumentList = insuranceDocumentRepository.findAll();
        assertThat(insuranceDocumentList).hasSize(databaseSizeBeforeUpdate);
        InsuranceDocument testInsuranceDocument = insuranceDocumentList.get(insuranceDocumentList.size() - 1);
        assertThat(testInsuranceDocument.getInsuranceId()).isEqualTo(UPDATED_INSURANCE_ID);
        assertThat(testInsuranceDocument.getInsuranceName()).isEqualTo(UPDATED_INSURANCE_NAME);
        assertThat(testInsuranceDocument.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testInsuranceDocument.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testInsuranceDocument.getDocumentTypeId()).isEqualTo(UPDATED_DOCUMENT_TYPE_ID);
        assertThat(testInsuranceDocument.getDocumentTypeName()).isEqualTo(UPDATED_DOCUMENT_TYPE_NAME);
        assertThat(testInsuranceDocument.getDocumentDate()).isEqualTo(UPDATED_DOCUMENT_DATE);
        assertThat(testInsuranceDocument.getDocumentNote()).isEqualTo(UPDATED_DOCUMENT_NOTE);
        assertThat(testInsuranceDocument.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testInsuranceDocument.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testInsuranceDocument.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInsuranceDocument.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testInsuranceDocument.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testInsuranceDocument.getDocumentTitle()).isEqualTo(UPDATED_DOCUMENT_TITLE);
        assertThat(testInsuranceDocument.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testInsuranceDocument.getScanDate()).isEqualTo(UPDATED_SCAN_DATE);
        assertThat(testInsuranceDocument.getScanBy()).isEqualTo(UPDATED_SCAN_BY);
        assertThat(testInsuranceDocument.getFileUploadPath()).isEqualTo(UPDATED_FILE_UPLOAD_PATH);
        assertThat(testInsuranceDocument.getUploadDate()).isEqualTo(UPDATED_UPLOAD_DATE);
        assertThat(testInsuranceDocument.getUploadBy()).isEqualTo(UPDATED_UPLOAD_BY);
        assertThat(testInsuranceDocument.getReviewStatus()).isEqualTo(UPDATED_REVIEW_STATUS);
        assertThat(testInsuranceDocument.getReviewReason()).isEqualTo(UPDATED_REVIEW_REASON);
        assertThat(testInsuranceDocument.getReviewDate()).isEqualTo(UPDATED_REVIEW_DATE);
        assertThat(testInsuranceDocument.getReviewBy()).isEqualTo(UPDATED_REVIEW_BY);
        assertThat(testInsuranceDocument.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInsuranceDocument.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testInsuranceDocument.getInsuranceDocumentUuid()).isEqualTo(UPDATED_INSURANCE_DOCUMENT_UUID);
    }

    @Test
    @Transactional
    void putNonExistingInsuranceDocument() throws Exception {
        int databaseSizeBeforeUpdate = insuranceDocumentRepository.findAll().size();
        insuranceDocument.setInsuranceDocumentId(count.incrementAndGet());

        // Create the InsuranceDocument
        InsuranceDocumentDTO insuranceDocumentDTO = insuranceDocumentMapper.toDto(insuranceDocument);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceDocumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, insuranceDocumentDTO.getInsuranceDocumentId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceDocument in the database
        List<InsuranceDocument> insuranceDocumentList = insuranceDocumentRepository.findAll();
        assertThat(insuranceDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInsuranceDocument() throws Exception {
        int databaseSizeBeforeUpdate = insuranceDocumentRepository.findAll().size();
        insuranceDocument.setInsuranceDocumentId(count.incrementAndGet());

        // Create the InsuranceDocument
        InsuranceDocumentDTO insuranceDocumentDTO = insuranceDocumentMapper.toDto(insuranceDocument);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceDocumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceDocument in the database
        List<InsuranceDocument> insuranceDocumentList = insuranceDocumentRepository.findAll();
        assertThat(insuranceDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInsuranceDocument() throws Exception {
        int databaseSizeBeforeUpdate = insuranceDocumentRepository.findAll().size();
        insuranceDocument.setInsuranceDocumentId(count.incrementAndGet());

        // Create the InsuranceDocument
        InsuranceDocumentDTO insuranceDocumentDTO = insuranceDocumentMapper.toDto(insuranceDocument);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceDocumentMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(insuranceDocumentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InsuranceDocument in the database
        List<InsuranceDocument> insuranceDocumentList = insuranceDocumentRepository.findAll();
        assertThat(insuranceDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInsuranceDocumentWithPatch() throws Exception {
        // Initialize the database
        insuranceDocumentRepository.saveAndFlush(insuranceDocument);

        int databaseSizeBeforeUpdate = insuranceDocumentRepository.findAll().size();

        // Update the insuranceDocument using partial update
        InsuranceDocument partialUpdatedInsuranceDocument = new InsuranceDocument();
        partialUpdatedInsuranceDocument.setInsuranceDocumentId(insuranceDocument.getInsuranceDocumentId());

        partialUpdatedInsuranceDocument
            .insuranceId(UPDATED_INSURANCE_ID)
            .branchId(UPDATED_BRANCH_ID)
            .documentNote(UPDATED_DOCUMENT_NOTE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .scanDate(UPDATED_SCAN_DATE)
            .fileUploadPath(UPDATED_FILE_UPLOAD_PATH)
            .uploadDate(UPDATED_UPLOAD_DATE)
            .uploadBy(UPDATED_UPLOAD_BY)
            .reviewReason(UPDATED_REVIEW_REASON)
            .reviewDate(UPDATED_REVIEW_DATE)
            .reviewBy(UPDATED_REVIEW_BY)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .insuranceDocumentUuid(UPDATED_INSURANCE_DOCUMENT_UUID);

        restInsuranceDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInsuranceDocument.getInsuranceDocumentId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInsuranceDocument))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceDocument in the database
        List<InsuranceDocument> insuranceDocumentList = insuranceDocumentRepository.findAll();
        assertThat(insuranceDocumentList).hasSize(databaseSizeBeforeUpdate);
        InsuranceDocument testInsuranceDocument = insuranceDocumentList.get(insuranceDocumentList.size() - 1);
        assertThat(testInsuranceDocument.getInsuranceId()).isEqualTo(UPDATED_INSURANCE_ID);
        assertThat(testInsuranceDocument.getInsuranceName()).isEqualTo(DEFAULT_INSURANCE_NAME);
        assertThat(testInsuranceDocument.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testInsuranceDocument.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testInsuranceDocument.getDocumentTypeId()).isEqualTo(DEFAULT_DOCUMENT_TYPE_ID);
        assertThat(testInsuranceDocument.getDocumentTypeName()).isEqualTo(DEFAULT_DOCUMENT_TYPE_NAME);
        assertThat(testInsuranceDocument.getDocumentDate()).isEqualTo(DEFAULT_DOCUMENT_DATE);
        assertThat(testInsuranceDocument.getDocumentNote()).isEqualTo(UPDATED_DOCUMENT_NOTE);
        assertThat(testInsuranceDocument.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testInsuranceDocument.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testInsuranceDocument.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testInsuranceDocument.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testInsuranceDocument.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testInsuranceDocument.getDocumentTitle()).isEqualTo(UPDATED_DOCUMENT_TITLE);
        assertThat(testInsuranceDocument.getDocumentName()).isEqualTo(DEFAULT_DOCUMENT_NAME);
        assertThat(testInsuranceDocument.getScanDate()).isEqualTo(UPDATED_SCAN_DATE);
        assertThat(testInsuranceDocument.getScanBy()).isEqualTo(DEFAULT_SCAN_BY);
        assertThat(testInsuranceDocument.getFileUploadPath()).isEqualTo(UPDATED_FILE_UPLOAD_PATH);
        assertThat(testInsuranceDocument.getUploadDate()).isEqualTo(UPDATED_UPLOAD_DATE);
        assertThat(testInsuranceDocument.getUploadBy()).isEqualTo(UPDATED_UPLOAD_BY);
        assertThat(testInsuranceDocument.getReviewStatus()).isEqualTo(DEFAULT_REVIEW_STATUS);
        assertThat(testInsuranceDocument.getReviewReason()).isEqualTo(UPDATED_REVIEW_REASON);
        assertThat(testInsuranceDocument.getReviewDate()).isEqualTo(UPDATED_REVIEW_DATE);
        assertThat(testInsuranceDocument.getReviewBy()).isEqualTo(UPDATED_REVIEW_BY);
        assertThat(testInsuranceDocument.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInsuranceDocument.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testInsuranceDocument.getInsuranceDocumentUuid()).isEqualTo(UPDATED_INSURANCE_DOCUMENT_UUID);
    }

    @Test
    @Transactional
    void fullUpdateInsuranceDocumentWithPatch() throws Exception {
        // Initialize the database
        insuranceDocumentRepository.saveAndFlush(insuranceDocument);

        int databaseSizeBeforeUpdate = insuranceDocumentRepository.findAll().size();

        // Update the insuranceDocument using partial update
        InsuranceDocument partialUpdatedInsuranceDocument = new InsuranceDocument();
        partialUpdatedInsuranceDocument.setInsuranceDocumentId(insuranceDocument.getInsuranceDocumentId());

        partialUpdatedInsuranceDocument
            .insuranceId(UPDATED_INSURANCE_ID)
            .insuranceName(UPDATED_INSURANCE_NAME)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .documentTypeId(UPDATED_DOCUMENT_TYPE_ID)
            .documentTypeName(UPDATED_DOCUMENT_TYPE_NAME)
            .documentDate(UPDATED_DOCUMENT_DATE)
            .documentNote(UPDATED_DOCUMENT_NOTE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdById(UPDATED_CREATED_BY_ID)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .documentName(UPDATED_DOCUMENT_NAME)
            .scanDate(UPDATED_SCAN_DATE)
            .scanBy(UPDATED_SCAN_BY)
            .fileUploadPath(UPDATED_FILE_UPLOAD_PATH)
            .uploadDate(UPDATED_UPLOAD_DATE)
            .uploadBy(UPDATED_UPLOAD_BY)
            .reviewStatus(UPDATED_REVIEW_STATUS)
            .reviewReason(UPDATED_REVIEW_REASON)
            .reviewDate(UPDATED_REVIEW_DATE)
            .reviewBy(UPDATED_REVIEW_BY)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .insuranceDocumentUuid(UPDATED_INSURANCE_DOCUMENT_UUID);

        restInsuranceDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInsuranceDocument.getInsuranceDocumentId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInsuranceDocument))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceDocument in the database
        List<InsuranceDocument> insuranceDocumentList = insuranceDocumentRepository.findAll();
        assertThat(insuranceDocumentList).hasSize(databaseSizeBeforeUpdate);
        InsuranceDocument testInsuranceDocument = insuranceDocumentList.get(insuranceDocumentList.size() - 1);
        assertThat(testInsuranceDocument.getInsuranceId()).isEqualTo(UPDATED_INSURANCE_ID);
        assertThat(testInsuranceDocument.getInsuranceName()).isEqualTo(UPDATED_INSURANCE_NAME);
        assertThat(testInsuranceDocument.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testInsuranceDocument.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testInsuranceDocument.getDocumentTypeId()).isEqualTo(UPDATED_DOCUMENT_TYPE_ID);
        assertThat(testInsuranceDocument.getDocumentTypeName()).isEqualTo(UPDATED_DOCUMENT_TYPE_NAME);
        assertThat(testInsuranceDocument.getDocumentDate()).isEqualTo(UPDATED_DOCUMENT_DATE);
        assertThat(testInsuranceDocument.getDocumentNote()).isEqualTo(UPDATED_DOCUMENT_NOTE);
        assertThat(testInsuranceDocument.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testInsuranceDocument.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testInsuranceDocument.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInsuranceDocument.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testInsuranceDocument.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testInsuranceDocument.getDocumentTitle()).isEqualTo(UPDATED_DOCUMENT_TITLE);
        assertThat(testInsuranceDocument.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testInsuranceDocument.getScanDate()).isEqualTo(UPDATED_SCAN_DATE);
        assertThat(testInsuranceDocument.getScanBy()).isEqualTo(UPDATED_SCAN_BY);
        assertThat(testInsuranceDocument.getFileUploadPath()).isEqualTo(UPDATED_FILE_UPLOAD_PATH);
        assertThat(testInsuranceDocument.getUploadDate()).isEqualTo(UPDATED_UPLOAD_DATE);
        assertThat(testInsuranceDocument.getUploadBy()).isEqualTo(UPDATED_UPLOAD_BY);
        assertThat(testInsuranceDocument.getReviewStatus()).isEqualTo(UPDATED_REVIEW_STATUS);
        assertThat(testInsuranceDocument.getReviewReason()).isEqualTo(UPDATED_REVIEW_REASON);
        assertThat(testInsuranceDocument.getReviewDate()).isEqualTo(UPDATED_REVIEW_DATE);
        assertThat(testInsuranceDocument.getReviewBy()).isEqualTo(UPDATED_REVIEW_BY);
        assertThat(testInsuranceDocument.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testInsuranceDocument.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testInsuranceDocument.getInsuranceDocumentUuid()).isEqualTo(UPDATED_INSURANCE_DOCUMENT_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingInsuranceDocument() throws Exception {
        int databaseSizeBeforeUpdate = insuranceDocumentRepository.findAll().size();
        insuranceDocument.setInsuranceDocumentId(count.incrementAndGet());

        // Create the InsuranceDocument
        InsuranceDocumentDTO insuranceDocumentDTO = insuranceDocumentMapper.toDto(insuranceDocument);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, insuranceDocumentDTO.getInsuranceDocumentId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(insuranceDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceDocument in the database
        List<InsuranceDocument> insuranceDocumentList = insuranceDocumentRepository.findAll();
        assertThat(insuranceDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInsuranceDocument() throws Exception {
        int databaseSizeBeforeUpdate = insuranceDocumentRepository.findAll().size();
        insuranceDocument.setInsuranceDocumentId(count.incrementAndGet());

        // Create the InsuranceDocument
        InsuranceDocumentDTO insuranceDocumentDTO = insuranceDocumentMapper.toDto(insuranceDocument);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(insuranceDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceDocument in the database
        List<InsuranceDocument> insuranceDocumentList = insuranceDocumentRepository.findAll();
        assertThat(insuranceDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInsuranceDocument() throws Exception {
        int databaseSizeBeforeUpdate = insuranceDocumentRepository.findAll().size();
        insuranceDocument.setInsuranceDocumentId(count.incrementAndGet());

        // Create the InsuranceDocument
        InsuranceDocumentDTO insuranceDocumentDTO = insuranceDocumentMapper.toDto(insuranceDocument);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(insuranceDocumentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InsuranceDocument in the database
        List<InsuranceDocument> insuranceDocumentList = insuranceDocumentRepository.findAll();
        assertThat(insuranceDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInsuranceDocument() throws Exception {
        // Initialize the database
        insuranceDocumentRepository.saveAndFlush(insuranceDocument);

        int databaseSizeBeforeDelete = insuranceDocumentRepository.findAll().size();

        // Delete the insuranceDocument
        restInsuranceDocumentMockMvc
            .perform(delete(ENTITY_API_URL_ID, insuranceDocument.getInsuranceDocumentId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InsuranceDocument> insuranceDocumentList = insuranceDocumentRepository.findAll();
        assertThat(insuranceDocumentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
