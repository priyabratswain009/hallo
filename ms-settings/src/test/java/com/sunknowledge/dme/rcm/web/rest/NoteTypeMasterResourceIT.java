package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.NoteTypeMaster;
import com.sunknowledge.dme.rcm.repository.NoteTypeMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.NoteTypeMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.NoteTypeMasterMapper;
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
 * Integration tests for the {@link NoteTypeMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NoteTypeMasterResourceIT {

    private static final String DEFAULT_NOTE_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NOTE_TYPE_NAME = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_NOTE_TYPE_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_NOTE_TYPE_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/note-type-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{noteTypeId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NoteTypeMasterRepository noteTypeMasterRepository;

    @Autowired
    private NoteTypeMasterMapper noteTypeMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNoteTypeMasterMockMvc;

    private NoteTypeMaster noteTypeMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoteTypeMaster createEntity(EntityManager em) {
        NoteTypeMaster noteTypeMaster = new NoteTypeMaster()
            .noteTypeName(DEFAULT_NOTE_TYPE_NAME)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .status(DEFAULT_STATUS)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .noteTypeMasterUuid(DEFAULT_NOTE_TYPE_MASTER_UUID);
        return noteTypeMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoteTypeMaster createUpdatedEntity(EntityManager em) {
        NoteTypeMaster noteTypeMaster = new NoteTypeMaster()
            .noteTypeName(UPDATED_NOTE_TYPE_NAME)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .noteTypeMasterUuid(UPDATED_NOTE_TYPE_MASTER_UUID);
        return noteTypeMaster;
    }

    @BeforeEach
    public void initTest() {
        noteTypeMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createNoteTypeMaster() throws Exception {
        int databaseSizeBeforeCreate = noteTypeMasterRepository.findAll().size();
        // Create the NoteTypeMaster
        NoteTypeMasterDTO noteTypeMasterDTO = noteTypeMasterMapper.toDto(noteTypeMaster);
        restNoteTypeMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteTypeMasterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the NoteTypeMaster in the database
        List<NoteTypeMaster> noteTypeMasterList = noteTypeMasterRepository.findAll();
        assertThat(noteTypeMasterList).hasSize(databaseSizeBeforeCreate + 1);
        NoteTypeMaster testNoteTypeMaster = noteTypeMasterList.get(noteTypeMasterList.size() - 1);
        assertThat(testNoteTypeMaster.getNoteTypeName()).isEqualTo(DEFAULT_NOTE_TYPE_NAME);
        assertThat(testNoteTypeMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testNoteTypeMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testNoteTypeMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testNoteTypeMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testNoteTypeMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testNoteTypeMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testNoteTypeMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testNoteTypeMaster.getNoteTypeMasterUuid()).isEqualTo(DEFAULT_NOTE_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void createNoteTypeMasterWithExistingId() throws Exception {
        // Create the NoteTypeMaster with an existing ID
        noteTypeMaster.setNoteTypeId(1L);
        NoteTypeMasterDTO noteTypeMasterDTO = noteTypeMasterMapper.toDto(noteTypeMaster);

        int databaseSizeBeforeCreate = noteTypeMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNoteTypeMasterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteTypeMaster in the database
        List<NoteTypeMaster> noteTypeMasterList = noteTypeMasterRepository.findAll();
        assertThat(noteTypeMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllNoteTypeMasters() throws Exception {
        // Initialize the database
        noteTypeMasterRepository.saveAndFlush(noteTypeMaster);

        // Get all the noteTypeMasterList
        restNoteTypeMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=noteTypeId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].noteTypeId").value(hasItem(noteTypeMaster.getNoteTypeId().intValue())))
            .andExpect(jsonPath("$.[*].noteTypeName").value(hasItem(DEFAULT_NOTE_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].noteTypeMasterUuid").value(hasItem(DEFAULT_NOTE_TYPE_MASTER_UUID.toString())));
    }

    @Test
    @Transactional
    void getNoteTypeMaster() throws Exception {
        // Initialize the database
        noteTypeMasterRepository.saveAndFlush(noteTypeMaster);

        // Get the noteTypeMaster
        restNoteTypeMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, noteTypeMaster.getNoteTypeId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.noteTypeId").value(noteTypeMaster.getNoteTypeId().intValue()))
            .andExpect(jsonPath("$.noteTypeName").value(DEFAULT_NOTE_TYPE_NAME))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.noteTypeMasterUuid").value(DEFAULT_NOTE_TYPE_MASTER_UUID.toString()));
    }

    @Test
    @Transactional
    void getNonExistingNoteTypeMaster() throws Exception {
        // Get the noteTypeMaster
        restNoteTypeMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewNoteTypeMaster() throws Exception {
        // Initialize the database
        noteTypeMasterRepository.saveAndFlush(noteTypeMaster);

        int databaseSizeBeforeUpdate = noteTypeMasterRepository.findAll().size();

        // Update the noteTypeMaster
        NoteTypeMaster updatedNoteTypeMaster = noteTypeMasterRepository.findById(noteTypeMaster.getNoteTypeId()).get();
        // Disconnect from session so that the updates on updatedNoteTypeMaster are not directly saved in db
        em.detach(updatedNoteTypeMaster);
        updatedNoteTypeMaster
            .noteTypeName(UPDATED_NOTE_TYPE_NAME)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .noteTypeMasterUuid(UPDATED_NOTE_TYPE_MASTER_UUID);
        NoteTypeMasterDTO noteTypeMasterDTO = noteTypeMasterMapper.toDto(updatedNoteTypeMaster);

        restNoteTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, noteTypeMasterDTO.getNoteTypeId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteTypeMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the NoteTypeMaster in the database
        List<NoteTypeMaster> noteTypeMasterList = noteTypeMasterRepository.findAll();
        assertThat(noteTypeMasterList).hasSize(databaseSizeBeforeUpdate);
        NoteTypeMaster testNoteTypeMaster = noteTypeMasterList.get(noteTypeMasterList.size() - 1);
        assertThat(testNoteTypeMaster.getNoteTypeName()).isEqualTo(UPDATED_NOTE_TYPE_NAME);
        assertThat(testNoteTypeMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testNoteTypeMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testNoteTypeMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNoteTypeMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testNoteTypeMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testNoteTypeMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testNoteTypeMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testNoteTypeMaster.getNoteTypeMasterUuid()).isEqualTo(UPDATED_NOTE_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void putNonExistingNoteTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = noteTypeMasterRepository.findAll().size();
        noteTypeMaster.setNoteTypeId(count.incrementAndGet());

        // Create the NoteTypeMaster
        NoteTypeMasterDTO noteTypeMasterDTO = noteTypeMasterMapper.toDto(noteTypeMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoteTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, noteTypeMasterDTO.getNoteTypeId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteTypeMaster in the database
        List<NoteTypeMaster> noteTypeMasterList = noteTypeMasterRepository.findAll();
        assertThat(noteTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNoteTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = noteTypeMasterRepository.findAll().size();
        noteTypeMaster.setNoteTypeId(count.incrementAndGet());

        // Create the NoteTypeMaster
        NoteTypeMasterDTO noteTypeMasterDTO = noteTypeMasterMapper.toDto(noteTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoteTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteTypeMaster in the database
        List<NoteTypeMaster> noteTypeMasterList = noteTypeMasterRepository.findAll();
        assertThat(noteTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNoteTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = noteTypeMasterRepository.findAll().size();
        noteTypeMaster.setNoteTypeId(count.incrementAndGet());

        // Create the NoteTypeMaster
        NoteTypeMasterDTO noteTypeMasterDTO = noteTypeMasterMapper.toDto(noteTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoteTypeMasterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noteTypeMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NoteTypeMaster in the database
        List<NoteTypeMaster> noteTypeMasterList = noteTypeMasterRepository.findAll();
        assertThat(noteTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNoteTypeMasterWithPatch() throws Exception {
        // Initialize the database
        noteTypeMasterRepository.saveAndFlush(noteTypeMaster);

        int databaseSizeBeforeUpdate = noteTypeMasterRepository.findAll().size();

        // Update the noteTypeMaster using partial update
        NoteTypeMaster partialUpdatedNoteTypeMaster = new NoteTypeMaster();
        partialUpdatedNoteTypeMaster.setNoteTypeId(noteTypeMaster.getNoteTypeId());

        partialUpdatedNoteTypeMaster
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedByName(UPDATED_UPDATED_BY_NAME);

        restNoteTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNoteTypeMaster.getNoteTypeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNoteTypeMaster))
            )
            .andExpect(status().isOk());

        // Validate the NoteTypeMaster in the database
        List<NoteTypeMaster> noteTypeMasterList = noteTypeMasterRepository.findAll();
        assertThat(noteTypeMasterList).hasSize(databaseSizeBeforeUpdate);
        NoteTypeMaster testNoteTypeMaster = noteTypeMasterList.get(noteTypeMasterList.size() - 1);
        assertThat(testNoteTypeMaster.getNoteTypeName()).isEqualTo(DEFAULT_NOTE_TYPE_NAME);
        assertThat(testNoteTypeMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testNoteTypeMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testNoteTypeMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNoteTypeMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testNoteTypeMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testNoteTypeMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testNoteTypeMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testNoteTypeMaster.getNoteTypeMasterUuid()).isEqualTo(DEFAULT_NOTE_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void fullUpdateNoteTypeMasterWithPatch() throws Exception {
        // Initialize the database
        noteTypeMasterRepository.saveAndFlush(noteTypeMaster);

        int databaseSizeBeforeUpdate = noteTypeMasterRepository.findAll().size();

        // Update the noteTypeMaster using partial update
        NoteTypeMaster partialUpdatedNoteTypeMaster = new NoteTypeMaster();
        partialUpdatedNoteTypeMaster.setNoteTypeId(noteTypeMaster.getNoteTypeId());

        partialUpdatedNoteTypeMaster
            .noteTypeName(UPDATED_NOTE_TYPE_NAME)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .noteTypeMasterUuid(UPDATED_NOTE_TYPE_MASTER_UUID);

        restNoteTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNoteTypeMaster.getNoteTypeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNoteTypeMaster))
            )
            .andExpect(status().isOk());

        // Validate the NoteTypeMaster in the database
        List<NoteTypeMaster> noteTypeMasterList = noteTypeMasterRepository.findAll();
        assertThat(noteTypeMasterList).hasSize(databaseSizeBeforeUpdate);
        NoteTypeMaster testNoteTypeMaster = noteTypeMasterList.get(noteTypeMasterList.size() - 1);
        assertThat(testNoteTypeMaster.getNoteTypeName()).isEqualTo(UPDATED_NOTE_TYPE_NAME);
        assertThat(testNoteTypeMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testNoteTypeMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testNoteTypeMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNoteTypeMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testNoteTypeMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testNoteTypeMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testNoteTypeMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testNoteTypeMaster.getNoteTypeMasterUuid()).isEqualTo(UPDATED_NOTE_TYPE_MASTER_UUID);
    }

    @Test
    @Transactional
    void patchNonExistingNoteTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = noteTypeMasterRepository.findAll().size();
        noteTypeMaster.setNoteTypeId(count.incrementAndGet());

        // Create the NoteTypeMaster
        NoteTypeMasterDTO noteTypeMasterDTO = noteTypeMasterMapper.toDto(noteTypeMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoteTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, noteTypeMasterDTO.getNoteTypeId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noteTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteTypeMaster in the database
        List<NoteTypeMaster> noteTypeMasterList = noteTypeMasterRepository.findAll();
        assertThat(noteTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNoteTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = noteTypeMasterRepository.findAll().size();
        noteTypeMaster.setNoteTypeId(count.incrementAndGet());

        // Create the NoteTypeMaster
        NoteTypeMasterDTO noteTypeMasterDTO = noteTypeMasterMapper.toDto(noteTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoteTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noteTypeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoteTypeMaster in the database
        List<NoteTypeMaster> noteTypeMasterList = noteTypeMasterRepository.findAll();
        assertThat(noteTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNoteTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = noteTypeMasterRepository.findAll().size();
        noteTypeMaster.setNoteTypeId(count.incrementAndGet());

        // Create the NoteTypeMaster
        NoteTypeMasterDTO noteTypeMasterDTO = noteTypeMasterMapper.toDto(noteTypeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoteTypeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noteTypeMasterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NoteTypeMaster in the database
        List<NoteTypeMaster> noteTypeMasterList = noteTypeMasterRepository.findAll();
        assertThat(noteTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNoteTypeMaster() throws Exception {
        // Initialize the database
        noteTypeMasterRepository.saveAndFlush(noteTypeMaster);

        int databaseSizeBeforeDelete = noteTypeMasterRepository.findAll().size();

        // Delete the noteTypeMaster
        restNoteTypeMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, noteTypeMaster.getNoteTypeId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NoteTypeMaster> noteTypeMasterList = noteTypeMasterRepository.findAll();
        assertThat(noteTypeMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
