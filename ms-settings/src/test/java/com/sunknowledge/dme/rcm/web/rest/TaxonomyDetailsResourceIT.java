package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.TaxonomyDetails;
import com.sunknowledge.dme.rcm.repository.TaxonomyDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.TaxonomyDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaxonomyDetailsMapper;
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
 * Integration tests for the {@link TaxonomyDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaxonomyDetailsResourceIT {

    private static final String DEFAULT_TAXONOMY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TAXONOMY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TAXONOMY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TAXONOMY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TAXONOMY_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_TAXONOMY_DETAILS = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_TAXONOMY_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_TAXONOMY_DETAILS_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/taxonomy-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{taxonomyDetailsId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TaxonomyDetailsRepository taxonomyDetailsRepository;

    @Autowired
    private TaxonomyDetailsMapper taxonomyDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaxonomyDetailsMockMvc;

    private TaxonomyDetails taxonomyDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaxonomyDetails createEntity(EntityManager em) {
        TaxonomyDetails taxonomyDetails = new TaxonomyDetails()
            .taxonomyCode(DEFAULT_TAXONOMY_CODE)
            .taxonomyName(DEFAULT_TAXONOMY_NAME)
            .taxonomyDetails(DEFAULT_TAXONOMY_DETAILS)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .taxonomyDetailsUuid(DEFAULT_TAXONOMY_DETAILS_UUID);
        return taxonomyDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaxonomyDetails createUpdatedEntity(EntityManager em) {
        TaxonomyDetails taxonomyDetails = new TaxonomyDetails()
            .taxonomyCode(UPDATED_TAXONOMY_CODE)
            .taxonomyName(UPDATED_TAXONOMY_NAME)
            .taxonomyDetails(UPDATED_TAXONOMY_DETAILS)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taxonomyDetailsUuid(UPDATED_TAXONOMY_DETAILS_UUID);
        return taxonomyDetails;
    }

    @BeforeEach
    public void initTest() {
        taxonomyDetails = createEntity(em);
    }

    @Test
    @Transactional
    void createTaxonomyDetails() throws Exception {
        int databaseSizeBeforeCreate = taxonomyDetailsRepository.findAll().size();
        // Create the TaxonomyDetails
        TaxonomyDetailsDTO taxonomyDetailsDTO = taxonomyDetailsMapper.toDto(taxonomyDetails);
        restTaxonomyDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taxonomyDetailsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TaxonomyDetails in the database
        List<TaxonomyDetails> taxonomyDetailsList = taxonomyDetailsRepository.findAll();
        assertThat(taxonomyDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        TaxonomyDetails testTaxonomyDetails = taxonomyDetailsList.get(taxonomyDetailsList.size() - 1);
        assertThat(testTaxonomyDetails.getTaxonomyCode()).isEqualTo(DEFAULT_TAXONOMY_CODE);
        assertThat(testTaxonomyDetails.getTaxonomyName()).isEqualTo(DEFAULT_TAXONOMY_NAME);
        assertThat(testTaxonomyDetails.getTaxonomyDetails()).isEqualTo(DEFAULT_TAXONOMY_DETAILS);
        assertThat(testTaxonomyDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTaxonomyDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testTaxonomyDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testTaxonomyDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testTaxonomyDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testTaxonomyDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testTaxonomyDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testTaxonomyDetails.getTaxonomyDetailsUuid()).isEqualTo(DEFAULT_TAXONOMY_DETAILS_UUID);
    }

    @Test
    @Transactional
    void createTaxonomyDetailsWithExistingId() throws Exception {
        // Create the TaxonomyDetails with an existing ID
        taxonomyDetails.setTaxonomyDetailsId(1L);
        TaxonomyDetailsDTO taxonomyDetailsDTO = taxonomyDetailsMapper.toDto(taxonomyDetails);

        int databaseSizeBeforeCreate = taxonomyDetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaxonomyDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taxonomyDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaxonomyDetails in the database
        List<TaxonomyDetails> taxonomyDetailsList = taxonomyDetailsRepository.findAll();
        assertThat(taxonomyDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTaxonomyDetails() throws Exception {
        // Initialize the database
        taxonomyDetailsRepository.saveAndFlush(taxonomyDetails);

        // Get all the taxonomyDetailsList
        restTaxonomyDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=taxonomyDetailsId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].taxonomyDetailsId").value(hasItem(taxonomyDetails.getTaxonomyDetailsId().intValue())))
            .andExpect(jsonPath("$.[*].taxonomyCode").value(hasItem(DEFAULT_TAXONOMY_CODE)))
            .andExpect(jsonPath("$.[*].taxonomyName").value(hasItem(DEFAULT_TAXONOMY_NAME)))
            .andExpect(jsonPath("$.[*].taxonomyDetails").value(hasItem(DEFAULT_TAXONOMY_DETAILS)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].taxonomyDetailsUuid").value(hasItem(DEFAULT_TAXONOMY_DETAILS_UUID.toString())));
    }

    @Test
    @Transactional
    void getTaxonomyDetails() throws Exception {
        // Initialize the database
        taxonomyDetailsRepository.saveAndFlush(taxonomyDetails);

        // Get the taxonomyDetails
        restTaxonomyDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, taxonomyDetails.getTaxonomyDetailsId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.taxonomyDetailsId").value(taxonomyDetails.getTaxonomyDetailsId().intValue()))
            .andExpect(jsonPath("$.taxonomyCode").value(DEFAULT_TAXONOMY_CODE))
            .andExpect(jsonPath("$.taxonomyName").value(DEFAULT_TAXONOMY_NAME))
            .andExpect(jsonPath("$.taxonomyDetails").value(DEFAULT_TAXONOMY_DETAILS))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.taxonomyDetailsUuid").value(DEFAULT_TAXONOMY_DETAILS_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTaxonomyDetails() throws Exception {
        // Get the taxonomyDetails
        restTaxonomyDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTaxonomyDetails() throws Exception {
        // Initialize the database
        taxonomyDetailsRepository.saveAndFlush(taxonomyDetails);

        int databaseSizeBeforeUpdate = taxonomyDetailsRepository.findAll().size();

        // Update the taxonomyDetails
        TaxonomyDetails updatedTaxonomyDetails = taxonomyDetailsRepository.findById(taxonomyDetails.getTaxonomyDetailsId()).get();
        // Disconnect from session so that the updates on updatedTaxonomyDetails are not directly saved in db
        em.detach(updatedTaxonomyDetails);
        updatedTaxonomyDetails
            .taxonomyCode(UPDATED_TAXONOMY_CODE)
            .taxonomyName(UPDATED_TAXONOMY_NAME)
            .taxonomyDetails(UPDATED_TAXONOMY_DETAILS)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taxonomyDetailsUuid(UPDATED_TAXONOMY_DETAILS_UUID);
        TaxonomyDetailsDTO taxonomyDetailsDTO = taxonomyDetailsMapper.toDto(updatedTaxonomyDetails);

        restTaxonomyDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taxonomyDetailsDTO.getTaxonomyDetailsId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taxonomyDetailsDTO))
            )
            .andExpect(status().isOk());

        // Validate the TaxonomyDetails in the database
        List<TaxonomyDetails> taxonomyDetailsList = taxonomyDetailsRepository.findAll();
        assertThat(taxonomyDetailsList).hasSize(databaseSizeBeforeUpdate);
        TaxonomyDetails testTaxonomyDetails = taxonomyDetailsList.get(taxonomyDetailsList.size() - 1);
        assertThat(testTaxonomyDetails.getTaxonomyCode()).isEqualTo(UPDATED_TAXONOMY_CODE);
        assertThat(testTaxonomyDetails.getTaxonomyName()).isEqualTo(UPDATED_TAXONOMY_NAME);
        assertThat(testTaxonomyDetails.getTaxonomyDetails()).isEqualTo(UPDATED_TAXONOMY_DETAILS);
        assertThat(testTaxonomyDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTaxonomyDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testTaxonomyDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testTaxonomyDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTaxonomyDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testTaxonomyDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testTaxonomyDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testTaxonomyDetails.getTaxonomyDetailsUuid()).isEqualTo(UPDATED_TAXONOMY_DETAILS_UUID);
    }

    @Test
    @Transactional
    void putNonExistingTaxonomyDetails() throws Exception {
        int databaseSizeBeforeUpdate = taxonomyDetailsRepository.findAll().size();
        taxonomyDetails.setTaxonomyDetailsId(count.incrementAndGet());

        // Create the TaxonomyDetails
        TaxonomyDetailsDTO taxonomyDetailsDTO = taxonomyDetailsMapper.toDto(taxonomyDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaxonomyDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taxonomyDetailsDTO.getTaxonomyDetailsId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taxonomyDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaxonomyDetails in the database
        List<TaxonomyDetails> taxonomyDetailsList = taxonomyDetailsRepository.findAll();
        assertThat(taxonomyDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaxonomyDetails() throws Exception {
        int databaseSizeBeforeUpdate = taxonomyDetailsRepository.findAll().size();
        taxonomyDetails.setTaxonomyDetailsId(count.incrementAndGet());

        // Create the TaxonomyDetails
        TaxonomyDetailsDTO taxonomyDetailsDTO = taxonomyDetailsMapper.toDto(taxonomyDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaxonomyDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taxonomyDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaxonomyDetails in the database
        List<TaxonomyDetails> taxonomyDetailsList = taxonomyDetailsRepository.findAll();
        assertThat(taxonomyDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaxonomyDetails() throws Exception {
        int databaseSizeBeforeUpdate = taxonomyDetailsRepository.findAll().size();
        taxonomyDetails.setTaxonomyDetailsId(count.incrementAndGet());

        // Create the TaxonomyDetails
        TaxonomyDetailsDTO taxonomyDetailsDTO = taxonomyDetailsMapper.toDto(taxonomyDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaxonomyDetailsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(taxonomyDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaxonomyDetails in the database
        List<TaxonomyDetails> taxonomyDetailsList = taxonomyDetailsRepository.findAll();
        assertThat(taxonomyDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaxonomyDetailsWithPatch() throws Exception {
        // Initialize the database
        taxonomyDetailsRepository.saveAndFlush(taxonomyDetails);

        int databaseSizeBeforeUpdate = taxonomyDetailsRepository.findAll().size();

        // Update the taxonomyDetails using partial update
        TaxonomyDetails partialUpdatedTaxonomyDetails = new TaxonomyDetails();
        partialUpdatedTaxonomyDetails.setTaxonomyDetailsId(taxonomyDetails.getTaxonomyDetailsId());

        partialUpdatedTaxonomyDetails
            .taxonomyName(UPDATED_TAXONOMY_NAME)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .taxonomyDetailsUuid(UPDATED_TAXONOMY_DETAILS_UUID);

        restTaxonomyDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaxonomyDetails.getTaxonomyDetailsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaxonomyDetails))
            )
            .andExpect(status().isOk());

        // Validate the TaxonomyDetails in the database
        List<TaxonomyDetails> taxonomyDetailsList = taxonomyDetailsRepository.findAll();
        assertThat(taxonomyDetailsList).hasSize(databaseSizeBeforeUpdate);
        TaxonomyDetails testTaxonomyDetails = taxonomyDetailsList.get(taxonomyDetailsList.size() - 1);
        assertThat(testTaxonomyDetails.getTaxonomyCode()).isEqualTo(DEFAULT_TAXONOMY_CODE);
        assertThat(testTaxonomyDetails.getTaxonomyName()).isEqualTo(UPDATED_TAXONOMY_NAME);
        assertThat(testTaxonomyDetails.getTaxonomyDetails()).isEqualTo(DEFAULT_TAXONOMY_DETAILS);
        assertThat(testTaxonomyDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTaxonomyDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testTaxonomyDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testTaxonomyDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testTaxonomyDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testTaxonomyDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testTaxonomyDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testTaxonomyDetails.getTaxonomyDetailsUuid()).isEqualTo(UPDATED_TAXONOMY_DETAILS_UUID);
    }

    @Test
    @Transactional
    void fullUpdateTaxonomyDetailsWithPatch() throws Exception {
        // Initialize the database
        taxonomyDetailsRepository.saveAndFlush(taxonomyDetails);

        int databaseSizeBeforeUpdate = taxonomyDetailsRepository.findAll().size();

        // Update the taxonomyDetails using partial update
        TaxonomyDetails partialUpdatedTaxonomyDetails = new TaxonomyDetails();
        partialUpdatedTaxonomyDetails.setTaxonomyDetailsId(taxonomyDetails.getTaxonomyDetailsId());

        partialUpdatedTaxonomyDetails
            .taxonomyCode(UPDATED_TAXONOMY_CODE)
            .taxonomyName(UPDATED_TAXONOMY_NAME)
            .taxonomyDetails(UPDATED_TAXONOMY_DETAILS)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .taxonomyDetailsUuid(UPDATED_TAXONOMY_DETAILS_UUID);

        restTaxonomyDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaxonomyDetails.getTaxonomyDetailsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaxonomyDetails))
            )
            .andExpect(status().isOk());

        // Validate the TaxonomyDetails in the database
        List<TaxonomyDetails> taxonomyDetailsList = taxonomyDetailsRepository.findAll();
        assertThat(taxonomyDetailsList).hasSize(databaseSizeBeforeUpdate);
        TaxonomyDetails testTaxonomyDetails = taxonomyDetailsList.get(taxonomyDetailsList.size() - 1);
        assertThat(testTaxonomyDetails.getTaxonomyCode()).isEqualTo(UPDATED_TAXONOMY_CODE);
        assertThat(testTaxonomyDetails.getTaxonomyName()).isEqualTo(UPDATED_TAXONOMY_NAME);
        assertThat(testTaxonomyDetails.getTaxonomyDetails()).isEqualTo(UPDATED_TAXONOMY_DETAILS);
        assertThat(testTaxonomyDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTaxonomyDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testTaxonomyDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testTaxonomyDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTaxonomyDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testTaxonomyDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testTaxonomyDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testTaxonomyDetails.getTaxonomyDetailsUuid()).isEqualTo(UPDATED_TAXONOMY_DETAILS_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingTaxonomyDetails() throws Exception {
        int databaseSizeBeforeUpdate = taxonomyDetailsRepository.findAll().size();
        taxonomyDetails.setTaxonomyDetailsId(count.incrementAndGet());

        // Create the TaxonomyDetails
        TaxonomyDetailsDTO taxonomyDetailsDTO = taxonomyDetailsMapper.toDto(taxonomyDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaxonomyDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taxonomyDetailsDTO.getTaxonomyDetailsId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taxonomyDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaxonomyDetails in the database
        List<TaxonomyDetails> taxonomyDetailsList = taxonomyDetailsRepository.findAll();
        assertThat(taxonomyDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaxonomyDetails() throws Exception {
        int databaseSizeBeforeUpdate = taxonomyDetailsRepository.findAll().size();
        taxonomyDetails.setTaxonomyDetailsId(count.incrementAndGet());

        // Create the TaxonomyDetails
        TaxonomyDetailsDTO taxonomyDetailsDTO = taxonomyDetailsMapper.toDto(taxonomyDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaxonomyDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taxonomyDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaxonomyDetails in the database
        List<TaxonomyDetails> taxonomyDetailsList = taxonomyDetailsRepository.findAll();
        assertThat(taxonomyDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaxonomyDetails() throws Exception {
        int databaseSizeBeforeUpdate = taxonomyDetailsRepository.findAll().size();
        taxonomyDetails.setTaxonomyDetailsId(count.incrementAndGet());

        // Create the TaxonomyDetails
        TaxonomyDetailsDTO taxonomyDetailsDTO = taxonomyDetailsMapper.toDto(taxonomyDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaxonomyDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(taxonomyDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaxonomyDetails in the database
        List<TaxonomyDetails> taxonomyDetailsList = taxonomyDetailsRepository.findAll();
        assertThat(taxonomyDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaxonomyDetails() throws Exception {
        // Initialize the database
        taxonomyDetailsRepository.saveAndFlush(taxonomyDetails);

        int databaseSizeBeforeDelete = taxonomyDetailsRepository.findAll().size();

        // Delete the taxonomyDetails
        restTaxonomyDetailsMockMvc
            .perform(delete(ENTITY_API_URL_ID, taxonomyDetails.getTaxonomyDetailsId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TaxonomyDetails> taxonomyDetailsList = taxonomyDetailsRepository.findAll();
        assertThat(taxonomyDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
