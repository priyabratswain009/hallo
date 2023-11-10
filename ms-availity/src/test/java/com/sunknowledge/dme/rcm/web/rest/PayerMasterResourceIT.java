package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PayerMaster;
import com.sunknowledge.dme.rcm.repository.PayerMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.PayerMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PayerMasterMapper;
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
 * Integration tests for the {@link PayerMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PayerMasterResourceIT {

    private static final String DEFAULT_PAYER_ID = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_PAYER_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PAYER_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/payer-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{payerMasterId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PayerMasterRepository payerMasterRepository;

    @Autowired
    private PayerMasterMapper payerMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPayerMasterMockMvc;

    private PayerMaster payerMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PayerMaster createEntity(EntityManager em) {
        PayerMaster payerMaster = new PayerMaster()
            .payerId(DEFAULT_PAYER_ID)
            .payerName(DEFAULT_PAYER_NAME)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .payerMasterUuid(DEFAULT_PAYER_MASTER_UUID);
        return payerMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PayerMaster createUpdatedEntity(EntityManager em) {
        PayerMaster payerMaster = new PayerMaster()
            .payerId(UPDATED_PAYER_ID)
            .payerName(UPDATED_PAYER_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .payerMasterUuid(UPDATED_PAYER_MASTER_UUID);
        return payerMaster;
    }

    @BeforeEach
    public void initTest() {
        payerMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createPayerMaster() throws Exception {
        int databaseSizeBeforeCreate = payerMasterRepository.findAll().size();
        // Create the PayerMaster
        PayerMasterDTO payerMasterDTO = payerMasterMapper.toDto(payerMaster);
        restPayerMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payerMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeCreate + 1);
        PayerMaster testPayerMaster = payerMasterList.get(payerMasterList.size() - 1);
        assertThat(testPayerMaster.getPayerId()).isEqualTo(DEFAULT_PAYER_ID);
        assertThat(testPayerMaster.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testPayerMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPayerMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPayerMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPayerMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPayerMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPayerMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPayerMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPayerMaster.getPayerMasterUuid()).isEqualTo(DEFAULT_PAYER_MASTER_UUID);
    }

    @Test
    @Transactional
    void createPayerMasterWithExistingId() throws Exception {
        // Create the PayerMaster with an existing ID
        payerMaster.setPayerMasterId(1L);
        PayerMasterDTO payerMasterDTO = payerMasterMapper.toDto(payerMaster);

        int databaseSizeBeforeCreate = payerMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPayerMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payerMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPayerMasters() throws Exception {
        // Initialize the database
        payerMasterRepository.saveAndFlush(payerMaster);

        // Get all the payerMasterList
        restPayerMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=payerMasterId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].payerMasterId").value(hasItem(payerMaster.getPayerMasterId().intValue())))
            .andExpect(jsonPath("$.[*].payerId").value(hasItem(DEFAULT_PAYER_ID)))
            .andExpect(jsonPath("$.[*].payerName").value(hasItem(DEFAULT_PAYER_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].payerMasterUuid").value(hasItem(DEFAULT_PAYER_MASTER_UUID.toString())));
    }

    @Test
    @Transactional
    void getPayerMaster() throws Exception {
        // Initialize the database
        payerMasterRepository.saveAndFlush(payerMaster);

        // Get the payerMaster
        restPayerMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, payerMaster.getPayerMasterId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.payerMasterId").value(payerMaster.getPayerMasterId().intValue()))
            .andExpect(jsonPath("$.payerId").value(DEFAULT_PAYER_ID))
            .andExpect(jsonPath("$.payerName").value(DEFAULT_PAYER_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.payerMasterUuid").value(DEFAULT_PAYER_MASTER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPayerMaster() throws Exception {
        // Get the payerMaster
        restPayerMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPayerMaster() throws Exception {
        // Initialize the database
        payerMasterRepository.saveAndFlush(payerMaster);

        int databaseSizeBeforeUpdate = payerMasterRepository.findAll().size();

        // Update the payerMaster
        PayerMaster updatedPayerMaster = payerMasterRepository.findById(payerMaster.getPayerMasterId()).get();
        // Disconnect from session so that the updates on updatedPayerMaster are not directly saved in db
        em.detach(updatedPayerMaster);
        updatedPayerMaster
            .payerId(UPDATED_PAYER_ID)
            .payerName(UPDATED_PAYER_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .payerMasterUuid(UPDATED_PAYER_MASTER_UUID);
        PayerMasterDTO payerMasterDTO = payerMasterMapper.toDto(updatedPayerMaster);

        restPayerMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, payerMasterDTO.getPayerMasterId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payerMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeUpdate);
        PayerMaster testPayerMaster = payerMasterList.get(payerMasterList.size() - 1);
        assertThat(testPayerMaster.getPayerId()).isEqualTo(UPDATED_PAYER_ID);
        assertThat(testPayerMaster.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testPayerMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPayerMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPayerMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPayerMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPayerMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPayerMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPayerMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPayerMaster.getPayerMasterUuid()).isEqualTo(UPDATED_PAYER_MASTER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingPayerMaster() throws Exception {
        int databaseSizeBeforeUpdate = payerMasterRepository.findAll().size();
        payerMaster.setPayerMasterId(count.incrementAndGet());

        // Create the PayerMaster
        PayerMasterDTO payerMasterDTO = payerMasterMapper.toDto(payerMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPayerMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, payerMasterDTO.getPayerMasterId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payerMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPayerMaster() throws Exception {
        int databaseSizeBeforeUpdate = payerMasterRepository.findAll().size();
        payerMaster.setPayerMasterId(count.incrementAndGet());

        // Create the PayerMaster
        PayerMasterDTO payerMasterDTO = payerMasterMapper.toDto(payerMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayerMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payerMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPayerMaster() throws Exception {
        int databaseSizeBeforeUpdate = payerMasterRepository.findAll().size();
        payerMaster.setPayerMasterId(count.incrementAndGet());

        // Create the PayerMaster
        PayerMasterDTO payerMasterDTO = payerMasterMapper.toDto(payerMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayerMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payerMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePayerMasterWithPatch() throws Exception {
        // Initialize the database
        payerMasterRepository.saveAndFlush(payerMaster);

        int databaseSizeBeforeUpdate = payerMasterRepository.findAll().size();

        // Update the payerMaster using partial update
        PayerMaster partialUpdatedPayerMaster = new PayerMaster();
        partialUpdatedPayerMaster.setPayerMasterId(payerMaster.getPayerMasterId());

        partialUpdatedPayerMaster
            .payerId(UPDATED_PAYER_ID)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .payerMasterUuid(UPDATED_PAYER_MASTER_UUID);

        restPayerMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPayerMaster.getPayerMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPayerMaster))
            )
            .andExpect(status().isOk());

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeUpdate);
        PayerMaster testPayerMaster = payerMasterList.get(payerMasterList.size() - 1);
        assertThat(testPayerMaster.getPayerId()).isEqualTo(UPDATED_PAYER_ID);
        assertThat(testPayerMaster.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testPayerMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPayerMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPayerMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPayerMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPayerMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPayerMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPayerMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPayerMaster.getPayerMasterUuid()).isEqualTo(UPDATED_PAYER_MASTER_UUID);
    }

    @Test
    @Transactional
    void fullUpdatePayerMasterWithPatch() throws Exception {
        // Initialize the database
        payerMasterRepository.saveAndFlush(payerMaster);

        int databaseSizeBeforeUpdate = payerMasterRepository.findAll().size();

        // Update the payerMaster using partial update
        PayerMaster partialUpdatedPayerMaster = new PayerMaster();
        partialUpdatedPayerMaster.setPayerMasterId(payerMaster.getPayerMasterId());

        partialUpdatedPayerMaster
            .payerId(UPDATED_PAYER_ID)
            .payerName(UPDATED_PAYER_NAME)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .payerMasterUuid(UPDATED_PAYER_MASTER_UUID);

        restPayerMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPayerMaster.getPayerMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPayerMaster))
            )
            .andExpect(status().isOk());

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeUpdate);
        PayerMaster testPayerMaster = payerMasterList.get(payerMasterList.size() - 1);
        assertThat(testPayerMaster.getPayerId()).isEqualTo(UPDATED_PAYER_ID);
        assertThat(testPayerMaster.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testPayerMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPayerMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPayerMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPayerMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPayerMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPayerMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPayerMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPayerMaster.getPayerMasterUuid()).isEqualTo(UPDATED_PAYER_MASTER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingPayerMaster() throws Exception {
        int databaseSizeBeforeUpdate = payerMasterRepository.findAll().size();
        payerMaster.setPayerMasterId(count.incrementAndGet());

        // Create the PayerMaster
        PayerMasterDTO payerMasterDTO = payerMasterMapper.toDto(payerMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPayerMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, payerMasterDTO.getPayerMasterId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(payerMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPayerMaster() throws Exception {
        int databaseSizeBeforeUpdate = payerMasterRepository.findAll().size();
        payerMaster.setPayerMasterId(count.incrementAndGet());

        // Create the PayerMaster
        PayerMasterDTO payerMasterDTO = payerMasterMapper.toDto(payerMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayerMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(payerMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPayerMaster() throws Exception {
        int databaseSizeBeforeUpdate = payerMasterRepository.findAll().size();
        payerMaster.setPayerMasterId(count.incrementAndGet());

        // Create the PayerMaster
        PayerMasterDTO payerMasterDTO = payerMasterMapper.toDto(payerMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayerMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(payerMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PayerMaster in the database
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePayerMaster() throws Exception {
        // Initialize the database
        payerMasterRepository.saveAndFlush(payerMaster);

        int databaseSizeBeforeDelete = payerMasterRepository.findAll().size();

        // Delete the payerMaster
        restPayerMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, payerMaster.getPayerMasterId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PayerMaster> payerMasterList = payerMasterRepository.findAll();
        assertThat(payerMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
