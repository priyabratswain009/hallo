package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SalesOrderClassification;
import com.sunknowledge.dme.rcm.repository.SalesOrderClassificationRepository;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClassificationDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderClassificationMapper;
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
 * Integration tests for the {@link SalesOrderClassificationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SalesOrderClassificationResourceIT {

    private static final String DEFAULT_ORDER_CLASSIFICATION_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_CLASSIFICATION_DESCRIPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

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

    private static final UUID DEFAULT_SALES_ORDER_CLASSIFICATION_UUID = UUID.randomUUID();
    private static final UUID UPDATED_SALES_ORDER_CLASSIFICATION_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/sales-order-classifications";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{orderClassificationId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SalesOrderClassificationRepository salesOrderClassificationRepository;

    @Autowired
    private SalesOrderClassificationMapper salesOrderClassificationMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSalesOrderClassificationMockMvc;

    private SalesOrderClassification salesOrderClassification;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderClassification createEntity(EntityManager em) {
        SalesOrderClassification salesOrderClassification = new SalesOrderClassification()
            .orderClassificationDescription(DEFAULT_ORDER_CLASSIFICATION_DESCRIPTION)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .status(DEFAULT_STATUS)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .salesOrderClassificationUuid(DEFAULT_SALES_ORDER_CLASSIFICATION_UUID);
        return salesOrderClassification;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderClassification createUpdatedEntity(EntityManager em) {
        SalesOrderClassification salesOrderClassification = new SalesOrderClassification()
            .orderClassificationDescription(UPDATED_ORDER_CLASSIFICATION_DESCRIPTION)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .salesOrderClassificationUuid(UPDATED_SALES_ORDER_CLASSIFICATION_UUID);
        return salesOrderClassification;
    }

    @BeforeEach
    public void initTest() {
        salesOrderClassification = createEntity(em);
    }

    @Test
    @Transactional
    void createSalesOrderClassification() throws Exception {
        int databaseSizeBeforeCreate = salesOrderClassificationRepository.findAll().size();
        // Create the SalesOrderClassification
        SalesOrderClassificationDTO salesOrderClassificationDTO = salesOrderClassificationMapper.toDto(salesOrderClassification);
        restSalesOrderClassificationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesOrderClassificationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SalesOrderClassification in the database
        List<SalesOrderClassification> salesOrderClassificationList = salesOrderClassificationRepository.findAll();
        assertThat(salesOrderClassificationList).hasSize(databaseSizeBeforeCreate + 1);
        SalesOrderClassification testSalesOrderClassification = salesOrderClassificationList.get(salesOrderClassificationList.size() - 1);
        assertThat(testSalesOrderClassification.getOrderClassificationDescription()).isEqualTo(DEFAULT_ORDER_CLASSIFICATION_DESCRIPTION);
        assertThat(testSalesOrderClassification.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testSalesOrderClassification.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSalesOrderClassification.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSalesOrderClassification.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSalesOrderClassification.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSalesOrderClassification.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testSalesOrderClassification.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSalesOrderClassification.getSalesOrderClassificationUuid()).isEqualTo(DEFAULT_SALES_ORDER_CLASSIFICATION_UUID);
    }

    @Test
    @Transactional
    void createSalesOrderClassificationWithExistingId() throws Exception {
        // Create the SalesOrderClassification with an existing ID
        salesOrderClassification.setOrderClassificationId(1L);
        SalesOrderClassificationDTO salesOrderClassificationDTO = salesOrderClassificationMapper.toDto(salesOrderClassification);

        int databaseSizeBeforeCreate = salesOrderClassificationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalesOrderClassificationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesOrderClassificationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesOrderClassification in the database
        List<SalesOrderClassification> salesOrderClassificationList = salesOrderClassificationRepository.findAll();
        assertThat(salesOrderClassificationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSalesOrderClassifications() throws Exception {
        // Initialize the database
        salesOrderClassificationRepository.saveAndFlush(salesOrderClassification);

        // Get all the salesOrderClassificationList
        restSalesOrderClassificationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=orderClassificationId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].orderClassificationId").value(hasItem(salesOrderClassification.getOrderClassificationId().intValue()))
            )
            .andExpect(jsonPath("$.[*].orderClassificationDescription").value(hasItem(DEFAULT_ORDER_CLASSIFICATION_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].salesOrderClassificationUuid").value(hasItem(DEFAULT_SALES_ORDER_CLASSIFICATION_UUID.toString())));
    }

    @Test
    @Transactional
    void getSalesOrderClassification() throws Exception {
        // Initialize the database
        salesOrderClassificationRepository.saveAndFlush(salesOrderClassification);

        // Get the salesOrderClassification
        restSalesOrderClassificationMockMvc
            .perform(get(ENTITY_API_URL_ID, salesOrderClassification.getOrderClassificationId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.orderClassificationId").value(salesOrderClassification.getOrderClassificationId().intValue()))
            .andExpect(jsonPath("$.orderClassificationDescription").value(DEFAULT_ORDER_CLASSIFICATION_DESCRIPTION))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.salesOrderClassificationUuid").value(DEFAULT_SALES_ORDER_CLASSIFICATION_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingSalesOrderClassification() throws Exception {
        // Get the salesOrderClassification
        restSalesOrderClassificationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSalesOrderClassification() throws Exception {
        // Initialize the database
        salesOrderClassificationRepository.saveAndFlush(salesOrderClassification);

        int databaseSizeBeforeUpdate = salesOrderClassificationRepository.findAll().size();

        // Update the salesOrderClassification
        SalesOrderClassification updatedSalesOrderClassification = salesOrderClassificationRepository
            .findById(salesOrderClassification.getOrderClassificationId())
            .get();
        // Disconnect from session so that the updates on updatedSalesOrderClassification are not directly saved in db
        em.detach(updatedSalesOrderClassification);
        updatedSalesOrderClassification
            .orderClassificationDescription(UPDATED_ORDER_CLASSIFICATION_DESCRIPTION)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .salesOrderClassificationUuid(UPDATED_SALES_ORDER_CLASSIFICATION_UUID);
        SalesOrderClassificationDTO salesOrderClassificationDTO = salesOrderClassificationMapper.toDto(updatedSalesOrderClassification);

        restSalesOrderClassificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, salesOrderClassificationDTO.getOrderClassificationId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesOrderClassificationDTO))
            )
            .andExpect(status().isOk());

        // Validate the SalesOrderClassification in the database
        List<SalesOrderClassification> salesOrderClassificationList = salesOrderClassificationRepository.findAll();
        assertThat(salesOrderClassificationList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderClassification testSalesOrderClassification = salesOrderClassificationList.get(salesOrderClassificationList.size() - 1);
        assertThat(testSalesOrderClassification.getOrderClassificationDescription()).isEqualTo(UPDATED_ORDER_CLASSIFICATION_DESCRIPTION);
        assertThat(testSalesOrderClassification.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSalesOrderClassification.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSalesOrderClassification.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSalesOrderClassification.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSalesOrderClassification.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSalesOrderClassification.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSalesOrderClassification.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSalesOrderClassification.getSalesOrderClassificationUuid()).isEqualTo(UPDATED_SALES_ORDER_CLASSIFICATION_UUID);
    }

    @Test
    @Transactional
    void putNonExistingSalesOrderClassification() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClassificationRepository.findAll().size();
        salesOrderClassification.setOrderClassificationId(count.incrementAndGet());

        // Create the SalesOrderClassification
        SalesOrderClassificationDTO salesOrderClassificationDTO = salesOrderClassificationMapper.toDto(salesOrderClassification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesOrderClassificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, salesOrderClassificationDTO.getOrderClassificationId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesOrderClassificationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesOrderClassification in the database
        List<SalesOrderClassification> salesOrderClassificationList = salesOrderClassificationRepository.findAll();
        assertThat(salesOrderClassificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSalesOrderClassification() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClassificationRepository.findAll().size();
        salesOrderClassification.setOrderClassificationId(count.incrementAndGet());

        // Create the SalesOrderClassification
        SalesOrderClassificationDTO salesOrderClassificationDTO = salesOrderClassificationMapper.toDto(salesOrderClassification);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesOrderClassificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesOrderClassificationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesOrderClassification in the database
        List<SalesOrderClassification> salesOrderClassificationList = salesOrderClassificationRepository.findAll();
        assertThat(salesOrderClassificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSalesOrderClassification() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClassificationRepository.findAll().size();
        salesOrderClassification.setOrderClassificationId(count.incrementAndGet());

        // Create the SalesOrderClassification
        SalesOrderClassificationDTO salesOrderClassificationDTO = salesOrderClassificationMapper.toDto(salesOrderClassification);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesOrderClassificationMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesOrderClassificationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SalesOrderClassification in the database
        List<SalesOrderClassification> salesOrderClassificationList = salesOrderClassificationRepository.findAll();
        assertThat(salesOrderClassificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSalesOrderClassificationWithPatch() throws Exception {
        // Initialize the database
        salesOrderClassificationRepository.saveAndFlush(salesOrderClassification);

        int databaseSizeBeforeUpdate = salesOrderClassificationRepository.findAll().size();

        // Update the salesOrderClassification using partial update
        SalesOrderClassification partialUpdatedSalesOrderClassification = new SalesOrderClassification();
        partialUpdatedSalesOrderClassification.setOrderClassificationId(salesOrderClassification.getOrderClassificationId());

        partialUpdatedSalesOrderClassification
            .orderClassificationDescription(UPDATED_ORDER_CLASSIFICATION_DESCRIPTION)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .salesOrderClassificationUuid(UPDATED_SALES_ORDER_CLASSIFICATION_UUID);

        restSalesOrderClassificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalesOrderClassification.getOrderClassificationId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderClassification))
            )
            .andExpect(status().isOk());

        // Validate the SalesOrderClassification in the database
        List<SalesOrderClassification> salesOrderClassificationList = salesOrderClassificationRepository.findAll();
        assertThat(salesOrderClassificationList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderClassification testSalesOrderClassification = salesOrderClassificationList.get(salesOrderClassificationList.size() - 1);
        assertThat(testSalesOrderClassification.getOrderClassificationDescription()).isEqualTo(UPDATED_ORDER_CLASSIFICATION_DESCRIPTION);
        assertThat(testSalesOrderClassification.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSalesOrderClassification.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSalesOrderClassification.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSalesOrderClassification.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSalesOrderClassification.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSalesOrderClassification.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSalesOrderClassification.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSalesOrderClassification.getSalesOrderClassificationUuid()).isEqualTo(UPDATED_SALES_ORDER_CLASSIFICATION_UUID);
    }

    @Test
    @Transactional
    void fullUpdateSalesOrderClassificationWithPatch() throws Exception {
        // Initialize the database
        salesOrderClassificationRepository.saveAndFlush(salesOrderClassification);

        int databaseSizeBeforeUpdate = salesOrderClassificationRepository.findAll().size();

        // Update the salesOrderClassification using partial update
        SalesOrderClassification partialUpdatedSalesOrderClassification = new SalesOrderClassification();
        partialUpdatedSalesOrderClassification.setOrderClassificationId(salesOrderClassification.getOrderClassificationId());

        partialUpdatedSalesOrderClassification
            .orderClassificationDescription(UPDATED_ORDER_CLASSIFICATION_DESCRIPTION)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .salesOrderClassificationUuid(UPDATED_SALES_ORDER_CLASSIFICATION_UUID);

        restSalesOrderClassificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalesOrderClassification.getOrderClassificationId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderClassification))
            )
            .andExpect(status().isOk());

        // Validate the SalesOrderClassification in the database
        List<SalesOrderClassification> salesOrderClassificationList = salesOrderClassificationRepository.findAll();
        assertThat(salesOrderClassificationList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderClassification testSalesOrderClassification = salesOrderClassificationList.get(salesOrderClassificationList.size() - 1);
        assertThat(testSalesOrderClassification.getOrderClassificationDescription()).isEqualTo(UPDATED_ORDER_CLASSIFICATION_DESCRIPTION);
        assertThat(testSalesOrderClassification.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSalesOrderClassification.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSalesOrderClassification.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSalesOrderClassification.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSalesOrderClassification.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSalesOrderClassification.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSalesOrderClassification.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSalesOrderClassification.getSalesOrderClassificationUuid()).isEqualTo(UPDATED_SALES_ORDER_CLASSIFICATION_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingSalesOrderClassification() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClassificationRepository.findAll().size();
        salesOrderClassification.setOrderClassificationId(count.incrementAndGet());

        // Create the SalesOrderClassification
        SalesOrderClassificationDTO salesOrderClassificationDTO = salesOrderClassificationMapper.toDto(salesOrderClassification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesOrderClassificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, salesOrderClassificationDTO.getOrderClassificationId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salesOrderClassificationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesOrderClassification in the database
        List<SalesOrderClassification> salesOrderClassificationList = salesOrderClassificationRepository.findAll();
        assertThat(salesOrderClassificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSalesOrderClassification() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClassificationRepository.findAll().size();
        salesOrderClassification.setOrderClassificationId(count.incrementAndGet());

        // Create the SalesOrderClassification
        SalesOrderClassificationDTO salesOrderClassificationDTO = salesOrderClassificationMapper.toDto(salesOrderClassification);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesOrderClassificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salesOrderClassificationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesOrderClassification in the database
        List<SalesOrderClassification> salesOrderClassificationList = salesOrderClassificationRepository.findAll();
        assertThat(salesOrderClassificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSalesOrderClassification() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderClassificationRepository.findAll().size();
        salesOrderClassification.setOrderClassificationId(count.incrementAndGet());

        // Create the SalesOrderClassification
        SalesOrderClassificationDTO salesOrderClassificationDTO = salesOrderClassificationMapper.toDto(salesOrderClassification);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesOrderClassificationMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salesOrderClassificationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SalesOrderClassification in the database
        List<SalesOrderClassification> salesOrderClassificationList = salesOrderClassificationRepository.findAll();
        assertThat(salesOrderClassificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSalesOrderClassification() throws Exception {
        // Initialize the database
        salesOrderClassificationRepository.saveAndFlush(salesOrderClassification);

        int databaseSizeBeforeDelete = salesOrderClassificationRepository.findAll().size();

        // Delete the salesOrderClassification
        restSalesOrderClassificationMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, salesOrderClassification.getOrderClassificationId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SalesOrderClassification> salesOrderClassificationList = salesOrderClassificationRepository.findAll();
        assertThat(salesOrderClassificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
