package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.NoteReasonMaster;
import com.sunknowledge.dme.rcm.repository.NoteReasonMasterRepository;
import com.sunknowledge.dme.rcm.service.NoteReasonMasterService;
import com.sunknowledge.dme.rcm.service.dto.NoteReasonMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.NoteReasonMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NoteReasonMaster}.
 */
@Service
@Transactional
public class NoteReasonMasterServiceImpl implements NoteReasonMasterService {

    private final Logger log = LoggerFactory.getLogger(NoteReasonMasterServiceImpl.class);

    private final NoteReasonMasterRepository noteReasonMasterRepository;

    private final NoteReasonMasterMapper noteReasonMasterMapper;

    public NoteReasonMasterServiceImpl(
        NoteReasonMasterRepository noteReasonMasterRepository,
        NoteReasonMasterMapper noteReasonMasterMapper
    ) {
        this.noteReasonMasterRepository = noteReasonMasterRepository;
        this.noteReasonMasterMapper = noteReasonMasterMapper;
    }

    @Override
    public NoteReasonMasterDTO save(NoteReasonMasterDTO noteReasonMasterDTO) {
        log.debug("Request to save NoteReasonMaster : {}", noteReasonMasterDTO);
        NoteReasonMaster noteReasonMaster = noteReasonMasterMapper.toEntity(noteReasonMasterDTO);
        noteReasonMaster = noteReasonMasterRepository.save(noteReasonMaster);
        return noteReasonMasterMapper.toDto(noteReasonMaster);
    }

    @Override
    public NoteReasonMasterDTO update(NoteReasonMasterDTO noteReasonMasterDTO) {
        log.debug("Request to update NoteReasonMaster : {}", noteReasonMasterDTO);
        NoteReasonMaster noteReasonMaster = noteReasonMasterMapper.toEntity(noteReasonMasterDTO);
        noteReasonMaster = noteReasonMasterRepository.save(noteReasonMaster);
        return noteReasonMasterMapper.toDto(noteReasonMaster);
    }

    @Override
    public Optional<NoteReasonMasterDTO> partialUpdate(NoteReasonMasterDTO noteReasonMasterDTO) {
        log.debug("Request to partially update NoteReasonMaster : {}", noteReasonMasterDTO);

        return noteReasonMasterRepository
            .findById(noteReasonMasterDTO.getNoteReasonId())
            .map(existingNoteReasonMaster -> {
                noteReasonMasterMapper.partialUpdate(existingNoteReasonMaster, noteReasonMasterDTO);

                return existingNoteReasonMaster;
            })
            .map(noteReasonMasterRepository::save)
            .map(noteReasonMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NoteReasonMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NoteReasonMasters");
        return noteReasonMasterRepository.findAll(pageable).map(noteReasonMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NoteReasonMasterDTO> findOne(Long id) {
        log.debug("Request to get NoteReasonMaster : {}", id);
        return noteReasonMasterRepository.findById(id).map(noteReasonMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NoteReasonMaster : {}", id);
        noteReasonMasterRepository.deleteById(id);
    }
}
