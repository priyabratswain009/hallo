package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ClaimsReportFileProcessStatus;
import com.sunknowledge.dme.rcm.repository.ClaimsReportFileProcessStatusRepository;
import com.sunknowledge.dme.rcm.service.dto.ClaimsReportFileProcessStatusDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimsReportFileProcessStatusMapper;
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
 * Integration tests for the {@link ClaimsReportFileProcessStatusResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClaimsReportFileProcessStatusResourceIT {

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RUN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RUN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PROCESS_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PROCESS_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PROCESS_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PROCESS_STATUS = "BBBBBBBBBB";

    private static final UUID DEFAULT_CLAIMS_REPORT_FILE_PROCESS_STATUS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_CLAIMS_REPORT_FILE_PROCESS_STATUS_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/claims-report-file-process-statuses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{fileStatusId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClaimsReportFileProcessStatusRepository claimsReportFileProcessStatusRepository;

    @Autowired
    private ClaimsReportFileProcessStatusMapper claimsReportFileProcessStatusMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClaimsReportFileProcessStatusMockMvc;

    private ClaimsReportFileProcessStatus claimsReportFileProcessStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimsReportFileProcessStatus createEntity(EntityManager em) {
        ClaimsReportFileProcessStatus claimsReportFileProcessStatus = new ClaimsReportFileProcessStatus()
            .fileName(DEFAULT_FILE_NAME)
            .runDate(DEFAULT_RUN_DATE)
            .processDate(DEFAULT_PROCESS_DATE)
            .processStatus(DEFAULT_PROCESS_STATUS)
            .claimsReportFileProcessStatusUuid(DEFAULT_CLAIMS_REPORT_FILE_PROCESS_STATUS_UUID);
        return claimsReportFileProcessStatus;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimsReportFileProcessStatus createUpdatedEntity(EntityManager em) {
        ClaimsReportFileProcessStatus claimsReportFileProcessStatus = new ClaimsReportFileProcessStatus()
            .fileName(UPDATED_FILE_NAME)
            .runDate(UPDATED_RUN_DATE)
            .processDate(UPDATED_PROCESS_DATE)
            .processStatus(UPDATED_PROCESS_STATUS)
            .claimsReportFileProcessStatusUuid(UPDATED_CLAIMS_REPORT_FILE_PROCESS_STATUS_UUID);
        return claimsReportFileProcessStatus;
    }

    @BeforeEach
    public void initTest() {
        claimsReportFileProcessStatus = createEntity(em);
    }

    @Test
    @Transactional
    void createClaimsReportFileProcessStatus() throws Exception {
        int databaseSizeBeforeCreate = claimsReportFileProcessStatusRepository.findAll().size();
        // Create the ClaimsReportFileProcessStatus
        ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO = claimsReportFileProcessStatusMapper.toDto(
            claimsReportFileProcessStatus
        );
        restClaimsReportFileProcessStatusMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsReportFileProcessStatusDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ClaimsReportFileProcessStatus in the database
        List<ClaimsReportFileProcessStatus> claimsReportFileProcessStatusList = claimsReportFileProcessStatusRepository.findAll();
        assertThat(claimsReportFileProcessStatusList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimsReportFileProcessStatus testClaimsReportFileProcessStatus = claimsReportFileProcessStatusList.get(
            claimsReportFileProcessStatusList.size() - 1
        );
        assertThat(testClaimsReportFileProcessStatus.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testClaimsReportFileProcessStatus.getRunDate()).isEqualTo(DEFAULT_RUN_DATE);
        assertThat(testClaimsReportFileProcessStatus.getProcessDate()).isEqualTo(DEFAULT_PROCESS_DATE);
        assertThat(testClaimsReportFileProcessStatus.getProcessStatus()).isEqualTo(DEFAULT_PROCESS_STATUS);
        assertThat(testClaimsReportFileProcessStatus.getClaimsReportFileProcessStatusUuid())
            .isEqualTo(DEFAULT_CLAIMS_REPORT_FILE_PROCESS_STATUS_UUID);
    }

    @Test
    @Transactional
    void createClaimsReportFileProcessStatusWithExistingId() throws Exception {
        // Create the ClaimsReportFileProcessStatus with an existing ID
        claimsReportFileProcessStatus.setFileStatusId(1L);
        ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO = claimsReportFileProcessStatusMapper.toDto(
            claimsReportFileProcessStatus
        );

        int databaseSizeBeforeCreate = claimsReportFileProcessStatusRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimsReportFileProcessStatusMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsReportFileProcessStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsReportFileProcessStatus in the database
        List<ClaimsReportFileProcessStatus> claimsReportFileProcessStatusList = claimsReportFileProcessStatusRepository.findAll();
        assertThat(claimsReportFileProcessStatusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClaimsReportFileProcessStatuses() throws Exception {
        // Initialize the database
        claimsReportFileProcessStatusRepository.saveAndFlush(claimsReportFileProcessStatus);

        // Get all the claimsReportFileProcessStatusList
        restClaimsReportFileProcessStatusMockMvc
            .perform(get(ENTITY_API_URL + "?sort=fileStatusId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].fileStatusId").value(hasItem(claimsReportFileProcessStatus.getFileStatusId().intValue())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].runDate").value(hasItem(DEFAULT_RUN_DATE.toString())))
            .andExpect(jsonPath("$.[*].processDate").value(hasItem(DEFAULT_PROCESS_DATE.toString())))
            .andExpect(jsonPath("$.[*].processStatus").value(hasItem(DEFAULT_PROCESS_STATUS)))
            .andExpect(
                jsonPath("$.[*].claimsReportFileProcessStatusUuid")
                    .value(hasItem(DEFAULT_CLAIMS_REPORT_FILE_PROCESS_STATUS_UUID.toString()))
            );
    }

    @Test
    @Transactional
    void getClaimsReportFileProcessStatus() throws Exception {
        // Initialize the database
        claimsReportFileProcessStatusRepository.saveAndFlush(claimsReportFileProcessStatus);

        // Get the claimsReportFileProcessStatus
        restClaimsReportFileProcessStatusMockMvc
            .perform(get(ENTITY_API_URL_ID, claimsReportFileProcessStatus.getFileStatusId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.fileStatusId").value(claimsReportFileProcessStatus.getFileStatusId().intValue()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME))
            .andExpect(jsonPath("$.runDate").value(DEFAULT_RUN_DATE.toString()))
            .andExpect(jsonPath("$.processDate").value(DEFAULT_PROCESS_DATE.toString()))
            .andExpect(jsonPath("$.processStatus").value(DEFAULT_PROCESS_STATUS))
            .andExpect(jsonPath("$.claimsReportFileProcessStatusUuid").value(DEFAULT_CLAIMS_REPORT_FILE_PROCESS_STATUS_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingClaimsReportFileProcessStatus() throws Exception {
        // Get the claimsReportFileProcessStatus
        restClaimsReportFileProcessStatusMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewClaimsReportFileProcessStatus() throws Exception {
        // Initialize the database
        claimsReportFileProcessStatusRepository.saveAndFlush(claimsReportFileProcessStatus);

        int databaseSizeBeforeUpdate = claimsReportFileProcessStatusRepository.findAll().size();

        // Update the claimsReportFileProcessStatus
        ClaimsReportFileProcessStatus updatedClaimsReportFileProcessStatus = claimsReportFileProcessStatusRepository
            .findById(claimsReportFileProcessStatus.getFileStatusId())
            .get();
        // Disconnect from session so that the updates on updatedClaimsReportFileProcessStatus are not directly saved in db
        em.detach(updatedClaimsReportFileProcessStatus);
        updatedClaimsReportFileProcessStatus
            .fileName(UPDATED_FILE_NAME)
            .runDate(UPDATED_RUN_DATE)
            .processDate(UPDATED_PROCESS_DATE)
            .processStatus(UPDATED_PROCESS_STATUS)
            .claimsReportFileProcessStatusUuid(UPDATED_CLAIMS_REPORT_FILE_PROCESS_STATUS_UUID);
        ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO = claimsReportFileProcessStatusMapper.toDto(
            updatedClaimsReportFileProcessStatus
        );

        restClaimsReportFileProcessStatusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimsReportFileProcessStatusDTO.getFileStatusId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsReportFileProcessStatusDTO))
            )
            .andExpect(status().isOk());

        // Validate the ClaimsReportFileProcessStatus in the database
        List<ClaimsReportFileProcessStatus> claimsReportFileProcessStatusList = claimsReportFileProcessStatusRepository.findAll();
        assertThat(claimsReportFileProcessStatusList).hasSize(databaseSizeBeforeUpdate);
        ClaimsReportFileProcessStatus testClaimsReportFileProcessStatus = claimsReportFileProcessStatusList.get(
            claimsReportFileProcessStatusList.size() - 1
        );
        assertThat(testClaimsReportFileProcessStatus.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testClaimsReportFileProcessStatus.getRunDate()).isEqualTo(UPDATED_RUN_DATE);
        assertThat(testClaimsReportFileProcessStatus.getProcessDate()).isEqualTo(UPDATED_PROCESS_DATE);
        assertThat(testClaimsReportFileProcessStatus.getProcessStatus()).isEqualTo(UPDATED_PROCESS_STATUS);
        assertThat(testClaimsReportFileProcessStatus.getClaimsReportFileProcessStatusUuid())
            .isEqualTo(UPDATED_CLAIMS_REPORT_FILE_PROCESS_STATUS_UUID);
    }

    @Test
    @Transactional
    void putNonExistingClaimsReportFileProcessStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimsReportFileProcessStatusRepository.findAll().size();
        claimsReportFileProcessStatus.setFileStatusId(count.incrementAndGet());

        // Create the ClaimsReportFileProcessStatus
        ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO = claimsReportFileProcessStatusMapper.toDto(
            claimsReportFileProcessStatus
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimsReportFileProcessStatusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimsReportFileProcessStatusDTO.getFileStatusId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsReportFileProcessStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsReportFileProcessStatus in the database
        List<ClaimsReportFileProcessStatus> claimsReportFileProcessStatusList = claimsReportFileProcessStatusRepository.findAll();
        assertThat(claimsReportFileProcessStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClaimsReportFileProcessStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimsReportFileProcessStatusRepository.findAll().size();
        claimsReportFileProcessStatus.setFileStatusId(count.incrementAndGet());

        // Create the ClaimsReportFileProcessStatus
        ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO = claimsReportFileProcessStatusMapper.toDto(
            claimsReportFileProcessStatus
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsReportFileProcessStatusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsReportFileProcessStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsReportFileProcessStatus in the database
        List<ClaimsReportFileProcessStatus> claimsReportFileProcessStatusList = claimsReportFileProcessStatusRepository.findAll();
        assertThat(claimsReportFileProcessStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClaimsReportFileProcessStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimsReportFileProcessStatusRepository.findAll().size();
        claimsReportFileProcessStatus.setFileStatusId(count.incrementAndGet());

        // Create the ClaimsReportFileProcessStatus
        ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO = claimsReportFileProcessStatusMapper.toDto(
            claimsReportFileProcessStatus
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsReportFileProcessStatusMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsReportFileProcessStatusDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimsReportFileProcessStatus in the database
        List<ClaimsReportFileProcessStatus> claimsReportFileProcessStatusList = claimsReportFileProcessStatusRepository.findAll();
        assertThat(claimsReportFileProcessStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClaimsReportFileProcessStatusWithPatch() throws Exception {
        // Initialize the database
        claimsReportFileProcessStatusRepository.saveAndFlush(claimsReportFileProcessStatus);

        int databaseSizeBeforeUpdate = claimsReportFileProcessStatusRepository.findAll().size();

        // Update the claimsReportFileProcessStatus using partial update
        ClaimsReportFileProcessStatus partialUpdatedClaimsReportFileProcessStatus = new ClaimsReportFileProcessStatus();
        partialUpdatedClaimsReportFileProcessStatus.setFileStatusId(claimsReportFileProcessStatus.getFileStatusId());

        partialUpdatedClaimsReportFileProcessStatus
            .runDate(UPDATED_RUN_DATE)
            .processStatus(UPDATED_PROCESS_STATUS)
            .claimsReportFileProcessStatusUuid(UPDATED_CLAIMS_REPORT_FILE_PROCESS_STATUS_UUID);

        restClaimsReportFileProcessStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimsReportFileProcessStatus.getFileStatusId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimsReportFileProcessStatus))
            )
            .andExpect(status().isOk());

        // Validate the ClaimsReportFileProcessStatus in the database
        List<ClaimsReportFileProcessStatus> claimsReportFileProcessStatusList = claimsReportFileProcessStatusRepository.findAll();
        assertThat(claimsReportFileProcessStatusList).hasSize(databaseSizeBeforeUpdate);
        ClaimsReportFileProcessStatus testClaimsReportFileProcessStatus = claimsReportFileProcessStatusList.get(
            claimsReportFileProcessStatusList.size() - 1
        );
        assertThat(testClaimsReportFileProcessStatus.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testClaimsReportFileProcessStatus.getRunDate()).isEqualTo(UPDATED_RUN_DATE);
        assertThat(testClaimsReportFileProcessStatus.getProcessDate()).isEqualTo(DEFAULT_PROCESS_DATE);
        assertThat(testClaimsReportFileProcessStatus.getProcessStatus()).isEqualTo(UPDATED_PROCESS_STATUS);
        assertThat(testClaimsReportFileProcessStatus.getClaimsReportFileProcessStatusUuid())
            .isEqualTo(UPDATED_CLAIMS_REPORT_FILE_PROCESS_STATUS_UUID);
    }

    @Test
    @Transactional
    void fullUpdateClaimsReportFileProcessStatusWithPatch() throws Exception {
        // Initialize the database
        claimsReportFileProcessStatusRepository.saveAndFlush(claimsReportFileProcessStatus);

        int databaseSizeBeforeUpdate = claimsReportFileProcessStatusRepository.findAll().size();

        // Update the claimsReportFileProcessStatus using partial update
        ClaimsReportFileProcessStatus partialUpdatedClaimsReportFileProcessStatus = new ClaimsReportFileProcessStatus();
        partialUpdatedClaimsReportFileProcessStatus.setFileStatusId(claimsReportFileProcessStatus.getFileStatusId());

        partialUpdatedClaimsReportFileProcessStatus
            .fileName(UPDATED_FILE_NAME)
            .runDate(UPDATED_RUN_DATE)
            .processDate(UPDATED_PROCESS_DATE)
            .processStatus(UPDATED_PROCESS_STATUS)
            .claimsReportFileProcessStatusUuid(UPDATED_CLAIMS_REPORT_FILE_PROCESS_STATUS_UUID);

        restClaimsReportFileProcessStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimsReportFileProcessStatus.getFileStatusId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimsReportFileProcessStatus))
            )
            .andExpect(status().isOk());

        // Validate the ClaimsReportFileProcessStatus in the database
        List<ClaimsReportFileProcessStatus> claimsReportFileProcessStatusList = claimsReportFileProcessStatusRepository.findAll();
        assertThat(claimsReportFileProcessStatusList).hasSize(databaseSizeBeforeUpdate);
        ClaimsReportFileProcessStatus testClaimsReportFileProcessStatus = claimsReportFileProcessStatusList.get(
            claimsReportFileProcessStatusList.size() - 1
        );
        assertThat(testClaimsReportFileProcessStatus.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testClaimsReportFileProcessStatus.getRunDate()).isEqualTo(UPDATED_RUN_DATE);
        assertThat(testClaimsReportFileProcessStatus.getProcessDate()).isEqualTo(UPDATED_PROCESS_DATE);
        assertThat(testClaimsReportFileProcessStatus.getProcessStatus()).isEqualTo(UPDATED_PROCESS_STATUS);
        assertThat(testClaimsReportFileProcessStatus.getClaimsReportFileProcessStatusUuid())
            .isEqualTo(UPDATED_CLAIMS_REPORT_FILE_PROCESS_STATUS_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingClaimsReportFileProcessStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimsReportFileProcessStatusRepository.findAll().size();
        claimsReportFileProcessStatus.setFileStatusId(count.incrementAndGet());

        // Create the ClaimsReportFileProcessStatus
        ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO = claimsReportFileProcessStatusMapper.toDto(
            claimsReportFileProcessStatus
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimsReportFileProcessStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, claimsReportFileProcessStatusDTO.getFileStatusId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimsReportFileProcessStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsReportFileProcessStatus in the database
        List<ClaimsReportFileProcessStatus> claimsReportFileProcessStatusList = claimsReportFileProcessStatusRepository.findAll();
        assertThat(claimsReportFileProcessStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClaimsReportFileProcessStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimsReportFileProcessStatusRepository.findAll().size();
        claimsReportFileProcessStatus.setFileStatusId(count.incrementAndGet());

        // Create the ClaimsReportFileProcessStatus
        ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO = claimsReportFileProcessStatusMapper.toDto(
            claimsReportFileProcessStatus
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsReportFileProcessStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimsReportFileProcessStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsReportFileProcessStatus in the database
        List<ClaimsReportFileProcessStatus> claimsReportFileProcessStatusList = claimsReportFileProcessStatusRepository.findAll();
        assertThat(claimsReportFileProcessStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClaimsReportFileProcessStatus() throws Exception {
        int databaseSizeBeforeUpdate = claimsReportFileProcessStatusRepository.findAll().size();
        claimsReportFileProcessStatus.setFileStatusId(count.incrementAndGet());

        // Create the ClaimsReportFileProcessStatus
        ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO = claimsReportFileProcessStatusMapper.toDto(
            claimsReportFileProcessStatus
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsReportFileProcessStatusMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimsReportFileProcessStatusDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimsReportFileProcessStatus in the database
        List<ClaimsReportFileProcessStatus> claimsReportFileProcessStatusList = claimsReportFileProcessStatusRepository.findAll();
        assertThat(claimsReportFileProcessStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClaimsReportFileProcessStatus() throws Exception {
        // Initialize the database
        claimsReportFileProcessStatusRepository.saveAndFlush(claimsReportFileProcessStatus);

        int databaseSizeBeforeDelete = claimsReportFileProcessStatusRepository.findAll().size();

        // Delete the claimsReportFileProcessStatus
        restClaimsReportFileProcessStatusMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, claimsReportFileProcessStatus.getFileStatusId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimsReportFileProcessStatus> claimsReportFileProcessStatusList = claimsReportFileProcessStatusRepository.findAll();
        assertThat(claimsReportFileProcessStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
