package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.NoteReasonMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.NoteReasonMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.NoteReasonMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.NoteReasonMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.NoteReasonMasterAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NoteReasonMasterAuditLog}.
 */
@Service
@Transactional
public class NoteReasonMasterAuditLogServiceImpl implements NoteReasonMasterAuditLogService {

    private final Logger log = LoggerFactory.getLogger(NoteReasonMasterAuditLogServiceImpl.class);

    private final NoteReasonMasterAuditLogRepository noteReasonMasterAuditLogRepository;

    private final NoteReasonMasterAuditLogMapper noteReasonMasterAuditLogMapper;

    public NoteReasonMasterAuditLogServiceImpl(
        NoteReasonMasterAuditLogRepository noteReasonMasterAuditLogRepository,
        NoteReasonMasterAuditLogMapper noteReasonMasterAuditLogMapper
    ) {
        this.noteReasonMasterAuditLogRepository = noteReasonMasterAuditLogRepository;
        this.noteReasonMasterAuditLogMapper = noteReasonMasterAuditLogMapper;
    }

    @Override
    public NoteReasonMasterAuditLogDTO save(NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO) {
        log.debug("Request to save NoteReasonMasterAuditLog : {}", noteReasonMasterAuditLogDTO);
        NoteReasonMasterAuditLog noteReasonMasterAuditLog = noteReasonMasterAuditLogMapper.toEntity(noteReasonMasterAuditLogDTO);
        noteReasonMasterAuditLog = noteReasonMasterAuditLogRepository.save(noteReasonMasterAuditLog);
        return noteReasonMasterAuditLogMapper.toDto(noteReasonMasterAuditLog);
    }

    @Override
    public NoteReasonMasterAuditLogDTO update(NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO) {
        log.debug("Request to save NoteReasonMasterAuditLog : {}", noteReasonMasterAuditLogDTO);
        NoteReasonMasterAuditLog noteReasonMasterAuditLog = noteReasonMasterAuditLogMapper.toEntity(noteReasonMasterAuditLogDTO);
        noteReasonMasterAuditLog = noteReasonMasterAuditLogRepository.save(noteReasonMasterAuditLog);
        return noteReasonMasterAuditLogMapper.toDto(noteReasonMasterAuditLog);
    }

    @Override
    public Optional<NoteReasonMasterAuditLogDTO> partialUpdate(NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO) {
        log.debug("Request to partially update NoteReasonMasterAuditLog : {}", noteReasonMasterAuditLogDTO);

        return noteReasonMasterAuditLogRepository
            .findById(noteReasonMasterAuditLogDTO.getId())
            .map(existingNoteReasonMasterAuditLog -> {
                noteReasonMasterAuditLogMapper.partialUpdate(existingNoteReasonMasterAuditLog, noteReasonMasterAuditLogDTO);

                return existingNoteReasonMasterAuditLog;
            })
            .map(noteReasonMasterAuditLogRepository::save)
            .map(noteReasonMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NoteReasonMasterAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NoteReasonMasterAuditLogs");
        return noteReasonMasterAuditLogRepository.findAll(pageable).map(noteReasonMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NoteReasonMasterAuditLogDTO> findOne(Long id) {
        log.debug("Request to get NoteReasonMasterAuditLog : {}", id);
        return noteReasonMasterAuditLogRepository.findById(id).map(noteReasonMasterAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NoteReasonMasterAuditLog : {}", id);
        noteReasonMasterAuditLogRepository.deleteById(id);
    }
}
