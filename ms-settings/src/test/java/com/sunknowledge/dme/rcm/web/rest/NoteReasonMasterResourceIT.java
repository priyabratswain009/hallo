package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.NoteReasonMaster;
import com.sunknowledge.dme.rcm.repository.NoteReasonMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.NoteReasonMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.NoteReasonMasterMapper;
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
 * Integration tests for the {@link NoteReasonMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NoteReasonMasterResourceIT {

    private static final String DEFAULT_NOTE_REASON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NOTE_REASON_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_NOTE_REASON_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_NOTE_REASON_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/note-reason-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{noteReasonId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NoteReasonMasterRepository noteReasonMasterRepository;

    @Autowired
    private NoteReasonMasterMapper noteReasonMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNoteReasonMasterMockMvc;

    private NoteReasonMaster noteReasonMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoteReasonMaster createEntity(EntityManager em) {
        NoteReasonMaster noteReasonMaster = new NoteReasonMaster()
            .noteReasonName(DEFAULT_NOTE_REASON_NAME)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .status(DEFAULT_STATUS)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .noteReasonMasterUuid(DEFAULT_NOTE_REASON_MASTER_UUID);
        return noteReasonMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoteReasonMaster createUpdatedEntity(EntityManager em) {
        NoteReasonMaster noteReasonMaster = new NoteReasonMaster()
            .noteReasonName(UPDATED_NOTE_REASON_NAME)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .noteReasonMasterUuid(UPDATED_NOTE_REASON_MASTER_UUID);
        return noteReasonMaster;
    }

    @BeforeEach
    public void initTest() {
        noteReasonMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createNoteReasonMaster() throws Exception {
        int databaseSizeBeforeCreate = noteReasonMasterRepository.findAll().size();
        // Create the NoteReasonMaster
        NoteReasonMasterDTO noteReasonMasterDTO = noteReasonMasterMapper.toDto(noteReasonMaster);
        restNoteReasonMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteReasonMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the NoteReasonMaster in the database
        List<NoteReasonMaster> noteReasonMasterList = noteReasonMasterRepository.findAll();
        assertThat(noteReasonMasterList).hasSize(databaseSizeBeforeCreate + 1);
        NoteReasonMaster testNoteReasonMaster = noteReasonMasterList.get(noteReasonMasterList.size() - 1);
        assertThat(testNoteReasonMaster.getNoteReasonName()).isEqualTo(DEFAULT_NOTE_REASON_NAME);
        assertThat(testNoteReasonMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testNoteReasonMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testNoteReasonMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testNoteReasonMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testNoteReasonMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testNoteReasonMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testNoteReasonMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testNoteReasonMaster.getNoteReasonMasterUuid()).isEqualTo(DEFAULT_NOTE_REASON_MASTER_UUID);
    }

    @Test
    @Transactional
    void createNoteReasonMasterWithExistingId() throws Exception {
        // Create the NoteReasonMaster with an existing ID
        noteReasonMaster.setNoteReasonId(1L);
        NoteReasonMasterDTO noteReasonMasterDTO = noteReasonMasterMapper.toDto(noteReasonMaster);

        int databaseSizeBeforeCreate = noteReasonMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNoteReasonMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteReasonMaster in the database
        List<NoteReasonMaster> noteReasonMasterList = noteReasonMasterRepository.findAll();
        assertThat(noteReasonMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllNoteReasonMasters() throws Exception {
        // Initialize the database
        noteReasonMasterRepository.saveAndFlush(noteReasonMaster);

        // Get all the noteReasonMasterList
        restNoteReasonMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=noteReasonId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].noteReasonId").value(hasItem(noteReasonMaster.getNoteReasonId().intValue())))
            .andExpect(jsonPath("$.[*].noteReasonName").value(hasItem(DEFAULT_NOTE_REASON_NAME)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].noteReasonMasterUuid").value(hasItem(DEFAULT_NOTE_REASON_MASTER_UUID.toString())));
    }

    @Test
    @Transactional
    void getNoteReasonMaster() throws Exception {
        // Initialize the database
        noteReasonMasterRepository.saveAndFlush(noteReasonMaster);

        // Get the noteReasonMaster
        restNoteReasonMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, noteReasonMaster.getNoteReasonId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.noteReasonId").value(noteReasonMaster.getNoteReasonId().intValue()))
            .andExpect(jsonPath("$.noteReasonName").value(DEFAULT_NOTE_REASON_NAME))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.noteReasonMasterUuid").value(DEFAULT_NOTE_REASON_MASTER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingNoteReasonMaster() throws Exception {
        // Get the noteReasonMaster
        restNoteReasonMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingNoteReasonMaster() throws Exception {
        // Initialize the database
        noteReasonMasterRepository.saveAndFlush(noteReasonMaster);

        int databaseSizeBeforeUpdate = noteReasonMasterRepository.findAll().size();

        // Update the noteReasonMaster
        NoteReasonMaster updatedNoteReasonMaster = noteReasonMasterRepository.findById(noteReasonMaster.getNoteReasonId()).get();
        // Disconnect from session so that the updates on updatedNoteReasonMaster are not directly saved in db
        em.detach(updatedNoteReasonMaster);
        updatedNoteReasonMaster
            .noteReasonName(UPDATED_NOTE_REASON_NAME)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .noteReasonMasterUuid(UPDATED_NOTE_REASON_MASTER_UUID);
        NoteReasonMasterDTO noteReasonMasterDTO = noteReasonMasterMapper.toDto(updatedNoteReasonMaster);

        restNoteReasonMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, noteReasonMasterDTO.getNoteReasonId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteReasonMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the NoteReasonMaster in the database
        List<NoteReasonMaster> noteReasonMasterList = noteReasonMasterRepository.findAll();
        assertThat(noteReasonMasterList).hasSize(databaseSizeBeforeUpdate);
        NoteReasonMaster testNoteReasonMaster = noteReasonMasterList.get(noteReasonMasterList.size() - 1);
        assertThat(testNoteReasonMaster.getNoteReasonName()).isEqualTo(UPDATED_NOTE_REASON_NAME);
        assertThat(testNoteReasonMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testNoteReasonMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testNoteReasonMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNoteReasonMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testNoteReasonMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testNoteReasonMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testNoteReasonMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testNoteReasonMaster.getNoteReasonMasterUuid()).isEqualTo(UPDATED_NOTE_REASON_MASTER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingNoteReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = noteReasonMasterRepository.findAll().size();
        noteReasonMaster.setNoteReasonId(count.incrementAndGet());

        // Create the NoteReasonMaster
        NoteReasonMasterDTO noteReasonMasterDTO = noteReasonMasterMapper.toDto(noteReasonMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoteReasonMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, noteReasonMasterDTO.getNoteReasonId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteReasonMaster in the database
        List<NoteReasonMaster> noteReasonMasterList = noteReasonMasterRepository.findAll();
        assertThat(noteReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNoteReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = noteReasonMasterRepository.findAll().size();
        noteReasonMaster.setNoteReasonId(count.incrementAndGet());

        // Create the NoteReasonMaster
        NoteReasonMasterDTO noteReasonMasterDTO = noteReasonMasterMapper.toDto(noteReasonMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoteReasonMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteReasonMaster in the database
        List<NoteReasonMaster> noteReasonMasterList = noteReasonMasterRepository.findAll();
        assertThat(noteReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNoteReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = noteReasonMasterRepository.findAll().size();
        noteReasonMaster.setNoteReasonId(count.incrementAndGet());

        // Create the NoteReasonMaster
        NoteReasonMasterDTO noteReasonMasterDTO = noteReasonMasterMapper.toDto(noteReasonMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoteReasonMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteReasonMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NoteReasonMaster in the database
        List<NoteReasonMaster> noteReasonMasterList = noteReasonMasterRepository.findAll();
        assertThat(noteReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNoteReasonMasterWithPatch() throws Exception {
        // Initialize the database
        noteReasonMasterRepository.saveAndFlush(noteReasonMaster);

        int databaseSizeBeforeUpdate = noteReasonMasterRepository.findAll().size();

        // Update the noteReasonMaster using partial update
        NoteReasonMaster partialUpdatedNoteReasonMaster = new NoteReasonMaster();
        partialUpdatedNoteReasonMaster.setNoteReasonId(noteReasonMaster.getNoteReasonId());

        partialUpdatedNoteReasonMaster
            .noteReasonName(UPDATED_NOTE_REASON_NAME)
            .createdById(UPDATED_CREATED_BY_ID)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .noteReasonMasterUuid(UPDATED_NOTE_REASON_MASTER_UUID);

        restNoteReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNoteReasonMaster.getNoteReasonId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNoteReasonMaster))
            )
            .andExpect(status().isOk());

        // Validate the NoteReasonMaster in the database
        List<NoteReasonMaster> noteReasonMasterList = noteReasonMasterRepository.findAll();
        assertThat(noteReasonMasterList).hasSize(databaseSizeBeforeUpdate);
        NoteReasonMaster testNoteReasonMaster = noteReasonMasterList.get(noteReasonMasterList.size() - 1);
        assertThat(testNoteReasonMaster.getNoteReasonName()).isEqualTo(UPDATED_NOTE_REASON_NAME);
        assertThat(testNoteReasonMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testNoteReasonMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testNoteReasonMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testNoteReasonMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testNoteReasonMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testNoteReasonMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testNoteReasonMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testNoteReasonMaster.getNoteReasonMasterUuid()).isEqualTo(UPDATED_NOTE_REASON_MASTER_UUID);
    }

    @Test
    @Transactional
    void fullUpdateNoteReasonMasterWithPatch() throws Exception {
        // Initialize the database
        noteReasonMasterRepository.saveAndFlush(noteReasonMaster);

        int databaseSizeBeforeUpdate = noteReasonMasterRepository.findAll().size();

        // Update the noteReasonMaster using partial update
        NoteReasonMaster partialUpdatedNoteReasonMaster = new NoteReasonMaster();
        partialUpdatedNoteReasonMaster.setNoteReasonId(noteReasonMaster.getNoteReasonId());

        partialUpdatedNoteReasonMaster
            .noteReasonName(UPDATED_NOTE_REASON_NAME)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .noteReasonMasterUuid(UPDATED_NOTE_REASON_MASTER_UUID);

        restNoteReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNoteReasonMaster.getNoteReasonId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNoteReasonMaster))
            )
            .andExpect(status().isOk());

        // Validate the NoteReasonMaster in the database
        List<NoteReasonMaster> noteReasonMasterList = noteReasonMasterRepository.findAll();
        assertThat(noteReasonMasterList).hasSize(databaseSizeBeforeUpdate);
        NoteReasonMaster testNoteReasonMaster = noteReasonMasterList.get(noteReasonMasterList.size() - 1);
        assertThat(testNoteReasonMaster.getNoteReasonName()).isEqualTo(UPDATED_NOTE_REASON_NAME);
        assertThat(testNoteReasonMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testNoteReasonMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testNoteReasonMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNoteReasonMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testNoteReasonMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testNoteReasonMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testNoteReasonMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testNoteReasonMaster.getNoteReasonMasterUuid()).isEqualTo(UPDATED_NOTE_REASON_MASTER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingNoteReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = noteReasonMasterRepository.findAll().size();
        noteReasonMaster.setNoteReasonId(count.incrementAndGet());

        // Create the NoteReasonMaster
        NoteReasonMasterDTO noteReasonMasterDTO = noteReasonMasterMapper.toDto(noteReasonMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoteReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, noteReasonMasterDTO.getNoteReasonId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noteReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteReasonMaster in the database
        List<NoteReasonMaster> noteReasonMasterList = noteReasonMasterRepository.findAll();
        assertThat(noteReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNoteReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = noteReasonMasterRepository.findAll().size();
        noteReasonMaster.setNoteReasonId(count.incrementAndGet());

        // Create the NoteReasonMaster
        NoteReasonMasterDTO noteReasonMasterDTO = noteReasonMasterMapper.toDto(noteReasonMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoteReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noteReasonMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteReasonMaster in the database
        List<NoteReasonMaster> noteReasonMasterList = noteReasonMasterRepository.findAll();
        assertThat(noteReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNoteReasonMaster() throws Exception {
        int databaseSizeBeforeUpdate = noteReasonMasterRepository.findAll().size();
        noteReasonMaster.setNoteReasonId(count.incrementAndGet());

        // Create the NoteReasonMaster
        NoteReasonMasterDTO noteReasonMasterDTO = noteReasonMasterMapper.toDto(noteReasonMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoteReasonMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noteReasonMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NoteReasonMaster in the database
        List<NoteReasonMaster> noteReasonMasterList = noteReasonMasterRepository.findAll();
        assertThat(noteReasonMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNoteReasonMaster() throws Exception {
        // Initialize the database
        noteReasonMasterRepository.saveAndFlush(noteReasonMaster);

        int databaseSizeBeforeDelete = noteReasonMasterRepository.findAll().size();

        // Delete the noteReasonMaster
        restNoteReasonMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, noteReasonMaster.getNoteReasonId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NoteReasonMaster> noteReasonMasterList = noteReasonMasterRepository.findAll();
        assertThat(noteReasonMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
