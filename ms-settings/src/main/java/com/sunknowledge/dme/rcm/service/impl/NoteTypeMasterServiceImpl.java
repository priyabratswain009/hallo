package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.NoteTypeMaster;
import com.sunknowledge.dme.rcm.repository.NoteTypeMasterRepository;
import com.sunknowledge.dme.rcm.service.NoteTypeMasterService;
import com.sunknowledge.dme.rcm.service.dto.NoteTypeMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.NoteTypeMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NoteTypeMaster}.
 */
@Service
@Transactional
public class NoteTypeMasterServiceImpl implements NoteTypeMasterService {

    private final Logger log = LoggerFactory.getLogger(NoteTypeMasterServiceImpl.class);

    private final NoteTypeMasterRepository noteTypeMasterRepository;

    private final NoteTypeMasterMapper noteTypeMasterMapper;

    public NoteTypeMasterServiceImpl(NoteTypeMasterRepository noteTypeMasterRepository, NoteTypeMasterMapper noteTypeMasterMapper) {
        this.noteTypeMasterRepository = noteTypeMasterRepository;
        this.noteTypeMasterMapper = noteTypeMasterMapper;
    }

    @Override
    public NoteTypeMasterDTO save(NoteTypeMasterDTO noteTypeMasterDTO) {
        log.debug("Request to save NoteTypeMaster : {}", noteTypeMasterDTO);
        NoteTypeMaster noteTypeMaster = noteTypeMasterMapper.toEntity(noteTypeMasterDTO);
        noteTypeMaster = noteTypeMasterRepository.save(noteTypeMaster);
        return noteTypeMasterMapper.toDto(noteTypeMaster);
    }

    @Override
    public NoteTypeMasterDTO update(NoteTypeMasterDTO noteTypeMasterDTO) {
        log.debug("Request to update NoteTypeMaster : {}", noteTypeMasterDTO);
        NoteTypeMaster noteTypeMaster = noteTypeMasterMapper.toEntity(noteTypeMasterDTO);
        noteTypeMaster = noteTypeMasterRepository.save(noteTypeMaster);
        return noteTypeMasterMapper.toDto(noteTypeMaster);
    }

    @Override
    public Optional<NoteTypeMasterDTO> partialUpdate(NoteTypeMasterDTO noteTypeMasterDTO) {
        log.debug("Request to partially update NoteTypeMaster : {}", noteTypeMasterDTO);

        return noteTypeMasterRepository
            .findById(noteTypeMasterDTO.getNoteTypeId())
            .map(existingNoteTypeMaster -> {
                noteTypeMasterMapper.partialUpdate(existingNoteTypeMaster, noteTypeMasterDTO);

                return existingNoteTypeMaster;
            })
            .map(noteTypeMasterRepository::save)
            .map(noteTypeMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NoteTypeMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NoteTypeMasters");
        return noteTypeMasterRepository.findAll(pageable).map(noteTypeMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NoteTypeMasterDTO> findOne(Long id) {
        log.debug("Request to get NoteTypeMaster : {}", id);
        return noteTypeMasterRepository.findById(id).map(noteTypeMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NoteTypeMaster : {}", id);
        noteTypeMasterRepository.deleteById(id);
    }
}
