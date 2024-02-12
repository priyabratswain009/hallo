package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.NoteTypeMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.NoteTypeMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.NoteTypeMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.NoteTypeMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.NoteTypeMasterAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NoteTypeMasterAuditLog}.
 */
@Service
@Transactional
public class NoteTypeMasterAuditLogServiceImpl implements NoteTypeMasterAuditLogService {

    private final Logger log = LoggerFactory.getLogger(NoteTypeMasterAuditLogServiceImpl.class);

    private final NoteTypeMasterAuditLogRepository noteTypeMasterAuditLogRepository;

    private final NoteTypeMasterAuditLogMapper noteTypeMasterAuditLogMapper;

    public NoteTypeMasterAuditLogServiceImpl(
        NoteTypeMasterAuditLogRepository noteTypeMasterAuditLogRepository,
        NoteTypeMasterAuditLogMapper noteTypeMasterAuditLogMapper
    ) {
        this.noteTypeMasterAuditLogRepository = noteTypeMasterAuditLogRepository;
        this.noteTypeMasterAuditLogMapper = noteTypeMasterAuditLogMapper;
    }

    @Override
    public NoteTypeMasterAuditLogDTO save(NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO) {
        log.debug("Request to save NoteTypeMasterAuditLog : {}", noteTypeMasterAuditLogDTO);
        NoteTypeMasterAuditLog noteTypeMasterAuditLog = noteTypeMasterAuditLogMapper.toEntity(noteTypeMasterAuditLogDTO);
        noteTypeMasterAuditLog = noteTypeMasterAuditLogRepository.save(noteTypeMasterAuditLog);
        return noteTypeMasterAuditLogMapper.toDto(noteTypeMasterAuditLog);
    }

    @Override
    public NoteTypeMasterAuditLogDTO update(NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO) {
        log.debug("Request to update NoteTypeMasterAuditLog : {}", noteTypeMasterAuditLogDTO);
        NoteTypeMasterAuditLog noteTypeMasterAuditLog = noteTypeMasterAuditLogMapper.toEntity(noteTypeMasterAuditLogDTO);
        noteTypeMasterAuditLog = noteTypeMasterAuditLogRepository.save(noteTypeMasterAuditLog);
        return noteTypeMasterAuditLogMapper.toDto(noteTypeMasterAuditLog);
    }

    @Override
    public Optional<NoteTypeMasterAuditLogDTO> partialUpdate(NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO) {
        log.debug("Request to partially update NoteTypeMasterAuditLog : {}", noteTypeMasterAuditLogDTO);

        return noteTypeMasterAuditLogRepository
            .findById(noteTypeMasterAuditLogDTO.getId())
            .map(existingNoteTypeMasterAuditLog -> {
                noteTypeMasterAuditLogMapper.partialUpdate(existingNoteTypeMasterAuditLog, noteTypeMasterAuditLogDTO);

                return existingNoteTypeMasterAuditLog;
            })
            .map(noteTypeMasterAuditLogRepository::save)
            .map(noteTypeMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NoteTypeMasterAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NoteTypeMasterAuditLogs");
        return noteTypeMasterAuditLogRepository.findAll(pageable).map(noteTypeMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NoteTypeMasterAuditLogDTO> findOne(Long id) {
        log.debug("Request to get NoteTypeMasterAuditLog : {}", id);
        return noteTypeMasterAuditLogRepository.findById(id).map(noteTypeMasterAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NoteTypeMasterAuditLog : {}", id);
        noteTypeMasterAuditLogRepository.deleteById(id);
    }
}
