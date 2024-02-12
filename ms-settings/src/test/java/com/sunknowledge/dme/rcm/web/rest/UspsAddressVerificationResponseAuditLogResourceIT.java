package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.UspsAddressVerificationResponseAuditLog;
import com.sunknowledge.dme.rcm.repository.UspsAddressVerificationResponseAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.UspsAddressVerificationResponseAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.UspsAddressVerificationResponseAuditLogMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
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
 * Integration tests for the {@link UspsAddressVerificationResponseAuditLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UspsAddressVerificationResponseAuditLogResourceIT {

    private static final Long DEFAULT_USPS_ADESS_VEIFIATION_ID = 1L;
    private static final Long UPDATED_USPS_ADESS_VEIFIATION_ID = 2L;

    private static final String DEFAULT_OLD_ROW_DATA = "AAAAAAAAAA";
    private static final String UPDATED_OLD_ROW_DATA = "BBBBBBBBBB";

    private static final String DEFAULT_NEW_ROW_DATA = "AAAAAAAAAA";
    private static final String UPDATED_NEW_ROW_DATA = "BBBBBBBBBB";

    private static final String DEFAULT_DML_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DML_TYPE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DML_TIMESTAMP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DML_TIMESTAMP = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DML_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_DML_CREATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/usps-address-verification-response-audit-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UspsAddressVerificationResponseAuditLogRepository uspsAddressVerificationResponseAuditLogRepository;

    @Autowired
    private UspsAddressVerificationResponseAuditLogMapper uspsAddressVerificationResponseAuditLogMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUspsAddressVerificationResponseAuditLogMockMvc;

    private UspsAddressVerificationResponseAuditLog uspsAddressVerificationResponseAuditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UspsAddressVerificationResponseAuditLog createEntity(EntityManager em) {
        UspsAddressVerificationResponseAuditLog uspsAddressVerificationResponseAuditLog = new UspsAddressVerificationResponseAuditLog()
            .uspsAdessVeifiationId(DEFAULT_USPS_ADESS_VEIFIATION_ID)
            .oldRowData(DEFAULT_OLD_ROW_DATA)
            .newRowData(DEFAULT_NEW_ROW_DATA)
            .dmlType(DEFAULT_DML_TYPE)
            .dmlTimestamp(DEFAULT_DML_TIMESTAMP)
            .dmlCreatedBy(DEFAULT_DML_CREATED_BY);
        return uspsAddressVerificationResponseAuditLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UspsAddressVerificationResponseAuditLog createUpdatedEntity(EntityManager em) {
        UspsAddressVerificationResponseAuditLog uspsAddressVerificationResponseAuditLog = new UspsAddressVerificationResponseAuditLog()
            .uspsAdessVeifiationId(UPDATED_USPS_ADESS_VEIFIATION_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        return uspsAddressVerificationResponseAuditLog;
    }

    @BeforeEach
    public void initTest() {
        uspsAddressVerificationResponseAuditLog = createEntity(em);
    }

    @Test
    @Transactional
    void createUspsAddressVerificationResponseAuditLog() throws Exception {
        int databaseSizeBeforeCreate = uspsAddressVerificationResponseAuditLogRepository.findAll().size();
        // Create the UspsAddressVerificationResponseAuditLog
        UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO = uspsAddressVerificationResponseAuditLogMapper.toDto(
            uspsAddressVerificationResponseAuditLog
        );
        restUspsAddressVerificationResponseAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uspsAddressVerificationResponseAuditLogDTO))
            )
            .andExpect(status().isCreated());

        // Validate the UspsAddressVerificationResponseAuditLog in the database
        List<UspsAddressVerificationResponseAuditLog> uspsAddressVerificationResponseAuditLogList = uspsAddressVerificationResponseAuditLogRepository.findAll();
        assertThat(uspsAddressVerificationResponseAuditLogList).hasSize(databaseSizeBeforeCreate + 1);
        UspsAddressVerificationResponseAuditLog testUspsAddressVerificationResponseAuditLog = uspsAddressVerificationResponseAuditLogList.get(
            uspsAddressVerificationResponseAuditLogList.size() - 1
        );
        assertThat(testUspsAddressVerificationResponseAuditLog.getUspsAdessVeifiationId()).isEqualTo(DEFAULT_USPS_ADESS_VEIFIATION_ID);
        assertThat(testUspsAddressVerificationResponseAuditLog.getOldRowData()).isEqualTo(DEFAULT_OLD_ROW_DATA);
        assertThat(testUspsAddressVerificationResponseAuditLog.getNewRowData()).isEqualTo(DEFAULT_NEW_ROW_DATA);
        assertThat(testUspsAddressVerificationResponseAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testUspsAddressVerificationResponseAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testUspsAddressVerificationResponseAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void createUspsAddressVerificationResponseAuditLogWithExistingId() throws Exception {
        // Create the UspsAddressVerificationResponseAuditLog with an existing ID
        uspsAddressVerificationResponseAuditLog.setId(1L);
        UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO = uspsAddressVerificationResponseAuditLogMapper.toDto(
            uspsAddressVerificationResponseAuditLog
        );

        int databaseSizeBeforeCreate = uspsAddressVerificationResponseAuditLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUspsAddressVerificationResponseAuditLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uspsAddressVerificationResponseAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UspsAddressVerificationResponseAuditLog in the database
        List<UspsAddressVerificationResponseAuditLog> uspsAddressVerificationResponseAuditLogList = uspsAddressVerificationResponseAuditLogRepository.findAll();
        assertThat(uspsAddressVerificationResponseAuditLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUspsAddressVerificationResponseAuditLogs() throws Exception {
        // Initialize the database
        uspsAddressVerificationResponseAuditLogRepository.saveAndFlush(uspsAddressVerificationResponseAuditLog);

        // Get all the uspsAddressVerificationResponseAuditLogList
        restUspsAddressVerificationResponseAuditLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uspsAddressVerificationResponseAuditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].uspsAdessVeifiationId").value(hasItem(DEFAULT_USPS_ADESS_VEIFIATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].oldRowData").value(hasItem(DEFAULT_OLD_ROW_DATA)))
            .andExpect(jsonPath("$.[*].newRowData").value(hasItem(DEFAULT_NEW_ROW_DATA)))
            .andExpect(jsonPath("$.[*].dmlType").value(hasItem(DEFAULT_DML_TYPE)))
            .andExpect(jsonPath("$.[*].dmlTimestamp").value(hasItem(DEFAULT_DML_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].dmlCreatedBy").value(hasItem(DEFAULT_DML_CREATED_BY)));
    }

    @Test
    @Transactional
    void getUspsAddressVerificationResponseAuditLog() throws Exception {
        // Initialize the database
        uspsAddressVerificationResponseAuditLogRepository.saveAndFlush(uspsAddressVerificationResponseAuditLog);

        // Get the uspsAddressVerificationResponseAuditLog
        restUspsAddressVerificationResponseAuditLogMockMvc
            .perform(get(ENTITY_API_URL_ID, uspsAddressVerificationResponseAuditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(uspsAddressVerificationResponseAuditLog.getId().intValue()))
            .andExpect(jsonPath("$.uspsAdessVeifiationId").value(DEFAULT_USPS_ADESS_VEIFIATION_ID.intValue()))
            .andExpect(jsonPath("$.oldRowData").value(DEFAULT_OLD_ROW_DATA))
            .andExpect(jsonPath("$.newRowData").value(DEFAULT_NEW_ROW_DATA))
            .andExpect(jsonPath("$.dmlType").value(DEFAULT_DML_TYPE))
            .andExpect(jsonPath("$.dmlTimestamp").value(DEFAULT_DML_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.dmlCreatedBy").value(DEFAULT_DML_CREATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingUspsAddressVerificationResponseAuditLog() throws Exception {
        // Get the uspsAddressVerificationResponseAuditLog
        restUspsAddressVerificationResponseAuditLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingUspsAddressVerificationResponseAuditLog() throws Exception {
        // Initialize the database
        uspsAddressVerificationResponseAuditLogRepository.saveAndFlush(uspsAddressVerificationResponseAuditLog);

        int databaseSizeBeforeUpdate = uspsAddressVerificationResponseAuditLogRepository.findAll().size();

        // Update the uspsAddressVerificationResponseAuditLog
        UspsAddressVerificationResponseAuditLog updatedUspsAddressVerificationResponseAuditLog = uspsAddressVerificationResponseAuditLogRepository
            .findById(uspsAddressVerificationResponseAuditLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedUspsAddressVerificationResponseAuditLog are not directly saved in db
        em.detach(updatedUspsAddressVerificationResponseAuditLog);
        updatedUspsAddressVerificationResponseAuditLog
            .uspsAdessVeifiationId(UPDATED_USPS_ADESS_VEIFIATION_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);
        UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO = uspsAddressVerificationResponseAuditLogMapper.toDto(
            updatedUspsAddressVerificationResponseAuditLog
        );

        restUspsAddressVerificationResponseAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, uspsAddressVerificationResponseAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uspsAddressVerificationResponseAuditLogDTO))
            )
            .andExpect(status().isOk());

        // Validate the UspsAddressVerificationResponseAuditLog in the database
        List<UspsAddressVerificationResponseAuditLog> uspsAddressVerificationResponseAuditLogList = uspsAddressVerificationResponseAuditLogRepository.findAll();
        assertThat(uspsAddressVerificationResponseAuditLogList).hasSize(databaseSizeBeforeUpdate);
        UspsAddressVerificationResponseAuditLog testUspsAddressVerificationResponseAuditLog = uspsAddressVerificationResponseAuditLogList.get(
            uspsAddressVerificationResponseAuditLogList.size() - 1
        );
        assertThat(testUspsAddressVerificationResponseAuditLog.getUspsAdessVeifiationId()).isEqualTo(UPDATED_USPS_ADESS_VEIFIATION_ID);
        assertThat(testUspsAddressVerificationResponseAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testUspsAddressVerificationResponseAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testUspsAddressVerificationResponseAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testUspsAddressVerificationResponseAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testUspsAddressVerificationResponseAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingUspsAddressVerificationResponseAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = uspsAddressVerificationResponseAuditLogRepository.findAll().size();
        uspsAddressVerificationResponseAuditLog.setId(count.incrementAndGet());

        // Create the UspsAddressVerificationResponseAuditLog
        UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO = uspsAddressVerificationResponseAuditLogMapper.toDto(
            uspsAddressVerificationResponseAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUspsAddressVerificationResponseAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, uspsAddressVerificationResponseAuditLogDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uspsAddressVerificationResponseAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UspsAddressVerificationResponseAuditLog in the database
        List<UspsAddressVerificationResponseAuditLog> uspsAddressVerificationResponseAuditLogList = uspsAddressVerificationResponseAuditLogRepository.findAll();
        assertThat(uspsAddressVerificationResponseAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUspsAddressVerificationResponseAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = uspsAddressVerificationResponseAuditLogRepository.findAll().size();
        uspsAddressVerificationResponseAuditLog.setId(count.incrementAndGet());

        // Create the UspsAddressVerificationResponseAuditLog
        UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO = uspsAddressVerificationResponseAuditLogMapper.toDto(
            uspsAddressVerificationResponseAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUspsAddressVerificationResponseAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uspsAddressVerificationResponseAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UspsAddressVerificationResponseAuditLog in the database
        List<UspsAddressVerificationResponseAuditLog> uspsAddressVerificationResponseAuditLogList = uspsAddressVerificationResponseAuditLogRepository.findAll();
        assertThat(uspsAddressVerificationResponseAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUspsAddressVerificationResponseAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = uspsAddressVerificationResponseAuditLogRepository.findAll().size();
        uspsAddressVerificationResponseAuditLog.setId(count.incrementAndGet());

        // Create the UspsAddressVerificationResponseAuditLog
        UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO = uspsAddressVerificationResponseAuditLogMapper.toDto(
            uspsAddressVerificationResponseAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUspsAddressVerificationResponseAuditLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uspsAddressVerificationResponseAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UspsAddressVerificationResponseAuditLog in the database
        List<UspsAddressVerificationResponseAuditLog> uspsAddressVerificationResponseAuditLogList = uspsAddressVerificationResponseAuditLogRepository.findAll();
        assertThat(uspsAddressVerificationResponseAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUspsAddressVerificationResponseAuditLogWithPatch() throws Exception {
        // Initialize the database
        uspsAddressVerificationResponseAuditLogRepository.saveAndFlush(uspsAddressVerificationResponseAuditLog);

        int databaseSizeBeforeUpdate = uspsAddressVerificationResponseAuditLogRepository.findAll().size();

        // Update the uspsAddressVerificationResponseAuditLog using partial update
        UspsAddressVerificationResponseAuditLog partialUpdatedUspsAddressVerificationResponseAuditLog = new UspsAddressVerificationResponseAuditLog();
        partialUpdatedUspsAddressVerificationResponseAuditLog.setId(uspsAddressVerificationResponseAuditLog.getId());

        partialUpdatedUspsAddressVerificationResponseAuditLog.oldRowData(UPDATED_OLD_ROW_DATA).newRowData(UPDATED_NEW_ROW_DATA);

        restUspsAddressVerificationResponseAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUspsAddressVerificationResponseAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUspsAddressVerificationResponseAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the UspsAddressVerificationResponseAuditLog in the database
        List<UspsAddressVerificationResponseAuditLog> uspsAddressVerificationResponseAuditLogList = uspsAddressVerificationResponseAuditLogRepository.findAll();
        assertThat(uspsAddressVerificationResponseAuditLogList).hasSize(databaseSizeBeforeUpdate);
        UspsAddressVerificationResponseAuditLog testUspsAddressVerificationResponseAuditLog = uspsAddressVerificationResponseAuditLogList.get(
            uspsAddressVerificationResponseAuditLogList.size() - 1
        );
        assertThat(testUspsAddressVerificationResponseAuditLog.getUspsAdessVeifiationId()).isEqualTo(DEFAULT_USPS_ADESS_VEIFIATION_ID);
        assertThat(testUspsAddressVerificationResponseAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testUspsAddressVerificationResponseAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testUspsAddressVerificationResponseAuditLog.getDmlType()).isEqualTo(DEFAULT_DML_TYPE);
        assertThat(testUspsAddressVerificationResponseAuditLog.getDmlTimestamp()).isEqualTo(DEFAULT_DML_TIMESTAMP);
        assertThat(testUspsAddressVerificationResponseAuditLog.getDmlCreatedBy()).isEqualTo(DEFAULT_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateUspsAddressVerificationResponseAuditLogWithPatch() throws Exception {
        // Initialize the database
        uspsAddressVerificationResponseAuditLogRepository.saveAndFlush(uspsAddressVerificationResponseAuditLog);

        int databaseSizeBeforeUpdate = uspsAddressVerificationResponseAuditLogRepository.findAll().size();

        // Update the uspsAddressVerificationResponseAuditLog using partial update
        UspsAddressVerificationResponseAuditLog partialUpdatedUspsAddressVerificationResponseAuditLog = new UspsAddressVerificationResponseAuditLog();
        partialUpdatedUspsAddressVerificationResponseAuditLog.setId(uspsAddressVerificationResponseAuditLog.getId());

        partialUpdatedUspsAddressVerificationResponseAuditLog
            .uspsAdessVeifiationId(UPDATED_USPS_ADESS_VEIFIATION_ID)
            .oldRowData(UPDATED_OLD_ROW_DATA)
            .newRowData(UPDATED_NEW_ROW_DATA)
            .dmlType(UPDATED_DML_TYPE)
            .dmlTimestamp(UPDATED_DML_TIMESTAMP)
            .dmlCreatedBy(UPDATED_DML_CREATED_BY);

        restUspsAddressVerificationResponseAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUspsAddressVerificationResponseAuditLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUspsAddressVerificationResponseAuditLog))
            )
            .andExpect(status().isOk());

        // Validate the UspsAddressVerificationResponseAuditLog in the database
        List<UspsAddressVerificationResponseAuditLog> uspsAddressVerificationResponseAuditLogList = uspsAddressVerificationResponseAuditLogRepository.findAll();
        assertThat(uspsAddressVerificationResponseAuditLogList).hasSize(databaseSizeBeforeUpdate);
        UspsAddressVerificationResponseAuditLog testUspsAddressVerificationResponseAuditLog = uspsAddressVerificationResponseAuditLogList.get(
            uspsAddressVerificationResponseAuditLogList.size() - 1
        );
        assertThat(testUspsAddressVerificationResponseAuditLog.getUspsAdessVeifiationId()).isEqualTo(UPDATED_USPS_ADESS_VEIFIATION_ID);
        assertThat(testUspsAddressVerificationResponseAuditLog.getOldRowData()).isEqualTo(UPDATED_OLD_ROW_DATA);
        assertThat(testUspsAddressVerificationResponseAuditLog.getNewRowData()).isEqualTo(UPDATED_NEW_ROW_DATA);
        assertThat(testUspsAddressVerificationResponseAuditLog.getDmlType()).isEqualTo(UPDATED_DML_TYPE);
        assertThat(testUspsAddressVerificationResponseAuditLog.getDmlTimestamp()).isEqualTo(UPDATED_DML_TIMESTAMP);
        assertThat(testUspsAddressVerificationResponseAuditLog.getDmlCreatedBy()).isEqualTo(UPDATED_DML_CREATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingUspsAddressVerificationResponseAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = uspsAddressVerificationResponseAuditLogRepository.findAll().size();
        uspsAddressVerificationResponseAuditLog.setId(count.incrementAndGet());

        // Create the UspsAddressVerificationResponseAuditLog
        UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO = uspsAddressVerificationResponseAuditLogMapper.toDto(
            uspsAddressVerificationResponseAuditLog
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUspsAddressVerificationResponseAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, uspsAddressVerificationResponseAuditLogDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uspsAddressVerificationResponseAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UspsAddressVerificationResponseAuditLog in the database
        List<UspsAddressVerificationResponseAuditLog> uspsAddressVerificationResponseAuditLogList = uspsAddressVerificationResponseAuditLogRepository.findAll();
        assertThat(uspsAddressVerificationResponseAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUspsAddressVerificationResponseAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = uspsAddressVerificationResponseAuditLogRepository.findAll().size();
        uspsAddressVerificationResponseAuditLog.setId(count.incrementAndGet());

        // Create the UspsAddressVerificationResponseAuditLog
        UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO = uspsAddressVerificationResponseAuditLogMapper.toDto(
            uspsAddressVerificationResponseAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUspsAddressVerificationResponseAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uspsAddressVerificationResponseAuditLogDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UspsAddressVerificationResponseAuditLog in the database
        List<UspsAddressVerificationResponseAuditLog> uspsAddressVerificationResponseAuditLogList = uspsAddressVerificationResponseAuditLogRepository.findAll();
        assertThat(uspsAddressVerificationResponseAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUspsAddressVerificationResponseAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = uspsAddressVerificationResponseAuditLogRepository.findAll().size();
        uspsAddressVerificationResponseAuditLog.setId(count.incrementAndGet());

        // Create the UspsAddressVerificationResponseAuditLog
        UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO = uspsAddressVerificationResponseAuditLogMapper.toDto(
            uspsAddressVerificationResponseAuditLog
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUspsAddressVerificationResponseAuditLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uspsAddressVerificationResponseAuditLogDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UspsAddressVerificationResponseAuditLog in the database
        List<UspsAddressVerificationResponseAuditLog> uspsAddressVerificationResponseAuditLogList = uspsAddressVerificationResponseAuditLogRepository.findAll();
        assertThat(uspsAddressVerificationResponseAuditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUspsAddressVerificationResponseAuditLog() throws Exception {
        // Initialize the database
        uspsAddressVerificationResponseAuditLogRepository.saveAndFlush(uspsAddressVerificationResponseAuditLog);

        int databaseSizeBeforeDelete = uspsAddressVerificationResponseAuditLogRepository.findAll().size();

        // Delete the uspsAddressVerificationResponseAuditLog
        restUspsAddressVerificationResponseAuditLogMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, uspsAddressVerificationResponseAuditLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UspsAddressVerificationResponseAuditLog> uspsAddressVerificationResponseAuditLogList = uspsAddressVerificationResponseAuditLogRepository.findAll();
        assertThat(uspsAddressVerificationResponseAuditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
